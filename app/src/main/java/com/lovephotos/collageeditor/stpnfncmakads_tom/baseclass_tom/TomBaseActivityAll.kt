package com.lovephotos.collageeditor.stpnfncmakads_tom.baseclass_tom

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomPreferenceManager

open class TomBaseActivityAll : AppCompatActivity() {

    private val TAGtom: String? = TomBaseActivity::class.simpleName

    lateinit var preferenceManagerSTEDtom: TomPreferenceManager

    var permissionstom: ArrayList<String> = ArrayList()

    var countryPositiontom: Int = -1
    var serverPositiontom: Int = -1

    companion object {
        var isVPNStartedtom = false

    }

    fun bindViewtom(layoutId: Int): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For FileURIExposedException Handling
        val buildertom = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(buildertom.build())

        // To Prevent NetworkOnMainThread Exception
        val policytom = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policytom)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        preferenceManagerSTEDtom = TomPreferenceManager(this)

    }




}