package com.razor.myprogressbar.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class Restaurant {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("avg_rating")
    @Expose
    private String avgRating;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("cuisine")
    @Expose
    private ArrayList<String> cuisine = new ArrayList<>();
    @SerializedName("costForTwo")
    @Expose
    private String costForTwo;
    @SerializedName("deliveryTime")
    @Expose
    private Integer deliveryTime;
    @SerializedName("chain")
    @Expose
    private ArrayList<Chain> chain = new ArrayList<>();
    @SerializedName("closed")
    @Expose
    private Boolean closed;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", avgRating='" + avgRating + '\'' +
                ", cid='" + cid + '\'' +
                ", cuisine=" + cuisine +
                ", costForTwo='" + costForTwo + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", chain=" + chain +
                ", closed=" + closed +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public ArrayList<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(ArrayList<String> cuisine) {
        this.cuisine = cuisine;
    }

    public String getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(String costForTwo) {
        this.costForTwo = costForTwo;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public ArrayList<Chain> getChain() {
        return chain;
    }

    public void setChain(ArrayList<Chain> chain) {
        this.chain = chain;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

}
