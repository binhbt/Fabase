package com.vn.vega.base.net;

import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.vn.vega.base.model.VegaObject;
import com.vn.vega.base.net.request.RequestType;
import com.vn.vega.base.net.request.RestEndPoints;
import com.vn.vega.base.net.request.retrofit.RetrofitAdapterFactory;
import com.vn.vega.net.RequestLoader;
import com.vn.vega.net.adapter.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Function;

/**
 * Created by leobui on 10/27/2017.
 */

public abstract class FARequest<T> {
    public interface Convert<T, R> extends Func1<T, R> {
    }
    private static volatile RestEndPoints apix;

    private RestEndPoints getApiSingleton() {
        if (apix == null) {
            synchronized (RestEndPoints.class) {
                if (apix == null) {
                    apix = new Request.Builder()
                            .baseUrl(baseUrl == null ? getBaseUrl() : baseUrl)
                            .addRequestAdapterFactory(resAdapter == null ? getRequestAdapter() : resAdapter)
                            //.addRequestAdapterFactory(new OkHttpAdapterFactory())
                            .logging(isLogging ? isLogging() : isLogging)
                            .build()
                            .create(RestEndPoints.class);
                }
            }
        }
        return apix;
    }

    public interface RequestCallBack<T> {
        public void onStart();

        public void onError(Throwable t);

        public void onFinish(T result);
    }

    protected RequestType type;
    protected Object container;
    protected List<RequestCallBack> callBacks = new ArrayList<>();
    protected Observable<T> api;
    protected Type dataType;
    protected Map<String, String> params;
    protected String path;
    protected String baseUrl;
    protected Request.Factory resAdapter;
    protected boolean isLogging = true;

    public FARequest logging(boolean isLogging) {
        this.isLogging = isLogging;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLogging(boolean logging) {
        isLogging = logging;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public List<RequestCallBack> getCallBacks() {
        return callBacks;
    }

    public void setCallBacks(List<RequestCallBack> callBacks) {
        this.callBacks = callBacks;
    }

    public void setApi(Observable<T> api) {
        this.api = api;
    }

    public void setDataType(Type dataType) {
        this.dataType = dataType;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isLogging() {
        return true;
    }

    public FARequest requesAdaptert(Request.Factory resAdapter) {
        this.resAdapter = resAdapter;
        return this;
    }

    public Request.Factory getRequestAdapter() {
        return new RetrofitAdapterFactory();
    }

    public FARequest params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public FARequest addParams(String key, String value) {
        if (this.params == null)
            this.params = new HashMap<>();
        this.params.put(key, value);
        return this;
    }

    protected Map<String, String> headers;

    public FARequest headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }
    protected Convert getConvert() {
        return null;
    }
    public Map<String, String> getParams() {
        return null;
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    protected RestEndPoints getEndPoints() {
        return getApiSingleton();
    }

    protected Observable getApi() {
/*        RestEndPoints apix = new Request.Builder()
                .baseUrl(EndPoints.API_ENDPOINT)
                //.addRequestAdapterFactory(new RetrofitAdapterFactory())
                .addRequestAdapterFactory(new OkHttpAdapterFactory())
                .logging(true)
                .build()
                .create(RestEndPoints.class);*/
        if(getConvert() == null) {
            return getEndPoints().callApi(type == null ? getType() : type,
                    path == null ? getPath() : path,
                    params == null ? getParams() : params,
                    headers == null ? getHeaders() : headers,
                    dataType == null ? getDataType() : dataType);
        }else{
            return getEndPoints().callApi(type == null ? getType() : type,
                    path == null ? getPath() : path,
                    params == null ? getParams() : params,
                    headers == null ? getHeaders() : headers,
                    dataType == null ? getDataType() : dataType)
                    .map(getConvert());
        }
    }

    public FARequest path(String path) {
        this.path = path;
        return this;
    }

    public FARequest baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RequestType getType() {
        return RequestType.GET;
    }

    public FARequest dataType(Type type) {
        this.dataType = type;
        return this;
    }

    public FARequest dataType(Class type) {
        this.dataType = type;
        return this;
    }

    protected Type getDataType() {
        Type type = new TypeToken<Object>() {
        }.getType();
        return type;
    }

    protected String getPath() {
        return null;
    }

    protected String getBaseUrl() {
        return null;
    }

    public FARequest api(Observable<T> api) {
        this.api = api;
        return this;
    }

    public FARequest type(RequestType type) {
        this.type = type;
        return this;
    }

    public FARequest container(Object container) {
        this.container = container;
        return this;
    }

    public FARequest callBack(RequestCallBack callBack) {
        callBacks.add(callBack);
        return this;
    }

    public FARequest addCallBack(RequestCallBack callBack) {
        callBacks.add(callBack);
        return this;
    }

    public void doRequest() {
        if (this.api != null || getApi() != null) {
            doRequest(api == null ? getApi() : api);
        }
    }

    public void doRequest(Observable<T> observable) {
        new RequestLoader.Builder()
                .api(observable)
                .callback(new RequestLoader.CallBack<T>() {
                    @Override
                    public void onStart() {
                        if (callBacks != null && callBacks.size() > 0) {
                            for (RequestCallBack callBack : callBacks
                                    ) {
                                if (callBack != null) {
                                    callBack.onStart();
                                }
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (callBacks != null && callBacks.size() > 0) {
                            for (RequestCallBack callBack : callBacks
                                    ) {
                                if (callBack != null) {
                                    callBack.onError(t);
                                }
                            }

                        }
                    }

                    @Override
                    public void onFinish(T result) {
                        if (result != null) {
                            if (callBacks != null && callBacks.size() > 0) {
                                for (RequestCallBack callBack : callBacks
                                        ) {
                                    if (callBack != null) {
                                        callBack.onFinish(result);
                                    }
                                }
                                callBacks.clear();
                            }
                        }

                    }
                })
                .container(container == null ? "default_container" : container)
                .build();
    }

    static protected Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
}
