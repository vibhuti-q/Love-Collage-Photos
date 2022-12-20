package com.lovephotos.collageeditor.activity_tom

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.media.ExifInterface
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lovephotos.collageeditor.Helper_tom.TomPermissionManager
import com.lovephotos.collageeditor.Helper_tom.gone
import com.lovephotos.collageeditor.R
import com.lovephotos.collageeditor.TomCollageApplication
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.isValidClick
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.scanMediaFile
import com.lovephotos.collageeditor.Utilities_tom.TomConstants
import com.lovephotos.collageeditor.collage_tom.TomChoiceFrameActivityStyle
import com.lovephotos.collageeditor.custom_tom.TomPermissionInstructionDialog
import com.lovephotos.collageeditor.listener_tom.TomCustomDialogClickListener
import com.lovephotos.collageeditor.listener_tom.TomPermissionManagerListener
import com.lovephotos.collageeditor.mirrorlib_tom.TomUtility
import com.lovephotos.collageeditor.model.PermissionInstructionModel
import com.lovephotos.collageeditor.mynotification_goa.GoaAllNotificationDialog
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomCommon
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banner_ad_common_tom.*
import java.io.*
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class TomHomeActivity : AppCompatActivity(),
    TomPermissionManagerListener,
    TomCustomDialogClickListener {
    private val mContexttom: Context = this
    var sharedpreferencestom: SharedPreferences? = null
    private var displayadtom = 1
    private var whichAdFirsttom = 1
    var whichActivitytoStarttom = 0
    private var isActivityLefttom = false
    var app: TomCollageApplication? = null
    private var menutom: ImageView? = null
    private var montagens_sizetom: LinearLayout? = null
    private var montagens_collagetom: LinearLayout? = null
    private var montagens_mycreationtom: LinearLayout? = null
    private var llSettings: LinearLayout? = null
    var activitytom: AppCompatActivity? = null
    private var temp_filetom: File? = null
    private var permissionManagerTvstom: TomPermissionManager? = null

    private var admobObjEverySTEDtom: TomAdsGlobalClassEveryTime? = null

    @Inject
    lateinit var prefenrencMyPreferenceManagertom: TomMyPreferenceManager
    lateinit var preferencesgoa : SharedPreferences
    var dialogcatgoa : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2_tom)
        dialogcatgoa = Dialog(this@TomHomeActivity)
        preferencesgoa = getSharedPreferences(getString(R.string.app_name)+"photocollage", Context.MODE_PRIVATE)

        if(preferencesgoa.getBoolean(TomCollageApplication.isInAppMessagegoa,false)){
            openNotificationDialoggoa()
        }

        TomAdsGlobalClassEveryTime().showAdInHomeScreen(this@TomHomeActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {}
        }, TomMyPreferenceManager(this@TomHomeActivity).fIdtom())

        admobObjEverySTEDtom = TomAdsGlobalClassEveryTime()
        val displaytom = windowManager.defaultDisplay
        CAMERA_WIDTHtom = displaytom.width
        CAMERA_HEIGHTtom = displaytom.height
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE)
        isActivityLefttom = false
        activitytom = this@TomHomeActivity
        displayadtom = sharedpreferencestom!!.getInt("displayad", 1)
        whichAdFirsttom = sharedpreferencestom!!.getInt("whichAdFirst", 1)
        montagens_sizetom = findViewById(R.id.montagens_sizetom)
        montagens_collagetom = findViewById(R.id.montagens_collagetom)
        montagens_mycreationtom = findViewById(R.id.montagens_mycreationtom)
        llSettings = findViewById(R.id.llSettings)

        app = application as TomCollageApplication
        permissionManagerTvstom = TomPermissionManager(mContexttom, this)
