package com.vn.vega.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by binhbt on 8/2/2016.
 */
public class NetworkStatusReceiver extends BroadcastReceiver {

    public static final int CONNECTION_TYPE_OFF = 0;
    public static final int CONNECTION_TYPE_WIFI = 1;
    public static final int CONNECTION_TYPE_3G = 2;

    public static final String NETWORK_CHANGE = "vn.com.vega.networkchange";
    public static final String NETWORK_OFF = "vn.com.vega.networkoff";

    public static int networkStatus;

    @Override
    public void onReceive(Context context, Intent intent) {
        networkStatus = getNetworkStatus(context);
    }

    public static boolean isConnected() {
        return networkStatus != CONNECTION_TYPE_OFF;
    }
    public static boolean is3GNetWork (){
        return networkStatus == CONNECTION_TYPE_3G;
    }
    public static int getNetworkStatus(Context ctx) {
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

