package com.lovephotos.collageeditor.mirrorlib_tom;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TomBitmapResizer {

    public static Bitmap decodeFile(File f, int requiredSize) {
        try {
            Options o = new Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            int REQUIRED_SIZE = requiredSize;
            int width_tmptom = o.outWidth;
            int height_tmptom = o.outHeight;
            int scaletom = 1;
            while (width_tmptom / 2 >= REQUIRED_SIZE && height_tmptom / 2 >= REQUIRED_SIZE) {
                width_tmptom /= 2;
                height_tmptom /= 2;
                scaletom *= 2;
            }
            Options o2tom = new Options();
            o2tom.inSampleSize = scaletom;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2tom);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Point decodeFileSizetom(File f, int requiredSize) {
        try {
            Options otom = new Options();
            otom.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, otom);
            int REQUIRED_SIZEtom = requiredSize;
            int width_tmptom = otom.outWidth;
            int height_tmptom = otom.outHeight;
            int scaletom = 1;
            while (Math.max(width_tmptom, height_tmptom) / 2 > REQUIRED_SIZEtom) {
                width_tmptom /= 2;
                height_tmptom /= 2;
                scaletom *= 2;
            }
            if (scaletom == 1) {
                return new Point(-1, -1);
            }
            return new Point(width_tmptom, height_tmptom);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Point getFileSize(File f, int requiredSize) {
        try {
            Options o = new Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            return new Point(o.outWidth, o.outHeight);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Bitmap decodeBitmapFromFiletom(String selectedImagePath, int MAX_SIZE) {
        int orientationtom = 0;
        try {
            orientationtom = new ExifInterface(selectedImagePath).getAttributeInt("Orientation", 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap localBitmaptom = decodeFiletom(selectedImagePath, MAX_SIZE);
        if (localBitmaptom == null) {
            return null;
        }
        Bitmap graySourceBtmtom = rotateBitmaptom(localBitmaptom, orientationtom);
        if (graySourceBtmtom == null || VERSION.SDK_INT >= 13) {
            return graySourceBtmtom;
        }
        Bitmap temptom = graySourceBtmtom.copy(Config.ARGB_8888, true);
        if (graySourceBtmtom != temptom) {
            graySourceBtmtom.recycle();
        }
        return temptom;
    }

    public static Bitmap rotateBitmaptom(Bitmap bitmap, int orientation) {
        try {
            Bitmap bmRotatedtomtom;
            Matrix matrixtom = new Matrix();
            switch (orientation) {
                case 2:
                    matrixtom.setScale(-1.0f, 1.0f);
                case 3:
                    matrixtom.setRotate(180.0f);
                case 4:
                    matrixtom.setRotate(180.0f);
                    matrixtom.postScale(-1.0f, 1.0f);
                case 5:
                    matrixtom.setRotate(90.0f);
                    matrixtom.postScale(-1.0f, 1.0f);
                    bmRotatedtomtom = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
                    if (bmRotatedtomtom != bitmap) {
                        bitmap.recycle();
                    }
                    return bmRotatedtomtom;
                case 6:
                    matrixtom.setRotate(90.0f);
                    bmRotatedtomtom = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
                    if (bmRotatedtomtom != bitmap) {
                        bitmap.recycle();
                    }
                    return bmRotatedtomtom;
                case 7:
                    matrixtom.setRotate(-90.0f);
                    matrixtom.postScale(-1.0f, 1.0f);
                    bmRotatedtomtom = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
                    if (bmRotatedtomtom != bitmap) {
                        bitmap.recycle();
                    }
                    return bmRotatedtomtom;
                case 8:
                    matrixtom.setRotate(-90.0f);
                    bmRotatedtomtom = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
                    if (bmRotatedtomtom != bitmap) {
                        bitmap.recycle();
                    }
                    return bmRotatedtomtom;
                default:
                    return bitmap;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Bitmap decodeFiletom(String selectedImagePath, int MAX_SIZE) {
        Options o = new Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, o);
        int scaletom = 1;
        int width_tmptom = o.outWidth;
        int height_tmptom = o.outHeight;
        while (Math.max(width_tmptom, height_tmptom) / 2 > MAX_SIZE) {
            width_tmptom /= 2;
            height_tmptom /= 2;
            scaletom *= 2;
        }
        Options o2 = new Options();
        o2.inSampleSize = scaletom;
        Bitmap b = BitmapFactory.decodeFile(selectedImagePath, o2);
        if (b != null) {
            Log.e("decoded file height", String.valueOf(b.getHeight()));
            Log.e("decoded file width", String.valueOf(b.getWidth()));
        }
        return b;
    }
}
