package com.lovephotos.collageeditor.stpnfncmakads_tom.dialogs_tom

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import com.lovephotos.collageeditor.R
import kotlinx.android.synthetic.main.layout_loading_dialog_tom.*


class TomAdShowingDialog(var mContexttom: Context, private var messagetom: String?) : Dialog(mContexttom) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading_dialog_tom)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvMessagetom.text = Html.fromHtml(messagetom, Html.FROM_HTML_MODE_LEGACY)
        } else
            tvMessagetom.text = messagetom
    }

}
