package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomNativeAdListener
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import kotlinx.android.synthetic.main.activity_kdl_exit_tom.*
import kotlinx.android.synthetic.main.native_ad_common_tom.*
import kotlinx.android.synthetic.main.native_ad_common_tom.view.*

class TomExit_KDL_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_kdl_exit_tom)
        val btn_exittom = findViewById<AppCompatTextView>(R.id.btn_exittom)
        btn_exittom.setOnClickListener { finishAffinity() }
        val btn_notom = findViewById<AppCompatTextView>(R.id.btn_notom)
        btn_notom.setOnClickListener {
            val intenttom = Intent(this@TomExit_KDL_Activity, TomHomeActivity::class.java)
            startActivity(intenttom)
            finishAffinity()
        }

        val myPreferenceManagertom = TomMyPreferenceManager(this@TomExit_KDL_Activity)
        val adsGlobalClassEveryTimetom = TomAdsGlobalClassEveryTime()
        adsGlobalClassEveryTimetom.showAndLoadGoogleNativetom(activity = this,
            nadId = myPreferenceManagertom.nadIdtom(),
            nativeAdContainerG = nadViewtom.containertom,
            which = 1,
            nativeEventSTED = object : TomNativeAdListener {
                override fun setNativeSuccessJusi() {
                }

                override fun setNativeFailedJusi() {
                    adContainertom.gone()
                }
            })
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        return
    }
}