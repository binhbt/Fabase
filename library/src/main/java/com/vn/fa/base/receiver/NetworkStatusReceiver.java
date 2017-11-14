package com.vn.fa.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vn.fa.base.event.NetWorkEvent;

import org.greenrobot.eventbus.EventBus;

import static com.vn.fa.base.receiver.NetworkStatusReceiver.NetWorkConnectionStatus.CONNECTION_TYPE_3G;
import static com.vn.fa.base.receiver.NetworkStatusReceiver.NetWorkConnectionStatus.CONNECTION_TYPE_OFF;
import static com.vn.fa.base.receiver.NetworkStatusReceiver.NetWorkConnectionStatus.CONNECTION_TYPE_WIFI;

/**
 * Created by binhbt on 8/2/2016.
 */
public class NetworkStatusReceiver extends BroadcastReceiver {
    public enum NetWorkConnectionStatus{
        CONNECTION_TYPE_OFF,
        CONNECTION_TYPE_WIFI,
        CONNECTION_TYPE_3G
    }


    public static final String NETWORK_CHANGE = "vn.com.vega.networkchange";
    public static final String NETWORK_OFF = "vn.com.vega.networkoff";

    public static NetWorkConnectionStatus networkStatus;

    @Override
    public void onReceive(Context context, Intent intent) {
        networkStatus = getNetworkStatus(context);
        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.Type.NETWORK_STATUS_CHANGED, networkStatus));
    }

    public static boolean isConnected() {
        return networkStatus != CONNECTION_TYPE_OFF;
    }
    public static boolean is3GNetWork (){
        return networkStatus == CONNECTION_TYPE_3G;
    }
    public static NetWorkConnectionStatus getNetworkStatus(Context ctx) {
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo currentNetworkInfo = connec.getActiveNetworkInfo();

        if (currentNetworkInfo == null) {
            networkStatus =  CONNECTION_TYPE_OFF;
            return networkStatus;
        }

        if (currentNetworkInfo.isConnected()) {
            if (currentNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                networkStatus = CONNECTION_TYPE_WIFI;
            } else {
                networkStatus = CONNECTION_TYPE_3G;
            }
        } else {
            networkStatus = CONNECTION_TYPE_OFF;
        }
        return networkStatus;

    }

}

