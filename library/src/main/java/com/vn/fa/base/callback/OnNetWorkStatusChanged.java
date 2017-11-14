package com.vn.fa.base.callback;

import com.vn.fa.base.receiver.NetworkStatusReceiver;

/**
 * Created by leobui on 11/10/2017.
 */

public interface OnNetWorkStatusChanged {
    public void onStatusChanged(NetworkStatusReceiver.NetWorkConnectionStatus status);
}
