package com.vn.fa.base.util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;


public class DeviceUtil {

    public static int getScreenWidth(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getActionbarHeight(Context act) {
        TypedValue tv = new TypedValue();
        if (act.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, act.getResources().getDisplayMetrics());
            return actionBarHeight;
        }
        return 0;
    }

    public static String getDeviceId(Context ctx) {
        try {
			try {
				TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
				if (telephonyManager.getDeviceId() != null && telephonyManager.getDeviceId().length() > 0) {
					FaLog.e("telephonyManager " + telephonyManager.getDeviceId());
					return telephonyManager.getDeviceId();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
            String deviceId = Settings.Secure.getString(ctx.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            FaLog.e("ANDROID_ID " + deviceId);
            if (deviceId != null) {
                return deviceId;
            }else{
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            WifiManager manager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            String address = info.getMacAddress();
            FaLog.e("address " + address);
            return address;
        }

    }
}
