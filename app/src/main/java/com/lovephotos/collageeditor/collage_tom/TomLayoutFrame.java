package com.lovephotos.collageeditor.collage_tom;

import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.getBitmapFromView;
import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.isValidClick;
import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.scanMediaFile;
import static com.lovephotos.collageeditor.activity_tom.TomEditActivity.frameId_stom;
import static com.lovephotos.collageeditor.adapter_tom.TomImageAdapter.mThumbIdstom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.TomCollageApplication;
import com.lovephotos.collageeditor.Utilities_tom.TomApplicationProperties;
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils;
import com.lovephotos.collageeditor.Utilities_tom.TomConnectionDetector;
import com.lovephotos.collageeditor.activity_tom.TomHomeActivity;
import com.lovephotos.collageeditor.activity_tom.TomShareActivity;
import com.lovephotos.collageeditor.activity_tom.TomTextActivitytom;
import com.lovephotos.collageeditor.adapter_tom.TomImageAdapter;
import com.lovephotos.collageeditor.custom_tom.TomCustomConfirmationDialog;
import com.lovephotos.collageeditor.listener_tom.TomCustomConfirmationClickListener;
import com.lovephotos.collageeditor.model.CustomConfirmationDialogModel;
import com.lovephotos.collageeditor.sticker_tom.TomGalleryAdapter;
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent;
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime;
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TomLayoutFrame extends AppCompatActivity implements OnClickListener {

    public static final String mypreferencetom = "myprefadmob";
    private final String[] imagePathForViewtom = new String[10];
    private final Boolean[] picViewFlagtom = new Boolean[10];
    public Handler ImageViewLongClicktom = new handlerdata();
    SharedPreferences sharedpreferencestom;
    TomConnectionDetector connectionDetectortom;
    int displayadtom = 1;
    int whichAdFirsttom = 1;
    boolean isActivityLefttom;
    FrameLayout framePhotoGallerytom;
    ImageView frameImagetom;
    Gallery frameGallerytom;
    RecyclerView mRecyclerViewtom;
    LinearLayout spacer1tom;
    TomGalleryAdapter mGalleryAdaptertom;
    Matrix matrixtom;
    Bitmap rotatedtom;
    boolean isTexttom = false;
    TomCollageApplication apptom;
    byte[] textIdtom;
    Bitmap localBitmaptom;
    Bitmap bitmap2tom;
    String albamtom;
    String strtom;
    Activity activitytom;
    private ImageButton backBtntom;
    private ImageButton btn_donetom;
    private LinearLayout lsavetom;
    private com.xiaopo.flying.sticker.StickerView mStickerViewtom;
    private ViewSwitcher viewSwitchertom;
    private Button add_imagetom;
    private Button back_imagetom;
    private Button changeBgColortom;
    private Button button_square_frametom;
    private Button button_mirror_stickertom;
    private Button button_square_texttom;
    private ImageButton backViewtom;
    private ImageButton replaceImagetom;
    private ImageButton rotateRighttom;
    private ImageButton rotateLefttom;
    private ImageButton verticalFlitom;
    private ImageButton horizontalFlitom;
    private HorizontalScrollView colorTabletomtom;
    private Bitmap mBitmaptom;
    private Tomcustom_imageviewtom pic0tom = null;
    private Tomcustom_imageviewtom pic1tom = null;
    private Tomcustom_imageviewtom pic2tom = null;
    private Tomcustom_imageviewtom pic3tom = null;
    private Tomcustom_imageviewtom pic4tom = null;
    private Tomcustom_imageviewtom pic5tom = null;
    private Tomcustom_imageviewtom pic6tom = null;
    private Tomcustom_imageviewtom pic7tom = null;
    private Tomcustom_imageviewtom pic8tom = null;
    private Tomcustom_imageviewtom pic9tom = null;
    private int picCounttom = 0;
    private int selImageViewtom = -1;
    private Animation slide_in_lefttom;
    private Animation slide_out_righttom;
    private int totalSupportedPictom = 0;

    public static Bitmap loadBitmapFromViewtom(View v) {
        Bitmap btom;
        try {
            btom = getBitmapFromView(v);
        } catch (Exception e) {
            try {
                btom = getBitmapFromView(v, Color.TRANSPARENT);
            } catch (Exception ex) {
                btom = Bitmap.createBitmap(v.getDrawingCache());
            }
        }
        Canvas canvastom = new Canvas(btom);
        v.draw(canvastom);
        return btom;
    }

    @SuppressLint({"ResourceType", "StaticFieldLeak"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE);
        activitytom = TomLayoutFrame.this;

        connectionDetectortom = new TomConnectionDetector(getApplicationContext());
        boolean isInternetPresent = connectionDetectortom.isConnectingToInternettom();

        displayadtom = sharedpreferencestom.getInt("displayad", 1);
        whichAdFirsttom = sharedpreferencestom.getInt("whichAdFirst", 1);


        //region setContentView
        switch (getIntent().getIntExtra(TomChoiceFrameActivityStyle.NUMBERFRAMEtom, 1)) {
            case 1:
                setContentView(R.layout.frame1_tom);
                this.totalSupportedPictom = 2;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 2:
                setContentView(R.layout.frame2_tom);
                this.totalSupportedPictom = 2;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 3:
                setContentView(R.layout.frame3_tom);
                this.totalSupportedPictom = 3;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 4:
                setContentView(R.layout.frame4_tom);
                this.totalSupportedPictom = 3;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 5:
                setContentView(R.layout.frame5_tom);
                this.totalSupportedPictom = 3;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 6:
                setContentView(R.layout.frame6_tom);
                this.totalSupportedPictom = 4;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 7:
                setContentView(R.layout.frame7_tom);
                this.totalSupportedPictom = 5;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 8:
                setContentView(R.layout.frame8_tom);
                this.totalSupportedPictom = 5;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 9:
                setContentView(R.layout.frame9_tom);
                this.totalSupportedPictom = 5;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 10:
                setContentView(R.layout.frame10_tom);
                this.totalSupportedPictom = 6;
                this.picCounttom = 0;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                break;
            case 11:
                setContentView(R.layout.frame11_tom);
                this.totalSupportedPictom = 3;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 12:
                setContentView(R.layout.frame12_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 13:
                setContentView(R.layout.frame13_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 14:
                setContentView(R.layout.frame14_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case ViewDragHelper.EDGE_ALL /*15*/:
                setContentView(R.layout.frame15_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 16:
                setContentView(R.layout.frame16_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 17:
                setContentView(R.layout.frame17_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 18:
                setContentView(R.layout.frame18_tom);
                this.totalSupportedPictom = 8;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 19:
                setContentView(R.layout.frame19_tom);
                this.totalSupportedPictom = 9;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic8tom = findViewById(R.id.pic8tom);
                this.pic8tom.setMainactivitytom(this);
                this.pic8tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 20:
                setContentView(R.layout.frame20_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 21:
                setContentView(R.layout.frame21_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 22:
                setContentView(R.layout.frame22_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 23:
                setContentView(R.layout.frame23_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 24:
                setContentView(R.layout.frame24_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 25:
                setContentView(R.layout.frame25_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 26:
                setContentView(R.layout.frame26_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 27:
                setContentView(R.layout.frame27_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 28:
                setContentView(R.layout.frame28_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 29:
                setContentView(R.layout.frame29_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 30:
                setContentView(R.layout.frame30_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 31:
                setContentView(R.layout.frame31_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 32:
                setContentView(R.layout.frame32_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 33:
                setContentView(R.layout.frame33_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 34:
                setContentView(R.layout.frame34_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 35:
                setContentView(R.layout.frame35_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 36:
                setContentView(R.layout.frame36_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 37:
                setContentView(R.layout.frame37_tom);
                this.totalSupportedPictom = 10;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic8tom = findViewById(R.id.pic8tom);
                this.pic8tom.setMainactivitytom(this);
                this.pic8tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic9tom = findViewById(R.id.pic9tom);
                this.pic9tom.setMainactivitytom(this);
                this.pic9tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 38:
                setContentView(R.layout.frame38_tom);
                this.totalSupportedPictom = 6;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 39:
                setContentView(R.layout.frame39_tom);
                this.totalSupportedPictom = 5;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 40:
                setContentView(R.layout.frame40_tom);
                this.totalSupportedPictom = 4;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 41:
                setContentView(R.layout.frame41_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 42:
                setContentView(R.layout.frame42_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 43:
                setContentView(R.layout.frame43_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 44:
                setContentView(R.layout.frame44_tom);
                this.totalSupportedPictom = 8;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 45:
                setContentView(R.layout.frame45_tom);
                this.totalSupportedPictom = 8;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 46:
                setContentView(R.layout.frame46_tom);
                this.totalSupportedPictom = 7;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 47:
                setContentView(R.layout.frame47_tom);
                this.totalSupportedPictom = 10;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic8tom = findViewById(R.id.pic8tom);
                this.pic8tom.setMainactivitytom(this);
                this.pic8tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic9tom = findViewById(R.id.pic9tom);
                this.pic9tom.setMainactivitytom(this);
                this.pic9tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
            case 48:
                setContentView(R.layout.frame48_tom);
                this.totalSupportedPictom = 8;
                this.pic0tom = findViewById(R.id.pic0tom);
                this.pic0tom.setMainactivitytom(this);
                this.pic0tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic1tom = findViewById(R.id.pic1tom);
                this.pic1tom.setMainactivitytom(this);
                this.pic1tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic2tom = findViewById(R.id.pic2tom);
                this.pic2tom.setMainactivitytom(this);
                this.pic2tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic3tom = findViewById(R.id.pic3tom);
                this.pic3tom.setMainactivitytom(this);
                this.pic3tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic4tom = findViewById(R.id.pic4tom);
                this.pic4tom.setMainactivitytom(this);
                this.pic4tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic5tom = findViewById(R.id.pic5tom);
                this.pic5tom.setMainactivitytom(this);
                this.pic5tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic6tom = findViewById(R.id.pic6tom);
                this.pic6tom.setMainactivitytom(this);
                this.pic6tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.pic7tom = findViewById(R.id.pic7tom);
                this.pic7tom.setMainactivitytom(this);
                this.pic7tom.setLongClickHandlertom(this.ImageViewLongClicktom);
                this.picCounttom = 0;
                break;
        }
        //endregion

        apptom = ((TomCollageApplication) getApplication());


        lsavetom = findViewById(R.id.lsavetom);
        viewSwitchertom = findViewById(R.id.viewSwitchertom);
        LinearLayout spacer01 = findViewById(R.id.spacer01tom);
        spacer1tom = findViewById(R.id.spacer1tom);

        colorTabletomtom = findViewById(R.id.hsvtom);

        framePhotoGallerytom = findViewById(R.id.framePhotoGallerytom);

        framePhotoGallerytom.setDrawingCacheEnabled(true);
        framePhotoGallerytom.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        framePhotoGallerytom.layout(0, 0, framePhotoGallerytom.getMeasuredWidth(), framePhotoGallerytom.getMeasuredHeight());

        framePhotoGallerytom.buildDrawingCache(true);

        frameImagetom = findViewById(R.id.frameImagetom);
        mStickerViewtom = findViewById(R.id.sticker_viewtom);

        mRecyclerViewtom = findViewById(R.id.recycler_viewtom);
        frameGallerytom = findViewById(R.id.frameGallerytom);

        button_square_frametom = findViewById(R.id.button_square_frametom);
        button_mirror_stickertom = findViewById(R.id.button_mirror_stickertom);
        button_square_texttom = findViewById(R.id.button_square_texttom);

        this.add_imagetom = findViewById(R.id.add_imagetom);
        this.back_imagetom = findViewById(R.id.back_imagetom);
        this.backBtntom = findViewById(R.id.back_btntom);
        this.btn_donetom = findViewById(R.id.btn_donetom);
        this.backViewtom = findViewById(R.id.backViewtom);
        this.rotateRighttom = findViewById(R.id.rotateRighttom);
        this.rotateLefttom = findViewById(R.id.rotateLefttom);
        this.replaceImagetom = findViewById(R.id.replaceImagetom);
        this.horizontalFlitom = findViewById(R.id.horizontalFliptom);
        this.verticalFlitom = findViewById(R.id.verticalFliptom);
        this.changeBgColortom = findViewById(R.id.change_bgColortom);

        Display displaytom = getWindowManager().getDefaultDisplay();
        if (((double) (((float) displaytom.getHeight()) / ((float) displaytom.getWidth()))) < 1.4d) {
            LayoutParams spacer01Paramstom = (LayoutParams) spacer01.getLayoutParams();
            spacer01Paramstom.weight = 0.05f;
            spacer01.setLayoutParams(spacer01Paramstom);
            LayoutParams spacer1Paramstom = (LayoutParams) spacer1tom.getLayoutParams();
            spacer1Paramstom.weight = 0.8f;
            spacer1Paramstom.topMargin = 5;
            spacer1tom.setLayoutParams(spacer1Paramstom);
        }

        this.colorTabletomtom.setVisibility(View.GONE);
        this.selImageViewtom = -1;
        this.viewSwitchertom.setDisplayedChild(0);
        this.slide_in_lefttom = AnimationUtils.loadAnimation(this, 17432578);
        this.slide_out_righttom = AnimationUtils.loadAnimation(this, 17432579);
        this.viewSwitchertom.setInAnimation(this.slide_in_lefttom);
        this.viewSwitchertom.setOutAnimation(this.slide_out_righttom);
        this.colorTabletomtom.setAnimation(this.slide_in_lefttom);

        for (int itom = 0; itom < 10; itom++) {
            this.imagePathForViewtom[itom] = "";
            this.picViewFlagtom[itom] = false;
        }

        this.add_imagetom.setOnClickListener(v -> {
            isTexttom = false;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            Intent intent = new Intent(TomAction.ACTION_MULTIPLE_PICKtom);
            intent.putExtra("selectionLimit", totalSupportedPictom);
            startActivityForResult(intent, 200);
        });

        this.back_imagetom.setOnClickListener(v -> {
            isTexttom = false;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            frameImagetom.setImageResource(0);
            this.lsavetom.setBackgroundColor(Color.WHITE);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_default_tom);
            for (int jtom = 0; jtom < 10; jtom++) {
                setImageForViewtom(bitmap, jtom, false);
                imagePathForViewtom[jtom] = "";
                picViewFlagtom[jtom] = false;
            }
            picCounttom = 0;
        });

        this.changeBgColortom.setOnClickListener(v -> {
            isTexttom = false;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.VISIBLE);
            frameGallerytom.setVisibility(View.GONE);
            if (colorTabletomtom.getVisibility() == View.VISIBLE)
                colorTabletomtom.setVisibility(View.GONE);
            else
                colorTabletomtom.setVisibility(View.VISIBLE);
        });

        button_square_frametom.setOnClickListener(v -> {
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);

            mStickerViewtom.setLocked(true);
            isTexttom = false;

            if (frameGallerytom.getVisibility() == View.VISIBLE)
                frameGallerytom.setVisibility(View.INVISIBLE);
            else
                frameGallerytom.setVisibility(View.VISIBLE);
        });

        frameGallerytom.setAdapter(new TomImageAdapter(TomLayoutFrame.this));

        frameGallerytom.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0)
                frameImagetom.setImageResource(0);
            else
                frameImagetom.setImageResource(mThumbIdstom[position]);
        });

        button_mirror_stickertom.setOnClickListener(v -> {
            mStickerViewtom.setLocked(false);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            if (mRecyclerViewtom.getVisibility() == View.VISIBLE)
                mRecyclerViewtom.setVisibility(View.INVISIBLE);
            else
                mRecyclerViewtom.setVisibility(View.VISIBLE);
        });

        mGalleryAdaptertom = new TomGalleryAdapter(frameId_stom);

        LinearLayoutManager layoutManagertom = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewtom.setLayoutManager(layoutManagertom);
        mRecyclerViewtom.setAdapter(mGalleryAdaptertom);

        ColorDatatom();
        initEventtom();

        mStickerViewtom.setLocked(true);

        button_square_texttom.setOnClickListener(v -> {
            isTexttom = true;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            mStickerViewtom.setLocked(false);
            startActivity(new Intent(TomLayoutFrame.this, TomTextActivitytom.class));
        });

        this.backBtntom.setOnClickListener(v -> onBackPressed());

        this.btn_donetom.setOnClickListener(v -> {

            isTexttom = false;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            mStickerViewtom.saveclickdone();

            if (picCounttom < totalSupportedPictom) {
                Toast.makeText(getApplicationContext(), "Select images before save", Toast.LENGTH_SHORT).show();
                return;
            }

            new AsyncTask<Void, Void, Void>() {
                ProgressDialog progressDialog = null;

                protected void onPreExecute() {
                    progressDialog = ProgressDialog.show(TomLayoutFrame.this, "Loading...", "Saving photo to album...");
                }

                @Override
                protected Void doInBackground(Void... arg0) {
                    try {
                        albamtom = getString(R.string.app_name);
                        @SuppressLint("SimpleDateFormat") String currentdateTimtom = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        String photoNatomme = TomApplicationProperties.Album_Photo_Prefixtom + currentdateTimtom;
                        if (albamtom != null && albamtom.isEmpty())
                            strtom = TomCommonUtils.saveToGallerytom(loadBitmapFromViewtom(framePhotoGallerytom), photoNatomme, getContentResolver(), "png");
                        else
                            strtom = TomCommonUtils.saveToGallerytom(loadBitmapFromViewtom(framePhotoGallerytom), albamtom, photoNatomme, getContentResolver(), "png");

                        scanMediaFile(getApplicationContext(), strtom);

                    } catch (Exception localException) {
                        localException.printStackTrace();
                        finish();
                    }
                    return null;
                }

                protected void onPostExecute(Void result) {
                    progressDialog.dismiss();
                    new TomAdsGlobalClassEveryTime().showIntrestrialAdstom(TomLayoutFrame.this, new TomadmobCloseEvent() {
                        @Override
                        public void setAdmobCloseEventJusi() {
                            Toast.makeText(TomLayoutFrame.this, "Image Save ", Toast.LENGTH_SHORT).show();
                            replaceScreen();
                        }

                        @Override
                        public void setSuccessJusi() {
                        }

                        @Override
                        public void setFailedJusi() {
                        }
                    }, new TomMyPreferenceManager(TomLayoutFrame.this).fIdtom());
                }
            }.execute();
        });

        this.backViewtom.setOnClickListener(v -> {
            isTexttom = false;
            mRecyclerViewtom.setVisibility(View.GONE);
            spacer1tom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            viewSwitchertom.setDisplayedChild(0);
        });

        this.rotateRighttom.setOnClickListener(v -> {
            matrixtom = new Matrix();
            matrixtom.reset();
            matrixtom.postRotate(90.0f);
            if (!imagePathForViewtom[selImageViewtom].equals("")) {
                rotatedtom = Bitmap.createBitmap(mBitmaptom, 0, 0, mBitmaptom.getWidth(), mBitmaptom.getHeight(), matrixtom, true);
                mBitmaptom = rotatedtom;
                setImageForViewtom(rotatedtom, selImageViewtom, true);
            }
        });

        this.rotateLefttom.setOnClickListener(v -> {
            matrixtom = new Matrix();
            matrixtom.reset();
            matrixtom.postRotate(-90.0f);
            if (!imagePathForViewtom[selImageViewtom].equals("")) {
                rotatedtom = Bitmap.createBitmap(mBitmaptom, 0, 0, mBitmaptom.getWidth(), mBitmaptom.getHeight(), matrixtom, true);
                mBitmaptom = rotatedtom;
                setImageForViewtom(rotatedtom, selImageViewtom, true);
            }
        });

        this.replaceImagetom.setOnClickListener(v -> startActivityForResult(new Intent(TomAction.ACTION_PICKtom), 100));

        this.horizontalFlitom.setOnClickListener(v -> {
            matrixtom = new Matrix();
            matrixtom.reset();
            matrixtom.preScale(-1.0f, 1.0f);
            if (!imagePathForViewtom[selImageViewtom].equals("")) {
                rotatedtom = Bitmap.createBitmap(mBitmaptom, 0, 0, mBitmaptom.getWidth(), mBitmaptom.getHeight(), matrixtom, true);
                mBitmaptom = rotatedtom;
                setImageForViewtom(rotatedtom, selImageViewtom, true);
            }
        });

        this.verticalFlitom.setOnClickListener(v -> {
            matrixtom = new Matrix();
            matrixtom.reset();
            matrixtom.preScale(1.0f, -1.0f);
            if (!imagePathForViewtom[selImageViewtom].equals("")) {
                rotatedtom = Bitmap.createBitmap(mBitmaptom, 0, 0, mBitmaptom.getWidth(), mBitmaptom.getHeight(), matrixtom, true);
                mBitmaptom = rotatedtom;
                setImageForViewtom(rotatedtom, selImageViewtom, true);
            }
        });
    }

    private void initEventtom() {
        mGalleryAdaptertom.setOnItemClickListener((view, resId) -> addStickerItemtom(resId));
    }

    private void addStickerItemtom(int resId) {
        bitmap2tom = BitmapFactory.decodeResource(getResources(), resId);
        mStickerViewtom.addSticker(bitmap2tom);
        mStickerViewtom.setLocked(false);
    }

    @Override
    public void onBackPressed() {
        if (!isValidClick()) return;
        CustomConfirmationDialogModel confirmationDialogModel = new CustomConfirmationDialogModel(
                0,
                "Discard changes ?",
                "Are you sure, want to proceed without saving!",
                "back",
                "Yes",
                "No",
                new TomCustomConfirmationClickListener() {
                    @Override
                    public void onPositiveButtonClicktom(@NonNull String endpoint_joy) {
                        startActivity(new Intent(TomLayoutFrame.this, TomHomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onNegativeButtonClickJusi(@NonNull String endpoint_joy) {

                    }
                }
        );
        new TomCustomConfirmationDialog(TomLayoutFrame.this, confirmationDialogModel).show();
    }

    public void ColorDatatom() {
        Button colorBtn0tom = findViewById(R.id.color0tom);
        Button colorBtn1tom = findViewById(R.id.color1tom);
        Button colorBtn2tom = findViewById(R.id.color2tom);
        Button colorBtn3tom = findViewById(R.id.color3tom);
        Button colorBtn4tom = findViewById(R.id.color4tom);
        Button colorBtn5tom = findViewById(R.id.color5tom);
        Button colorBtn6tom = findViewById(R.id.color6tom);
        Button colorBtn7tom = findViewById(R.id.color7tom);
        Button colorBtn8tom = findViewById(R.id.color8tom);
        Button colorBtn9tom = findViewById(R.id.color9tom);
        Button colorBtn10tom = findViewById(R.id.color10tom);
        Button colorBtn11tom = findViewById(R.id.color11tom);
        Button colorBtn12tom = findViewById(R.id.color12tom);
        Button colorBtn13tom = findViewById(R.id.color13tom);
        Button colorBtn14tom = findViewById(R.id.color14tom);
        Button colorBtn15tom = findViewById(R.id.color15tom);
        Button colorBtn16tom = findViewById(R.id.color16tom);
        Button colorBtn17tom = findViewById(R.id.color17tom);

        colorBtn0tom.setOnClickListener(this);
        colorBtn1tom.setOnClickListener(this);
        colorBtn2tom.setOnClickListener(this);
        colorBtn3tom.setOnClickListener(this);
        colorBtn4tom.setOnClickListener(this);
        colorBtn5tom.setOnClickListener(this);
        colorBtn6tom.setOnClickListener(this);
        colorBtn7tom.setOnClickListener(this);
        colorBtn8tom.setOnClickListener(this);
        colorBtn9tom.setOnClickListener(this);
        colorBtn10tom.setOnClickListener(this);
        colorBtn11tom.setOnClickListener(this);
        colorBtn12tom.setOnClickListener(this);
        colorBtn13tom.setOnClickListener(this);
        colorBtn14tom.setOnClickListener(this);
        colorBtn15tom.setOnClickListener(this);
        colorBtn16tom.setOnClickListener(this);
        colorBtn17tom.setOnClickListener(this);
    }

    private void showTextImagetom(byte[] textId_) {
        System.gc();

        localBitmaptom = BitmapFactory.decodeByteArray(textId_, 0, textId_.length);
        mStickerViewtom.addSticker(localBitmaptom);
    }


    public void onPause() {
        super.onPause();
        this.isActivityLefttom = true;
    }

    public void onResume() {
        super.onResume();
        this.isActivityLefttom = false;

        if (isTexttom) {
            if (apptom.isTexttom())
                apptom.setTexttom(false);
            else {
                mStickerViewtom.setLocked(false);
                textIdtom = apptom.getTextIdtom();
                showTextImagetom(textIdtom);
                isTexttom = false;
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.isActivityLefttom = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.isActivityLefttom = true;
    }

    private void replaceScreen() {
        Intent localIntent = new Intent(TomLayoutFrame.this, TomShareActivity.class);
        localIntent.putExtra("IMAGE_DATA", strtom);
        startActivity(localIntent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.color0tom:
                this.lsavetom.setBackgroundColor(Color.WHITE);
                return;
            case R.id.color1tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#696969"));
                return;
            case R.id.color2tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#EEE8AA"));
                return;
            case R.id.color3tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#F4A460"));
                return;
            case R.id.color4tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#8470FF"));
                return;
            case R.id.color5tom:
                this.lsavetom.setBackgroundColor(Color.BLUE);
                return;
            case R.id.color6tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#00FFFF"));
                return;
            case R.id.color7tom:
                this.lsavetom.setBackgroundColor(Color.GREEN);
                return;
            case R.id.color8tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#FFFF00"));
                return;
            case R.id.color9tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#CD5C5C"));
                return;
            case R.id.color10tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#FF7F00"));
                return;
            case R.id.color11tom:
                this.lsavetom.setBackgroundColor(Color.RED);
                return;
            case R.id.color12tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#FF1493"));
                return;
            case R.id.color13tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#CD00CD"));
                return;
            case R.id.color14tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#ECAB53"));
                return;
            case R.id.color15tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#C0C0C0"));
                return;
            case R.id.color16tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#008080"));
                return;
            case R.id.color17tom:
                this.lsavetom.setBackgroundColor(Color.parseColor("#FFCC99"));
                return;
            default:
        }
    }

    protected void onActivityResult(int requestCode, int resultCodetom, Intent data) {
        if (resultCodetom == -1) {
            switch (requestCode) {
                case 100:
                    String single_path = data.getStringExtra("single_path");
                    if (single_path != null) {
                        if (!this.picViewFlagtom[this.selImageViewtom])
                            this.picCounttom++;

                        this.imagePathForViewtom[this.selImageViewtom] = single_path;
                        this.mBitmaptom = scaleBitmaptom(single_path);
                        setImageForViewtom(this.mBitmaptom, this.selImageViewtom, true);
                        break;
                    }
                    break;
                case 200:
                    String[] all_path = data.getStringArrayExtra("all_path");
                    for (int itom = 0; itom < all_path.length && this.picCounttom < this.totalSupportedPictom; itom++) {
                        if (!this.picViewFlagtom[0]) {
                            this.picCounttom++;
                            Bitmap resizedBitmap = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[0] = all_path[itom];
                            this.pic0tom.setImageBitmap(resizedBitmap);
                            this.pic0tom.initializeValues3tom();
                            this.picViewFlagtom[0] = true;
                        } else if (!this.picViewFlagtom[1]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[1] = all_path[itom];
                            this.pic1tom.setImageBitmap(a);
                            this.pic1tom.initializeValues3tom();
                            this.picViewFlagtom[1] = true;
                        } else if (!this.picViewFlagtom[2]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[2] = all_path[itom];
                            this.pic2tom.setImageBitmap(a);
                            this.pic2tom.initializeValues3tom();
                            this.picViewFlagtom[2] = true;
                        } else if (!this.picViewFlagtom[3]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[3] = all_path[itom];
                            this.pic3tom.setImageBitmap(a);
                            this.pic3tom.initializeValues3tom();
                            this.picViewFlagtom[3] = true;
                        } else if (!this.picViewFlagtom[4]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[4] = all_path[itom];
                            this.pic4tom.setImageBitmap(a);
                            this.pic4tom.initializeValues3tom();
                            this.picViewFlagtom[4] = true;
                        } else if (!this.picViewFlagtom[5]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[5] = all_path[itom];
                            this.pic5tom.setImageBitmap(a);
                            this.pic5tom.initializeValues3tom();
                            this.picViewFlagtom[5] = true;
                        } else if (!this.picViewFlagtom[6]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[6] = all_path[itom];
                            this.pic6tom.setImageBitmap(a);
                            this.pic6tom.initializeValues3tom();
                            this.picViewFlagtom[6] = true;
                        } else if (!this.picViewFlagtom[7]) {
                            this.picCounttom++;
                            Bitmap a = scaleBitmaptom(all_path[itom]);
                            this.imagePathForViewtom[7] = all_path[itom];
                            this.pic7tom.setImageBitmap(a);
                            this.pic7tom.initializeValues3tom();
                            this.picViewFlagtom[7] = true;
                        } else if (!this.picViewFlagtom[8]) {
                            this.picCounttom++;
                            this.imagePathForViewtom[8] = all_path[itom];
                            this.pic8tom.setImageBitmap(scaleBitmaptom(all_path[itom]));
                            this.pic8tom.initializeValues3tom();
                            this.picViewFlagtom[8] = true;
                        } else if (!this.picViewFlagtom[9]) {
                            this.picCounttom++;
                            this.imagePathForViewtom[9] = all_path[itom];
                            this.pic9tom.setImageBitmap(scaleBitmaptom(all_path[itom]));
                            this.pic9tom.initializeValues3tom();
                            this.picViewFlagtom[9] = true;
                        }
                    }
                    break;
            }
            super.onActivityResult(requestCode, resultCodetom, data);
        }
    }

    public void setImageForViewtom(Bitmap a, int ImageViewOrder, Boolean flag) {
        switch (ImageViewOrder) {
            case 0:
                if (this.pic0tom != null) {
                    this.pic0tom.setImageBitmap(a);
                    this.pic0tom.initializeValues3tom();
                    this.picViewFlagtom[0] = flag;
                    return;
                }
                return;
            case 1:
                if (this.pic1tom != null) {
                    this.pic1tom.setImageBitmap(a);
                    this.pic1tom.initializeValues3tom();
                    this.picViewFlagtom[1] = flag;
                    return;
                }
                return;
            case 2:
                if (this.pic2tom != null) {
                    this.pic2tom.setImageBitmap(a);
                    this.pic2tom.initializeValues3tom();
                    this.picViewFlagtom[2] = flag;
                    return;
                }
                return;
            case 3:
                if (this.pic3tom != null) {
                    this.pic3tom.setImageBitmap(a);
                    this.pic3tom.initializeValues3tom();
                    this.picViewFlagtom[3] = flag;
                    return;
                }
                return;
            case 4:
                if (this.pic4tom != null) {
                    this.pic4tom.setImageBitmap(a);
                    this.pic4tom.initializeValues3tom();
                    this.picViewFlagtom[4] = flag;
                    return;
                }
                return;
            case 5:
                if (this.pic5tom != null) {
                    this.pic5tom.setImageBitmap(a);
                    this.pic5tom.initializeValues3tom();
                    this.picViewFlagtom[5] = true;
                    return;
                }
                return;
            case 6:
                if (this.pic6tom != null) {
                    this.pic6tom.setImageBitmap(a);
                    this.pic6tom.initializeValues3tom();
                    this.picViewFlagtom[6] = true;
                    return;
                }
                return;
            case 7:
                if (this.pic7tom != null) {
                    this.pic7tom.setImageBitmap(a);
                    this.pic7tom.initializeValues3tom();
                    this.picViewFlagtom[7] = true;
                    return;
                }
                return;
            case 8:
                if (this.pic8tom != null) {
                    this.pic8tom.setImageBitmap(a);
                    this.pic8tom.initializeValues3tom();
                    this.picViewFlagtom[8] = true;
                    return;
                }
                return;
            case 9:
                if (this.pic9tom != null) {
                    this.pic9tom.setImageBitmap(a);
                    this.pic9tom.initializeValues3tom();
                    this.picViewFlagtom[9] = flag;
                }
        }
    }

    private Bitmap scaleBitmaptom(String path) {
        Options otom = new Options();
        otom.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, otom);
        int scaletom = 1;
        while (((double) (otom.outWidth * otom.outHeight)) * (1.0d / Math.pow(scaletom, 2.0d)) > 800000.0d) {
            scaletom++;
        }
        if (scaletom <= 1)
            return BitmapFactory.decodeFile(path);

        scaletom--;
        otom = new Options();
        otom.inSampleSize = scaletom;
        Bitmap b = BitmapFactory.decodeFile(path, otom);
        int heighttom = b.getHeight();
        int widthtom = b.getWidth();
        double ytom = Math.sqrt(800000.0d / (((double) widthtom) / ((double) heighttom)));
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) ((ytom / ((double) heighttom)) * ((double) widthtom)), (int) ytom, true);
        b.recycle();
        b = scaledBitmap;
        System.gc();
        return b;
    }

    @SuppressLint("HandlerLeak")
    class handlerdata extends Handler {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int temp1tom = msg.getData().getInt("KEY");
            if (colorTabletomtom.getVisibility() == View.VISIBLE)
                colorTabletomtom.setVisibility(View.GONE);

            if (viewSwitchertom.getDisplayedChild() == 0)
                viewSwitchertom.setDisplayedChild(1);

            if (temp1tom != selImageViewtom) {
                selImageViewtom = temp1tom;
                if (!imagePathForViewtom[selImageViewtom].equals(""))
                    mBitmaptom = scaleBitmaptom(imagePathForViewtom[selImageViewtom]);
            }

            if (mBitmaptom == null)
                mBitmaptom = scaleBitmaptom(imagePathForViewtom[temp1tom]);
        }
    }
}
