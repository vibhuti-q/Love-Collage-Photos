package com.lovephotos.collageeditor.mynotification_goa

import android.app.Application
import android.content.Context
import android.content.Intent
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OneSignal
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.TomCollageApplication.Companion.buttonActionUrlgoa
import com.lovephotos.collageeditor.TomCollageApplication.Companion.buttonTextgoa
import com.lovephotos.collageeditor.TomCollageApplication.Companion.imageUrlgoa
import com.lovephotos.collageeditor.TomCollageApplication.Companion.isInAppMessagegoa
import com.lovephotos.collageeditor.TomCollageApplication.Companion.text1goa
import com.lovephotos.collageeditor.TomCollageApplication.Companion.text2goa
import com.lovephotos.collageeditor.stpnfncmakads_tom.activity_tom.TommSplashActivity


class GoaOneSignalNotificationOpenedHandler(private val contextgoa: Application): OneSignal.OSNotificationOpenedHandler {

    override fun notificationOpened(resultrrb: OSNotificationOpenedResult) {
        val datagoa = resultrrb.notification.additionalData

        if (datagoa != null && datagoa.length()>0) {

           var imageUrlJgoa:String? = null
           var text1Jgoa:String? = null
           var text2Jgoa:String? = null
           var buttonTextJgoa:String? = null
           var buttonActionUrlJgoa:String? = null

            val preferencesgoa=contextgoa.getSharedPreferences(contextgoa.getString(R.string.app_name)+ "photocollage", Context.MODE_PRIVATE)
            val editorgoa = preferencesgoa.edit()
            editorgoa.putBoolean(isInAppMessagegoa,true).apply()

            if( datagoa.has(imageUrlgoa)){
                imageUrlJgoa=datagoa.getString(imageUrlgoa)
                editorgoa.putString(imageUrlgoa, imageUrlJgoa).apply()
            }
            if (datagoa.has(text1goa)){
                text1Jgoa=datagoa.getString(text1goa)
                editorgoa.putString(text1goa, text1Jgoa).apply()
            }
            if (datagoa.has(text2goa)){
                text2Jgoa=datagoa.getString(text2goa)
                editorgoa.putString(text2goa, text2Jgoa).apply()
            }
            if (datagoa.has(buttonTextgoa)){
                buttonTextJgoa=datagoa.getString(buttonTextgoa)
                editorgoa.putString(buttonTextgoa, buttonTextJgoa).apply()
            }
            if (datagoa.has(buttonActionUrlgoa)){
                buttonActionUrlJgoa=datagoa.getString(buttonActionUrlgoa)
                editorgoa.putString(buttonActionUrlgoa, buttonActionUrlJgoa).apply()
            }

            val intentgoa = Intent(contextgoa, TommSplashActivity::class.java)
            intentgoa.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            contextgoa.startActivity(intentgoa)
        }
        else{

            val intentgoa = Intent(contextgoa, TommSplashActivity::class.java)
            intentgoa.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            contextgoa.startActivity(intentgoa)
        }
    }

}