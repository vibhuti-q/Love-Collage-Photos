package com.lovephotos.collageeditor.sticker_tom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.lovephotos.collageeditor.R;

public class TomGalleryAdapter extends RecyclerView.Adapter<TomGalleryAdapter.ViewHolder> implements View.OnClickListener {

    private int[] mResIdstom;

    public TomGalleryAdapter(int[] resIds) {
        this.mResIdstom = resIds;
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int resId);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View viewtom = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gallery_sticker_tom, viewGroup, false);
        ViewHolder vhtom = new ViewHolder(viewtom);
        viewtom.setOnClickListener(this);
        return vhtom;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(mResIdstom[position]);
        viewHolder.itemView.setTag(mResIdstom[position]);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mResIdstom.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_stickertom);
        }
    }
}
