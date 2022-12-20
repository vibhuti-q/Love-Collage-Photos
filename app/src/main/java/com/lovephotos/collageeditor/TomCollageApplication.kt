package com.lovephotos.collageeditor

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.multidex.MultiDex
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.lovephotos.collageeditor.Utilities_tom.TomApplicationProperties
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils
import com.lovephotos.collageeditor.graphics_tom.TomImageProcessor
import com.lovephotos.collageeditor.mynotification_goa.GoaMyNotificationWillShowInForegroundHandler
import com.lovephotos.collageeditor.mynotification_goa.GoaOneSignalNotificationOpenedHandler
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TomCollageApplication : Application(){
    var position = 0
    var rotationtom = 0f
    lateinit var textIdtom: ByteArray
    var isFromCameratom = false
    var isTexttom = false
    var selectedImageUritom: Uri? = null
    private var prefstom: SharedPreferences? = null
    private var editortom: SharedPreferences.Editor? = null
    private var totalCounttom = 0

    private var appOpenManagerTVStom: TomAppOpenManager? = null

    companion object {
        var instance: TomCollageApplication? = null
            private set
        const val mypreferencetom = "myprefadmob"

        @JvmField
        var sharedpreferencestom: SharedPreferences? = null

        private const val ONESIGNAL_APP_IDgoa = ""
        const val buttonTextgoa = "ZJ9"
        const val buttonActionUrlgoa = "ZJ10"
        const val text1goa = "ZJ11"
        const val text2goa = "ZJ12"
        const val imageUrlgoa = "ZJ13"
        const val isInAppMessagegoa = "isInAppMessage"

        fun initImageLoadertom(context: Context?) {
            val configtom = ImageLoaderConfiguration.Builder(context)
            configtom.threadPriority(3)
            configtom.denyCacheImageMultipleSizesInMemory()
            configtom.diskCacheFileNameGenerator(Md5FileNameGenerator())
            configtom.diskCacheSize(52428800)
            configtom.tasksProcessingOrder(QueueProcessingType.LIFO)
            configtom.writeDebugLogs()
            ImageLoader.getInstance().init(configtom.build())
            val config1tom = ImageLoaderConfiguration.Builder(context)
            config1tom.threadPriority(Thread.NORM_PRIORITY - 2)
            config1tom.denyCacheImageMultipleSizesInMemory()
            config1tom.diskCacheFileNameGenerator(Md5FileNameGenerator())
            config1tom.diskCacheSize(50 * 1024 * 1024)
            config1tom.tasksProcessingOrder(QueueProcessingType.LIFO)
            config1tom.writeDebugLogs()
            ImageLoader.getInstance().init(config1tom.build())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appOpenManagerTVStom = TomAppOpenManager(this)
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)

        initImageLoadertom(applicationContext)
        TomImageProcessor.getInstance().setApplicationCotnexttom(applicationContext)
        prefstom = getSharedPreferences(TomApplicationProperties.AppPreftom, 0)
        editortom = prefstom!!.edit()
        totalCounttom = prefstom!!.getInt("appcounter", 0)
        totalCounttom++
        editortom!!.putInt("appcounter", totalCounttom)
        editortom!!.commit()
        TomCommonUtils.createRootDirectorytom()
        val config = ImageLoaderConfiguration.Builder(applicationContext)
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .memoryCacheSize(2 * 1024 * 1024) // 2 Mb
            .denyCacheImageMultipleSizesInMemory()
            .discCacheFileNameGenerator(Md5FileNameGenerator())
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .build()
        ImageLoader.getInstance().init(config)
        initImageLoadertom(applicationContext)



        //region OneSignal
        if (BuildConfig.DEBUG) {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        }
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_IDgoa);
        OneSignal.setNotificationOpenedHandler(GoaOneSignalNotificationOpenedHandler(this))
        OneSignal.setNotificationWillShowInForegroundHandler(
            GoaMyNotificationWillShowInForegroundHandler(this)
        )
        //endregion
    }




}