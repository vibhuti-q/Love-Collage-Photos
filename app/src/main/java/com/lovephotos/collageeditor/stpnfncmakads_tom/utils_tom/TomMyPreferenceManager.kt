package com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom

import android.content.Context
import com.lovephotos.collageeditor.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TomMyPreferenceManager @Inject constructor(@ApplicationContext private val mcontext: Context?) {

    private val sharedPreftom = mcontext!!.getSharedPreferences(TomCommon.PREFNAMEtom, Context.MODE_PRIVATE)
    private val editortom = sharedPreftom.edit()

    private val BANNERtom = "banner"
    private val INTERSTITALtom = "interstital"
    private val NADIDtom = "NADID"
    private val OPENADtom = "openad"
    private val RIDtom = "rid"
    private val INTERVALtom = "interval"
    private val RANDOMERMtom = "randomErm"
    private val RANDOMOTHERtom = "randomOther"
    private val LASTTIMINGtom = "lastTiming"
    private val APPPIDtom = "apppid"//usr
    private val SSLtom = "SSLSHA"
    private val SSLURLtom = "SSLURL"
    private val TALLtom = "tAll"
    private val TSTARTtom = "tStart"
    private val OURLtom = "ourl"

    private val PRIVACYPOLICYtom = "privacyPolicy"
    private val TERMSANDCONDITIONStom = "termsAndCondition"
    private val MOREAPPStom = "moreApps"
    private val ISSTARTSCREENtom = "isStartScreen"
    private val SHOW_COUNTtom = "showCount"
    private val INSTALLTIMEgoa = "installtime"
    private val DYNAMICDAYSgoa = "dynamicDays"
    private val DYNAMICSHOWSgoa = "dynamicShows"
    private val HOMEADSHOWgoa = "homeAdShow"

    private fun storeStringtom(key: String, value: String?) = editortom.run {
        putString(key, value)
        apply()
    }

    private fun storeLongtom(key: String, value: Long) = editortom.run {
        putLong(key, value)
        apply()
    }

    private fun storeInttom(key: String, value: Int) = editortom.run {
        putInt(key, value)
        apply()
    }

    private fun storeBooleantom(key: String, value: Boolean) = editortom.run {
        putBoolean(key, value)
        apply()
    }

    private fun getStringtom(key: String) = sharedPreftom.getString(key, "")

    var oURl: String?
        get() = sharedPreftom.getString(OURLtom, null)
        set(setOurl) = storeStringtom(OURLtom, setOurl)

    var sslSha: String?
        get() = sharedPreftom.getString(SSLtom, "")
        set(setsslSha) = storeStringtom(SSLtom, setsslSha)

    var sslUrl: String?
        get() = sharedPreftom.getString(SSLURLtom, "")
        set(setsslUrl) = storeStringtom(SSLURLtom, setsslUrl)

    var tAll: Int
        get() = sharedPreftom.getInt(TALLtom, 5)
        set(setLastTime) = storeInttom(TALLtom, setLastTime)

    var tStart: Int
        get() = sharedPreftom.getInt(TSTARTtom, 10)
        set(setLastTime) = storeInttom(TSTARTtom, setLastTime)

    var isAppUpdate: Boolean
        get() = sharedPreftom.getBoolean("isAppUpdate", true)
        set(setisUpdate) = storeBooleantom("isAppUpdate", setisUpdate)

    var privacyPolicy: String?
        get() = sharedPreftom.getString(PRIVACYPOLICYtom, "http://www.google.com")
        set(setPrivacyPolicy) = storeStringtom(PRIVACYPOLICYtom, setPrivacyPolicy)

    var termsAndCondition: String?
        get() = sharedPreftom.getString(TERMSANDCONDITIONStom, "http://www.google.com")
        set(setTermsAndCondition) = storeStringtom(PRIVACYPOLICYtom, setTermsAndCondition)

    var moreApps: String?
        get() = sharedPreftom.getString(MOREAPPStom, "https://play.google.com/store/apps/developer?id=")
        set(setMoreApps) = storeStringtom(MOREAPPStom, setMoreApps)

    var isStartScreen: Boolean
        get() = sharedPreftom.getBoolean(ISSTARTSCREENtom, true)
        set(setIsStartScreen) = storeBooleantom(ISSTARTSCREENtom, setIsStartScreen)

    var showCount: Int
        get() = sharedPreftom.getInt(SHOW_COUNTtom, 3)
        set(setShowCount) = storeInttom(SHOW_COUNTtom, setShowCount)

    private fun verifyInstallerId(context: Context): Boolean {
        val validInstallers: List<String> = ArrayList(listOf("com.android.vending", "com.google.android.feedback"))
        val installer = context.packageManager.getInstallerPackageName(context.packageName)
        return installer != null && validInstallers.contains(installer)
    }

    fun setopenIdtom(id: String?) {
        storeStringtom(OPENADtom, id)
    }

    fun openIdtom(): String? {
        return if (BuildConfig.DEBUG)
            "ca-app-pub-3940256099942544/3419835294"
        else {
            if (verifyInstallerId(mcontext!!))
                if (sharedPreftom.getString(OPENADtom, null) != null) sharedPreftom.getString(OPENADtom, null)
                else null
            else null
        }
    }

    fun setfIdtom(id: String?) {
        storeStringtom(INTERSTITALtom, id)
    }

    fun fIdtom(): String? {
        return if (BuildConfig.DEBUG)
            "ca-app-pub-3940256099942544/1033173712"
        else {
            if (verifyInstallerId(mcontext!!))
                if (sharedPreftom.getString(INTERSTITALtom, null) != null) sharedPreftom.getString(INTERSTITALtom, null)
                else null
            else null
        }
    }

    fun setGBannerIDtom(id: String?) {
        storeStringtom(BANNERtom, id)
    }

    fun getGBannerIDtom(): String? {
        return if (BuildConfig.DEBUG)
            "ca-app-pub-3940256099942544/6300978111"
        else {
            if (verifyInstallerId(mcontext!!)) {
                if (sharedPreftom.getString(BANNERtom, null) != null) sharedPreftom.getString(BANNERtom, null)
                else null
            } else null
        }
    }

    fun setnadIdtom(id: String?) {
        storeStringtom(NADIDtom, id)
    }

    fun nadIdtom(): String? {
        return if (BuildConfig.DEBUG)
            "ca-app-pub-3940256099942544/2247696110"
        else {
            if (verifyInstallerId(mcontext!!)) {
                if (sharedPreftom.getString(NADIDtom, null) != null) sharedPreftom.getString(NADIDtom, null)
                else null
            } else null
        }
    }

    fun setrIdtom(id: String?) {
        storeStringtom(RIDtom, id)
    }

    fun rId(): String? {
        return if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/5224354917"
        } else {
            if (verifyInstallerId(mcontext!!)) {
                if (sharedPreftom.getString(RIDtom, null) != null) sharedPreftom.getString(RIDtom, null)
                else null
            } else null
        }
    }

    private fun getRandomErm(): Int {
        return sharedPreftom.getInt(RANDOMERMtom, 3)
    }

    fun setRandomOther(id: Int) {
        storeInttom(RANDOMOTHERtom, id)
    }

    private fun getRandomOther(): Int {
        return sharedPreftom.getInt(RANDOMOTHERtom, 3)
    }


    var installTimegoa: Long
        get() = sharedPreftom.getLong(INSTALLTIMEgoa, 0)
        set(setInstallTime) {
            val editor1goa = sharedPreftom.edit()
            editor1goa.putLong(INSTALLTIMEgoa, setInstallTime)
            editor1goa.apply()
        }


    var dynamicDaysgoa: Int
        get() = sharedPreftom.getInt(DYNAMICDAYSgoa, 0)
        set(setDynamicDays) {
            val editor1goa = sharedPreftom.edit()
            editor1goa.putInt(DYNAMICDAYSgoa, setDynamicDays)
            editor1goa.apply()
        }

    var dynamicShowsgoa: Boolean
        get() = sharedPreftom.getBoolean(DYNAMICSHOWSgoa, false)
        set(setDynamicShows) {
            val editor1goa = sharedPreftom.edit()
            editor1goa.putBoolean(DYNAMICSHOWSgoa, setDynamicShows)
            editor1goa.apply()
        }


    var homeAdShowgoa: Boolean
        get() = sharedPreftom.getBoolean(HOMEADSHOWgoa, false)
        set(setHomeAdShow) {
            val editor1goa = sharedPreftom.edit()
            editor1goa.putBoolean(HOMEADSHOWgoa, setHomeAdShow)
            editor1goa.apply()
        }
}