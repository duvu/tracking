package com.vd5.tracking.dcs.protocol.xirgo2050;


import com.vd5.tracking.dcs.AbstractProtocol;
import com.vd5.tracking.dcs.TrackerServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author beou on 8/25/17 14:44
 * @version 1.0
 */
@Slf4j
public class Xirgo2050 extends AbstractProtocol {

    public Xirgo2050() {}

    @Override
    public void initTrackerServer(Map<String, TrackerServer> serverList) {
        int udpPort = this.getUdpPort();
        int tcpPort = this.getTcpPort();
        String address = this.getAddress();
        String name = this.getName();

        if (udpPort > 0) {
            //init udp-server
            Bootstrap bootstrap = new Bootstrap();
            TrackerServer udpServer = new TrackerServer(bootstrap, getName());
            //udpServer.setChannelInitializer(new Xirgo2050ChannelInitializer());
            udpServer.setPort(udpPort);
            udpServer.setAddress(address);
            serverList.put(name, udpServer);
        }

        if (tcpPort > 0) {
            //init tcp-server
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            TrackerServer tcpServer = new TrackerServer(serverBootstrap, getName());
            tcpServer.setChannelInitializer(new Xirgo2050ChannelInitializer());
            tcpServer.setPort(tcpPort);
            tcpServer.setAddress(address);
            serverList.put(name, tcpServer);
        }
    }
}
