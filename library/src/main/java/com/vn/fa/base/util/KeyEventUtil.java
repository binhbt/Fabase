package com.vn.fa.base.util;

import android.app.Instrumentation;

/**
 * Created by binhbt on 12/2/2016.
 */
public class KeyEventUtil  {
    public static void sendKey(final int keyCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
