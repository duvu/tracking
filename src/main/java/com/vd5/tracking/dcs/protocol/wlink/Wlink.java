package com.vd5.tracking.dcs.protocol.wlink;


import com.vd5.tracking.dcs.AbstractProtocol;
import com.vd5.tracking.dcs.TrackerServer;

import java.util.Map;

/**
 * @author beou on 8/25/17 18:45
 * @version 1.0
 */
public class Wlink extends AbstractProtocol {

    public Wlink() {
        super("Wlink");
    }

    @Override
    public void initTrackerServer(Map<String, TrackerServer> serverMap) {

    }
}
