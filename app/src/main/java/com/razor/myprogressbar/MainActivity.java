package com.razor.myprogressbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.razor.myprogressbar.adapter.RestaurantAdapter;
import com.razor.myprogressbar.api.ApiClient;
import com.razor.myprogressbar.listeners.LoaderHelper;
import com.razor.myprogressbar.listeners.RecyclerSearchOnScrollListener;
import com.razor.myprogressbar.models.Response;
import com.razor.myprogressbar.models.Restaurant;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements LoaderHelper {

    @Bind(R.id.my_recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    private RestaurantAdapter mAdapter;
    private int mPageNumber;
    private boolean isLoading;
    private Call<Response> call;
    private String PAGE_NUMBER = "page_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        callApi();
    }

    private void callApi() {
        if (Utility.isNetworkAvailable(this)){
            getStateCityList();
        }else {
            showError(getString(R.string.no_network));
        }
    }

    private void initRecyclerView() {
        mAdapter = new RestaurantAdapter(this,new ArrayList<Restaurant>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerSearchOnScrollListener(this,layoutManager));
    }

    public void getStateCityList(){
        if (isLoading) return;
        call = ApiClient.getInstance(this).getmApiService().fetchRestaurantsDetails(String.valueOf(mPageNumber));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
                isLoading = false;
                if (mPageNumber==0 && response.isSuccess()){
                    mProgressBar.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    initRecyclerView();
                    mPageNumber++;
                    mAdapter.addItems(response.body().getRestaurants());
                }else if (response.isSuccess()){
                    mPageNumber++;
                    mAdapter.addItems(response.body().getRestaurants());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                isLoading = false;
                showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadMoreOutlets() {
        getStateCityList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(PAGE_NUMBER,mPageNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPageNumber = savedInstanceState.getInt(PAGE_NUMBER,0);
    }

    private void showError(String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                callApi();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setMessage(error);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
