package com.lovephotos.collageeditor.Utilities_tom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TomConnectionDetector {

    private final Context _contexttom;

    public TomConnectionDetector(Context context) {
        this._contexttom = context;
    }

    public boolean isConnectingToInternettom() {
        ConnectivityManager connectivitytom = (ConnectivityManager) _contexttom.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivitytom != null) {
            NetworkInfo[] info = connectivitytom.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfotom : info) {
                    if (networkInfotom.getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }
}