package com.vn.fa.base.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.adapter.multipleviewtype.VegaBindAdapter;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.base.holder.OnItemClickListener;
import com.vn.fa.base.net.FaRequest;
import com.vn.fa.base.net.request.RequestType;
import com.vn.fa.widget.RecyclerViewWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leobui on 11/2/2017.
 */

public class FARecyclerview extends RecyclerViewWrapper implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnRequestExcute{
        public void onStart();
        public void onError(Throwable t);
        public void onFinish(List<IViewBinder> result);
    }

    public FARecyclerview(Context context) {
        super(context);
    }

    public FARecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FARecyclerview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onRefresh() {

        offset =0;
        loadData(false);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        offset = adapter.size() -1;
        loadData(true);
    }

    private Object container;
    private boolean isCanRefresh = false;
    private boolean isCanLoadMore = false;
    private int limit = 20;
    private int offset = 0;
    private RequestType type;
    private String path;
    private Map<String, String> params = new HashMap<>();
    private FaAdapter adapter;
    private FaRequest request;
    private OnRequestExcute requestCallback;
    private String limitName = "limit";
    private String offsetName ="offset";
    public FARecyclerview api(FaRequest request) {
        this.request = request;
        return this;
    }
    public FARecyclerview requestCallback(OnRequestExcute requestCallback) {
        this.requestCallback = requestCallback;
        return this;
    }

    public FARecyclerview params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public FARecyclerview path(String path) {
        this.path = path;
        return this;
    }

    public FARecyclerview limit(int limit) {
        this.limit = limit;
        return this;
    }
    public FARecyclerview limit(String limitName, int limit) {
        this.limit = limit;
        this.limitName = limitName;
        return this;
    }
    public FARecyclerview offset(String offsetName, int offset) {
        this.offset = offset;
        this.offsetName = offsetName;
        return this;
    }
    public FARecyclerview offset(int offset) {
        this.offset = offset;
        return this;
    }

    public FARecyclerview container(Object container) {
        this.container = container;
        return this;
    }

    public FARecyclerview canLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
        return this;
    }

    public FARecyclerview canRefresh(boolean isCanRefresh) {
        this.isCanRefresh = isCanRefresh;
        return this;
    }

    public FARecyclerview type(RequestType type) {
        this.type = type;
        return this;
    }

    public boolean isCanRefresh() {
        return isCanRefresh;
    }

    public void setCanRefresh(boolean canRefresh) {
        isCanRefresh = canRefresh;
    }

    public boolean isCanLoadMore() {
        return isCanLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        isCanLoadMore = canLoadMore;
    }

    @Override
    public VegaBindAdapter getAdapter() {
        return adapter;
    }

    public void load() {
        if (isCanLoadMore) {
            setOnMoreListener(this);
        }
        if (isCanRefresh) {
            setRefreshListener(this);
        }
        loadData(false);
    }
    public void addAllData(List<IViewBinder> result){
        if (adapter == null){
            adapter = new FaAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            setAdapter(adapter);
        }
        adapter.addAllDataObject(result);
    }
    public void addData(IViewBinder result){
        if (adapter == null){
            adapter = new FaAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            setAdapter(adapter);
        }
        adapter.addDataObject(result);
    }
    private void loadData(final boolean isLoadMore) {
        params.put(limitName, limit+"");
        params.put(offsetName, offset +"");
        type = type == null?this.request.getType():type;
        if (this.request != null) {
            this.request.addCallBack(new FaRequest.RequestCallBack<List<IViewBinder>>() {
                @Override
                public void onStart() {
                    if (requestCallback != null){
                        requestCallback.onStart();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    if (requestCallback != null){
                        requestCallback.onError(t);
                    }
                    //Log.e("err", "err");
                    if (adapter == null){
                        adapter = new FaAdapter();
                        adapter.setOnItemClickListener(onItemClickListener);
                        setAdapter(adapter);
                    }
                    endData();
                }

                @Override
                public void onFinish(List<IViewBinder> result) {
                    if (requestCallback != null){
                        requestCallback.onFinish(result);
                    }
                    if (adapter == null){
                        adapter = new FaAdapter();
                        adapter.setOnItemClickListener(onItemClickListener);
                        setAdapter(adapter);
                    }
                    if (!isLoadMore){
                        adapter.clear();
                    }
                    if (result != null && result.size() >0) {
                        adapter.addAllDataObject(result);
                        if (result.size() <limit){
                            endData();
                        }
                    }else{
                        endData();
                    }
                }
            })
                    .params(params)
                    .path(path)
                    .type(type == null?RequestType.GET:type)
                    .container(container == null? "recycler_request":container)
                    .doRequest();
        }
    }
}
