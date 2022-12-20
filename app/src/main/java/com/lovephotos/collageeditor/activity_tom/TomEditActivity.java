package com.lovephotos.collageeditor.activity_tom;

import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.getBitmapFromView;
import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.isValidClick;
import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.scanMediaFile;
import static com.lovephotos.collageeditor.adapter_tom.TomImageAdapter.mThumbIdstom;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.TomCollageApplication;
import com.lovephotos.collageeditor.Utilities_tom.TomApplicationProperties;
import com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils;
import com.lovephotos.collageeditor.adapter_tom.TomImageAdapter;
import com.lovephotos.collageeditor.adapter_tom.TomImageAdapterFilter;
import com.lovephotos.collageeditor.custom_tom.TomCustomConfirmationDialog;
import com.lovephotos.collageeditor.graphics_tom.TomCommandsPreset;
import com.lovephotos.collageeditor.graphics_tom.TomImageProcessor;
import com.lovephotos.collageeditor.graphics_tom.TomImageProcessorListener;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomImageProcessingCommand;
import com.lovephotos.collageeditor.listener_tom.TomCustomConfirmationClickListener;
import com.lovephotos.collageeditor.model.CustomConfirmationDialogModel;
import com.lovephotos.collageeditor.sticker_tom.TomGalleryAdapter;
import com.lovephotos.collageeditor.stpnfncmakads_tom.interfaces_tom.TomadmobCloseEvent;
import com.lovephotos.collageeditor.stpnfncmakads_tom.main_tom.TomAdsGlobalClassEveryTime;
import com.lovephotos.collageeditor.stpnfncmakads_tom.utils_tom.TomMyPreferenceManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TomEditActivity extends AppCompatActivity implements TomImageProcessorListener, OnTouchListener {

    public static final String mypreferenceJusitom = "myprefadmob";
    public static final int RESTORE_PREVIEW_BITMAPtom = 1;
    static final int DRAGtom = 1;
    static final int NONEtom = 0;
    static final int ZOOMtom = 2;
    public static int[] frameId_stom = new int[]{
            R.drawable.emotion_01_tom, R.drawable.emotion_02_tom, R.drawable.emotion_03_tom, R.drawable.emotion_04_tom, R.drawable.emotion_05_tom, R.drawable.emotion_06_tom, R.drawable.emotion_07_tom,
            R.drawable.emotion_08_tom, R.drawable.emotion_09_tom, R.drawable.emotion_10_tom, R.drawable.emotion_11_tom, R.drawable.emotion_12_tom, R.drawable.emotion_13_tom,
            R.drawable.emotion_14_tom, R.drawable.emotion_15_tom, R.drawable.emotion_16_tom, R.drawable.emotion_17_tom, R.drawable.emotion_18_tom,
            R.drawable.emotion_19_tom, R.drawable.emotion_20_tom, R.drawable.emotion_21_tom, R.drawable.emotion_22_tom, R.drawable.emotion_23_tom,
            R.drawable.emotion_24_tom, R.drawable.emotion_25_tom,

            R.drawable.candy_01_tom, R.drawable.candy_02_tom, R.drawable.candy_03_tom, R.drawable.candy_04_tom,
            R.drawable.candy_05_tom, R.drawable.candy_06_tom, R.drawable.candy_07_tom, R.drawable.candy_08_tom, R.drawable.candy_09_tom, R.drawable.candy_10_tom,
            R.drawable.candy_11_tom, R.drawable.candy_12_tom, R.drawable.candy_13_tom, R.drawable.candy_14_tom, R.drawable.candy_15_tom, R.drawable.candy_16_tom,
            R.drawable.candy_17_tom, R.drawable.candy_18_tom, R.drawable.candy_19_tom, R.drawable.candy_20_tom,

            R.drawable.comic_01_tom, R.drawable.comic_02_tom,
            R.drawable.comic_03_tom, R.drawable.comic_04_tom, R.drawable.comic_05_tom, R.drawable.comic_06_tom, R.drawable.comic_07_tom, R.drawable.comic_08_tom,
            R.drawable.comic_09_tom, R.drawable.comic_10_tom, R.drawable.comic_11_tom,
            R.drawable.comic_12_tom, R.drawable.comic_13_tom, R.drawable.comic_14_tom, R.drawable.comic_15_tom, R.drawable.comic_16_tom, R.drawable.comic_17_tom,
            R.drawable.comic_18_tom, R.drawable.comic_19_tom, R.drawable.comic_20_tom, R.drawable.comic_21_tom, R.drawable.comic_22_tom, R.drawable.comic_23_tom,
            R.drawable.comic_24_tom, R.drawable.comic_25_tom, R.drawable.comic_26_tom, R.drawable.comic_27_tom, R.drawable.comic_28_tom,

            R.drawable.glasses_01_tom, R.drawable.glasses_02_tom, R.drawable.glasses_03_tom, R.drawable.glasses_04_tom, R.drawable.glasses_05_tom, R.drawable.glasses_06_tom, R.drawable.glasses_07_tom,
            R.drawable.glasses_08_tom, R.drawable.glasses_09_tom, R.drawable.glasses_10_tom, R.drawable.glasses_11_tom, R.drawable.glasses_12_tom, R.drawable.glasses_13_tom,
            R.drawable.glasses_14_tom, R.drawable.glasses_15_tom, R.drawable.glasses_16_tom, R.drawable.glasses_17_tom, R.drawable.glasses_18_tom, R.drawable.glasses_19_tom,
            R.drawable.glasses_20_tom, R.drawable.glasses_21_tom, R.drawable.glasses_22_tom, R.drawable.glasses_23_tom, R.drawable.glasses_24_tom, R.drawable.glasses_25_tom,

            R.drawable.hat_01_tom, R.drawable.hat_02_tom, R.drawable.hat_03_tom, R.drawable.hat_04_tom, R.drawable.hat_05_tom, R.drawable.hat_06_tom, R.drawable.hat_07_tom,
            R.drawable.hat_08_tom, R.drawable.hat_09_tom, R.drawable.hat_10_tom, R.drawable.hat_11_tom,

            R.drawable.love_01_tom, R.drawable.love_03_tom, R.drawable.love_04_tom, R.drawable.love_11_tom, R.drawable.love_13_tom,
            R.drawable.love_14_tom, R.drawable.love_15_tom, R.drawable.love_26_tom, R.drawable.love_32_tom, R.drawable.love_35_tom,

            R.drawable.snap_057_tom, R.drawable.snap_058_tom, R.drawable.snap_059_tom, R.drawable.snap_060_tom, R.drawable.snap_061_tom,
            R.drawable.snap_062_tom, R.drawable.snap_063_tom, R.drawable.snap_064_tom, R.drawable.snap_065_tom, R.drawable.snap_067_tom,
            R.drawable.snap_068_tom, R.drawable.snap_069_tom,

            R.drawable.snap_emerald_tiara_tom, R.drawable.snap_eye_left_tom, R.drawable.snap_eye_right_tom,
            R.drawable.snap_flower_crown_0_tom, R.drawable.snap_flower_crown_1_tom, R.drawable.snap_flower_crown_2_tom, R.drawable.snap_flower_crown_3_tom, R.drawable.snap_flower_crown_4_tom,
            R.drawable.snap_flower_crown_5_tom, R.drawable.snap_flower_crown_6_tom, R.drawable.snap_flower_crown_7_tom, R.drawable.snap_flower_crown_8_tom, R.drawable.snap_flower_crown_9_tom,
            R.drawable.snap_flower_crown_10_tom, R.drawable.snap_flower_crown_11_tom, R.drawable.snap_flower_crown_12_tom, R.drawable.snap_flower_crown_13_tom, R.drawable.snap_flower_crown_14_tom,
            R.drawable.snap_flower_crown_15_tom, R.drawable.snap_flower_crown_16_tom, R.drawable.snap_flower_crown_17_tom, R.drawable.snap_flower_crown_18_tom, R.drawable.snap_flower_crown_19_tom,
            R.drawable.snap_flower_crown_20_tom, R.drawable.snap_flower_crown_21_tom, R.drawable.snap_flower_crown_22_tom, R.drawable.snap_flower_tiara_tom, R.drawable.snap_tiara_0_tvs_tom,
            R.drawable.snap_tiara_2_tvs_tom,};
    public static Bitmap selectedBitmaptom;
    final int IMG_FROM_GALLERYtom = 2;
    private final OnItemClickListener listenertom = (parent, v, position, id) -> runImageProcessortom(position);
    private final ArrayList<Uri> tempUris = new ArrayList<>();
    SharedPreferences sharedpreferencestom;
    boolean isActivityLefttom;
    String Ad_Idtom;
    float[] lastEventtom = null;
    float dtom = 0f;
    float newRottom = 0f;
    float oldDisttom = 1.0F;
    int modetom = 0;
    int positiontom;
    Boolean isGallaryopentom = false;
    boolean fromCameratom;
    boolean isTexttom = false;
    Context _contexttom;
    Bitmap bitmap2tom;
    Bitmap localBitmaptom;
    PointF midtom = new PointF();
    Matrix matrixtom = new Matrix();
    Matrix savedMatrixtom = new Matrix();
    PointF starttom = new PointF();
    TomCollageApplication apptom;
    byte[] textIdtom;
    String albamtom;
    String strtom;
    Uri imgImageCaptureUritom;
    FrameLayout frmtom;
    ImageView mImageViewtom;
    ImageView frameImagetom;
    ImageView selectImagetom;
    ImageView imageblurtom;
    RecyclerView mRecyclerViewtom;
    TomGalleryAdapter mGalleryAdaptertom;
    Gallery frameGallerytom;
    Gallery gallerytom;
    Button button_square_picturetom;
    Button button_square_frametom;
    Button button_square_effecttom;
    Button button_mirror_stickertom;
    Button button_square_texttom;
    ImageButton btn_canceltom;
    ImageButton btn_donetom;
    Bitmap bittom;
    TomAdsGlobalClassEveryTime adsGlobalClassEveryTime1tom;
    private TomImageProcessor imageProcessortom;
    private Bitmap selectedImageUritom;
    private com.xiaopo.flying.sticker.StickerView mStickerViewtom;

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

    public static Bitmap rotatetom(Bitmap bitmap, float degrees) {
        Matrix matrixtom = new Matrix();
        matrixtom.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
    }

    public static Bitmap fliptom(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrixtom = new Matrix();
        matrixtom.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixtom, true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_edit_tom);
        adsGlobalClassEveryTime1tom = new TomAdsGlobalClassEveryTime();
        sharedpreferencestom = getSharedPreferences(mypreferenceJusitom, MODE_PRIVATE);
        isActivityLefttom = false;

        Ad_Idtom = sharedpreferencestom.getString("Full_Ad_Id", "");
        positiontom = getIntent().getIntExtra("position", 0);

        _contexttom = getApplicationContext();
        apptom = ((TomCollageApplication) getApplication());

        selectedImageUritom = TomHomeActivity.bitmap;
        fromCameratom = apptom.isFromCameratom();

        initViewtom();
        initEventtom();

        frmtom = findViewById(R.id.framePhotoGallerytom);

        imageblurtom = findViewById(R.id.imageblurtom);
        selectImagetom = findViewById(R.id.imagetom);
        frameImagetom = findViewById(R.id.frameImagetom);
        mStickerViewtom = findViewById(R.id.sticker_viewtom);

        button_square_picturetom = findViewById(R.id.button_square_picturetom);
        button_square_frametom = findViewById(R.id.button_square_frametom);
        button_square_effecttom = findViewById(R.id.button_square_effecttom);
        button_mirror_stickertom = findViewById(R.id.button_mirror_stickertom);
        button_square_texttom = findViewById(R.id.button_square_texttom);

        btn_canceltom = findViewById(R.id.btn_canceltom);
        btn_donetom = findViewById(R.id.btn_donetom);

        selectImagetom.setOnTouchListener(new TouchImageViewtom(this));

        btn_canceltom.setOnClickListener(v -> onBackPressed());

        btn_donetom.setOnClickListener(view -> {
            mStickerViewtom.saveclickdone();
            adsGlobalClassEveryTime1tom.showIntrestrialAdstom(this, new TomadmobCloseEvent() {
                @Override
                public void setFailedJusi() {
                }

                @Override
                public void setSuccessJusi() {
                }

                @SuppressLint("StaticFieldLeak")
                @Override
                public void setAdmobCloseEventJusi() {
                    new AsyncTask<Void, Void, Void>() {
                        ProgressDialog progressDialog = null;

                        protected void onPreExecute() {
                            progressDialog = ProgressDialog.show(TomEditActivity.this, "Loading...", "Saving photo to album...");
                        }

                        @Override
                        protected Void doInBackground(Void... arg0) {
                            try {
                                albamtom = getString(R.string.app_name);
                                @SuppressLint("SimpleDateFormat") String currentdateTimeJusi = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                String photoNameJusi = TomApplicationProperties.Album_Photo_Prefixtom + currentdateTimeJusi;
                                if (albamtom != null && albamtom.isEmpty()) {
                                    strtom = TomCommonUtils.saveToGallerytom(loadBitmapFromViewtom(frmtom), photoNameJusi, getContentResolver(), "png");
                                } else {
                                    strtom = TomCommonUtils.saveToGallerytom(loadBitmapFromViewtom(frmtom), albamtom, photoNameJusi, getContentResolver(), "png");
                                }

                                scanMediaFile(getApplicationContext(), strtom);

                            } catch (Exception localException) {
                                localException.printStackTrace();
                                finish();
                            }
                            return null;
                        }

                        protected void onPostExecute(Void result) {
                            progressDialog.dismiss();
                            Toast.makeText(TomEditActivity.this, "Image Save ", Toast.LENGTH_SHORT).show();
                            replaceScreentom();
                        }
                    }.execute();
                }
            }, new TomMyPreferenceManager(TomEditActivity.this).fIdtom());
        });

        button_square_picturetom.setOnClickListener(view -> {
            mRecyclerViewtom.setVisibility(View.GONE);
            gallerytom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            isTexttom = false;

            Intent localIntent1tom = new Intent("android.intent.action.PICK");
            localIntent1tom.setType("image/*");
            startActivityForResult(localIntent1tom, IMG_FROM_GALLERYtom);
        });

        button_square_frametom.setOnClickListener(view -> {
            mRecyclerViewtom.setVisibility(View.GONE);
            gallerytom.setVisibility(View.GONE);

            mStickerViewtom.setLocked(true);
            isTexttom = false;

            if (frameGallerytom.getVisibility() == View.VISIBLE)
                frameGallerytom.setVisibility(View.INVISIBLE);
            else
                frameGallerytom.setVisibility(View.VISIBLE);
        });

        button_square_effecttom.setOnClickListener(view -> {
            mRecyclerViewtom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            mStickerViewtom.setLocked(true);

            isGallaryopentom = true;
            if (gallerytom.getVisibility() == View.VISIBLE) {
                gallerytom.setVisibility(View.INVISIBLE);
            } else {
                gallerytom.setVisibility(View.VISIBLE);
                readBitmapthistom(getImageUritom(TomEditActivity.this, selectedImageUritom));
                TomEditActivity.this.selectImagetom.setImageBitmap(selectedImageUritom);
                TomEditActivity.this.imageblurtom.setImageBitmap(createBlurBitmaptom(selectedImageUritom, 25));
            }
        });

        button_mirror_stickertom.setOnClickListener(view -> {
            mStickerViewtom.setLocked(false);
            gallerytom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            selectImagetom.setOnTouchListener(null);
            if (mRecyclerViewtom.getVisibility() == View.VISIBLE) {
                mRecyclerViewtom.setVisibility(View.INVISIBLE);
            } else {
                mRecyclerViewtom.setVisibility(View.VISIBLE);
            }
        });

        button_square_texttom.setOnClickListener(view -> {
            isTexttom = true;
            mRecyclerViewtom.setVisibility(View.GONE);
            gallerytom.setVisibility(View.GONE);
            frameGallerytom.setVisibility(View.GONE);
            selectImagetom.setOnTouchListener(null);
            mStickerViewtom.setLocked(false);
            startActivity(new Intent(TomEditActivity.this, TomTextActivitytom.class));
        });

        mStickerViewtom.setLocked(true);

        DisplayMetrics localDisplayMetricsJusi = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetricsJusi);
        int heightScreentom = localDisplayMetricsJusi.heightPixels;
        int widthScreentom = localDisplayMetricsJusi.widthPixels;

        gallerytom = findViewById(R.id.filterGallerytom);
        gallerytom.setAdapter(new TomImageAdapterFilter(this));
        gallerytom.setOnItemClickListener(listenertom);
        gallerytom.setVisibility(View.GONE);

        frameGallerytom = findViewById(R.id.frameGallerytom);
        frameGallerytom.setAdapter(new TomImageAdapter(TomEditActivity.this));

        frameGallerytom.setOnItemClickListener((parent, view, positiontom, id) -> {
            if (positiontom == 0) {
                frameImagetom.setImageResource(0);
            } else {
                frameImagetom.setImageResource(mThumbIdstom[positiontom]);
            }
        });

        int colortom = 0;

        frmtom.setDrawingCacheEnabled(true);
        frmtom.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        frmtom.layout(0, 0, frmtom.getMeasuredWidth(), frmtom.getMeasuredHeight());

        frmtom.buildDrawingCache(true);

        TomEditActivity.this.selectImagetom.setImageBitmap(selectedImageUritom);
        TomEditActivity.this.imageblurtom.setImageBitmap(createBlurBitmaptom(selectedImageUritom, 25));

        readBitmapthistom(getImageUritom(TomEditActivity.this, selectedImageUritom));
    }

    private void initViewtom() {
        mImageViewtom = findViewById(R.id.imagetom);
        mRecyclerViewtom = findViewById(R.id.recycler_viewtom);

        mGalleryAdaptertom = new TomGalleryAdapter(frameId_stom);

        LinearLayoutManager layoutManagertom = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewtom.setLayoutManager(layoutManagertom);
        mRecyclerViewtom.setAdapter(mGalleryAdaptertom);
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
    public void onProcessStartJusi() {
    }

    @Override
    public void onProcessEndJusi(Bitmap result) {
        selectImagetom.setImageBitmap(result);
        selectImagetom.invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView viewtom = (ImageView) v;
        float scaletom;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrixtom.set(matrixtom);
                starttom.set(event.getX(), event.getY());
                modetom = DRAGtom;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                oldDisttom = spacingtom(event);
                if (oldDisttom > 10f) {
                    savedMatrixtom.set(matrixtom);
                    midPointtom(midtom, event);
                    modetom = ZOOMtom;
                }
                lastEventtom = new float[4];
                lastEventtom[0] = event.getX(0);
                lastEventtom[1] = event.getX(1);
                lastEventtom[2] = event.getY(0);
                lastEventtom[3] = event.getY(1);
                dtom = rotationtom(event);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                modetom = NONEtom;
                break;

            case MotionEvent.ACTION_MOVE:

                if (modetom == DRAGtom) {
                    matrixtom.set(savedMatrixtom);
                    matrixtom.postTranslate(event.getX() - starttom.x, event.getY() - starttom.y);

                } else if (modetom == ZOOMtom && event.getPointerCount() == 2) {
                    float newDisttom = spacingtom(event);
                    matrixtom.set(savedMatrixtom);
                    if (newDisttom > 10f) {
                        scaletom = newDisttom / oldDisttom;
                        matrixtom.postScale(scaletom, scaletom, midtom.x, midtom.y);
                    }
                    if (lastEventtom != null) {
                        newRottom = rotationtom(event);
                        float r = newRottom - dtom;
                        matrixtom.postRotate(r, viewtom.getMeasuredWidth() / 2, viewtom.getMeasuredHeight() / 2);
                    }
                }
                break;
        }
        viewtom.setImageMatrix(matrixtom);
        return true;
    }

    private float rotationtom(MotionEvent event) {
        double delta_xtom = (event.getX(0) - event.getX(1));
        double delta_ytom = (event.getY(0) - event.getY(1));
        double radianstom = Math.atan2(delta_ytom, delta_xtom);

        return (float) Math.toDegrees(radianstom);
    }

    private void midPointtom(PointF paramPointF, MotionEvent paramMotionEvent) {
        try {
            float f1tom = paramMotionEvent.getX(0) + paramMotionEvent.getX(1);
            float f2tom = paramMotionEvent.getY(0) + paramMotionEvent.getY(1);
            paramPointF.set(f1tom / 2.0F, f2tom / 2.0F);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTextImagetom(byte[] textId_) {
        System.gc();
        localBitmaptom = BitmapFactory.decodeByteArray(textId_, 0, textId_.length);
        mStickerViewtom.addSticker(localBitmaptom);
    }

    private float spacingtom(MotionEvent paramMotionEvent) {
        try {
            float f1tom = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
            float f2tom = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
            return (float) Math.sqrt(f1tom * f1tom + f2tom * f2tom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getPath(Uri paramUri) {
        Cursor localCursortom = managedQuery(paramUri, new String[]{"_data"}, null, null, null);
        int itom = localCursortom.getColumnIndexOrThrow("_data");
        localCursortom.moveToFirst();
        return localCursortom.getString(itom);
    }

    public Uri getImageUritom(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytestom = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytestom);
        String pathtom = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        if (pathtom != null) {
            Uri tempUri = Uri.parse(pathtom);
            tempUris.add(tempUri);
            return tempUri;
        } else
            return null;
    }

    public void readBitmapthistom(Uri selectedImageC) {

        Bitmap bmtom;

        AssetFileDescriptor fileDescriptortom = null;

        DisplayMetrics localDisplayMetricstom = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetricstom);

        int screenWidthtom = localDisplayMetricstom.widthPixels;
        int screenHeighttom = localDisplayMetricstom.heightPixels;

        BitmapFactory.Options optionstom;
        try {

            try {

                fileDescriptortom = this.getContentResolver().openAssetFileDescriptor(selectedImageC, "r");

                optionstom = new BitmapFactory.Options();

                optionstom.inJustDecodeBounds = true;

                BitmapFactory.decodeFileDescriptor(fileDescriptortom.getFileDescriptor(), null, optionstom);

                int widthtom = optionstom.outWidth;
                int heighttom = optionstom.outHeight;

                if ((widthtom * heighttom) > (screenWidthtom * screenHeighttom)) {
                    int widthRatiotom;
                    if (widthtom > heighttom) {
                        widthRatiotom = Math.round((float) widthtom / (float) screenWidthtom);
                    } else {
                        widthRatiotom = Math.round((float) heighttom / (float) screenHeighttom);
                    }
                    optionstom.inSampleSize = widthRatiotom;
                }

                optionstom.inJustDecodeBounds = false;

                bmtom = BitmapFactory.decodeFileDescriptor(fileDescriptortom.getFileDescriptor(), null, optionstom);

                selectedBitmaptom = bmtom;

                TomImageProcessor.getInstance().setBitmapJusi(bmtom);
                imageProcessortom = TomImageProcessor.getInstance();

                initializeValuestom();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileDescriptortom != null)
                    fileDescriptortom.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runImageProcessortom(int position) {
        TomImageProcessingCommand commandtom = getCommandtom(position);
        TomImageProcessor.getInstance().setProcessListenertom(this);
        TomImageProcessor.getInstance().runCommandtom(commandtom);
    }

    private TomImageProcessingCommand getCommandtom(int position) {
        return TomCommandsPreset.Presettom.get(position);
    }

    private void initializeValuestom() {
        final Object datatom = getLastNonConfigurationInstance();
        if (datatom == null)
            selectImagetom.setImageBitmap(imageProcessortom.getBitmap());
        else
            restoreSavedValuestom(datatom);
    }

    private void restoreSavedValuestom(Object data) {
        Bundle savedValuestom = (Bundle) data;

        int bitmapToReadtom = savedValuestom.getInt("BITMAP");
        boolean isRunningtom = savedValuestom.getBoolean("IS_RUNNING");
        int selectedFilterIdxtom = savedValuestom.getInt("SELECTED_FILTER_POSITION");

        if (bitmapToReadtom == RESTORE_PREVIEW_BITMAPtom)
            selectImagetom.setImageBitmap(imageProcessortom.getLastResultBitmaptom());
        else
            selectImagetom.setImageBitmap(imageProcessortom.getBitmap());

        gallerytom.setSelection(selectedFilterIdxtom);

        if (isRunningtom) {
            onProcessStartJusi();
            imageProcessortom.setProcessListenertom(this);
        }
    }

    protected void onRestoreInstanceState(Bundle paramBundle) {
        super.onRestoreInstanceState(paramBundle);
        if (paramBundle.containsKey("cameraImageUri")) {
            this.imgImageCaptureUritom = Uri.parse(paramBundle
                    .getString("cameraImageUri"));
        }
    }

    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        if (this.imgImageCaptureUritom != null) {
            paramBundle.putString("cameraImageUri",
                    this.imgImageCaptureUritom.toString());
        }
    }

    @Override
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 != -1)
            return;

        switch (paramInt1) {
            default:
                return;
            case IMG_FROM_GALLERYtom:
                imgImageCaptureUritom = paramIntent.getData();

                String[] filePathColumntom = {MediaStore.Images.Media.DATA};
                Cursor cursortom = getContentResolver().query(imgImageCaptureUritom, filePathColumntom, null, null, null);
                cursortom.moveToFirst();

                int columnIndextom = cursortom.getColumnIndex(filePathColumntom[0]);
                String picturePathtom = cursortom.getString(columnIndextom);
                cursortom.close();

                Bitmap loadedBitmaptom = BitmapFactory.decodeFile(picturePathtom);

                ExifInterface eitom = null;
                try {
                    eitom = new ExifInterface(picturePathtom);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientationtom = eitom.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientationtom) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bittom = rotatetom(loadedBitmaptom, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bittom = rotatetom(loadedBitmaptom, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bittom = rotatetom(loadedBitmaptom, 270);
                        break;

                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bittom = fliptom(loadedBitmaptom, true, false);
                        break;

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bittom = fliptom(loadedBitmaptom, false, true);
                        break;

                    default:
                        bittom = loadedBitmaptom;
                        break;
                }

                selectedImageUritom = bittom;
                TomEditActivity.this.selectImagetom.setImageBitmap(selectedImageUritom);
                TomEditActivity.this.imageblurtom.setImageBitmap(createBlurBitmaptom(selectedImageUritom, 25));
                readBitmapthistom(getImageUritom(TomEditActivity.this, bittom));
                break;
        }
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
                        if (tempUris.size() > 0) {
                            for (int i = 0; i < tempUris.size(); i++) {
                                Uri tempUri = tempUris.get(i);
                                getContentResolver().delete(tempUri, null, null);
                            }
                        }

                        startActivity(new Intent(TomEditActivity.this, TomHomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onNegativeButtonClickJusi(@NonNull String endpoint_joy) {

                    }
                }
        );
        new TomCustomConfirmationDialog(TomEditActivity.this, confirmationDialogModel).show();
    }

    private Bitmap createBlurBitmaptom(Bitmap src, float r) {
        if (src != null) {
            Bitmap bitmaptom = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
            RenderScript renderScripttom = RenderScript.create(this);
            Allocation blurInputtom = Allocation.createFromBitmap(renderScripttom, src);
            Allocation blurOutputtom = Allocation.createFromBitmap(renderScripttom, bitmaptom);
            ScriptIntrinsicBlur blurtom = ScriptIntrinsicBlur.create(renderScripttom, Element.U8_4(renderScripttom));
            blurtom.setInput(blurInputtom);
            blurtom.setRadius(25);
            blurtom.forEach(blurOutputtom);
            blurOutputtom.copyTo(bitmaptom);
            renderScripttom.destroy();
            return bitmaptom;

        } else
            Toast.makeText(_contexttom, "Choose another image", Toast.LENGTH_SHORT).show();
        return null;
    }

    private void replaceScreentom() {
        Intent localIntenttom = new Intent(TomEditActivity.this, TomShareActivity.class);
        localIntenttom.putExtra("IMAGE_DATA", strtom);
        startActivity(localIntenttom);
        finish();
    }

    protected void onResume() {
        super.onResume();

        if (isTexttom) {
            if (apptom.isTexttom()) {
                apptom.setTexttom(false);
            } else {
                mStickerViewtom.setLocked(false);
                textIdtom = apptom.getTextIdtom();
                showTextImagetom(textIdtom);
                isTexttom = false;
            }
        }
        this.isActivityLefttom = false;
    }

    public void onPause() {
        super.onPause();
        this.isActivityLefttom = true;
    }

    protected void onStop() {
        super.onStop();
        this.isActivityLefttom = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.isActivityLefttom = true;
    }

    static class TouchImageViewtom implements OnTouchListener {
        static int NONEtom;
        static int DRAGtom = 1;
        static int ZOOMtom = 2;
        final TomEditActivity Touch_EditPhototom;
        Matrix matrixtom;
        Matrix savedMatrixtom;
        int modetom = NONEtom;

        PointF starttom;
        PointF midtom;
        float oldDisttom;

        float[] lastEventtom;
        float dtom;
        float newRottom;

        TouchImageViewtom(TomEditActivity editPhoto) {
            this.lastEventtom = null;
            this.dtom = 0f;
            this.newRottom = 0f;
            this.Touch_EditPhototom = editPhoto;
            this.matrixtom = new Matrix();
            this.savedMatrixtom = new Matrix();
            NONEtom = 0;
            this.starttom = new PointF();
            this.midtom = new PointF();
            this.oldDisttom = 1.0F;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    savedMatrixtom.set(matrixtom);
                    starttom.set(event.getX(), event.getY());
                    modetom = DRAGtom;
                    lastEventtom = null;
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDisttom = spacingtom(event);
                    if (oldDisttom > 10f) {
                        savedMatrixtom.set(matrixtom);
                        midPointtom(midtom, event);
                        modetom = ZOOMtom;
                    }
                    lastEventtom = new float[4];
                    lastEventtom[0] = event.getX(0);
                    lastEventtom[1] = event.getX(1);
                    lastEventtom[2] = event.getY(0);
                    lastEventtom[3] = event.getY(1);
                    dtom = rotationtom(event);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    modetom = NONEtom;
                    lastEventtom = null;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (modetom == DRAGtom) {
                        matrixtom.set(savedMatrixtom);
                        matrixtom.postTranslate(event.getX() - starttom.x, event.getY() - starttom.y);
                    } else if (modetom == ZOOMtom && event.getPointerCount() == 2) {
                        float newDist = spacingtom(event);
                        matrixtom.set(savedMatrixtom);
                        if (newDist > 10f) {
                            float scale = newDist / oldDisttom;
                            matrixtom.postScale(scale, scale, midtom.x, midtom.y);
                        }
                        if (lastEventtom != null) {
                            newRottom = rotationtom(event);
                            float r = newRottom - dtom;
                            matrixtom.postRotate(r, v.getMeasuredWidth() / 2, v.getMeasuredHeight() / 2);
                        }
                    }
                    break;
            }
            ((ImageView) v).setImageMatrix(matrixtom);
            return true;
        }

        private float spacingtom(MotionEvent event) {
            float xtom = event.getX(0) - event.getX(1);
            float ytom = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(xtom * xtom + ytom * ytom);
        }

        private void midPointtom(PointF point, MotionEvent event) {
            float xtom = event.getX(0) + event.getX(1);
            float ytom = event.getY(0) + event.getY(1);
            point.set(xtom / 2, ytom / 2);
        }

        private float rotationtom(MotionEvent event) {
            double delta_xtom = (event.getX(0) - event.getX(1));
            double delta_ytom = (event.getY(0) - event.getY(1));
            double radianstom = Math.atan2(delta_ytom, delta_xtom);
            return (float) Math.toDegrees(radianstom);
        }
    }
}


