package com.lovephotos.collageeditor.stpnfncmakads_tom.baseclass_tom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomPreferenceManager

abstract class TomBaseActivity : AppCompatActivity() {

    lateinit var preferenceManagerSTED: TomPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(geViewBinding())
        preferenceManagerSTED = TomPreferenceManager(this)
        observeViewModel()
    }

    protected abstract fun geViewBinding(): View
    protected abstract fun observeViewModel()

    override fun onResume() {
        super.onResume()
    }
}