package com.lovephotos.collageeditor.Utilities_tom;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class TomCommonUtils {

    private static final String APPLICATION_NAMEtom = "Love Collage Photo Editor";

    public static long lastClicked = 0L;

    public static boolean isValidClick() {
        if (SystemClock.elapsedRealtime() - lastClicked < 1000) {
            lastClicked = SystemClock.elapsedRealtime();
            return false;
        }
        lastClicked = SystemClock.elapsedRealtime();
        return true;
    }

    public static void scanMediaFile(Context mcontext, String path) {
        MediaScannerConnection.scanFile(mcontext, new String[]{path}, null, (path1, uri) -> {});
        mcontext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE));
    }

    public static void deleteFile(Context mcontext, String path) {
        File file = new File(path);
        String[] projection = {MediaStore.Images.Media._ID};
        String selection = MediaStore.Images.Media.DATA + " = ?";
        String[] selectionArgs = new String[]{file.getAbsolutePath()};
        Uri queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = mcontext.getContentResolver();
        Cursor c = contentResolver.query(queryUri, projection, selection, selectionArgs, null);
        if (c != null && c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
            Uri deleteUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            try {
                contentResolver.delete(deleteUri, null, null);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            c.close();
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap getBitmapFromView(View view, int defaultColor) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(defaultColor);
        view.draw(canvas);
        return bitmap;
    }

    public static String saveToGallerytom(Bitmap paramBitmap, String paramString, ContentResolver paramContentResolver, String type) {
        String strtom = "";
        try {
            File localFile1tom = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + APPLICATION_NAMEtom);
            if (!localFile1tom.exists())
                localFile1tom.mkdirs();
            File localFile2tom;
            FileOutputStream localFileOutputStreamtom;
            if (type.equals("png")) {
                localFile2tom = new File(localFile1tom + "/" + paramString + ".png");
                strtom = localFile1tom + "/" + paramString + ".png";
                localFileOutputStreamtom = new FileOutputStream(localFile2tom);
                paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStreamtom);
            } else {
                localFile2tom = new File(localFile1tom + "/" + paramString + ".jpg");
                strtom = localFile1tom + "/" + paramString + ".jpg";
                localFileOutputStreamtom = new FileOutputStream(localFile2tom);
                paramBitmap.compress(Bitmap.CompressFormat.JPEG, 60, localFileOutputStreamtom);
            }

            localFileOutputStreamtom.flush();
            localFileOutputStreamtom.close();

            if (type.equals("png")) {
                ContentValues localContentValuestom = new ContentValues();
                localContentValuestom.put("_display_name", paramString);
                localContentValuestom.put("mime_type", "image/png");
                localContentValuestom.put("orientation", 0);
                localContentValuestom.put("bucket_id", "Animal Photo Frames");
                localContentValuestom.put("bucket_display_name", paramString);
                localContentValuestom.put("_data", localFile2tom.getAbsolutePath());
                localContentValuestom.put("size", localFile2tom.length());
                localContentValuestom.put(MediaStore.MediaColumns.TITLE, paramString);
                localContentValuestom.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    localContentValuestom.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis());
                paramContentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValuestom);
            }
            return strtom;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return strtom;
    }

    public static String saveToGallerytom(Bitmap paramBitmap, String sfe, String paramString, ContentResolver paramContentResolver, String type) {
        String strtom = "";
        try {
            File localFile1tom = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + APPLICATION_NAMEtom);
            if (!localFile1tom.exists()) localFile1tom.mkdirs();

            File localFile2tom;
            FileOutputStream localFileOutputStreamJusi;
            if (type.equals("png")) {

                localFile2tom = new File(localFile1tom + "/" + paramString + ".png");
                localFileOutputStreamJusi = new FileOutputStream(localFile2tom);
                paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStreamJusi);

                strtom = localFile1tom + "/" + paramString + ".png";

            } else {

                localFile2tom = new File(localFile1tom + "/" + paramString + ".jpg");
                localFileOutputStreamJusi = new FileOutputStream(localFile2tom);
                paramBitmap.compress(Bitmap.CompressFormat.JPEG, 60, localFileOutputStreamJusi);
                strtom = localFile1tom + "/" + paramString + ".jpg";

            }

            localFileOutputStreamJusi.flush();
            localFileOutputStreamJusi.close();

            if (type.equals("png")) {
                ContentValues localContentValuesJusi = new ContentValues();
                localContentValuesJusi.put("_display_name", paramString);
                localContentValuesJusi.put("mime_type", "image/png");
                localContentValuesJusi.put("orientation", 0);
                localContentValuesJusi.put("bucket_id", "Animal Photo Frames");
                localContentValuesJusi.put("bucket_display_name", paramString);
                localContentValuesJusi.put("_data", localFile2tom.getAbsolutePath());
                localContentValuesJusi.put("size", localFile2tom.length());
                localContentValuesJusi.put(MediaStore.MediaColumns.TITLE, paramString);
                localContentValuesJusi.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) localContentValuesJusi.put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis());
                paramContentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValuesJusi);
            }
            return strtom;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return strtom;
    }

    public static void createRootDirectorytom() {
        try {
            File albumDirtom = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + APPLICATION_NAMEtom);
            if (!albumDirtom.exists())
                albumDirtom.mkdirs();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
