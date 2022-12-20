package com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.lovephotos.collageeditor.BuildConfig
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity
import com.lovephotos.collageeditor.databinding.ActivitySplashTomBinding
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.model.Settings
import com.lovephotos.collageeditor.stpnfncmakads_tom.retrofit.ApiInterface
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomConnectionDetector
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class TommSplashActivity : AppCompatActivity() {

    private val mContexttom: Context = this
    private lateinit var bindingtom: ActivitySplashTomBinding
    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intent.action != null && intent.action?.equals(Intent.ACTION_MAIN)!!) {
            finish()
            return
        } else {
            bindingtom = ActivitySplashTomBinding.inflate(layoutInflater)
            setContentView(bindingtom.root)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        if (cdtom.isConnectingToInternettom) {
            getFromRestAPItom()
        } else {
            setNullId()
            redirectToNextActivitytom()
        }
    }

    private val cdtom: TomConnectionDetector by lazy {
        TomConnectionDetector(applicationContext)
    }


    private fun redirectToNextActivitytom() {
        val handlertom = Handler(Looper.getMainLooper())
        handlertom.postDelayed(runnable, AUTO_HIDE_DELAY_MILLIS)
    }

    private fun getFromRestAPItom() {

        val BASE_URLtom = getBaseURL()

        val clienttom: OkHttpClient = if (BuildConfig.DEBUG) {
            val interceptortom = HttpLoggingInterceptor()
            interceptortom.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(interceptortom) // remove when live it's for log
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        }
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URLtom)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clienttom)
            .build()

        val myApitom = retrofit.create(ApiInterface::class.java)
        val pkgtom = packageName.replace(".", "_") + ".json"
        val calltom: Call<Settings> = myApitom.getAdIds(pkgtom)
        calltom.enqueue(object : Callback<Settings> {
            override fun onResponse(call: Call<Settings>, response: Response<Settings>) {
                val settingModel: Settings? = response.body()

                if (settingModel != null) {

                    prefenrencMyPreferenceManagertom.setGBannerIDtom(settingModel.googleBanner)
                    prefenrencMyPreferenceManagertom.setfIdtom(settingModel.googleInterstitial)
                    prefenrencMyPreferenceManagertom.setnadIdtom(settingModel.googleNative)
                    prefenrencMyPreferenceManagertom.setrIdtom(settingModel.googleRewarded)
                    prefenrencMyPreferenceManagertom.setopenIdtom(settingModel.googleOpenAdId)

                    //region new dynamic show 2
                    prefenrencMyPreferenceManagertom.dynamicShowsgoa=settingModel.dynamicShows
                    prefenrencMyPreferenceManagertom.dynamicDaysgoa=settingModel.dynamicDays
                    if(prefenrencMyPreferenceManagertom.installTimegoa<=0){
                        prefenrencMyPreferenceManagertom.installTimegoa=System.currentTimeMillis()
                    }

                    prefenrencMyPreferenceManagertom.homeAdShowgoa= settingModel.homeAdShow

                    //endregion


                    prefenrencMyPreferenceManagertom.privacyPolicy = settingModel.privacyPolicy
                    prefenrencMyPreferenceManagertom.termsAndCondition = settingModel.termsandcondition
                    prefenrencMyPreferenceManagertom.moreApps = settingModel.moreApps
                    prefenrencMyPreferenceManagertom.isStartScreen = settingModel.isStartScreen
                    prefenrencMyPreferenceManagertom.showCount = settingModel.showCount

                    TomAdsGlobalClassEveryTime.loadGoogleNative(this@TommSplashActivity, prefenrencMyPreferenceManagertom.nadIdtom(), null)
                    TomAdsGlobalClassEveryTime.loadbanner(this@TommSplashActivity, prefenrencMyPreferenceManagertom.getGBannerIDtom(), null)
                    TomAdsGlobalClassEveryTime.loadInterstitialAds(this@TommSplashActivity, prefenrencMyPreferenceManagertom.fIdtom())

                    val appCurrentVersiontom: Long =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                            packageManager.getPackageInfo(packageName, 0).longVersionCode
                        else
                            packageManager.getPackageInfo(packageName, 0).versionCode.toLong()

                    val appWebVersion = settingModel.version ?: 1//settingModel.version

                    when {
                        appWebVersion > appCurrentVersiontom -> {
                            val updateApptom = Intent(mContexttom, TomAppUpdateActivity::class.java)
                            settingModel.versionMessage?.let {
                                updateApptom.putExtra("versionMessage", it)
                            }
                            startActivity(updateApptom)
                            finish()
                        }
                        settingModel.isMaintaince -> {
                            val maintenance = Intent(mContexttom, TomAppUpdateActivity::class.java)
                            settingModel.maintainceMessage?.let {
                                maintenance.putExtra("maintainceMessage", it)
                            }
                            startActivity(maintenance)
                            finish()
                        }
                        settingModel.isMoved -> {
                            val moved = Intent(mContexttom, TomAppUpdateActivity::class.java)
                            settingModel.movedURL?.let {
                                moved.putExtra("movedURL", it)
                                moved.putExtra("movedDescription", settingModel.movedDescription)
                            }
                            startActivity(moved)
                            finish()
                        }
                        else -> redirectToNextActivitytom()
                    }
                } else redirectToNextActivitytom()
            }

            override fun onFailure(call: Call<Settings>, t: Throwable) {
                setNullId()
                redirectToNextActivitytom()
            }
        })
    }

    private var runnable = Runnable {
        val isStartScreentom = prefenrencMyPreferenceManagertom.isStartScreen
        if (isStartScreentom) {
            startActivity(Intent(mContexttom, TomHomeActivity::class.java))
            finishAffinity()
        } else {
            startActivity(Intent(mContexttom, TomHomeActivity::class.java))
            finishAffinity()
        }
    }

    private fun setNullId() {
        prefenrencMyPreferenceManagertom.setGBannerIDtom(null)
        prefenrencMyPreferenceManagertom.setfIdtom(null)
        prefenrencMyPreferenceManagertom.setnadIdtom(null)
        prefenrencMyPreferenceManagertom.setrIdtom(null)
        prefenrencMyPreferenceManagertom.setopenIdtom(null)
    }

    private external fun getBaseURL(): String

    companion object {
        private const val AUTO_HIDE_DELAY_MILLIS = 500L

        init {
            System.loadLibrary("native-lib")
        }
    }
}