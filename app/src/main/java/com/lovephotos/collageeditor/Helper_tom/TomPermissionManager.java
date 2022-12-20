package com.lovephotos.collageeditor.Helper_tom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity;
import com.lovephotos.collageeditor.listener_tom.TomPermissionManagerListener;

import java.util.ArrayList;
import java.util.List;

public class TomPermissionManager {

    private static AlertDialog.Builder buildertom = null;
    private final Context mContexttom;
    private String endPointtom;
    private TomPermissionManagerListener listenertom;

    public TomPermissionManager(Context mContext, TomPermissionManagerListener listener) {
        this.mContexttom = mContext;
        this.listenertom = listener;
    }

    public void setSinglePermission(String permissionName) {

        Dexter.withContext(mContexttom)
                .withPermission(permissionName)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (listenertom == null)
                            listenertom = (TomPermissionManagerListener) mContexttom;
                        listenertom.onSinglePermissionGrantedtom(permissionName, endPointtom);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        if (permissionDeniedResponse.isPermanentlyDenied())
                            showSettingsDialogtom();
                        else setSinglePermission(permissionDeniedResponse.getPermissionName());
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    public void setMultiplePermissiontom(ArrayList<String> permissions) {

        Dexter.withContext(mContexttom)
                .withPermissions(permissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialogtom();
                        } else if (report.areAllPermissionsGranted()) {
                            ArrayList<String> grantedPermissions = new ArrayList<>();
                            for (int itom = 0; itom < report.getGrantedPermissionResponses().size(); itom++) {
                                grantedPermissions.add(report.getGrantedPermissionResponses().get(itom).getPermissionName());
                            }

                            if (listenertom == null) listenertom = (TomPermissionManagerListener) mContexttom;

                            if (endPointtom != null && !endPointtom.isEmpty()) listenertom.onMultiplePermissionGrantedtom(grantedPermissions, endPointtom);
                            else listenertom.onMultiplePermissionGrantedtom(grantedPermissions);
                        } else {
                            ArrayList<String> deniedPermissionsJusi = new ArrayList<>();
                            for (int itom = 0; itom < report.getDeniedPermissionResponses().size(); itom++) {
                                deniedPermissionsJusi.add(report.getDeniedPermissionResponses().get(itom).getPermissionName());
                            }
                            setMultiplePermissiontom(deniedPermissionsJusi);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialogtom() {
        if (buildertom == null) {
            buildertom = new AlertDialog.Builder(mContexttom);
            buildertom.setTitle("Need Permissions");
            buildertom.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
            buildertom.setCancelable(false);
            buildertom.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
                openSettingstom();
                dialog.dismiss();
                buildertom = null;
            });
            buildertom.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
                if (mContexttom instanceof TomHomeActivity)
                    ((TomHomeActivity) mContexttom).finishAffinity();
            });
            buildertom.show();
        }
    }

    public boolean isPermissionGrantedtom(String permissionName) {
        int tmptom = mContexttom.checkCallingOrSelfPermission(permissionName);
        return tmptom == PackageManager.PERMISSION_GRANTED;
    }

    private void openSettingstom() {
        Intent intenttom = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intenttom.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uritom = Uri.fromParts("package", mContexttom.getPackageName(), null);
        intenttom.setData(uritom);
        ((Activity) mContexttom).startActivityForResult(intenttom, 101);
    }
}