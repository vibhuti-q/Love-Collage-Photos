package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lovephotos.collageeditor.R;

public class TomFarmeImageAdapter extends BaseAdapter {

    private static int HEIGHT_SHOWtom = 130;
    private static int WIDTH_SHOWtom = 150;
    private Context mContexttom;
    public static Integer[] mThumbIdsframetom;

    public TomFarmeImageAdapter(Context paramContext) {

        Integer[] arrayOfIntegerframe = new Integer[57];
        arrayOfIntegerframe[0] = R.drawable.s_frame_default_preview_tom;
        arrayOfIntegerframe[1] = R.drawable.border_36_tom;
        arrayOfIntegerframe[2] = R.drawable.border_37_tom;
        arrayOfIntegerframe[3] = R.drawable.border_38_tom;
        arrayOfIntegerframe[4] = R.drawable.border_39_tom;
        arrayOfIntegerframe[5] = R.drawable.border_40_tom;
        arrayOfIntegerframe[6]= R.drawable.border_41_tom;
        arrayOfIntegerframe[7] = R.drawable.border_42_tom;
        arrayOfIntegerframe[8] = R.drawable.border_43_tom;
        arrayOfIntegerframe[9] = R.drawable.border_44_tom;
        arrayOfIntegerframe[10] = R.drawable.border_45_tom;
        arrayOfIntegerframe[11] = R.drawable.border_46_tom;
        arrayOfIntegerframe[12] = R.drawable.border_47_tom;
        arrayOfIntegerframe[13] = R.drawable.border_48_tom;
        arrayOfIntegerframe[14] = R.drawable.border_49_tom;
        arrayOfIntegerframe[15] = R.drawable.border_50_tom;
        arrayOfIntegerframe[16] = R.drawable.border_51_tom;
        arrayOfIntegerframe[17] = R.drawable.border_52_tom;
        arrayOfIntegerframe[18] = R.drawable.border_53_tom;
        arrayOfIntegerframe[19] = R.drawable.border_54_tom;
        arrayOfIntegerframe[20] = R.drawable.border_55_tom;
        arrayOfIntegerframe[21] = R.drawable.border_0_tom;
        arrayOfIntegerframe[22] = R.drawable.border_1_tom;
        arrayOfIntegerframe[23] = R.drawable.border_2_tom;
        arrayOfIntegerframe[24] = R.drawable.border_3_tom;
        arrayOfIntegerframe[25] = R.drawable.border_4_tom;
        arrayOfIntegerframe[26] = R.drawable.border_5_tom;
        arrayOfIntegerframe[27] = R.drawable.border_6_tom;
        arrayOfIntegerframe[28] = R.drawable.border_7_tom;
        arrayOfIntegerframe[29] = R.drawable.border_8_tom;
        arrayOfIntegerframe[30] = R.drawable.border_9_tom;
        arrayOfIntegerframe[31] = R.drawable.border_10_tom;
        arrayOfIntegerframe[32] = R.drawable.border_11_tom;
        arrayOfIntegerframe[33] = R.drawable.border_12_tom;
        arrayOfIntegerframe[34] = R.drawable.border_13_tom;
        arrayOfIntegerframe[35] = R.drawable.border_14_tom;
        arrayOfIntegerframe[36] = R.drawable.border_15_tom;
        arrayOfIntegerframe[37] = R.drawable.border_16_tom;
        arrayOfIntegerframe[38] = R.drawable.border_17_tom;
        arrayOfIntegerframe[39] = R.drawable.border_18_tom;
        arrayOfIntegerframe[40] = R.drawable.border_19_tom;
        arrayOfIntegerframe[41] = R.drawable.border_20_tom;
        arrayOfIntegerframe[42] = R.drawable.border_21_tom;
        arrayOfIntegerframe[43] = R.drawable.border_22_tom;
        arrayOfIntegerframe[44] = R.drawable.border_23_tom;
        arrayOfIntegerframe[45] = R.drawable.border_24_tom;
        arrayOfIntegerframe[46] = R.drawable.border_25_tom;
        arrayOfIntegerframe[47] = R.drawable.border_26_tom;
        arrayOfIntegerframe[48] = R.drawable.border_27_tom;
        arrayOfIntegerframe[49] = R.drawable.border_28_tom;
        arrayOfIntegerframe[50] = R.drawable.border_29_tom;
        arrayOfIntegerframe[51] = R.drawable.border_30_tom;
        arrayOfIntegerframe[52] = R.drawable.border_31_tom;
        arrayOfIntegerframe[53] = R.drawable.border_32_tom;
        arrayOfIntegerframe[54] = R.drawable.border_33_tom;
        arrayOfIntegerframe[55] = R.drawable.border_34_tom;
        arrayOfIntegerframe[56] = R.drawable.border_35_tom;

        this.mThumbIdsframetom = arrayOfIntegerframe;
        this.mContexttom = paramContext;
    }

    public int getCount()
    {
        return this.mThumbIdsframetom.length;
    }

    public Object getItem(int paramInt) {
        return null;
    }

    public long getItemId(int paramInt) {
        return this.mThumbIdsframetom[paramInt];
    }

    public View getView(int paramInt, View paramViewtom, ViewGroup paramViewGroup) {
        if (paramViewtom == null) {
            paramViewtom = LayoutInflater.from(this.mContexttom).inflate(
                    R.layout.frame_item_tom, null);
        }
        ImageView localImageViewtom = (ImageView) paramViewtom
                .findViewById(R.id.frame_imagetom);
        BitmapFactory.Options localOptionstom = new BitmapFactory.Options();
        localOptionstom.inSampleSize = 5;
        Bitmap localBitmaptom = BitmapFactory.decodeResource(
                this.mContexttom.getResources(),
                this.mThumbIdsframetom[paramInt], localOptionstom);
        int jtom = 0;
        int itom = 0;
        if (localBitmaptom.getHeight() > localBitmaptom.getWidth()) {
            jtom = HEIGHT_SHOWtom;
            itom = HEIGHT_SHOWtom * localBitmaptom.getWidth() / localBitmaptom.getHeight();
        }
        for (;;) {
            itom = WIDTH_SHOWtom;
            jtom = WIDTH_SHOWtom * localBitmaptom.getHeight() / localBitmaptom.getWidth();
            localImageViewtom.setImageBitmap(Bitmap.createScaledBitmap(
                    localBitmaptom, itom, jtom, false));
            return paramViewtom;
        }
    }
}
