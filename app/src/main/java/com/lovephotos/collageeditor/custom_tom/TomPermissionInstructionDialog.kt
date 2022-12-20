package com.lovephotos.collageeditor.custom_tom

import android.content.Context
import android.os.Bundle
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAdapter
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAlertDialog
import com.lovephotos.collageeditor.databinding.DialogPermissionConsentTomBinding
import com.lovephotos.collageeditor.listener_tom.TomCustomDialogClickListener
import com.lovephotos.collageeditor.model.PermissionInstructionModel

class TomPermissionInstructionDialog(
    mContext: Context, private val instructiontom: ArrayList<PermissionInstructionModel>, val listenerTvstom: TomCustomDialogClickListener
) :
    TomBaseAlertDialog<DialogPermissionConsentTomBinding>(DialogPermissionConsentTomBinding::inflate, mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adaptertom = TomBaseAdapter(mContext, instructiontom, R.layout.item_permission_instruction_tom)
        bindingtom.rvInstructionstom.adapter = adaptertom

        bindingtom.btnConfirmtom.setOnClickListener {
            dismiss()
            listenerTvstom.onDialogOKClickedtom("instructions")
        }
    }
}