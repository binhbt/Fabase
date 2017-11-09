package com.vn.fa.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * Created by binhbt on 8/31/2016.
 */
public class PakageUtil {
    public static String getVersionName(Context ctx, String pakageName){
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(pakageName, 0);
            return  pInfo.versionName;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static int getVersionCode(Context ctx, String pakageName){
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(pakageName, 0);
            return  pInfo.versionCode;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
