package com.razor.myprogressbar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by kuliza-265 on 19/10/16.
 */

public class Utility {
     public static String concatCuisuine(@NonNull ArrayList<String> strings){
         StringBuilder builder = new StringBuilder();
         for (String string:strings) builder.append(string).append(", ");
         builder.delete(builder.length()-2,builder.length());
         return builder.toString();
     }

    /**
     * Is network available.
     *
     * @param ctx the context
     * @return the true if the network connection is there false otherwise.
     */
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = null;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            // Toast.makeText(ctx, Constants.toastInternetError, Toast.LENGTH_SHORT).show();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
        } catch (NullPointerException e) {
            //Toast.makeText(ctx, Constants.toastInternetError, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
