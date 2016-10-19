package com.razor.myprogressbar.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class RecyclerSearchOnScrollListener extends RecyclerView.OnScrollListener {

    private LoaderHelper mListener;
    private LinearLayoutManager mLayoutManager;
    private int totalItemCount;
    private int lastVisibleItem;
    private int visibleThreshold = 3;

    public RecyclerSearchOnScrollListener(LoaderHelper fragment, LinearLayoutManager layoutManager) {
        this.mListener = fragment;
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        totalItemCount = mLayoutManager.getItemCount();
        lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        if (totalItemCount <= (lastVisibleItem + visibleThreshold)) mListener.loadMoreOutlets();
    }
}




