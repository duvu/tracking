package com.vd5.tracking.dcs;

/**
 * @author beou on 8/27/17 01:44
 * @version 1.0
 */
public class Entry {
    private String name;        //=tk10x
    private boolean enabled;    //=false
    private int tcpPort;        //=31272
    private int udpPort;        //=31272
    private String address;     //=
    private String className;   //=com.vd5.dcs.protocol.tk10x.Tk10x

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
