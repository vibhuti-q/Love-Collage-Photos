package com.lovephotos.collageeditor.activity_tom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.TomCollageApplication
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.isValidClick
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class TomShareActivity : AppCompatActivity(), View.OnClickListener {
    var sharedpreferencestom: SharedPreferences? = null
    private var displayadtom = 1
    private var whichAdFirsttom = 1
    var whichActivitytoStarttom = 0
    private var isActivityLefttom = false
    private var editorResultImageViewtom: ImageView? = null
    var imageUrltom: String? = null
    var apptom: TomCollageApplication? = null
    var bitmaptom: Bitmap? = null
    var activitytom: Context = this

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    override fun onCreate(paramBundle: Bundle?) {
        super.onCreate(paramBundle)
        setContentView(R.layout.fragment_editor_result_tom)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)
        isActivityLefttom = false
        apptom = application as TomCollageApplication
        displayadtom = sharedpreferencestom!!.getInt("displayad", 1)
        whichAdFirsttom = sharedpreferencestom!!.getInt("whichAdFirst", 1)
        editorResultImageViewtom = findViewById<View>(R.id.editorResultImageViewtom) as ImageView
        imageUrltom = intent.getStringExtra("IMAGE_DATA")
        findViewById<View>(R.id.imageButtonEditorResultBacktom).setOnClickListener(this)
        findViewById<View>(R.id.imageButtonEditorResultCollagetom).setOnClickListener(this)
        findViewById<View>(R.id.imageButtonEditorResultSharetom).setOnClickListener(this)
        System.gc()
        try {
            bitmaptom = BitmapFactory.decodeFile(imageUrltom)
            val localDisplayMetricstom = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(localDisplayMetricstom)
            localDisplayMetricstom.heightPixels
            if (bitmaptom == null) {
                Toast.makeText(this@TomShareActivity, "Failed to load image!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
        } catch (localException: Exception) {
            Toast.makeText(this@TomShareActivity, "Failed to load image!", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        editorResultImageViewtom!!.setImageBitmap(bitmaptom)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@TomShareActivity, TomHomeActivity::class.java))
        finish()
        super.onBackPressed()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageButtonEditorResultBacktom -> {
                if (!isValidClick()) return
                whichActivitytoStarttom = 1
                replaceScreentom()
            }
            R.id.imageButtonEditorResultCollagetom -> {
                if (!isValidClick()) return
                admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomShareActivity, object : TomadmobCloseEvent {
                    override fun setAdmobCloseEventJusi() {
                        whichActivitytoStarttom = 2
                        replaceScreentom()
                    }
                }, TomMyPreferenceManager(this@TomShareActivity).fIdtom())
                return
            }
            R.id.imageButtonEditorResultSharetom -> {
                if (!isValidClick()) return
                admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomShareActivity, object : TomadmobCloseEvent {
                    override fun setAdmobCloseEventJusi() {
                        val shareIntenttom = Intent(Intent.ACTION_SEND)
                        shareIntenttom.type = "image/png"
                        shareIntenttom.putExtra(Intent.EXTRA_SUBJECT, "Check out " + resources.getString(R.string.app_name))
                        val imageFileToSharetom = File(imageUrltom)
                        val apkURItom = FileProvider.getUriForFile(this@TomShareActivity, applicationContext.packageName + ".provider", imageFileToSharetom)
                        shareIntenttom.putExtra(Intent.EXTRA_STREAM, apkURItom)
                        startActivity(Intent.createChooser(shareIntenttom, "Share via"))
                        return
                    }
                }, TomMyPreferenceManager(this@TomShareActivity).fIdtom())
            }
            else -> return
        }
    }

    public override fun onPause() {
        super.onPause()
        isActivityLefttom = true
    }

    public override fun onResume() {
        super.onResume()
        TomAdsGlobalClassEveryTime().showBannerAdtom(
            this, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {}
                override fun setSuccessJusi() {
                }

                override fun setFailedJusi() {
                    super.setFailedJusi()
                    adContainertom.gone()
                }
            },
            prefenrencMyPreferenceManagertom.getGBannerIDtom(), adViewtom
        )
        TomCommon.setUpadDialogtom(this@TomShareActivity)
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

    private fun replaceScreentom() {
        if (whichActivitytoStarttom == 1) {
            startActivity(Intent(this@TomShareActivity, TomHomeActivity::class.java))
            finish()
        } else if (whichActivitytoStarttom == 2) {
            startActivity(Intent(this@TomShareActivity, TomHomeActivity::class.java))
            finish()
        }
    }

    companion object {
        const val mypreferencetom = "myprefadmob"
    }
}