package com.lovephotos.collageeditor.activity_tom

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.Utilities_tom.TomConnectionDetector
import com.lovephotos.collageeditor.Utilities_tom.TomImgItem
import com.lovephotos.collageeditor.adapter_tom.TomViewAdapter
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAdapter
import com.lovephotos.collageeditor.custom_tom.TomCustomMediaPreviewDialog
import com.lovephotos.collageeditor.listener_tom.TomItemClickListener
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import java.io.File
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TomMyCreationActivity : AppCompatActivity(), TomItemClickListener {

    private val FilePathStringstom = ArrayList<TomImgItem>()
    private val filePathstom = ArrayList<String>()
    var sharedpreferencestom: SharedPreferences? = null
    var connectionDetectortom: TomConnectionDetector? = null
    private var displayadtom = 1
    private var whichAdFirsttom = 1
    private var isActivityLefttom = false
    var filetom: File? = null
    var postom = 0
    private var viewpagertom: ViewPager? = null
    var adaptertom: TomViewAdapter? = null
    private var emptycontenttom: LinearLayout? = null
    var activitytom: AppCompatActivity? = null
    private var recyclerViewtom: RecyclerView? = null
    private lateinit var listFiletom: Array<File>

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycreation_tom)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        val displaytom = windowManager.defaultDisplay
        CAMERA_WIDTHtom = displaytom.width
        CAMERA_HEIGHTtom = displaytom.height
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)
        isActivityLefttom = false
        activitytom = this@TomMyCreationActivity
        connectionDetectortom =
            TomConnectionDetector(
                applicationContext
            )
        displayadtom = sharedpreferencestom!!.getInt("displayad", 1)
        whichAdFirsttom = sharedpreferencestom!!.getInt("whichAdFirst", 1)
        val toolbar = findViewById<Toolbar>(R.id.toolbartom)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("My Creation")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { v: View? ->
            onBackPressed()
        }
        viewpagertom = findViewById(R.id.viewpagertom)
        recyclerViewtom = findViewById(R.id.rvImagestom)
        emptycontenttom = findViewById(R.id.emptycontenttom)
        viewpagertom!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                postom = position
            }

            override fun onPageSelected(position: Int) {
                postom = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun refreshListtom() {
        FilePathStringstom.clear()
        filePathstom.clear()
        filetom = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + getString(R.string.directory))
        if (!filetom!!.exists()) filetom!!.mkdirs()
        if (filetom!!.isDirectory) {
            listFiletom = filetom!!.listFiles()
            for (i in listFiletom.indices) {
                FilePathStringstom.add(
                    TomImgItem(
                        listFiletom[i].absolutePath
                    )
                )
                filePathstom.add(listFiletom[i].absolutePath)
            }
            val myAdaptertom = TomBaseAdapter(this@TomMyCreationActivity, filePathstom, R.layout.item_my_creation_list, this)
            recyclerViewtom!!.layoutManager = GridLayoutManager(this, 4)
            recyclerViewtom!!.adapter = myAdaptertom
            if (Objects.requireNonNull(filetom!!.listFiles()).isNotEmpty()) {
                adaptertom =
                    TomViewAdapter(
                        this@TomMyCreationActivity,
                        FilePathStringstom
                    )
                viewpagertom!!.adapter = adaptertom
                emptycontenttom!!.visibility = View.GONE
            } else emptycontenttom!!.visibility = View.VISIBLE
        } else emptycontenttom!!.visibility = View.VISIBLE
    }

    public override fun onPause() {
        super.onPause()
        isActivityLefttom = true
    }

    public override fun onResume() {
        super.onResume()
        isActivityLefttom = false
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
        TomCommon.setUpadDialogtom(this@TomMyCreationActivity)
        refreshListtom()
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
        finish()
    }

    override fun onItemClicktom(itemPosition: Int, vararg objects: Any) {
        TomCustomMediaPreviewDialog(this, itemPosition, filePathstom).show()
    }

    companion object {
        const val mypreferencetom = "myprefadmob"
        var CAMERA_HEIGHTtom = 0
        var CAMERA_WIDTHtom = 0
    }
}