package com.lovephotos.collageeditor.activity_tom

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_settings_tom.*
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class TomSettingsActivity : AppCompatActivity() {

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager
    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_tom)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        val displaytom = windowManager.defaultDisplay
        CAMERA_WIDTHtom = displaytom.width
        CAMERA_HEIGHTtom = displaytom.height

        val toolbar = findViewById<Toolbar>(R.id.toolbartom)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Settings")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { v: View? ->
            onBackPressed()
        }

        llPrivacyBlue.setOnClickListener {
            try {
                val urlBlue = TomMyPreferenceManager(this@TomSettingsActivity).privacyPolicy
                val iBlue = Intent(Intent.ACTION_VIEW)
                iBlue.data = Uri.parse(urlBlue)
                startActivity(iBlue)
            }catch (eBlue: Exception){}
        }

        llRateUs.setOnClickListener {
            val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName))
            try {
                startActivity(myAppLinkToMarket)
            } catch (e: Exception) {
                val myAppLinkToMarket2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName))
                startActivity(myAppLinkToMarket2)
            }
        }

        llMoreAppsBlue.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TomMyPreferenceManager(this@TomSettingsActivity).moreApps)))
            } catch (eBlue: ActivityNotFoundException) {}
        }
    }


    public override fun onResume() {
        super.onResume()
        TomAdsGlobalClassEveryTime().showBannerAdtom(
            this, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {}
                override fun setSuccessJusi() {}
                override fun setFailedJusi() {
                    super.setFailedJusi()
                    adContainertom.gone()
                }
            },
            prefenrencMyPreferenceManagertom.getGBannerIDtom(), adViewtom
        )
        TomCommon.setUpadDialogtom(this@TomSettingsActivity)
    }

    override fun onBackPressed() {
        finish()
    }



    companion object {
        const val mypreferencetom = "myprefadmob"
        var CAMERA_HEIGHTtom = 0
        var CAMERA_WIDTHtom = 0
    }
}