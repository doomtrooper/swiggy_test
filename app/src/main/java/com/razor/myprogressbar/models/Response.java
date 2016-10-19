package com.razor.myprogressbar.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class Response {
    @SerializedName("restaurants")
    @Expose
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    public String toString() {
        return "Response{" +
                "restaurants=" + restaurants +
                '}';
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
