package com.vd5.tracking.dcs;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;


/**
 * @author beou on 8/24/17 06:24
 * @version 1.0
 */
@Data
@Slf4j
public class TrackerServer {
    private String name;
    private int port;
    private String address;

    private AbstractBootstrap bootstrap;
    private ChannelInitializer channelInitializer;



    private final NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    private Channel channel;

    public TrackerServer(AbstractBootstrap bootstrap, String name) {
        this.bootstrap = bootstrap;
        this.name = name;
        if (isDuplex()) {
            workerGroup = new NioEventLoopGroup();
        }
        bossGroup = new NioEventLoopGroup();
    }

    private boolean isDuplex() {
        return bootstrap instanceof ServerBootstrap;
    }

    public void start() throws Exception {
        InetSocketAddress enpoint;
        enpoint = StringUtils.isNotEmpty(address) ? new InetSocketAddress(address, port) : new InetSocketAddress(port);
        if (isDuplex()) {
            log.info("...starting tcp-server");

            ServerBootstrap tcpServerBootstrap = (ServerBootstrap) bootstrap;
            tcpServerBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(channelInitializer);

            channel = tcpServerBootstrap.bind(enpoint).sync().channel().closeFuture().sync().channel();


        } else if (bootstrap instanceof Bootstrap) {
            log.info("...starting udp-server");
            Bootstrap udpServerBootstrap = (Bootstrap)bootstrap;
            udpServerBootstrap.group(bossGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(channelInitializer);
            channel = udpServerBootstrap.bind(enpoint).sync().channel().closeFuture().sync().channel();
        }
    }

    public void stop() {
        if (isDuplex()) {
            bossGroup.shutdownGracefully();
        }
        workerGroup.shutdownGracefully();

        channel.close();
        channel.parent().close();
    }
}
