package com.lovephotos.collageeditor.adapter_tom;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.Utilities_tom.TomImgItem;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class TomViewAdapter extends PagerAdapter {

    Context contexttom;
    LayoutInflater mLayoutInflatertom;
    public static ArrayList<TomImgItem> filepathtom;

    public TomViewAdapter(Context context, ArrayList<TomImgItem> fpath) {
        this.contexttom = context;
        filepathtom = fpath;
        mLayoutInflatertom = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.filepathtom.size();
    }

    public Object getItem(int position) {
        return filepathtom.get(position);
    }

    public long getItemId(int position) {
        return filepathtom.indexOf(getItem(position));
    }

    @Override
    public boolean isViewFromObject(View viewtom, Object object) {
        return viewtom == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemViewtom = mLayoutInflatertom.inflate(R.layout.creation_item_tom, container, false);
        ImageView imageViewtom = (ImageView) itemViewtom.findViewById(R.id.Show_Imagetom);

        Picasso.with(contexttom).load(new File(filepathtom.get(position).getAb_txt_imgtom()))
                .placeholder(R.drawable.viewpagerload_tom).error(R.drawable.viewpagerload_tom)
                .into(imageViewtom);

        container.addView(itemViewtom);
        return itemViewtom;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
