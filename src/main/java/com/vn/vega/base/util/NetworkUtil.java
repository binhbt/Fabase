package com.vn.vega.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by binhbt on 8/16/2016.
 */
public class NetworkUtil {
    public static int TYPE_ETHERNET =3;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET)
                return TYPE_ETHERNET;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkUtil.TYPE_WIFI) {
            status = "Kết nối mạng Wifi thành công";
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status = "Mobile data connected";
        } else if (conn == NetworkUtil.TYPE_ETHERNET) {
            status = "Kết nối mạng dây thành công";
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = "Chưa kết nối mạng internet";
        }
        return status;
    }

    public static boolean isConnected(Context ctx) {
        final ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            return true;
        } else {
            // notify user you are not online
            return false;
        }
    }

    public static String checkNetworkStatusAviable(Context ctx){

        final ConnectivityManager connMgr = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        final NetworkInfo ethernet =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        String result="";
        if(wifi!=null&& wifi.isAvailable() ){

            result+= "Wifi avaiable";
        }
        else if(mobile!=null&& mobile.isAvailable() ){

            result+= "Mobile 3G avaiable";
        }
        else if(ethernet!=null&& ethernet.isAvailable() ){

            result+= "Ethernet avaiable ";
        }else {

            result+= "No Network avaiable";
        }
        return  result;
    }
    public static String checkEthernetAvaiable(Context ctx){
        ConnectivityManager e = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = e.getActiveNetworkInfo();

        final ConnectivityManager connMgr = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

/*
        final android.net.NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
*/

        final NetworkInfo ethernet =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        String result="";
        if(networkInfo!=null&& networkInfo.isAvailable() ){
            result+= "ok ";
        }else{
            result+= "not ok ";
        }
        if(ethernet!=null&& ethernet.isAvailable() ){
            result+= "Ethernet avaiable ";
        }else {
            result+= "No Ethernet Network avaiable";
        }
        return  result;
    }
    public static boolean isEthernetAvaiable(Context ctx){
        ConnectivityManager e = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = e.getActiveNetworkInfo();
        if(networkInfo!=null&& networkInfo.isAvailable() ){
            return true;
        }else{
            return false;
        }
    }
    private Boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Boolean isWifiConnected(Context ctx){
        if(isNetworkAvailable(ctx)){
            ConnectivityManager cm
                    = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
        }
        return false;
    }

    public Boolean isEthernetConnected(Context ctx){
        if(isNetworkAvailable(ctx)){
            ConnectivityManager cm
                    = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_ETHERNET);
        }
        return false;
    }
    public static String getNetworkClass(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info==null || !info.isConnected())
            return "-"; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "?";
            }
        }
        return "?";
    }
}
