package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lovephotos.collageeditor.R;

public final class TomFontAdaptertom extends BaseAdapter {

    public static String[] f73btom;
    public TextView f74atom;
    Context f75ctom;
    public Typeface f76dtom;

    public TomFontAdaptertom(Context context) {
        this.f75ctom = context;
        f73btom = context.getResources().getStringArray(R.array.FontFamily);
    }

    public final int getCount() {
        return f73btom.length;
    }

    public final Object getItem(int i) {
        return null;
    }

    public final long getItemId(int i) {
        return 0;
    }

    public final View getView(int i, View viewtom, ViewGroup viewGroup) {
        if (viewtom == null) {
            this.f74atom = new TextView(this.f75ctom);
        } else {
            this.f74atom = (TextView) viewtom;
        }
        this.f76dtom = Typeface.createFromAsset(this.f75ctom.getAssets(), f73btom[i]);
        this.f74atom.setTypeface(this.f76dtom);
        this.f74atom.setText("Aa");
        this.f74atom.setPadding(6, 6, 6, 6);
        this.f74atom.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f74atom.setBackgroundColor(-1);
        this.f74atom.setGravity(17);
        this.f74atom.setTextSize(2, 20.0f);
        this.f74atom.setWidth(100);
        this.f74atom.setHeight(100);
        return this.f74atom;
    }
}
