package com.lovephotos.collageeditor.collage_tom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.lovephotos.collageeditor.R;

import java.util.ArrayList;

public class TomGalleryAdapter extends BaseAdapter {

    private final int selectionLimit;
    ImageLoader imageLoadertom;
    private final ArrayList<TomCustomGallery> datatom = new ArrayList<>();
    private final LayoutInflater infaltertom;
    private boolean isActionMultiplePicktom;
    private final Context mContexttom;
    private int alreadySelected = 0;

    public TomGalleryAdapter(Context c, ImageLoader imageLoader, int selectionLimit) {
        this.infaltertom = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContexttom = c;
        this.imageLoadertom = imageLoader;
        this.selectionLimit = selectionLimit;
    }

    public int getCount() {
        return this.datatom.size();
    }

    public TomCustomGallery getItem(int position) {
        return this.datatom.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void setMultiplePick(boolean isMultiplePick) {
        this.isActionMultiplePicktom = isMultiplePick;
    }

    public void selectAll(boolean selection) {
        for (int itom = 0; itom < this.datatom.size(); itom++) {
            this.datatom.get(itom).isSeletedtom = selection;
        }
        notifyDataSetChanged();
    }

    public boolean isAllSelected() {
        for (int iv = 0; iv < this.datatom.size(); iv++) {
            if (!this.datatom.get(iv).isSeletedtom) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnySelected() {
        for (int itom = 0; itom < this.datatom.size(); itom++) {
            if (this.datatom.get(itom).isSeletedtom) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<TomCustomGallery> getSelected() {
        ArrayList<TomCustomGallery> dataT = new ArrayList<>();
        for (int itom = 0; itom < this.datatom.size(); itom++) {
            if (this.datatom.get(itom).isSeletedtom) {
                dataT.add(this.datatom.get(itom));
            }
        }
        return dataT;
    }

    public void addAlltom(ArrayList<TomCustomGallery> files) {
        try {
            this.datatom.clear();
            this.datatom.addAll(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void changeSelectiontom(View v, int position) {
        this.datatom.get(position).isSeletedtom = !this.datatom.get(position).isSeletedtom;
        if (!this.datatom.get(position).isSeletedtom) alreadySelected--;
        if (alreadySelected >= selectionLimit) {
            this.datatom.get(position).isSeletedtom = !this.datatom.get(position).isSeletedtom;
            Toast.makeText(mContexttom, "You can not select more than " + selectionLimit + " images.", Toast.LENGTH_SHORT).show();
        } else {
            if (this.datatom.get(position).isSeletedtom) alreadySelected++;
            ((ViewHoldertom) v.getTag()).imgQueueMultiSelectedtom.setSelected(this.datatom.get(position).isSeletedtom);
        }
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertViewtom, ViewGroup parent) {
        final ViewHoldertom holdertom;
        if (convertViewtom == null) {
            convertViewtom = this.infaltertom.inflate(R.layout.gallery_item_tom, null);
            holdertom = new ViewHoldertom();
            holdertom.imgQueuetom = convertViewtom.findViewById(R.id.imgQueuevtom);
            holdertom.imgQueueMultiSelectedtom = convertViewtom.findViewById(R.id.imgQueueMultiSelectedtom);
            if (this.isActionMultiplePicktom) {
                holdertom.imgQueueMultiSelectedtom.setVisibility(View.VISIBLE);
            } else {
                holdertom.imgQueueMultiSelectedtom.setVisibility(View.GONE);
            }
            convertViewtom.setTag(holdertom);
        } else {
            holdertom = (ViewHoldertom) convertViewtom.getTag();
        }
        holdertom.imgQueuetom.setTag(position);
        try {
            this.imageLoadertom.displayImage("file://" + this.datatom.get(position).sdcardPathtom, holdertom.imgQueuetom, new SimpleImageLoadingListener() {
                public void onLoadingStarted(String imageUri, View view) {
                    holdertom.imgQueuetom.setImageResource(R.drawable.no_media_tom);
                    super.onLoadingStarted(imageUri, view);
                }
            });
            if (this.isActionMultiplePicktom) {
                holdertom.imgQueueMultiSelectedtom.setSelected(this.datatom.get(position).isSeletedtom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertViewtom;
    }

    public void clearCachetom() {
        this.imageLoadertom.clearDiscCache();
        this.imageLoadertom.clearMemoryCache();
    }

    public void clearv() {
        this.datatom.clear();
        notifyDataSetChanged();
    }

    public static class ViewHoldertom {
        ImageView imgQueuetom;
        ImageView imgQueueMultiSelectedtom;
    }
}
