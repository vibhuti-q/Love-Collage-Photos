package com.lovephotos.collageeditor.custom_tom

import android.content.Context
import android.os.Bundle
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAlertDialog
import com.lovephotos.collageeditor.databinding.DialogConfirmationBinding
import com.lovephotos.collageeditor.listener_tom.TomCustomConfirmationClickListener
import com.lovephotos.collageeditor.model.CustomConfirmationDialogModel

class TomCustomConfirmationDialog(mContext: Context, val modeltom: CustomConfirmationDialogModel) : TomBaseAlertDialog<DialogConfirmationBinding>
    (DialogConfirmationBinding::inflate, mContext) {

    private var listenertom: TomCustomConfirmationClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingtom.tvDialogDescriptiontom.text = modeltom.dialogDescriptiontom
        bindingtom.tvDialogTitletom.text = modeltom.dialogTitletom

        bindingtom.btnYestom.setOnClickListener {
            dismiss()
            listenertom = modeltom.listenertom ?: mContext as TomCustomConfirmationClickListener
            listenertom!!.onPositiveButtonClicktom(modeltom.dialogEndPointtom)
        }

        bindingtom.btnNotom.setOnClickListener {
            dismiss()
            listenertom = modeltom.listenertom ?: mContext as TomCustomConfirmationClickListener
            listenertom!!.onNegativeButtonClickJusi(modeltom.dialogEndPointtom)
        }
    }
}