package com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime

object TomCommon {

    @JvmField
    var PREFNAMEtom = "JAKKAASS"

    fun killMe(activity: Activity) {
        activity.finishAffinity()
    }

    fun setUpadDialogtom(activity: Activity) {
        try {
            TomAdsGlobalClassEveryTime.setAdShowDialogtom(activity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("HardwareIds")
    fun getAId(activity: Context): String? {
        return Settings.Secure.getString(activity.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun whatsApp(mobileNumberwtsap: String?, context: Context) {
        val isWhatsappInstalledtom = wappInstalledOrNottom("com.whatsapp", context)
        if (isWhatsappInstalledtom) {
            val sendIntenttom = Intent("android.intent.action.MAIN")
            sendIntenttom.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
            sendIntenttom.putExtra("jid", PhoneNumberUtils.stripSeparators(mobileNumberwtsap) + "@s.whatsapp.net")
            context.startActivity(sendIntenttom)
        } else {
            val uritom = Uri.parse("market://details?id=com.whatsapp")
            val goToMarkettom = Intent(Intent.ACTION_VIEW, uritom)
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT).show()
            context.startActivity(goToMarkettom)
        }
    }

    private fun wappInstalledOrNottom(targetPackage: String, context: Context): Boolean {
        return try {
            val pmtom = context.packageManager
            pmtom.getPackageInfo(targetPackage, PackageManager.GET_META_DATA)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}