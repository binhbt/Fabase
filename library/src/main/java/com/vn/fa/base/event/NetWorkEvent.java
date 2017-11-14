package com.vn.fa.base.event;

import com.vn.fa.base.receiver.NetworkStatusReceiver;

/**
 * Created by leobui on 11/10/2017.
 */

public class NetWorkEvent {
    private Type type;

    public NetWorkEvent(Type type, NetworkStatusReceiver.NetWorkConnectionStatus status) {
        this.type = type;
        this.status = status;
    }

    private NetworkStatusReceiver.NetWorkConnectionStatus status;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public NetworkStatusReceiver.NetWorkConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(NetworkStatusReceiver.NetWorkConnectionStatus status) {
        this.status = status;
    }

    public enum Type{
        NETWORK_STATUS_CHANGED
    }
}
