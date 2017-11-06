package com.vn.vega.base.net.request.okhttp;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by leobui on 10/25/2017.
 */

public class ApiConnection implements Callable<String> {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection create(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    public String requestSyncCall(Map<String, String> params) {
        connectToApi(params);
        return response;
    }
    public String postSync(Map<String, String> params) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (params != null && params.size() >0){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                System.out.println(entry.getKey()+" : "+entry.getValue());
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .post(formBody.build())
                .build();

        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void connectToApi(Map<String, String> params) {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url+convertMapToQueryString(params))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        //okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        //okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);

        return okHttpClient;
    }

    @Override public String call() throws Exception {
        return null;
    }
    private String convertMapToQueryString(Map<String, String> params){
        if (params == null || params.size() ==0) return "";
/*        return params.entrySet().stream()
                .map(p -> p.getKey() + "=" + p.getValue())
                .reduce((p1, p2) -> p1 + "&" + p2)
                .map(s -> "?" + s)
                .orElse("");*/
        try {
            StringBuilder sb = new StringBuilder();
            sb.append('?');
            for (HashMap.Entry<String, String> e : params.entrySet()) {
                if (sb.length() > 1) {
                    sb.append('&');
                }
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(e.getValue(), "UTF-8"));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return  "";
        }
    }
}
