package com.lovephotos.collageeditor.baseclass_tom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.viewbinding.ViewBinding
import com.lovephotos.collageeditor.R

abstract class TomBaseAlertDialog<B : ViewBinding>(val bindView: (LayoutInflater) -> B, val mContext: Context) :
    Dialog(mContext, R.style.WideDialog) {

    val bindingtom: B by lazy { bindView(layoutInflater) }

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(bindingtom.root)
    }
}