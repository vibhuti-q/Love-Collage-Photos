package com.lovephotos.collageeditor.custom_tom

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.deleteFile
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.isValidClick
import com.lovephotos.collageeditor.Utilities_tom.TomConstants
import com.lovephotos.collageeditor.activity_tom.TomMyCreationActivity
import com.lovephotos.collageeditor.baseclass_tom.TomBaseAdapter
import com.lovephotos.collageeditor.databinding.DialogImagePreviewBinding
import com.lovephotos.collageeditor.listener_tom.TomCustomConfirmationClickListener
import com.lovephotos.collageeditor.listener_tom.TomItemClickListener
import com.lovephotos.collageeditor.model.CustomConfirmationDialogModel
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import java.io.File

class TomCustomMediaPreviewDialog(private val mContext: Context, private var selectedPosition: Int, private var media: ArrayList<String>) :
    Dialog(mContext,R.style.FullScreenDialog),
    TomItemClickListener, TomCustomConfirmationClickListener {

    private val bindingtom: DialogImagePreviewBinding by lazy { DialogImagePreviewBinding.inflate(layoutInflater) }

    private lateinit var adaptertom: TomBaseAdapter

    private lateinit var fileInfotom: String
    var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        adaptertom = TomBaseAdapter(mContext, media, R.layout.item_image_preview, this)
        bindingtom.viewPagertom.adapter = adaptertom
        bindingtom.viewPagertom.currentItem = selectedPosition

        bindingtom.executePendingBindings()

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(bindingtom.root)

        bindingtom.ivClosetom.setOnClickListener {
            if (!isValidClick()) return@setOnClickListener
            dismiss()
        }

        bindingtom.deleteLayouttom.setOnClickListener {
            if (!isValidClick()) return@setOnClickListener
            val confirmationDialogModel = CustomConfirmationDialogModel(
                dialogTitletom = "Delete Confirmation!!!",
                dialogDescriptiontom = "Are you sure, want to delete this Media ?",
                dialogEndPointtom = TomConstants.deleteMediatom,
                listenertom = this@TomCustomMediaPreviewDialog
            )
            TomCustomConfirmationDialog(mContext, confirmationDialogModel).show()
        }

        bindingtom.shareLayouttom.setOnClickListener {
            if (!isValidClick()) return@setOnClickListener
            admobObjEverySTEDtom?.showIntrestrialAdstom(mContext as Activity, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {
                    val shareIntenttom = Intent(Intent.ACTION_SEND)
                    shareIntenttom.type = "image/png"
                    shareIntenttom.putExtra(Intent.EXTRA_SUBJECT, mContext.resources.getString(R.string.app_name))
                    val imageFileToSharetom = File(fileInfotom)
                    val apkURItom = FileProvider.getUriForFile(mContext, mContext.applicationContext.packageName + ".provider", imageFileToSharetom)
                    shareIntenttom.putExtra(Intent.EXTRA_STREAM, apkURItom)
                    mContext.startActivity(Intent.createChooser(shareIntenttom, "Share Image!"))
                }
            }, TomMyPreferenceManager(mContext).fIdtom())
        }

        bindingtom.viewPagertom.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                selectedPosition = position
                fileInfotom = media[selectedPosition]
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectedPosition = position
                fileInfotom = media[selectedPosition]
            }
        })
    }

    override fun onPositiveButtonClicktom(endpoint: String) {

        mContext as TomMyCreationActivity

        when (endpoint) {
            TomConstants.deleteMediatom -> {
                val isDeletedtom = File(fileInfotom).deleteRecursively()
                deleteFile(mContext, fileInfotom)
                if (isDeletedtom) {
                    media.removeAt(selectedPosition)
                    adaptertom.notifyItemRemoved(selectedPosition)
                    with(mContext) {
                        refreshListtom()
                    }

                    if (media.size <= 0) {
                        dismiss()
                        return
                    } else if (selectedPosition >= media.size)
                        selectedPosition = media.size - 1
                    fileInfotom = media[selectedPosition]
                    bindingtom.viewPagertom.currentItem = selectedPosition
                }
            }
        }
    }

    override fun onNegativeButtonClickJusi(endpoint: String) {
    }

    override fun onItemClicktom(itemPosition: Int, vararg objects: Any?) {
    }
}