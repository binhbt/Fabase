package com.example.binhbt.demo.ui.mvpdemo;

import com.example.binhbt.demo.model.User;
import com.example.binhbt.demo.model.VersionApp;
import com.example.binhbt.demo.net.BaseRequest;
import com.example.binhbt.demo.net.DemoRequestFactory;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.data.net.FaRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by binhbt on 3/7/2017.
 */
public class ListAppPresenter extends BasePresenter<ListAppView> {

    public void loadData() {

        DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                        t.printStackTrace();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                        //testUser();
                        //mixRequest();
                    }
                })
                .doRequest();

    }
    public void flatRequest(){
        final FaRequest request1 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());

        final FaRequest request2 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());
        BaseRequest flatRequest = new BaseRequest(){
            @Override
            public Observable getApi() {
                Observable<List<VersionApp>> flatMap = request1.getApi().flatMap(ls->request2.getApi());
                return flatMap;
            }
        };
        flatRequest.params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .doRequest();
    }

    public void mixRequest(){
        final FaRequest request1 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());

        final FaRequest request2 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());
        BaseRequest mixRequest = new BaseRequest(){
            @Override
            public Observable getApi() {
                Observable<List<VersionApp>> combined = Observable.zip(request1.getApi(), request2.getApi(), new BiFunction<List<VersionApp> , List<VersionApp>, List<VersionApp>>() {
                    @Override
                    public List<VersionApp> apply(List<VersionApp> appList1, List<VersionApp> appList2) {
                        List<VersionApp> result = new ArrayList<>();
                        result.addAll(appList1);
                        result.addAll(appList2);
                        return result;
                    }
                });
                return combined;
            }
        };
        mixRequest.params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .doRequest();
    }
    public void testUser(){
        DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.USER_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<User>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<User>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(User result) {
                        if (result != null) {
                            //mixRequest();
                        }
                    }
                })
                .doRequest();
    }
}
