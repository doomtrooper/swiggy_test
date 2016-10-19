package com.razor.myprogressbar.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.razor.myprogressbar.models.Response;

import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class ApiClient {
    public static final String TAG = ApiClient.class.getSimpleName();
    public static final String BASE_URL_UAT = "http://mpl.hdfclife.tech/api/";
    public static final String BASE_URL_PROD = "http://mobapp.hdfclife.com/api/";
    public static final String BASE_URL_OMA = "http://oma.hdfclife.tech/api/";
    public static final String BASE_URL = BASE_URL_OMA;
    public static final String TEST_CC_URL = "http://192.168.150.125:8000/api/";
    private static ApiClient instance;
    private APIService mApiService;
    private Context mContext;
    private Retrofit retrofit;

    private ApiClient(Context context) {
        this.mContext = context.getApplicationContext();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofit.client().setReadTimeout(1, TimeUnit.MINUTES);
        mApiService = getAPIServiceEndPoint();
    }

    public static ApiClient getInstance(Context context){
        if (instance==null){
            instance = new ApiClient(context);
        }
        return instance;
    }

    public APIService getmApiService() {
        return mApiService;
    }

    public void setmApiService(APIService mApiService) {
        this.mApiService = mApiService;
    }

    private APIService getAPIServiceEndPoint() {
        if (mApiService == null) {
            mApiService = retrofit.create(APIService.class);
        }
        return mApiService;
    }

    public interface APIService{
        @GET("https://api.myjson.com/bins/ngcc")
        Call<Response> fetchRestaurantsDetails(@Query("page") String page);
    }


}
