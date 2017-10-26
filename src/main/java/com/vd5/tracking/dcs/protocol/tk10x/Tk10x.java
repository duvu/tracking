package com.vd5.tracking.dcs.protocol.tk10x;


import com.vd5.tracking.dcs.AbstractProtocol;
import com.vd5.tracking.dcs.TrackerServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;

import java.util.Map;

/**
 * @author beou on 8/25/17 18:12
 * @version 1.0
 */
public class Tk10x extends AbstractProtocol {

    public Tk10x() { }

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
            udpServer.setChannelInitializer(new Tk10xChannelInitializer());
            udpServer.setPort(udpPort);
            udpServer.setAddress(address);
            serverList.put(name, udpServer);
        }

        if (tcpPort > 0) {
            //init tcp-server
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            TrackerServer tcpServer = new TrackerServer(serverBootstrap, getName());
            tcpServer.setChannelInitializer(new Tk10xChannelInitializer());
            tcpServer.setPort(tcpPort);
            tcpServer.setAddress(address);
            serverList.put(name, tcpServer);
        }
    }
}
