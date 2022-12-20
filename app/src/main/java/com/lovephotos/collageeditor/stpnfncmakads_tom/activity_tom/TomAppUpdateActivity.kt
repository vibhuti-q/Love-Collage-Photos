package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lovephotos.collageeditor.R
import kotlinx.android.synthetic.main.activity_app_update_tom.*

class TomAppUpdateActivity : AppCompatActivity() {
    var sharedpreferencestom: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_update_tom)

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                update_descriptiontom.text = Html.fromHtml(intent.getStringExtra("versionMessage"), Html.FROM_HTML_MODE_LEGACY)
            } else {
                update_descriptiontom.text = Html.fromHtml(intent.getStringExtra("versionMessage"))
            }
        }catch (e : Exception){}


        btn_updatetom.setOnClickListener { v: View? ->
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

    }
}