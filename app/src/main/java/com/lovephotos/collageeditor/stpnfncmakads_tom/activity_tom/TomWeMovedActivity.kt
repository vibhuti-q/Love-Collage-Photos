package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lovephotos.collageeditor.R
import kotlinx.android.synthetic.main.activity_we_moved_tom.*

class TomWeMovedActivity : AppCompatActivity() {
    var sharedpreferencestom: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_we_moved_tom)
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                descriptiontom.setText(Html.fromHtml(intent.getStringExtra("movedDescription"), Html.FROM_HTML_MODE_LEGACY))
            } else {
                descriptiontom.setText(Html.fromHtml(intent.getStringExtra("movedDescription")))
            }
        }catch (e: Exception){}

        btnChecktom.setOnClickListener {
            val urltom = intent.getStringExtra("movedURL")
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urltom)))
            } catch (anfe: ActivityNotFoundException) {
                Toast.makeText(this@TomWeMovedActivity, "Url not found...", Toast.LENGTH_SHORT).show()
            }
        }

    }
}