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

public class TomImageAdapter extends BaseAdapter {

    public static Integer[] mThumbIdstom;
    private static int HEIGHT_SHOWtom = 130;
    private static int WIDTH_SHOWtom = 150;
    private Context mContexttom;

    public TomImageAdapter(Context paramContext) {

        Integer[] arrayOfIntegertom = new Integer[87];
        arrayOfIntegertom[0] = R.drawable.s_frame_default_preview_tom;
        arrayOfIntegertom[1] = R.drawable.s_frame_0_tom;
        arrayOfIntegertom[2] = R.drawable.s_frame_1_tom;
        arrayOfIntegertom[3] = R.drawable.s_frame_2_tom;
        arrayOfIntegertom[4] = R.drawable.s_frame_3_tom;
        arrayOfIntegertom[5] = R.drawable.s_frame_4_tom;
        arrayOfIntegertom[6] = R.drawable.s_frame_5_tom;
        arrayOfIntegertom[7] = R.drawable.s_frame_6_tom;
        arrayOfIntegertom[8] = R.drawable.s_frame_7_tom;
        arrayOfIntegertom[9] = R.drawable.s_frame_8_tom;
        arrayOfIntegertom[10] = R.drawable.s_frame_9_tom;
        arrayOfIntegertom[11] = R.drawable.s_frame_10_tom;
        arrayOfIntegertom[12] = R.drawable.s_frame_11_tom;
        arrayOfIntegertom[13] = R.drawable.s_frame_12_tom;
        arrayOfIntegertom[14] = R.drawable.s_frame_13_tom;
        arrayOfIntegertom[15] = R.drawable.s_frame_14_tom;
        arrayOfIntegertom[16] = R.drawable.s_frame_15_tom;
        arrayOfIntegertom[17] = R.drawable.s_frame_16_tom;
        arrayOfIntegertom[18] = R.drawable.s_frame_17_tom;
        arrayOfIntegertom[19] = R.drawable.s_frame_18_tom;
        arrayOfIntegertom[20] = R.drawable.s_frame_19_tom;
        arrayOfIntegertom[21] = R.drawable.s_frame_20_tom;
        arrayOfIntegertom[22] = R.drawable.s_frame_21_tom;
        arrayOfIntegertom[23] = R.drawable.s_frame_22_tom;
        arrayOfIntegertom[24] = R.drawable.s_frame_23_tom;
        arrayOfIntegertom[25] = R.drawable.s_frame_24_tom;
        arrayOfIntegertom[26] = R.drawable.s_frame_25_tom;
        arrayOfIntegertom[27] = R.drawable.s_frame_26_tom;
        arrayOfIntegertom[28] = R.drawable.s_frame_27_tom;
        arrayOfIntegertom[29] = R.drawable.s_frame_28_tom;
        arrayOfIntegertom[30] = R.drawable.s_frame_29_tom;
        arrayOfIntegertom[31] = R.drawable.border_36_tom;
        arrayOfIntegertom[32] = R.drawable.border_37_tom;
        arrayOfIntegertom[33] = R.drawable.border_38_tom;
        arrayOfIntegertom[34] = R.drawable.border_39_tom;
        arrayOfIntegertom[35] = R.drawable.border_40_tom;
        arrayOfIntegertom[36] = R.drawable.border_41_tom;
        arrayOfIntegertom[37] = R.drawable.border_42_tom;
        arrayOfIntegertom[38] = R.drawable.border_43_tom;
        arrayOfIntegertom[39] = R.drawable.border_44_tom;
        arrayOfIntegertom[40] = R.drawable.border_45_tom;
        arrayOfIntegertom[41] = R.drawable.border_46_tom;
        arrayOfIntegertom[42] = R.drawable.border_47_tom;
        arrayOfIntegertom[43] = R.drawable.border_48_tom;
        arrayOfIntegertom[44] = R.drawable.border_49_tom;
        arrayOfIntegertom[45] = R.drawable.border_50_tom;
        arrayOfIntegertom[46] = R.drawable.border_51_tom;
        arrayOfIntegertom[47] = R.drawable.border_52_tom;
        arrayOfIntegertom[48] = R.drawable.border_53_tom;
        arrayOfIntegertom[49] = R.drawable.border_54_tom;
        arrayOfIntegertom[50] = R.drawable.border_55_tom;
        arrayOfIntegertom[51] = R.drawable.border_0_tom;
        arrayOfIntegertom[52] = R.drawable.border_1_tom;
        arrayOfIntegertom[53] = R.drawable.border_2_tom;
        arrayOfIntegertom[54] = R.drawable.border_3_tom;
        arrayOfIntegertom[55] = R.drawable.border_4_tom;
        arrayOfIntegertom[56] = R.drawable.border_5_tom;
        arrayOfIntegertom[57] = R.drawable.border_6_tom;
        arrayOfIntegertom[58] = R.drawable.border_7_tom;
        arrayOfIntegertom[59] = R.drawable.border_8_tom;
        arrayOfIntegertom[60] = R.drawable.border_9_tom;
        arrayOfIntegertom[61] = R.drawable.border_10_tom;
        arrayOfIntegertom[62] = R.drawable.border_11_tom;
        arrayOfIntegertom[63] = R.drawable.border_12_tom;
        arrayOfIntegertom[64] = R.drawable.border_13_tom;
        arrayOfIntegertom[65] = R.drawable.border_14_tom;
        arrayOfIntegertom[66] = R.drawable.border_15_tom;
        arrayOfIntegertom[67] = R.drawable.border_16_tom;
        arrayOfIntegertom[68] = R.drawable.border_17_tom;
        arrayOfIntegertom[69] = R.drawable.border_18_tom;
        arrayOfIntegertom[70] = R.drawable.border_19_tom;
        arrayOfIntegertom[71] = R.drawable.border_20_tom;
        arrayOfIntegertom[72] = R.drawable.border_21_tom;
        arrayOfIntegertom[73] = R.drawable.border_22_tom;
        arrayOfIntegertom[74] = R.drawable.border_23_tom;
        arrayOfIntegertom[75] = R.drawable.border_24_tom;
        arrayOfIntegertom[76] = R.drawable.border_25_tom;
        arrayOfIntegertom[77] = R.drawable.border_26_tom;
        arrayOfIntegertom[78] = R.drawable.border_27_tom;
        arrayOfIntegertom[79] = R.drawable.border_28_tom;
        arrayOfIntegertom[80] = R.drawable.border_29_tom;
        arrayOfIntegertom[81] = R.drawable.border_30_tom;
        arrayOfIntegertom[82] = R.drawable.border_31_tom;
        arrayOfIntegertom[83] = R.drawable.border_32_tom;
        arrayOfIntegertom[84] = R.drawable.border_33_tom;
        arrayOfIntegertom[85] = R.drawable.border_34_tom;
        arrayOfIntegertom[86] = R.drawable.border_35_tom;

        this.mThumbIdstom = arrayOfIntegertom;
        this.mContexttom = paramContext;
    }