//        showInstructionDialogtom()
        montagens_sizetom!!.setOnClickListener { MontagenSizeFunctiontom() }
        montagens_collagetom!!.setOnClickListener { MontagenCollageFunctiontom() }
        montagens_mycreationtom!!.setOnClickListener { MontagenMycreationFunctiontom() }

        llSettings!!.setOnClickListener {
            try {
                startActivity(Intent(this@TomHomeActivity, TomSettingsActivity::class.java))
            } catch (epop: Exception) {}
        }


      /*  menutom = findViewById(R.id.option_menutom)

        menutom!!.setOnClickListener {
            val popuptom = PopupMenu(this@TomHomeActivity, menutom)
            popuptom.menuInflater.inflate(R.menu.main, popuptom.menu)
            popuptom.setOnMenuItemClickListener { item: MenuItem ->
                when (item.title) {
                    "Share" -> Sharetom()
                    "Rate Us" -> RateUstom()
                    "More Apps" -> MoreAppstom()
                    "Privacy Policy" -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(prefenrencMyPreferenceManagertom.privacyPolicy)))
                }
                true
            }
            popuptom.show()
        }*/
    }

    private fun openNotificationDialoggoa() {

        val imageUrlgoa=preferencesgoa.getString(TomCollageApplication.imageUrlgoa,null)
        val text1goa=preferencesgoa.getString(TomCollageApplication.text1goa,null)
        val text2goa=preferencesgoa.getString(TomCollageApplication.text2goa,null)
        val buttonTextgoa=preferencesgoa.getString(TomCollageApplication.buttonTextgoa,null)
        val buttonActionUrlgoa=preferencesgoa.getString(TomCollageApplication.buttonActionUrlgoa,null)

        val notificationDialoggoa = GoaAllNotificationDialog(this@TomHomeActivity, imageUrlgoa, text1goa,text2goa,buttonTextgoa,buttonActionUrlgoa)
        notificationDialoggoa.requestWindowFeature(Window.FEATURE_NO_TITLE)
        notificationDialoggoa.setCanceledOnTouchOutside(false)
        notificationDialoggoa.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        notificationDialoggoa.setListenergoa(object : GoaAllNotificationDialog.OnButtonClick {
            override fun onPositiveButtonClickgoa(linkrrb: String?) {
                if(linkrrb.isNullOrEmpty()){
                    notificationDialoggoa.dismiss()
                }
                else{
                    try {
                        val urlgoa = linkrrb
                        val igoa = Intent(Intent.ACTION_VIEW)
                        igoa.data = Uri.parse(urlgoa)
                        startActivity(igoa)
                    } catch (egoa: Exception) {
                        egoa.printStackTrace()
                        Toast.makeText(this@TomHomeActivity,"", Toast.LENGTH_SHORT).show()
                    } finally {
                        notificationDialoggoa.dismiss()
                    }
                }
            }
            override fun onNegativeButtonClickgoa() {
                notificationDialoggoa.dismiss()
            }
        })

        if (!isFinishing) {
            preferencesgoa.edit().clear().apply()
            notificationDialoggoa.show()
        }
    }


    private fun MontagenSizeFunctiontom() {
        if (!isValidClick()) return
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomHomeActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                if (isFileManagerPermissionGranted) {
                    file_environmenttom()
                    val localIntent1tom = Intent("android.intent.action.PICK")
                    localIntent1tom.type = "image/*"
                    startActivityForResult(localIntent1tom, IMG_FROM_GALLERYtom)
                } else requestFileManagerPermissiontom()
            }
        }, TomMyPreferenceManager(this@TomHomeActivity).fIdtom())
    }

    private fun MontagenCollageFunctiontom() {
        if (!isValidClick()) return
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomHomeActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                if (isFileManagerPermissionGranted) {
                    whichActivitytoStarttom = 1
                    replaceScreentom()
                } else requestFileManagerPermissiontom()
            }
        }, TomMyPreferenceManager(this@TomHomeActivity).fIdtom())
    }



    private fun MontagenMycreationFunctiontom() {
        if (!isValidClick()) return
        admobObjEverySTEDtom?.showIntrestrialAdstom(this@TomHomeActivity, object : TomadmobCloseEvent {
            override fun setAdmobCloseEventJusi() {
                if (isFileManagerPermissionGranted) {
                    whichActivitytoStarttom = 2
                    replaceScreentom()
                } else requestFileManagerPermissiontom()
            }
        }, TomMyPreferenceManager(this@TomHomeActivity).fIdtom())
    }

    private fun MoreAppstom() {
        if (!isValidClick()) return
        val alertDialogBuildertom = AlertDialog.Builder(this@TomHomeActivity)
        alertDialogBuildertom.setTitle("More Application")
        alertDialogBuildertom.setMessage("Hi, Take a Minute to View More Application.")
            .setCancelable(false)
            .setPositiveButton("Watch Now") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }.setNegativeButton("Remind me later") { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alertDialogtom = alertDialogBuildertom.create()
        alertDialogtom.show()
    }

    private fun Sharetom() {
        if (!isValidClick()) return
        val alertDialogBuildertom = AlertDialog.Builder(this@TomHomeActivity)
        alertDialogBuildertom.setTitle("Share")
        alertDialogBuildertom.setMessage("Hi, Take a Minute to Share This Application.")
            .setCancelable(false)
            .setPositiveButton("Share Now") { dialog: DialogInterface, _: Int ->
                val sharetom = Intent(Intent.ACTION_SEND)
                sharetom.type = "text_list/plain"
                sharetom.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                sharetom.putExtra(Intent.EXTRA_SUBJECT, " ")
                sharetom.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=$packageName")
                sharetom.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
                startActivity(Intent.createChooser(sharetom, "Share link!"))
                dialog.dismiss()
            }.setNegativeButton("Remind me later") { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alertDialogtom = alertDialogBuildertom.create()
        alertDialogtom.show()
    }

    private fun RateUstom() {
        if (!isValidClick()) return
        val alertDialogBuildertom = AlertDialog.Builder(this@TomHomeActivity)
        alertDialogBuildertom.setTitle("Rate Us")
        alertDialogBuildertom.setMessage("Hi, Take a Minute to Rate This Application and Help to Improve More New Features.")
            .setCancelable(false)
            .setPositiveButton("Rate Now") { dialog: DialogInterface, _: Int ->
                val `in` = Intent(Intent.ACTION_VIEW)
                `in`.data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                startActivity(`in`)
                dialog.dismiss()
            }
            .setNegativeButton("Remind me later") { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alertDialogtom = alertDialogBuildertom.create()
        alertDialogtom.show()
    }

    private fun file_environmenttom() {
        val fitomle = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val pathtom = externalMediaDirs.first()
                val foldertom = File(pathtom, resources.getString(R.string.app_name))
                if (!foldertom.exists()) foldertom.mkdirs()
                foldertom
            }
            else -> {
                val foldertom = File(Environment.getExternalStorageDirectory().toString() + "/" + resources.getString(R.string.app_name))
                if (!foldertom.exists()) foldertom.mkdirs()
                foldertom
            }
        }
        temp_filetom = File(fitomle, "temp.jpg")
    }

    private fun replaceScreentom() {
        if (whichActivitytoStarttom == 1) {
            startActivity(Intent(this@TomHomeActivity, TomChoiceFrameActivityStyle::class.java))
            finish()
        } else if (whichActivitytoStarttom == 2) startActivity(Intent(this@TomHomeActivity, TomMyCreationActivity::class.java))
    }

    //region Activity Lifecycle Methods
    public override fun onPause() {
        super.onPause()
        isActivityLefttom = true
    }

    public override fun onResume() {
        super.onResume()
        isActivityLefttom = false

        TomAdsGlobalClassEveryTime().showBannerAdtom(
            this, object : TomadmobCloseEvent {
                override fun setAdmobCloseEventJusi() {}
                override fun setSuccessJusi() {
                }

                override fun setFailedJusi() {
                    super.setFailedJusi()
                    adContainertom.gone()
                }
            }, prefenrencMyPreferenceManagertom.getGBannerIDtom(), adViewtom
        )
        TomCommon.setUpadDialogtom(this@TomHomeActivity)
    }

    override fun onStop() {
        super.onStop()
        isActivityLefttom = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isActivityLefttom = true
    }
    //endregion

    private fun showInstructionDialogtom() {
        if (!isFileManagerPermissionGranted) {
            val instructionstom = ArrayList<PermissionInstructionModel>()
            instructionstom.add(
                PermissionInstructionModel(
                    "Storage (Required)",
                    "Read/write photos, media and files on device.",
                    R.drawable.ic_storage_tom
                )
            )
            val dialogtom = TomPermissionInstructionDialog(mContexttom, instructionstom, this)
            dialogtom.show()
        }
    }

    private fun requestFileManagerPermissiontom() {
        val permissionstom = ArrayList<String>()
        permissionstom.add(TomConstants.FileWritePermissiontom)
        permissionstom.add(TomConstants.FileReadPermissiontom)
        permissionManagerTvstom!!.setMultiplePermissiontom(permissionstom)
    }

    private val isFileManagerPermissionGranted: Boolean
        get() = permissionManagerTvstom!!.isPermissionGrantedtom(TomConstants.FileWritePermissiontom) && permissionManagerTvstom!!.isPermissionGrantedtom(TomConstants.FileReadPermissiontom)

    @Deprecated("Deprecated in Java")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && data.data != null) {
            when (requestCode) {
                IMG_FROM_GALLERYtom -> {
                    val imgImageCaptureUri2tom = data.data
                    val filePathColumntom = arrayOf(MediaStore.Images.Media.DATA)
                    val cursortom =
                        contentResolver.query(imgImageCaptureUri2tom!!, filePathColumntom, null, null, null)
                    cursortom!!.moveToFirst()
                    val columnIndextom = cursortom.getColumnIndex(filePathColumntom[0])
                    val picturePathtom = cursortom.getString(columnIndextom)
                    cursortom.close()
                    val loadedBitmaptom = BitmapFactory.decodeFile(picturePathtom)
                    try {
                        val eitom = ExifInterface(picturePathtom)
                        val orientationtom = eitom.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL
                        )
                        bitmap = when (orientationtom) {
                            ExifInterface.ORIENTATION_ROTATE_90 -> rotate(loadedBitmaptom, 90f)
                            ExifInterface.ORIENTATION_ROTATE_180 -> rotate(loadedBitmaptom, 180f)
                            ExifInterface.ORIENTATION_ROTATE_270 -> rotate(loadedBitmaptom, 270f)
                            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flip(loadedBitmaptom, horizontal = true, vertical = false)
                            ExifInterface.ORIENTATION_FLIP_VERTICAL -> flip(loadedBitmaptom, horizontal = false, vertical = true)
                            else -> loadedBitmaptom
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    val localtom = Intent(applicationContext, TomEditActivity::class.java)
                    app!!.selectedImageUritom = imgImageCaptureUri2tom
                    app!!.isFromCameratom = false
                    startActivity(localtom)
                    finish()
                }


            }
        }
    }

    override fun onSinglePermissionGrantedtom(permissionName: String, vararg endPoint: String) {}

    override fun onMultiplePermissionGrantedtom(permissions: ArrayList<String>, vararg endPoint: String) {
        if (!isFileManagerPermissionGranted) requestFileManagerPermissiontom()
    }

    override fun onDialogOKClickedtom(endPoint: String) {
        requestFileManagerPermissiontom()
    }

    companion object {
        const val mypreferencetom = "myprefadmob"
        private const val IMG_FROM_GALLERYtom = 2
        private const val IMG_FROM_GALLERY_Mirrortom = 3
        var CAMERA_HEIGHTtom = 0

        @JvmField
        var CAMERA_WIDTHtom = 0

        @JvmField
        var bitmap: Bitmap? = null
        fun rotate(bitmap: Bitmap, degrees: Float): Bitmap {
            val matrixtom = Matrix()
            matrixtom.postRotate(degrees)
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixtom, true)
        }

        fun flip(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap {
            val matrixtom = Matrix()
            matrixtom.preScale(
                (if (horizontal) -1 else 1.toFloat()) as Float,
                (if (vertical) -1 else 1.toFloat()) as Float
            )
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixtom, true)
        }

        @Throws(IOException::class)
        private fun open_filestreamtom(inputStream: InputStream?, outputStream: OutputStream) {
            val bArrtom = ByteArray(1024)
            while (true) {
                val readtom = inputStream!!.read(bArrtom)
                if (readtom != -1) outputStream.write(bArrtom, 0, readtom) else return
            }
        }
    }
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}