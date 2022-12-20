package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.lovephotos.collageeditor.R;

public final class TomImageAdapter_Text extends BaseAdapter {

    private Context f77atom;
    private Integer[] f78btom;

    public TomImageAdapter_Text(Context context) {
        this.f78btom = new Integer[]{Integer.valueOf(R.drawable.bg_pattern_01_tom),
                Integer.valueOf(R.drawable.bg_pattern_02_tom),
                Integer.valueOf(R.drawable.bg_pattern_03_tom),
                Integer.valueOf(R.drawable.bg_pattern_04_tom),
                Integer.valueOf(R.drawable.bg_pattern_05_tom),
                Integer.valueOf(R.drawable.bg_pattern_06_tom),
                Integer.valueOf(R.drawable.bg_pattern_07_tom),
                Integer.valueOf(R.drawable.bg_pattern_08_tom),
                Integer.valueOf(R.drawable.bg_pattern_09_tom),
                Integer.valueOf(R.drawable.bg_pattern_010_tom),
                Integer.valueOf(R.drawable.bg_pattern_011_tom),
                Integer.valueOf(R.drawable.bg_pattern_012_tom),
                Integer.valueOf(R.drawable.bg_pattern_013_tom),
                Integer.valueOf(R.drawable.bg_pattern_014_tom),
                Integer.valueOf(R.drawable.bg_pattern_015_tom),
                Integer.valueOf(R.drawable.bg_pattern_016_tom)};
        this.f77atom = context;
    }

    public final int getCount() {
        return this.f78btom.length;
    }

    public final Object getItem(int i) {
        return i;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getItemViewType(int i) {
        return 0;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageViewtom = new ImageView(this.f77atom);
        imageViewtom.setImageResource(this.f78btom[i]);
        imageViewtom.setLayoutParams(new LayoutParams(100, 100));
        imageViewtom.setBackgroundColor(17170447);
        imageViewtom.setScaleType(ScaleType.FIT_XY);
        return imageViewtom;
    }

    public final int getViewTypeCount() {
        return 0;
    }

    public final boolean hasStableIds() {
        return false;
    }

    public final boolean isEmpty() {
        return false;
    }

    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
