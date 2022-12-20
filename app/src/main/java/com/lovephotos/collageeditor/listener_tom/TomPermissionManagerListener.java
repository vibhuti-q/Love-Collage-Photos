package com.lovephotos.collageeditor.listener_tom;

import java.util.ArrayList;

public interface TomPermissionManagerListener {

    void onSinglePermissionGrantedtom(String permissionName, String... endPoint);

    void onMultiplePermissionGrantedtom(ArrayList<String> permissions, String... endPoint);

}
