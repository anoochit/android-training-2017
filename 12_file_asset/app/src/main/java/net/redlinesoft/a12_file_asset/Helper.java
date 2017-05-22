package net.redlinesoft.a12_file_asset;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by xavier on 5/19/2017 AD.
 */

public class Helper {

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



}
