package com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom

import android.app.ActivityManager
import android.content.Context
import android.content.SharedPreferences
import com.lovephotos.collageeditor.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val YOUR_CUSTOM_TIMEOUT = "60000".toLong()
    private var pref_name = TomCommon.PREFNAMEtom

    @Provides
    @Singleton
    fun mySharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(pref_name, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun connectionDetector(@ApplicationContext context: Context): TomConnectionDetector {
        return TomConnectionDetector(
            context
        )
    }

    @Provides
    fun myCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideActivityManager(@ApplicationContext context: Context): ActivityManager {
        return context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            builder.addInterceptor(logging)
        }
        builder.readTimeout(YOUR_CUSTOM_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(YOUR_CUSTOM_TIMEOUT, TimeUnit.SECONDS)
        builder.connectTimeout(YOUR_CUSTOM_TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
        val CACHE_SIZE = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, CACHE_SIZE)
    }
}
