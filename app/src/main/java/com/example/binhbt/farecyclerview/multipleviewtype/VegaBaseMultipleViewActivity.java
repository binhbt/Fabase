package com.example.binhbt.farecyclerview.multipleviewtype;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.binhbt.demo.R;
import com.example.binhbt.farecyclerview.PaddingItemDecoration;
import com.example.binhbt.farecyclerview.multipleviewtype.model.PhotoItem;
import com.example.binhbt.farecyclerview.multipleviewtype.model.TextItem;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.swipe.SparseItemRemoveAnimator;
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;
import com.vn.fa.adapter.FaAdapter;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.widget.FaRecyclerView;

import java.util.ArrayList;

/**
 * Created by binhbt on 7/22/2016.
 */
public abstract class VegaBaseMultipleViewActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

    private FaRecyclerView mRecycler;
    private FaAdapter mAdapter;
    private SparseItemRemoveAnimator mSparseAnimator;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler mHandler;
    private int pageCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ArrayList<String> list = new ArrayList<>();
        mAdapter = new FaAdapter();

        mRecycler = (FaRecyclerView) findViewById(R.id.list);
        //mLayoutManager = getLayoutManager();
        //mRecycler.setLayoutManager(mLayoutManager);
        if (getLayoutManager() != null)
            mRecycler.setLayoutManager(getLayoutManager());
        mRecycler.addItemDecoration(new PaddingItemDecoration());

        boolean dismissEnabled = isSwipeToDismissEnabled();
        if (dismissEnabled) {
            mRecycler.setupSwipeToDismiss(this);
            mSparseAnimator = new SparseItemRemoveAnimator();
            mRecycler.setItemAnimator(mSparseAnimator);
        }

        mHandler = new Handler(Looper.getMainLooper());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecycler.setAdapter(mAdapter);
                        mRecycler.getProgressView().setVisibility(View.GONE);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //mAdapter.addAll(new String[]{"More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff"});
                        mAdapter.addBinder(new PhotoItem().getViewBinder());
                        mAdapter.addBinder(new TextItem().getViewBinder());
                        mAdapter.notifyDataSetChanged();
                    }
                });


            }
        });
        thread.start();

        mRecycler.setRefreshListener(this);
        mRecycler.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecycler.setupMoreListener(this);
    }

    protected abstract int getLayoutId();

    protected abstract boolean isSwipeToDismissEnabled();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();
        mRecycler.supportLoadMore(true);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mAdapter.addDataObject(new PhotoItem());
                mAdapter.addDataObject(new TextItem());
            }
        }, 2000);
        pageCount =0;
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        Toast.makeText(this, "More "+pageCount, Toast.LENGTH_LONG).show();
        if (pageCount <20) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    ArrayList<IViewBinder> list = new ArrayList<IViewBinder>();
                    list.add(new PhotoItem());
                    list.add(new TextItem());
                    list.add(new PhotoItem());
                    list.add(new TextItem());
                    mAdapter.addAllDataObject(list);
                }
            }, 300);
            pageCount ++;
        }else{
            mRecycler.endData();
        }
    }

    @Override
    public boolean canDismiss(int position) {
        return isSwipeToDismissEnabled();
    }

    @Override
    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
//            mSparseAnimator.setSkipNext(true);
//            mAdapter.remove(position);
        }
    }
}
