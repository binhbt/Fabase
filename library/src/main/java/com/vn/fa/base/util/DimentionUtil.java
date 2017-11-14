package com.vn.fa.base.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class DimentionUtil {

	public static int dpToPx(int dp, Context ctx) {
	    DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
	    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}

	public static int pxToDp(int px, Context ctx) {
	    DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
	    int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	    return dp;
	}
}
