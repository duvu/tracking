package com.vd5.tracking.dcs;

import com.vd5.tracking.entity.DCSRuntimeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author beou on 8/24/17 06:24
 * @version 1.0
 */
@Slf4j
@Component
@Data
@PropertySource("classpath:dcs.properties")
@ConfigurationProperties(prefix="dcs")
public class ServerManager {

    private final Map<String, TrackerServer> serverMap = new ConcurrentHashMap<>();
    private final List<String> serverNameList = new ArrayList<>();
    private final List<Entry> entries = new ArrayList<>();

    public ServerManager() {
        log.info("init bean server-manager");
    }

    public ServerManager init() {
        for (Entry entry : entries) {
            if (!entry.isEnabled() || StringUtils.isEmpty(entry.getClassName())) {
                continue;
            }
            String name = entry.getName();
            name = StringUtils.uncapitalize(name);
            serverNameList.add(name);

            String className = entry.getClassName();
            try {
                Class protocolClass = Class.forName(className);
                if (AbstractProtocol.class.isAssignableFrom(protocolClass)) {

                    AbstractProtocol protocol = (AbstractProtocol) protocolClass.newInstance();
                    protocol.setName(name);

                    //init server-protocol
                    protocol.setUdpPort(entry.getUdpPort());
                    protocol.setTcpPort(entry.getTcpPort());
                    protocol.setAddress(entry.getAddress());
                    protocol.setEnabled(entry.isEnabled());
                    protocol.initTrackerServer(serverMap);
                }
            } catch (ClassNotFoundException cnfe) {
                throw new  DCSRuntimeException(cnfe);
            } catch (IllegalAccessException | InstantiationException iae) {
                throw new DCSRuntimeException(iae);
            }
        }
        return this;
    }

    public void start() {
        for (String serverName : serverNameList) {
            this.start(serverName);
        }
    }

    public void stop() {
        for (String serverName : serverNameList) {
            this.stop(serverName);
        }
    }

    //--
    public void start(String name) {
        log.debug("...starting server: " + name);
        TrackerServer server = this.serverMap.get(name);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception while start server: " + name);
        }
    }

    public void stop(String name) {
        log.debug("...stopping server: " + name);
        TrackerServer server = this.serverMap.get(name);
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception while stopping server: " + name);
        }
    }
}
