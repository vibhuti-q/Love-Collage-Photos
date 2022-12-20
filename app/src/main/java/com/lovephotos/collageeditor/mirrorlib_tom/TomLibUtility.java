package com.lovephotos.collageeditor.mirrorlib_tom;

import android.os.Debug;
import com.lovephotos.collageeditor.R;

public class TomLibUtility {

    public static int MODE_MULTIPLYtom = 1;
    public static int MODE_NONEtom = -1;
    public static int MODE_OVERLAYtom = 2;
    public static int MODE_SCREENtom = 3;
    private static final String TAG = TomLibUtility.class.getSimpleName();

    public static int[] borderRestom = new int[]{
            MODE_NONEtom,
                    R.drawable.border_36_tom,
                    R.drawable.border_37_tom,
                    R.drawable.border_38_tom,
                    R.drawable.border_39_tom,
                    R.drawable.border_40_tom,
                    R.drawable.border_41_tom,
                    R.drawable.border_42_tom,
                    R.drawable.border_43_tom,
                    R.drawable.border_44_tom,
                    R.drawable.border_45_tom,
                    R.drawable.border_46_tom,
                    R.drawable.border_47_tom,
                    R.drawable.border_48_tom,
                    R.drawable.border_49_tom,
                    R.drawable.border_50_tom,
                    R.drawable.border_51_tom,
                    R.drawable.border_52_tom,
                    R.drawable.border_53_tom,
                    R.drawable.border_54_tom,
                    R.drawable.border_55_tom,
                    R.drawable.border_0_tom,
                    R.drawable.border_1_tom,
                    R.drawable.border_2_tom,
                    R.drawable.border_3_tom,
                    R.drawable.border_4_tom,
                    R.drawable.border_5_tom,
                    R.drawable.border_6_tom,
                    R.drawable.border_7_tom,
                    R.drawable.border_8_tom,
                    R.drawable.border_9_tom,
                    R.drawable.border_10_tom,
                    R.drawable.border_11_tom,
                    R.drawable.border_12_tom,
                    R.drawable.border_13_tom,
                    R.drawable.border_14_tom,
                    R.drawable.border_15_tom,
                    R.drawable.border_16_tom,
                    R.drawable.border_17_tom,
                    R.drawable.border_18_tom,
                    R.drawable.border_19_tom,
                    R.drawable.border_20_tom,
                    R.drawable.border_21_tom,
                    R.drawable.border_22_tom,
                    R.drawable.border_23_tom,
                    R.drawable.border_24_tom,
                    R.drawable.border_25_tom,
                    R.drawable.border_26_tom,
                    R.drawable.border_27_tom,
                    R.drawable.border_28_tom,
                    R.drawable.border_29_tom,
                    R.drawable.border_30_tom,
                    R.drawable.border_31_tom,
                    R.drawable.border_32_tom,
                    R.drawable.border_33_tom,
                    R.drawable.border_34_tom,
                    R.drawable.border_35_tom};

    public interface BuyProVersion {
        void proVersionCalled();
    }

    public interface ExcludeTabListener {
        void exclude();
    }

    public interface FooterVisibilityListener {
        void setVisibility();
    }

    public static double getLeftSizeOfMemorytom() {
        double totalSizetom = (double) Runtime.getRuntime().maxMemory();
        double heapAllocatedtom = (double) Runtime.getRuntime().totalMemory();
        return (totalSizetom - (heapAllocatedtom - (double) Runtime.getRuntime().freeMemory())) - (double) Debug.getNativeHeapAllocatedSize();
    }

}
