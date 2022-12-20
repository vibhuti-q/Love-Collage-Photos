package com.lovephotos.collageeditor.baseclass_tom

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lovephotos.collageeditor.BR
import com.lovephotos.collageeditor.Helper_tom.inflate
import com.lovephotos.collageeditor.databinding.ItemMyCreationListBinding
import com.lovephotos.collageeditor.listener_tom.TomItemClickListener
import java.util.*

class TomBaseAdapter() : RecyclerView.Adapter<TomBaseAdapter.MyViewHoldertom>() {

    private lateinit var mContext: Context
    private lateinit var itemstom: ArrayList<*>
    private var layoutIdtom: Int = 0
    private lateinit var itemClickListenerTvstom: TomItemClickListener

    constructor(mContext: Context, items: ArrayList<*>, layoutId: Int, itemClickListenerTvs: TomItemClickListener) : this() {
        this.mContext = mContext
        this.itemstom = items
        this.layoutIdtom = layoutId
        this.itemClickListenerTvstom = itemClickListenerTvs
    }

    constructor(mContext: Context, items: ArrayList<*>, layoutId: Int) : this() {
        this.mContext = mContext
        this.itemstom = items
        this.layoutIdtom = layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHoldertom(parent.inflate(mContext, layoutIdtom))

    override fun onBindViewHolder(holder: MyViewHoldertom, position: Int) = holder.setBinding(itemstom[position])

    override fun getItemCount() = itemstom.size

    @SuppressLint("NotifyDataSetChanged")
    fun checkedItem(itemPosition: Int) {
        val item = itemstom[itemPosition]

        notifyDataSetChanged()
    }

    inner class MyViewHoldertom(private var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setBinding(item: Any) {
            binding.setVariable(BR.data, item)
            binding.executePendingBindings()

            if (binding is ItemMyCreationListBinding) {

                (binding as ItemMyCreationListBinding).rootLayouttom.setOnClickListener {
                    itemClickListenerTvstom.onItemClicktom(layoutPosition)
                }
            }
        }
    }
}