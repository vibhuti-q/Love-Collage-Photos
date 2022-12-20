package com.lovephotos.collageeditor.collage_tom

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.Utilities_tom.TomConnectionDetector
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import javax.inject.Inject

@AndroidEntryPoint
class TomChoiceFrameActivityStyle : AppCompatActivity() {
    private var mThumbIdstom = intArrayOf(
        R.drawable.t1_tom,
        R.drawable.t2_tom,
        R.drawable.t3_tom,
        R.drawable.t4_tom,
        R.drawable.t5_tom,
        R.drawable.t6_tom,
        R.drawable.t7_tom,
        R.drawable.t8_tom,
        R.drawable.t9_tom,
        R.drawable.t10_tom,
        R.drawable.t11_tom,
        R.drawable.t12_tom,
        R.drawable.t13_tom,
        R.drawable.t14_tom,
        R.drawable.t15_tom,
        R.drawable.t16_tom,
        R.drawable.t17_tom,
        R.drawable.t18_tom,
        R.drawable.t19_tom,
        R.drawable.t20_tom,
        R.drawable.t21_tom,
        R.drawable.t22_tom,
        R.drawable.t23_tom,
        R.drawable.t24_tom,
        R.drawable.t25_tom,
        R.drawable.t26_tom,
        R.drawable.t27_tom,
        R.drawable.t28_tom,
        R.drawable.t29_tom,
        R.drawable.t30_tom,
        R.drawable.t31_tom,
        R.drawable.t32_tom,
        R.drawable.t33_tom,
        R.drawable.t34_tom,
        R.drawable.t35_tom,
        R.drawable.t36_tom,
        R.drawable.t37_tom,
        R.drawable.t38_tom,
        R.drawable.t39_tom,
        R.drawable.t40_tom,
        R.drawable.t41_tom,
        R.drawable.t42_tom,
        R.drawable.t43_tom,
        R.drawable.t44_tom,
        R.drawable.t45_tom,
        R.drawable.t46_tom,
        R.drawable.t47_tom,
        R.drawable.t48_tom
    )
    private var backBtntom: ImageButton? = null
    var sharedpreferencestom: SharedPreferences? = null
    var connectionDetectortom: TomConnectionDetector? = null
    private var displayadtom = 1
    private var whichAdFirsttom = 1
    private var isActivityLefttom = false
    var activitytom: AppCompatActivity? = null

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_frame_style_tom)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)
        isActivityLefttom = false
        activitytom = this@TomChoiceFrameActivityStyle
        connectionDetectortom =
            TomConnectionDetector(
                applicationContext
            )
        displayadtom = sharedpreferencestom!!.getInt("displayad", 1)
        whichAdFirsttom = sharedpreferencestom!!.getInt("whichAdFirst", 1)
        val display = windowManager.defaultDisplay
        CAMERA_WIDTHtom = display.width
        CAMERA_HEIGHTtom = display.height
        backBtntom = findViewById<View>(R.id.back_btntom) as ImageButton
        backBtntom!!.setOnClickListener {
            startActivity(Intent(this@TomChoiceFrameActivityStyle, TomHomeActivity::class.java))
            finish()
        }
        val gridViewtom = findViewById<View>(R.id.gridView1tom) as GridView
        gridViewtom.adapter =
            TomImageAdapterStyle(
                this,
                mThumbIdstom
            )
        gridViewtom.onItemClickListener = AdapterView.OnItemClickListener { _: AdapterView<*>?, _: View?, arg2: Int, _: Long ->
            admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomChoiceFrameActivityStyle, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {
                    val `in` = Intent(this@TomChoiceFrameActivityStyle, TomLayoutFrame::class.java)
                    when (arg2) {
                        0 -> `in`.putExtra(NUMBERFRAMEtom, 1)
                        1 -> `in`.putExtra(NUMBERFRAMEtom, 2)
                        2 -> `in`.putExtra(NUMBERFRAMEtom, 3)
                        3 -> `in`.putExtra(NUMBERFRAMEtom, 4)
                        4 -> `in`.putExtra(NUMBERFRAMEtom, 5)
                        5 -> `in`.putExtra(NUMBERFRAMEtom, 6)
                        6 -> `in`.putExtra(NUMBERFRAMEtom, 7)
                        7 -> `in`.putExtra(NUMBERFRAMEtom, 8)
                        8 -> `in`.putExtra(NUMBERFRAMEtom, 9)
                        9 -> `in`.putExtra(NUMBERFRAMEtom, 10)
                        10 -> `in`.putExtra(NUMBERFRAMEtom, 11)
                        11 -> `in`.putExtra(NUMBERFRAMEtom, 12)
                        12 -> `in`.putExtra(NUMBERFRAMEtom, 13)
                        13 -> `in`.putExtra(NUMBERFRAMEtom, 14)
                        14 -> `in`.putExtra(NUMBERFRAMEtom, 15)
                        15 -> `in`.putExtra(NUMBERFRAMEtom, 16)
                        16 -> `in`.putExtra(NUMBERFRAMEtom, 17)
                        17 -> `in`.putExtra(NUMBERFRAMEtom, 18)
                        18 -> `in`.putExtra(NUMBERFRAMEtom, 19)
                        19 -> `in`.putExtra(NUMBERFRAMEtom, 20)
                        20 -> `in`.putExtra(NUMBERFRAMEtom, 21)
                        21 -> `in`.putExtra(NUMBERFRAMEtom, 22)
                        22 -> `in`.putExtra(NUMBERFRAMEtom, 23)
                        23 -> `in`.putExtra(NUMBERFRAMEtom, 24)
                        24 -> `in`.putExtra(NUMBERFRAMEtom, 25)
                        25 -> `in`.putExtra(NUMBERFRAMEtom, 26)
                        26 -> `in`.putExtra(NUMBERFRAMEtom, 27)
                        27 -> `in`.putExtra(NUMBERFRAMEtom, 28)
                        28 -> `in`.putExtra(NUMBERFRAMEtom, 29)
                        29 -> `in`.putExtra(NUMBERFRAMEtom, 30)
                        30 -> `in`.putExtra(NUMBERFRAMEtom, 31)
                        31 -> `in`.putExtra(NUMBERFRAMEtom, 32)
                        32 -> `in`.putExtra(NUMBERFRAMEtom, 33)
                        33 -> `in`.putExtra(NUMBERFRAMEtom, 34)
                        34 -> `in`.putExtra(NUMBERFRAMEtom, 35)
                        35 -> `in`.putExtra(NUMBERFRAMEtom, 36)
                        36 -> `in`.putExtra(NUMBERFRAMEtom, 37)
                        37 -> `in`.putExtra(NUMBERFRAMEtom, 38)
                        38 -> `in`.putExtra(NUMBERFRAMEtom, 39)
                        39 -> `in`.putExtra(NUMBERFRAMEtom, 40)
                        40 -> `in`.putExtra(NUMBERFRAMEtom, 41)
                        41 -> `in`.putExtra(NUMBERFRAMEtom, 42)
                        42 -> `in`.putExtra(NUMBERFRAMEtom, 43)
                        43 -> `in`.putExtra(NUMBERFRAMEtom, 44)
                        44 -> `in`.putExtra(NUMBERFRAMEtom, 45)
                        45 -> `in`.putExtra(NUMBERFRAMEtom, 46)
                        46 -> `in`.putExtra(NUMBERFRAMEtom, 47)
                        47 -> `in`.putExtra(NUMBERFRAMEtom, 48)
                    }
                    this@TomChoiceFrameActivityStyle.startActivity(`in`)
                }
            }, TomMyPreferenceManager(this@TomChoiceFrameActivityStyle).fIdtom())
        }
    }

    public override fun onPause() {
        super.onPause()
        isActivityLefttom = true
    }

    public override fun onResume() {
        super.onResume()
        TomAdsGlobalClassEveryTime().showBannerAdtom(this, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {}
            override fun setSuccessJusi() {
            }

            override fun setFailedJusi() {
                super.setFailedJusi()
                adContainertom.gone()
            }
        }, prefenrencMyPreferenceManagertom.getGBannerIDtom(), adViewtom)
        TomCommon.setUpadDialogtom(this@TomChoiceFrameActivityStyle)
        isActivityLefttom = false
    }

    override fun onStop() {
        super.onStop()
        isActivityLefttom = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isActivityLefttom = true
    }

    override fun onBackPressed() {
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomChoiceFrameActivityStyle, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                startActivity(Intent(this@TomChoiceFrameActivityStyle, TomHomeActivity::class.java))
                finish()
            }
        }, TomMyPreferenceManager(this@TomChoiceFrameActivityStyle).fIdtom())
    }

    companion object {
        const val mypreferencetom = "myprefadmob"
        private const val TAGtom = "FBDEMO"
        var CAMERA_HEIGHTtom = 0
        var CAMERA_WIDTHtom = 0

        @JvmField
        var NUMBERFRAMEtom = "numberframe"
    }
}