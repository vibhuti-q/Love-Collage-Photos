package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Html
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.stpnfncmakads_tom.baseclass_tom.TomBaseActivityAll
import kotlinx.android.synthetic.main.activity_maintenance_tom.*

class TomMaintenanceActivity : TomBaseActivityAll() {

    var sharedpreferencestom: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_tom)

        val messagetom = intent.getStringExtra("maintainceMessage")

        if (!messagetom.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                descriptiontom.text = Html.fromHtml(messagetom, Html.FROM_HTML_MODE_LEGACY)
            } else
                descriptiontom.text = messagetom
        }

        btnContacttom.setOnClickListener {
            finish()
        }

    }
}