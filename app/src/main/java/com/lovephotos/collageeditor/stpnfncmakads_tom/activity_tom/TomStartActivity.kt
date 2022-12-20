package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.Intent
import android.os.Bundle
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.databinding.ActivityStartTomBinding
import com.lovephotos.collageeditor.stpnfncmakads_tom.baseclass_tom.TomBaseActivityAll
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomNativeAdListener
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon.setUpadDialogtom
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_start_tom.*
import kotlinx.android.synthetic.main.native_ad_common_tom.*
import kotlinx.android.synthetic.main.native_ad_common_tom.view.*
import javax.inject.Inject

@AndroidEntryPoint
class TomStartActivity : TomBaseActivityAll() {

    private lateinit var bindingtom: ActivityStartTomBinding

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        bindingtom = bindViewtom(R.layout.activity_start_tom) as ActivityStartTomBinding
        setContentView(bindingtom.root)

        observerViewModeltom()
    }

    private fun observerViewModeltom() {

        admobObjEverySTEDtom?.showAndLoadGoogleNativetom(activity = this,
            nadId = prefenrencMyPreferenceManagertom.nadIdtom(),
            nativeAdContainerG = nadViewtom.containertom,
            which = 1,
            nativeEventSTED = object : TomNativeAdListener {
                override fun setNativeSuccessJusi() {
                }

                override fun setNativeFailedJusi() {
                    adContainertom.gone()
                }
            })

        bindingtom.btnStarttom.setOnClickListener {
            admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomStartActivity, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {
                    val itom = Intent(this@TomStartActivity, TomDashboardActivity::class.java)
                    startActivity(itom)
                    finish()
                }
            }, TomMyPreferenceManager(this@TomStartActivity).fIdtom())
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        setUpadDialogtom(this@TomStartActivity)
    }
}