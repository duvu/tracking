package com.vd5.tracking.dcs;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author beou on 8/25/17 15:46
 * @version 1.0
 */
@Slf4j
public class BaseAsciiProtocolDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String m = (String) msg;

        log.info("String: " + m);
    }
}
