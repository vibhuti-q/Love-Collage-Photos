package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.lovephotos.collageeditor.R;

public final class TomColorAdaptertom extends BaseAdapter {

    public String[] colorstom;
    private final Context mContexttom;

    public TomColorAdaptertom(Context context) {
        this.mContexttom = context;
        this.colorstom = context.getResources().getStringArray(R.array.colorArray);
    }

    public final int getCount() {
        return colorstom.length;
    }

    public final Object getItem(int i) {
        return i;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageViewtom = new ImageView(mContexttom);
        imageViewtom.setLayoutParams(new LayoutParams(100, 100));
        imageViewtom.setScaleType(ScaleType.FIT_XY);
        imageViewtom.setBackgroundColor(Color.parseColor(colorstom[i]));
        return imageViewtom;
    }
}
