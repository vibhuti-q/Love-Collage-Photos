package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import kotlinx.android.synthetic.main.banner_ad_common_tom.*

class TomDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_tom)
        val btnCreateVideotom = findViewById<AppCompatButton>(R.id.btnCreateVideotom)
        val myPreferenceManagertom = TomMyPreferenceManager(this@TomDashboardActivity)
        TomAdsGlobalClassEveryTime().showBannerAdtom(this, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {}
            override fun setSuccessJusi() {
            }

            override fun setFailedJusi() {
                super.setFailedJusi()
                adContainertom.gone()
            }
        }, myPreferenceManagertom.getGBannerIDtom(), adViewtom)

        btnCreateVideotom.setOnClickListener {
            TomAdsGlobalClassEveryTime().showIntrestrialAdstom(this, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {
                    val itom = Intent(this@TomDashboardActivity, TomHomeActivity::class.java)
                    startActivity(itom)
                    finish()
                }
            }, TomMyPreferenceManager(this).fIdtom())
        }

        val ivRatetom = findViewById<AppCompatImageView>(R.id.ivRatetom)
        ivRatetom.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }

        val ivSharetom = findViewById<AppCompatImageView>(R.id.ivSharetom)
        ivSharetom.setOnClickListener {
            val sharingIntenttom = Intent(Intent.ACTION_SEND)
            sharingIntenttom.type = "text/plain"
            val shareBodyJusi = "Download this app from here, \n\nhttps://play.google.com/store/apps/details?id=$packageName"
            sharingIntenttom.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            sharingIntenttom.putExtra(Intent.EXTRA_TEXT, shareBodyJusi)
            startActivity(Intent.createChooser(sharingIntenttom, "Share via"))
        }
    }

    override fun onResume() {
        super.onResume()
        TomCommon.setUpadDialogtom(this@TomDashboardActivity)
    }

    public override fun onDestroy() {
        super.onDestroy()
    }
}