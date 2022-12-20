package com.lovephotos.collageeditor.custom_tom

import android.content.Context
import android.os.Bundle
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAlertDialog
import com.lovephotos.collageeditor.databinding.DialogPermissionConsentExtraTomBinding
import com.lovephotos.collageeditor.model.ExtraPermissionInstructionModel

class TomExtraPermissionInstructionDialog(mContext: Context, private val modelTvstom: ExtraPermissionInstructionModel) :
    TomBaseAlertDialog<DialogPermissionConsentExtraTomBinding>(DialogPermissionConsentExtraTomBinding::inflate, mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtom.ivInstructiontom.setImageResource(modelTvstom.icon)
        bindingtom.btnConfirmtom.setOnClickListener {
            dismiss()
            modelTvstom.listener.onDialogOKClickedtom(modelTvstom.endPoint)
        }
    }
}
