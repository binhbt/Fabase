package com.vn.fa.base.net;

import android.util.Log;

import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.DataRepository;
import com.vn.fa.base.data.cache.CacheFactory;
import com.vn.fa.base.domain.Repository;
import com.vn.fa.base.net.request.RequestType;
import com.vn.fa.base.net.request.RestEndPoints;
import com.vn.fa.base.net.request.retrofit.RetrofitAdapterFactory;
import com.vn.fa.base.util.HttpUtil;
import com.vn.fa.base.util.StringUtil;
import com.vn.fa.net.RequestLoader;
import com.vn.fa.net.adapter.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by leobui on 10/27/2017.
 */

public abstract class FaRequest<T> {
    public interface Convert<T, R> extends Function<T, R> {
    }
    protected static volatile RestEndPoints apix;

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
    protected String tag;
    protected List<RequestCallBack> callBacks = new ArrayList<>();
    protected Observable<T> api;
    protected Type dataType;
    protected Map<String, String> params;
    protected String path;
    protected String baseUrl;
    protected Request.Factory resAdapter;
    protected boolean isLogging = true;
    protected boolean isNewInstance = false;
    protected Repository dataRepository;
    protected boolean isCache = false;
    public FaRequest cache(boolean isCache) {
        this.isCache = isCache;
        return this;
    }
    public FaRequest dataRepository(Repository dataRepository) {
        this.dataRepository = dataRepository;
        return this;
    }
    public FaRequest tag(String tag) {
        this.tag = tag;
        return this;
    }
    public FaRequest logging(boolean isLogging) {
        this.isLogging = isLogging;
        return this;
    }
    public FaRequest newInstance(boolean isNewInstance) {
        this.isNewInstance = isNewInstance;
        return this;
    }
    public Repository getDataRepository(){
        return null;
    }
    public boolean isCache() {
        return false;
    }
    public boolean isNewInstance() {
        return false;
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

    public FaRequest requesAdaptert(Request.Factory resAdapter) {
        this.resAdapter = resAdapter;
        return this;
    }

    public Request.Factory getRequestAdapter() {
        return new RetrofitAdapterFactory();
    }

    public FaRequest params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public FaRequest addParams(String key, String value) {
        if (this.params == null)
            this.params = new HashMap<>();
        this.params.put(key, value);
        return this;
    }

    protected Map<String, String> headers;

    public FaRequest headers(Map<String, String> headers) {
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
        if (isNewInstance || isNewInstance()){
            return new Request.Builder()
                    .baseUrl(baseUrl == null ? getBaseUrl() : baseUrl)
                    .addRequestAdapterFactory(resAdapter == null ? getRequestAdapter() : resAdapter)
                    //.addRequestAdapterFactory(new OkHttpAdapterFactory())
                    .logging(isLogging ? isLogging() : isLogging)
                    .build()
                    .create(RestEndPoints.class);
        }
        return getApiSingleton();
    }

    protected Observable getApi() {
        if ((getDataRepository() == null && dataRepository == null)) {
            if (getConvert() == null) {
                return getEndPoints().callApi(type == null ? getType() : type,
                        path == null ? getPath() : path,
                        params == null ? getParams() : params,
                        headers == null ? getHeaders() : headers,
                        dataType == null ? getDataType() : dataType);
            } else {
                return getEndPoints().callApi(type == null ? getType() : type,
                        path == null ? getPath() : path,
                        params == null ? getParams() : params,
                        headers == null ? getHeaders() : headers,
                        dataType == null ? getDataType() : dataType)
                        .map(getConvert());
            }
        }else{
            Repository repository = getDataRepository() == null?dataRepository:getDataRepository();
            if (getConvert() == null) {
                return repository.callApi(type == null ? getType() : type,
                        path == null ? getPath() : path,
                        params == null ? getParams() : params,
                        headers == null ? getHeaders() : headers,
                        dataType == null ? getDataType() : dataType, StringUtil.getBase64(path+ HttpUtil.convertMapToQueryString(params)), !(isCache||isCache()));
            } else {
                return repository.callApi(type == null ? getType() : type,
                        path == null ? getPath() : path,
                        params == null ? getParams() : params,
                        headers == null ? getHeaders() : headers,
                        dataType == null ? getDataType() : dataType, StringUtil.getBase64(path+ HttpUtil.convertMapToQueryString(params)), !(isCache||isCache()))
                        .map(getConvert());
            }
        }
    }

    public FaRequest path(String path) {
        this.path = path;
        return this;
    }

    public FaRequest baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RequestType getType() {
        return RequestType.GET;
    }

    public FaRequest dataType(Type type) {
        this.dataType = type;
        return this;
    }

    public FaRequest dataType(Class type) {
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

    public FaRequest api(Observable<T> api) {
        this.api = api;
        return this;
    }

    public FaRequest type(RequestType type) {
        this.type = type;
        return this;
    }

    public FaRequest container(Object container) {
        this.container = container;
        return this;
    }

    public FaRequest callBack(RequestCallBack callBack) {
        callBacks.add(callBack);
        return this;
    }

    public FaRequest addCallBack(RequestCallBack callBack) {
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
                .tag(tag)
                .build();
    }
    public void cancel(){
        if (tag != null) {
            RequestLoader.getDefault().cancelByTag(tag);
        }else{
            Log.e("Cancel Request", "You have to add tag for this request for cancel");
        }
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
