package com.lovephotos.collageeditor.collage_tom

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener
import com.nostra13.universalimageloader.utils.StorageUtils
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.scanMediaFile
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class TomCustomGalleryActivity : AppCompatActivity() {
    var sharedpreferencestom: SharedPreferences? = null
    private var displayadtom = 1
    private var whichAdFirsttom = 1
    private var isActivityLefttom = false
    private var actiontom: String? = null
    var adaptertom: TomGalleryAdapter? = null
    private var backBtntom: ImageButton? = null
    private var btnGalleryOktom: ImageButton? = null
    private var gridGallerytom: GridView? = null
    var handlertom: Handler? = null
    private var imgNoMediatom: ImageView? = null
    var activitytom: AppCompatActivity? = null

    var selectionLimit: Int = 2

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    private var mItemMulClickListenertom = AdapterView.OnItemClickListener { _: AdapterView<*>?, view: View?, position: Int, _: Long ->
        adaptertom!!.changeSelectiontom(view, position)
    }

    private var mItemSingleClickListenertom = AdapterView.OnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
        this@TomCustomGalleryActivity.setResult(-1, Intent().putExtra("single_path", adaptertom!!.getItem(position).sdcardPathtom))
        finish()
    }

    private var mOkClickListenertom = View.OnClickListener {
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomCustomGalleryActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                val selectedtom = adaptertom!!.selected
                val allPathtom = arrayOfNulls<String>(selectedtom.size)
                for (i in allPathtom.indices) {
                    allPathtom[i] = selectedtom[i].sdcardPathtom
                }
                this@TomCustomGalleryActivity.setResult(-1, Intent().putExtra("all_path", allPathtom))
                finish()
            }
        }, TomMyPreferenceManager(this@TomCustomGalleryActivity).fIdtom())
    }

    private var mbackBtnClickListenertom = View.OnClickListener {
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomCustomGalleryActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                val allPathtom = arrayOf("")
                this@TomCustomGalleryActivity.setResult(0, Intent().putExtra("all_path", allPathtom))
                this@TomCustomGalleryActivity.setResult(0, Intent().putExtra("single_path", allPathtom[0]))
                finish()
            }
        }, TomMyPreferenceManager(this@TomCustomGalleryActivity).fIdtom())
    }

    private var imageLoadertom: ImageLoader? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        setContentView(R.layout.gallery_tom)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)
        isActivityLefttom = false
        activitytom = this@TomCustomGalleryActivity

        scanMediaFile(this, "")
        selectionLimit = intent.getIntExtra("selectionLimit", 2);

        displayadtom = sharedpreferencestom!!.getInt("displayad", 1)
        whichAdFirsttom = sharedpreferencestom!!.getInt("whichAdFirst", 1)
        actiontom = intent.action
        if (actiontom == null)
            finish()
        initImageLoadertom()
        inittom()
    }

    private fun initImageLoadertom() {
        val CACHE_DIRtom = StringBuilder(Environment.getExternalStorageDirectory().absolutePath).append("/.temp_tmp").toString()
        File(CACHE_DIRtom).mkdirs()
        val cacheDirtom = StorageUtils.getOwnCacheDirectory(baseContext, CACHE_DIRtom)
        val configtom = ImageLoaderConfiguration.Builder(baseContext).defaultDisplayImageOptions(
            DisplayImageOptions.Builder().cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).build()
        ).discCache(UnlimitedDiskCache(cacheDirtom)).memoryCache(WeakMemoryCache()).build()
        imageLoadertom = ImageLoader.getInstance()
        imageLoadertom!!.init(configtom)
    }

    private fun inittom() {
        backBtntom = findViewById(R.id.back_btntom)
        handlertom = Handler()
        gridGallerytom = findViewById(R.id.gridGallerytom)
        gridGallerytom!!.isFastScrollEnabled = true
        adaptertom =
            TomGalleryAdapter(
                applicationContext,
                imageLoadertom, selectionLimit
            )
        gridGallerytom!!.setOnScrollListener(PauseOnScrollListener(imageLoadertom, true, true))
        if (actiontom.equals(TomAction.ACTION_MULTIPLE_PICKtom, ignoreCase = true)) {
            findViewById<View>(R.id.btnGalleryOktom).visibility = View.VISIBLE
            gridGallerytom!!.onItemClickListener = mItemMulClickListenertom
            adaptertom!!.setMultiplePick(true)
        } else if (actiontom.equals(TomAction.ACTION_PICKtom, ignoreCase = true)) {
            findViewById<View>(R.id.btnGalleryOktom).visibility = View.GONE
            gridGallerytom!!.onItemClickListener = mItemSingleClickListenertom
            adaptertom!!.setMultiplePick(false)
        }
        gridGallerytom!!.adapter = adaptertom
        imgNoMediatom = findViewById(R.id.imgNoMediatom)
        btnGalleryOktom = findViewById(R.id.btnGalleryOktom)
        btnGalleryOktom!!.setOnClickListener(mOkClickListenertom)
        backBtntom!!.setOnClickListener(mbackBtnClickListenertom)
        val threadJusi: Thread = object : Thread() {
            override fun run() {
                Looper.prepare()
                handlertom!!.post {
                    adaptertom!!.addAlltom(galleryTvsPhotos)
                    checkImageStatustom()
                }
                Looper.loop()
            }
        }
        threadJusi.start()
    }

    private fun checkImageStatustom() {
        if (adaptertom!!.isEmpty)
            imgNoMediatom!!.visibility = View.VISIBLE
        else
            imgNoMediatom!!.visibility = View.GONE
    }

    private val galleryTvsPhotos: ArrayList<TomCustomGallery?>
        @SuppressLint("Range")
        get() {
            val galleryTvsList: ArrayList<TomCustomGallery?> = ArrayList<TomCustomGallery?>()
            try {
                val orderBy = "_id"
                val imagecursortom = managedQuery(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    arrayOf("_data", "_id"),
                    null,
                    null,
                    "_id"
                )
                if (imagecursortom != null && imagecursortom.count > 0) {
                    while (imagecursortom.moveToNext()) {
                        val itemtom =
                            TomCustomGallery()
                        itemtom.sdcardPathtom = imagecursortom.getString(imagecursortom.getColumnIndex("_data"))
                        galleryTvsList.add(itemtom)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            galleryTvsList.reverse()
            return galleryTvsList
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
            }, prefenrencMyPreferenceManagertom.getGBannerIDtom(), adViewtom
        )
        TomCommon.setUpadDialogtom(this@TomCustomGalleryActivity)
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

    companion object {
        const val mypreferencetom = "myprefadmob"
        private const val TAGtom = "FBDEMO"
    }
}