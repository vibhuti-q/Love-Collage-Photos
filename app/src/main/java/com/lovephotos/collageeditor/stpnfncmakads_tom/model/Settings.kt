package com.lovephotos.collageeditor.stpnfncmakads_tom.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Settings : Serializable {
    @SerializedName("v")
    val version: Int = 0

    @SerializedName("vm")
    var versionMessage: String?=null

    @SerializedName("iMnc")
    var isMaintaince:Boolean = false

    @SerializedName("mM")
    var maintainceMessage: String? = null

    @SerializedName("iMo")
    var isMoved:Boolean = false

    @SerializedName("mU")
    var movedURL: String? = null

    @SerializedName("mD")
    var movedDescription: String?=null

    @SerializedName("gB")
    var googleBanner: String?=null

    @SerializedName("gI")
    var googleInterstitial: String?=null

    @SerializedName("gN")
    var googleNative: String?=null

    @SerializedName("gR")
    var googleRewarded: String?=null

    @SerializedName("gO")
    var googleOpenAdId: String?=null

    @SerializedName("isS")
    val isStartScreen:Boolean = false

    @SerializedName("pP")
    var privacyPolicy: String?="http://www.google.com"

    @SerializedName("tC")
    var termsandcondition: String?="http://www.google.com"

    @SerializedName("mA")
    var moreApps: String?="https://play.google.com/store/apps/developer?id="

    @SerializedName("sC")
    val showCount: Int = 2



    @SerializedName("dMSW")
    var dynamicShows:Boolean = false

    @SerializedName("dMDY")
    val dynamicDays: Int = 0

    @SerializedName("haOO")
    var homeAdShow:Boolean = false


}