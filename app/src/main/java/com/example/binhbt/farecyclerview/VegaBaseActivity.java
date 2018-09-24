package com.example.binhbt.farecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.binhbt.demo.R;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.swipe.SparseItemRemoveAnimator;
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;
import com.vn.fa.widget.FaRecyclerView;

import java.util.ArrayList;

/**
 * Created by binhbt on 6/27/2016.
 */
public abstract class VegaBaseActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

    private FaRecyclerView mRecycler;
    private StringListAdapter   mAdapter;
    private SparseItemRemoveAnimator mSparseAnimator;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler mHandler;
    private int pageCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ArrayList<String> list = new ArrayList<>();
        mAdapter = new StringListAdapter(list);

        mRecycler = (FaRecyclerView) findViewById(R.id.list);
        //mLayoutManager = getLayoutManager();
        //mRecycler.setLayoutManager(mLayoutManager);
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
                        mAdapter.addAll(new String[]{"More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff", "More stuff"});
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
                mAdapter.add("New stuff");
            }
        }, 2000);
        pageCount =0;
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        Toast.makeText(this, "More "+pageCount, Toast.LENGTH_LONG).show();
        if (pageCount <10) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    mAdapter.add("More asked, more served");
                    mAdapter.add("More asked, more served");
                    mAdapter.add("More asked, more served");
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
            mSparseAnimator.setSkipNext(true);
            mAdapter.remove(position);
        }
    }
}