    public int getCount() {
        return this.mThumbIdstom.length;
    }

    public Object getItem(int paramInt) {
        return null;
    }

    public long getItemId(int paramInt) {
        return this.mThumbIdstom[paramInt];
    }

    public View getView(int paramInt, View paramViewtom, ViewGroup paramViewGroup) {
        if (paramViewtom == null)
            paramViewtom = LayoutInflater.from(this.mContexttom).inflate(R.layout.frame_item_tom, null);

        ImageView localImageViewtom = paramViewtom.findViewById(R.id.frame_imagetom);
        BitmapFactory.Options localOptionstom = new BitmapFactory.Options();
        localOptionstom.inSampleSize = 5;
        Bitmap localBitmaptom = BitmapFactory.decodeResource(
                this.mContexttom.getResources(),
                this.mThumbIdstom[paramInt], localOptionstom);
        int jtom;
        int itom;
        if (localBitmaptom.getHeight() > localBitmaptom.getWidth()) {
            jtom = HEIGHT_SHOWtom;
            itom = HEIGHT_SHOWtom * localBitmaptom.getWidth() / localBitmaptom.getHeight();
        }
        for (; ; ) {
            itom = WIDTH_SHOWtom;
            jtom = WIDTH_SHOWtom * localBitmaptom.getHeight() / localBitmaptom.getWidth();
            localImageViewtom.setImageBitmap(Bitmap.createScaledBitmap(localBitmaptom, itom, jtom, false));
            return paramViewtom;
        }
    }
}
