package com.razor.myprogressbar.holders;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razor.myprogressbar.R;
import com.razor.myprogressbar.adapter.RestaurantAdapter;
import com.razor.myprogressbar.models.Chain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.outlet_name) TextView outletNameTv;
    @Bind(R.id.cuisine_type) TextView outletCuisineTypeTv;
    @Bind(R.id.cost_for_two) TextView outletCostTv;
    @Bind(R.id.rating) TextView outletRatingTv;
    @Bind(R.id.delivery_time) TextView outletDeliveryTimeTv;
    @Bind(R.id.seperator) View seperatorView;
    @Bind(R.id.outlet_count) TextView outletCountTv;
    @Bind(R.id.chain_container) LinearLayout outletChainContainer;
    @Bind(R.id.image) ImageView outletImage;

    private Context mContext;
    private RestaurantAdapter adapter;

    public RestaurantViewHolder(View itemView, Context context, RestaurantAdapter restaurantAdapter) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext= context;
        this.adapter = restaurantAdapter;
    }


    @OnClick(R.id.card_view)
    void onCardClick(){
        if (adapter.getItemAt(getAdapterPosition()).getChain().size()<=1){
            Toast.makeText(mContext,"Show details...",Toast.LENGTH_SHORT).show();
        }else {
            toggleOutletChainContainerVisibility();
        }
    }

    @OnClick(R.id.outlet_count)
    void onCountClick(){
        toggleOutletChainContainerVisibility();
    }

    public void setOutletName(@Nullable String outletName){
        outletNameTv.setText(outletName);
    }

    public void setOutletCuisineType(@Nullable String outletCuisineType){
        outletCuisineTypeTv.setText(outletCuisineType);
    }

    public void setOutletCost(@Nullable String outletCost){
        Spannable wordtoSpan = new SpannableString(mContext.getString(R.string.cost_for_two));
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.GREEN), 0, outletCost.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        outletCostTv.setText(wordtoSpan);
    }

    public void setoutletRating(@Nullable String outletRating){
        outletRatingTv.setText(outletRating);
    }

    public void setOutletDeliveryTimeTv(@Nullable String outletDeliveryTime){
        outletDeliveryTimeTv.setText(outletDeliveryTime);
    }

    public void setVisibilitySeperator(int visibility){
        seperatorView.setVisibility(visibility);
    }

    public void setOutletCountVisibility(int visibility){
        outletCountTv.setVisibility(visibility);
    }

    public void setOutletCount(String outletCount){
        outletCountTv.setText(outletCount);
    }

    public void setOutletImage(String imageId){
        Picasso.with(mContext).load("https://res.cloudinary.com/swiggy/image/upload/"+imageId)
                .resize(800, 800) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(outletImage);
    }

    public void setOutletBranches(ArrayList<Chain> chains) {
        if (outletChainContainer.getChildCount()>0) outletChainContainer.removeAllViews();
        if (chains.size()>1){
            seperatorView.setVisibility(View.VISIBLE);
            outletCountTv.setVisibility(View.VISIBLE);
            outletCountTv.setText(chains.size()+" more outlets.");
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (Chain chain:chains){
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.more_outlet_row,null);
                ((TextView) layout.getChildAt(0)).setText(chain.getCostForTwo());
                ((TextView) layout.getChildAt(1)).setText(chain.getAvgRating());
                ((TextView) layout.getChildAt(2)).setText(chain.getDeliveryTime()+" mins");
                outletChainContainer.addView(layout);
            }
        }else {
            seperatorView.setVisibility(View.GONE);
            outletCountTv.setVisibility(View.GONE);
            outletChainContainer.setVisibility(View.GONE);
        }
    }

    public void setOutletChainContainerVisibility(int visibility){
        outletChainContainer.setVisibility(visibility);
    }

    private void toggleOutletChainContainerVisibility(){
        outletChainContainer.setVisibility(outletChainContainer.getVisibility()==View.GONE?View.VISIBLE:View.GONE);
        adapter.setBooleanArrayAt(getAdapterPosition(),outletChainContainer.getVisibility()==View.VISIBLE);
    }
}
