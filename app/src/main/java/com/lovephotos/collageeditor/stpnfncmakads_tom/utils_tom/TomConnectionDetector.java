package com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TomConnectionDetector {

    private Context _contexttom;

    public TomConnectionDetector(Context context) {
        this._contexttom = context;
    }

    public boolean isConnectingToInternettom() {
        ConnectivityManager connectivitytom = (ConnectivityManager) _contexttom.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfotom = connectivitytom.getAllNetworkInfo();

        for (NetworkInfo netInfotom : networkInfotom) {

            if (netInfotom.getTypeName().equalsIgnoreCase("WIFI"))

                for (int itom = 0; itom < networkInfotom.length; itom++) {
                    if (netInfotom.isConnected())

                        return true;
                }
            if (netInfotom.getTypeName().equalsIgnoreCase("MOBILE"))

                if (netInfotom.isConnected())

                    return true;
        }
        return false;
    }

}

