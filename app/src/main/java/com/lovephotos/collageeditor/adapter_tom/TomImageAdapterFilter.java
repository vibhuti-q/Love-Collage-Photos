package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.graphics_tom.TomCommandsPreset;

public class TomImageAdapterFilter extends BaseAdapter {

    int galleryItemBackgroundtom;
    private Context contexttom;
    private Integer[] mImageIdstom = TomCommandsPreset.ImageIdstom;

    public TomImageAdapterFilter(Context c) {
        contexttom = c;
        TypedArray attrtom = contexttom.obtainStyledAttributes(R.styleable.FiltersGallery);
        galleryItemBackgroundtom = attrtom.getResourceId(R.styleable.FiltersGallery_android_galleryItemBackground, 0);
        attrtom.recycle();
    }

    public int getCount() {
        return mImageIdstom.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageViewtom = new ImageView(contexttom);

        imageViewtom.setImageResource(mImageIdstom[position]);
        imageViewtom.setLayoutParams(new Gallery.LayoutParams(200, 200));
        imageViewtom.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewtom.setBackgroundResource(galleryItemBackgroundtom);

        return imageViewtom;
    }
}
