package com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.stpnfncmakads_tom.dialogs_tom.TomAdShowingDialog
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomNativeAdListener
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.`TomNativeAdLoadListener`
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.beVisible
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class TomAdsGlobalClassEveryTime {

    fun showAdAfter(context: Activity, closeEventSTED: TomadmobCloseEvent, adId: String?, dc: Int) {
        count += 1
        try {
            if (count > dc) {
                count = 0
                showAdDailog(context)
                if (!isNetworkAvailable(context)) {
                    dismmisEverything(context, closeEventSTED)
                    return
                }
                if (mInterstitialAd == null) {
                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    return
                }
                if (!context.isFinishing) {
                    if (mInterstitialAd != null ) {
                        GlobalScope.launch {
                            delay(500)
                            context.runOnUiThread {
//                                TomMyPreferenceManager(context).setLastTimeShowkaka()
                                dismisAdDialog(context)
                                if (mInterstitialAd != null) {

                                    mInterstitialAd?.show(context)
                                    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                                        override fun onAdDismissedFullScreenContent() {
                                            // show it a second time.
                                            dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                        }

                                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                            // Called when fullscreen content failed to show.
                                            // Make sure to set your reference to null so you don't
                                            // show it a second time.
                                            dismmisEverything(context, closeEventSTED)
                                        }

                                        override fun onAdShowedFullScreenContent() {
                                            // Called when fullscreen content is shown.
                                        }
                                    }

                                } else {
                                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                }

                            }
                        }

                    } else {
                        dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    }
                } else {
                    dismmisEverything(context, closeEventSTED)
                }
            } else {
                dismmisEverything(context, closeEventSTED)
            }
        } catch (e: Exception) {
            dismmisEverythingWithLoad(context, closeEventSTED, adId)
        }

    }

    fun showIntrestrialAdstom(context: Activity, closeEventSTED: TomadmobCloseEvent, adId: String?) {
        val millisInDay = (1000 * 60 * 60 * 24).toLong()

        val dcFinal = TomMyPreferenceManager(context).showCount
        val dc = 0
        val dynamicDays = TomMyPreferenceManager(context).dynamicDaysgoa //2
        val dynamicShows = TomMyPreferenceManager(context).dynamicShowsgoa//true
        val firstTime = TomMyPreferenceManager(context).installTimegoa//install time

        val currentTime = System.currentTimeMillis()
        val diffrance = currentTime - firstTime
        val diffranceFinal = diffrance / millisInDay

        if (dynamicShows) {

            if (diffranceFinal >= dynamicDays) {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            } else {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            }
        } else {
            if (diffranceFinal >= dynamicDays) {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            } else {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            }
        }
    }

    fun showAdInHomeScreen(context: Activity, closeEventSTED: TomadmobCloseEvent, adId: String?) {
        try {
            if(!TomMyPreferenceManager(context).homeAdShowgoa){
                closeEventSTED.setAdmobCloseEventJusi()
                return
            }

            if(adId == null){
                closeEventSTED.setAdmobCloseEventJusi()
                return
            }

            val adShowDiloagH = TomAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagH!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagH!!.setCanceledOnTouchOutside(false)
            adShowDiloagH.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (!context.isFinishing && adShowDiloagH != null) {
                adShowDiloagH.show()
            }
            if (!isNetworkAvailable(context)) {
                if ( !context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                closeEventSTED.setAdmobCloseEventJusi()
                return
            }
            if (mInterstitialAd == null) {
                GlobalScope.launch {
                    delay(5000)
                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                }
            } else {
                if (!context.isFinishing) {
                    GlobalScope.launch {
                        delay(500)
                        context.runOnUiThread {
                            if (mInterstitialAd != null) {
                                showHaD(context, adShowDiloagH, closeEventSTED, adId)
                            } else {
                                GlobalScope.launch {
                                    delay(4500)
                                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                                }
                            }
                        }
                    }
                } else {
                    if (!context.isFinishing && adShowDiloagH.isShowing) {
                        adShowDiloagH.cancel()
                    }
                    closeEventSTED.setAdmobCloseEventJusi()
                }
            }

        } catch (e: Exception) {
            closeEventSTED.setAdmobCloseEventJusi()
        }
    }

    fun showHaD(
        context: Activity,
        adShowDiloagH: TomAdShowingDialog,
        closeEventSTED: TomadmobCloseEvent,
        adId: String?
    ) {
        context.runOnUiThread {
            if (!context.isFinishing && adShowDiloagH.isShowing) {
                adShowDiloagH.cancel()
            }
            if (mInterstitialAd != null) {

                mInterstitialAd?.show(context)
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // show it a second time.
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventJusi()
                        loadInterstitialAds(context, adId)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Called when fullscreen content failed to show.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventJusi()
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                    }
                }
            } else {
                if (!context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                loadInterstitialAds(context, adId)
                closeEventSTED.setAdmobCloseEventJusi()
            }
        }
    }


    fun dismmisEverything(context: Activity, closeEventSTED: TomadmobCloseEvent) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventJusi()
    }

    fun dismmisEverythingWithLoad(
        context: Activity,
        closeEventSTED: TomadmobCloseEvent,
        adId: String?
    ) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventJusi()
        loadInterstitialAds(context, adId)
    }

    fun showAdDailog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null) {
            adShowDiloagSTED.show()
        }
    }

    fun dismisAdDialog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null && adShowDiloagSTED.isShowing) {
            adShowDiloagSTED.cancel()
        }
    }

    /** Which layout
     * @author Hp
     * @param which = 1 - R.layout.native_single_start
     * @param which = 2 - R.layout.native_g_dialog
     * @param which = 3 - R.layout.native_single_gad_unified
     * @param which = 4 - R.layout.native_single_gad_vd
     */
    fun showAndLoadGoogleNativetom(
        activity: Activity?,
        nadId: String?,
        nativeAdContainerG: FrameLayout?,
        wantToShow: Boolean = true,
        which: Int,
        nativeEventSTED: TomNativeAdListener

    ) {
        nativeAdContainer = nativeAdContainerG


        if (gnativeAd != null) {
            if (nativeAdContainerG != null) {
                if (wantToShow) {
                    try {
                        if (activity != null) {
                            showNativeGoogle(activity, nativeAdContainerG, which, nativeEventSTED)
                            loadGoogleNative(activity, nadId, null)
                        } else {
                            nativeEventSTED.setNativeFailedJusi()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        nativeEventSTED.setNativeFailedJusi()
                    }
                } else {
                    loadGoogleNative(activity, nadId, object : TomNativeAdLoadListener {
                        override fun setNativeSuccessJusi() {
                        }

                        override fun setNativeFailedJusi() {
                        }
                    })
                }
            } else {
                nativeEventSTED.setNativeFailedJusi()
            }

        } else {
            loadGoogleNative(activity, nadId, object : TomNativeAdLoadListener {
                override fun setNativeSuccessJusi() {
                    if (activity != null && nativeAdContainerG != null) {
                        showNativeGoogle(activity, nativeAdContainerG, which, nativeEventSTED)
                        loadGoogleNative(activity, nadId, null)
                    } else {
                        nativeEventSTED.setNativeFailedJusi()
                    }
                }

                override fun setNativeFailedJusi() {
                    nativeEventSTED.setNativeFailedJusi()
                }
            })
        }

    }

    fun showNativeGoogle(
        activity: Activity,
        nativeAdContainerG: FrameLayout,
        which: Int,
        nativeEventSTED: TomNativeAdListener
    ) {
        when (which) {
            1 -> {
                nativeAdContainerG.beVisible()
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_start_tom,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                TomNativeGoogle().populateUnifiedNativeAdViewStartGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccessJusi()
            }
            2 -> {
                nativeAdContainerG.beVisible()
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_g_dialog_tom,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                TomNativeGoogle().populateUnifiedNativeAdViewDGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccessJusi()
            }
            3 -> {
                nativeAdContainerG.beVisible()
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_unified_tom,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                TomNativeGoogle().populateUnifiedNativeAdViewUnifiGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccessJusi()
            }
            4 -> {
                nativeAdContainerG.beVisible()
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_vd_tom,
                    null
                ) as NativeAdView
//                    adView.isHardwareAccelerated
                TomNativeGoogle().populateUnifiedNativeAdViewUnifiGoa(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccessJusi()

            }
        }
    }


    fun showBannerAdtom(
        activity: Activity,
        closeEventSTED: TomadmobCloseEvent,
        BannerID: String?,
        adContainer: LinearLayout,
    ) {
        try {
            if (mAdView != null) {
                adContainer.visibility = View.VISIBLE
                if (adContainer.childCount > 0)
                    adContainer.removeAllViews()

                adContainer.addView(mAdView)

                mAdView?.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        closeEventSTED.setFailedJusi()
                        onLoadagainBanner(activity, closeEventSTED, BannerID)
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED.setSuccessJusi()
                    }

                }
                onLoadagainBanner(activity, closeEventSTED, BannerID)


            } else {
                onLoadagainBanner(activity, closeEventSTED, BannerID)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onLoadagainBanner(activity, closeEventSTED, BannerID)
        }
    }


    fun onLoadagainBanner(activity: Activity, closeEventSTED: TomadmobCloseEvent, BannerID: String?) {
        closeEventSTED.setAdmobCloseEventJusi()
        loadbanner(activity, BannerID, closeEventSTED)
    }


    companion object {
        var count = 0
        lateinit var adShowDiloagSTED: TomAdShowingDialog
        var mInterstitialAd: InterstitialAd? = null
        var gnativeAd: NativeAd? = null
        var mAdView: AdView? = null
        var nativeAdContainer: ViewGroup? = null

        @JvmStatic
        fun loadInterstitialAds(activity: Activity, fId: String?) {
            if (fId != null && isNetworkAvailable(activity)) {
                nowLoadFull(activity, fId)
            } else {
                mInterstitialAd = null
            }
        }

        fun nowLoadFull(activity: Activity, fId: String) {
            try {
                val adRequest = AdRequest.Builder().build()
                InterstitialAd.load(activity, fId, adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(ad: InterstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.

                            mInterstitialAd = ad
                            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                                override fun onAdDismissedFullScreenContent() {
                                }

                                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                    mInterstitialAd = null
                                }

                                override fun onAdShowedFullScreenContent() {
                                }
                            }
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            // Handle the error
                            val error: String = String.format(
                                Locale.ENGLISH,
                                "domain: %s, code: %d, message: %s",
                                loadAdError.domain,
                                loadAdError.code,
                                loadAdError.message
                            )
                            mInterstitialAd = null

                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun setAdShowDialogtom(context: Activity) {
            adShowDiloagSTED = TomAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagSTED.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagSTED.setCanceledOnTouchOutside(false)
            adShowDiloagSTED.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        fun loadGoogleNative(
            activity: Activity?,
            nadId: String?,
            nativeAdLoadListenerSTED: TomNativeAdLoadListener?
        ) {
            if (activity != null && nadId != null) {
                val videoOptions =
                    VideoOptions.Builder().setStartMuted(true).build()

                val adLoader = AdLoader.Builder(activity, nadId)
                    .forNativeAd { ad : NativeAd ->
                        gnativeAd?.destroy()
                        gnativeAd = ad
                    }
                    .withAdListener(object : AdListener() {
                        override fun onAdFailedToLoad(errorCode: LoadAdError) {
                            nativeAdLoadListenerSTED?.setNativeFailedJusi()
                        }

                        override fun onAdLoaded() {
                            super.onAdLoaded()
                            nativeAdLoadListenerSTED?.setNativeSuccessJusi()
                        }

                        override fun onAdClicked() {
                            super.onAdClicked()
                        }
                    })
                    .withNativeAdOptions(NativeAdOptions.Builder().setVideoOptions(videoOptions).build())
                    .build()

                adLoader.loadAd(AdRequest.Builder().build())
            } else {
                nativeAdLoadListenerSTED?.setNativeFailedJusi()
            }
        }

        fun loadbanner(activity: Activity, BannerID: String?, closeEventSTED: TomadmobCloseEvent?) {
            if (!activity.isFinishing && BannerID != null) {
                mAdView = AdView(activity)

                mAdView?.setAdSize(getAdSize(activity))
                mAdView?.adUnitId = BannerID

                val adRequest = AdRequest.Builder().build()

                mAdView?.loadAd(adRequest)
                mAdView?.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        mAdView = null
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED?.setSuccessJusi()
                    }
                }
            } else {
                mAdView = null
            }
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        }

        private fun getAdSize(activityNow: Activity): AdSize {
            // Step 2 - Determine the screen width (less decorations) to use for the ad width.
            val display: Display = activityNow.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val widthPixels = outMetrics.widthPixels.toFloat()
            val density = outMetrics.density

            val adWidth = (widthPixels / density).toInt()

            // Step 3 - Get adaptive ad size and return for setting on the ad view.
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activityNow, adWidth)
        }

    }


}