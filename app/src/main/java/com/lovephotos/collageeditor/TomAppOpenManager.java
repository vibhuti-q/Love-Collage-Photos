package com.lovephotos.collageeditor;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager;

import java.util.Date;
import java.util.Objects;

public class TomAppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private static boolean isShowingAdtom = false;
    private final TomCollageApplication myapplicationtom;
    private AppOpenAd appOpenAdtom = null;
    private Activity currentActivitytom;
    private long loadTimetom = 0;

    public TomAppOpenManager(TomCollageApplication myApplicationAll) {
        this.myapplicationtom = myApplicationAll;
        this.myapplicationtom.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
            showAdIfAvailabletom();
    }

    public void fetchAdtom() {
        if (isAdAvailabletom()) return;
        AppOpenAd.AppOpenAdLoadCallback loadCallbacktom = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                TomAppOpenManager.this.appOpenAdtom = ad;
                TomAppOpenManager.this.loadTimetom = (new Date()).getTime();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            }
        };

        AdRequest requesttom = getAdRequesttom();
        TomMyPreferenceManager myPreferenceManagerJusi = new TomMyPreferenceManager(currentActivitytom);
        if (myPreferenceManagerJusi.openIdtom() != null){
            AppOpenAd.load(myapplicationtom, Objects.requireNonNull(myPreferenceManagerJusi.openIdtom()), requesttom, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallbacktom);
        }
    }

    private AdRequest getAdRequesttom() {
        return new AdRequest.Builder().build();
    }

    private boolean wasLoadTimeLessThanNHoursAgotom(long numHours) {
        long dateDifferencetom = (new Date()).getTime() - this.loadTimetom;
        long numMilliSecondsPerHourtom = 3600000;
        return (dateDifferencetom < (numMilliSecondsPerHourtom * numHours));
    }

    public boolean isAdAvailabletom() {
        return appOpenAdtom != null && wasLoadTimeLessThanNHoursAgotom(4);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivitytom = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivitytom = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivitytom = null;
    }

    public void showAdIfAvailabletom() {
        if (!isShowingAdtom && isAdAvailabletom()) {
            FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    TomAppOpenManager.this.appOpenAdtom = null;
                    isShowingAdtom = false;
                    fetchAdtom();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    isShowingAdtom = true;
                }
            };
            appOpenAdtom.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAdtom.show(currentActivitytom);
        } else fetchAdtom();
    }
}
