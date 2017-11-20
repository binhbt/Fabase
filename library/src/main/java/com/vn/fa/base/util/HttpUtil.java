package com.vn.fa.base.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leobui on 11/20/2017.
 */

public class HttpUtil {
    public static String convertMapToQueryString(Map<String, String> params){
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
