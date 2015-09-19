package com.licheedev.customglidemodelloaderdemo.okhttp;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by John on 2015/9/19.
 */
public class OkHttpManager {

    private static OkHttpManager sManager;
    private OkHttpClient mClient;

    private OkHttpManager() {
        mClient = new OkHttpClient();
    }

    public static OkHttpManager getManager() {
        if (sManager == null) {
            synchronized (OkHttpManager.class) {
                if (sManager == null) {
                    sManager = new OkHttpManager();
                }
            }
        }
        return sManager;
    }

    public static OkHttpClient getClient() {
        return OkHttpManager.getManager().mClient;
    }
    
}
