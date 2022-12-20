package com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class TomPreferenceManager(var mContext: Context) {

    //region Object Declaration

    private val sharedPreferenceFileNametom = "shared_preferences"
    private val sharedPreferencestom: SharedPreferences = mContext.getSharedPreferences(sharedPreferenceFileNametom, MODE_PRIVATE)

    //endregion

    //region Save Value
    fun save(key: String, value: String?) {
        val editortom: SharedPreferences.Editor = sharedPreferencestom.edit()
        editortom.putString(key, value ?: "")
        editortom.apply()
    }

    fun save(key: String, value: Int) {
        val editortom: SharedPreferences.Editor = sharedPreferencestom.edit()
        editortom.putInt(key, value)
        editortom.apply()
    }

    fun save(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferencestom.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun save(key: String, value: Float) {
        val editor: SharedPreferences.Editor = sharedPreferencestom.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun save(key: String, value: Long) {
        val editortom: SharedPreferences.Editor = sharedPreferencestom.edit()
        editortom.putLong(key, value)
        editortom.apply()
    }
    //endregion

    //region Get Value
    fun getStringtom(key: String): String {
        return sharedPreferencestom.getString(key, "")!!
    }

    fun getInttom(key: String, defaultValue: Int = -1): Int {
        return sharedPreferencestom.getInt(key, defaultValue)
    }

    fun getLongtom(key: String): Long {
        return sharedPreferencestom.getLong(key, 0L)
    }

    fun getFloattom(key: String): Float {
        return sharedPreferencestom.getFloat(key, 0f)
    }

    fun getBooleantom(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferencestom.getBoolean(key, defaultValue)
    }
    //endregion

    //region Clear Preference
    fun clearAllPreferences() {
        val editortom: SharedPreferences.Editor = sharedPreferencestom.edit()
        editortom.clear()
        editortom.apply()
    }

    fun clearPreference(key: String) {
        val editortom: SharedPreferences.Editor = sharedPreferencestom.edit()
        editortom.remove(key)
        editortom.apply()
    }
    //endregion
}