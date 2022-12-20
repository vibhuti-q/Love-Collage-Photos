package com.lovephotos.collageeditor.collage_tom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity;

public class TomImageAdapterStyle extends BaseAdapter {

    private Context contexttom;
    private final int[] mThumbIdstom;

    public TomImageAdapterStyle(Context context, int[] mThumbIds) {
        this.contexttom = context;
        this.mThumbIdstom = mThumbIds;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflatertom = (LayoutInflater) this.contexttom.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewtom = convertView;
        viewtom = new View(this.contexttom);
        viewtom = inflatertom.inflate(R.layout.item_gridview_tom, null);
        ImageView imageViewtom = (ImageView) viewtom.findViewById(R.id.img_moreappstom);
        imageViewtom.setImageResource(this.mThumbIdstom[position]);
        imageViewtom.getLayoutParams().height = (int) (((float) TomHomeActivity.CAMERA_WIDTHtom) / 3.0f);
        imageViewtom.getLayoutParams().width = (int) (((float) TomHomeActivity.CAMERA_WIDTHtom) / 3.4f);
        return viewtom;
    }

    public int getCount() {
        return this.mThumbIdstom.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
}
