package com.lovephotos.collageeditor.mirrorlib_tom;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;

public class TomUtility {

    public static float DEFAULT_HUE_VALtom;

    static {
        DEFAULT_HUE_VALtom = 120.0f;
    }

    @SuppressLint("WrongConstant")
    public static boolean getAmazonMarket(Context context) {
        int AMAZON_MARKETtom = 0;
        try {
            AMAZON_MARKETtom = context.getPackageManager().getApplicationInfo(context.getPackageName(), 1 << 7).metaData.getInt("amazon_market");
            if (AMAZON_MARKETtom < 0) {
                AMAZON_MARKETtom = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (AMAZON_MARKETtom == 1) {
            return true;
        }
        return false;
    }

    public static boolean isSDCardAvialable() {
        String statetom = Environment.getExternalStorageState();
        if ("mounted".equals(statetom)) {
            boolean mExternalStorageAvailable = true;
            return true;
        } else if ("mounted_ro".equals(statetom)) {
            return false;
        } else {
            int mExternalStorageAvailable2 = 0;
            return false;
        }
    }

    public static boolean isPackageProEx(Context context) {
        return context.getPackageName().toLowerCase().contains("pro");
    }

    public static long getFreeMemoryEx(Context context) {
        int heapMemory = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() * AccessibilityNodeInfoCompat.ACTION_DISMISS;
        return (((long) heapMemory) - Debug.getNativeHeapAllocatedSize()) - Runtime.getRuntime().totalMemory();
    }

    public static double getLeftSizeOfMemorytom() {
        double totalSizetom = (double) Runtime.getRuntime().maxMemory();
        double heapAllocatedtom = (double) Runtime.getRuntime().totalMemory();
        double nativeAllocatedtom = (double) Debug.getNativeHeapAllocatedSize();
        double usedMemorytom = heapAllocatedtom - (double) Runtime.getRuntime().freeMemory();
        Log.e("heapAllocated", " " + Runtime.getRuntime().totalMemory());
        Log.e("nativeAllocated", " " + Debug.getNativeHeapAllocatedSize());
        Log.e("getNativeHeapFreeSize", " " + Debug.getNativeHeapFreeSize());
        Log.e("usedMemory", " " + usedMemorytom);
        Log.e("old free memory ", " " + ((totalSizetom - heapAllocatedtom) - nativeAllocatedtom));
        return (totalSizetom - usedMemorytom) - nativeAllocatedtom;
    }

    public static void pssFreeMemoryJusi() {
        MemoryInfo memoryInfotom = new MemoryInfo();
        Debug.getMemoryInfo(memoryInfotom);
        String memMessage = String.format("Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB", new Object[]{Double.valueOf(((double) memoryInfotom.getTotalPss()) / 1024.0d), Double.valueOf(((double) memoryInfotom.getTotalPrivateDirty()) / 1024.0d), Double.valueOf(((double) memoryInfotom.getTotalSharedDirty()) / 1024.0d)});
        Log.e("new free memory", " " + (Runtime.getRuntime().maxMemory() - ((long) (memoryInfotom.getTotalPss() * AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT))));
    }

    public static int maxSizeForLoadtom() {
        int maxSizetom = (int) Math.sqrt(getLeftSizeOfMemorytom() / 80.0d);
        if (maxSizetom > 1080) {
            return 1080;
        }
        return maxSizetom;
    }

    public static int maxSizeForSavetom() {
        int maxSizetom = (int) Math.sqrt(getLeftSizeOfMemorytom() / 40.0d);
        if (maxSizetom > 1080) {
            return 1080;
        }
        return maxSizetom;
    }

    public static void logFreeMemorytom() {
        Log.e("free memory", String.valueOf(getLeftSizeOfMemorytom()));
    }

    public static long getFreeMemoryEx() {
        return Runtime.getRuntime().maxMemory() - Debug.getNativeHeapAllocatedSize();
    }
}
