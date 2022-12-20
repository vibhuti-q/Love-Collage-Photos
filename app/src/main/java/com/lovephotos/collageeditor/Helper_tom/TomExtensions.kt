package com.lovephotos.collageeditor.Helper_tom

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.lovephotos.collageeditor.R

//region View Visibility Method
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.visible() {
    this.visibility = View.VISIBLE
}

fun ViewGroup.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.invisible() {
    this.visibility = View.INVISIBLE
}
//endregion

//region Logger Methods
fun logD(TAG: String, message: String) {
    Log.d(TAG, message)
}

fun logE(TAG: String, message: String) {
    Log.e(TAG, message)
}

fun logI(TAG: String, message: String) {
    Log.i(TAG, message)
}

fun logV(TAG: String, message: String) {
    Log.v(TAG, message)
}

fun logW(TAG: String, message: String) {
    Log.w(TAG, message)
}
//endregion

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun ViewGroup.inflate(layoutRes: Int): ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, false)
}

fun ViewGroup.inflate(mContext: Context, layoutRes: Int): ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutRes, this, false)
}

//region Image Binding Methods
@BindingAdapter("bindImage")
fun ImageView.setImageUsingUrl(imageUrl: String) {
    Glide
        .with(context)
        .load(imageUrl)
        .error(R.drawable.ic_not_found_tom)
        .into(this)
}

@BindingAdapter("bindImage")
fun ImageView.setImageUsingId(imageUrl: Int) {
    Glide
        .with(context)
        .load(imageUrl)
        .error(R.drawable.ic_not_found_tom)
        .into(this)
}

@BindingAdapter("bindCircleImage")
fun ImageView.setCircleImageUsingUrl(imageUrl: String) {
    Glide
        .with(context)
        .load(imageUrl)
        .error(R.drawable.ic_not_found_tom)
        .centerInside()
        .into(this)
}
//endregion