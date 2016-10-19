package com.razor.myprogressbar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razor.myprogressbar.R;
import com.razor.myprogressbar.Utility;
import com.razor.myprogressbar.holders.RestaurantViewHolder;
import com.razor.myprogressbar.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private Context mContext;
    private ArrayList<Restaurant> mRestaurantArrayList;
    private SparseBooleanArray booleanArray;

    public RestaurantAdapter(Context mContext, ArrayList<Restaurant> mRestaurantArrayList) {
        this.mContext = mContext;
        this.mRestaurantArrayList = mRestaurantArrayList;
        booleanArray = new SparseBooleanArray();
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View restaurantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row_card, parent, false);
        return new RestaurantViewHolder(restaurantView,mContext,this);
    }

    public void setBooleanArrayAt(int pos,boolean val){
        booleanArray.append(pos,val);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.setOutletName(mRestaurantArrayList.get(position).getName());
        holder.setOutletCuisineType(Utility.concatCuisuine(mRestaurantArrayList.get(position).getCuisine()));
        holder.setOutletCost(mRestaurantArrayList.get(position).getCostForTwo());
        holder.setoutletRating(mRestaurantArrayList.get(position).getAvgRating());
        holder.setOutletDeliveryTimeTv(String.valueOf(mRestaurantArrayList.get(position).getDeliveryTime())+ " mins");
        holder.setOutletImage(mRestaurantArrayList.get(position).getCid());
        holder.setOutletBranches(mRestaurantArrayList.get(position).getChain());
        holder.setOutletChainContainerVisibility(booleanArray.get(position)?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return mRestaurantArrayList==null?0:mRestaurantArrayList.size();
    }

    public void addItems(ArrayList<Restaurant> restaurantArrayList){
        for (Restaurant restaurant:restaurantArrayList){
            mRestaurantArrayList.add(restaurant);
            notifyItemInserted(mRestaurantArrayList.size()-1);
        }
    }

    public Restaurant getItemAt(int adapterPosition) {
        return mRestaurantArrayList.get(adapterPosition);
    }


}
