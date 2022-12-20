package com.lovephotos.collageeditor.activity_tom;

import static com.lovephotos.collageeditor.Utilities_tom.TomCommonUtils.getBitmapFromView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.TomCollageApplication;
import com.lovephotos.collageeditor.adapter_tom.TomColorAdaptertom;
import com.lovephotos.collageeditor.adapter_tom.TomFontAdaptertom;
import com.lovephotos.collageeditor.adapter_tom.TomImageAdapter_Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TomTextActivitytom extends Activity implements OnClickListener, OnSeekBarChangeListener {

    public static final String mypreferencetom = "myprefadmob";
    private final Integer[] f69xtom;
    public File imageFiletom;
    SharedPreferences sharedpreferencestom;
    OnItemClickListener f48ctom;
    OnItemClickListener f49dtom;
    TomCollageApplication apptom;
    //LinearLayout l_edit, l_font, l_fcolor, l_fshadow, l_frame;
    RelativeLayout allLayouttom;
    private Dialog dialogtom;
    private EditText editTexttom;
    private TextView textViewtom;
    private Typeface typefacetom;
    private String[] f54itom;
    private TomColorAdaptertom colorAdaptertom;
    private RelativeLayout relativeLayouttom;
    private int f57ltom;
    private int f58mtom;
    private int f59ntom;
    private int f60otom;
    private boolean f61ptom;
    private boolean f62qtom;
    private boolean f63rtom;
    private String f64stom;
    private String f65ttom;
    private String f66utom;
    private String f67vtom;
    private String selectedColortom;

    public TomTextActivitytom() {

        editTexttom = null;
        textViewtom = null;
        imageFiletom = null;
        f57ltom = 1;
        f58mtom = 30;
        f59ntom = 0;
        f60otom = 0;
        f61ptom = true;
        f62qtom = true;
        f63rtom = true;
        f69xtom = new Integer[]{R.drawable.bg_pattern_01_tom,
                R.drawable.bg_pattern_02_tom,
                R.drawable.bg_pattern_03_tom,
                R.drawable.bg_pattern_04_tom,
                R.drawable.bg_pattern_05_tom, R.drawable.bg_pattern_06_tom, R.drawable.bg_pattern_07_tom,
                R.drawable.bg_pattern_08_tom, R.drawable.bg_pattern_09_tom, R.drawable.bg_pattern_010_tom,
                R.drawable.bg_pattern_011_tom, R.drawable.bg_pattern_012_tom, R.drawable.bg_pattern_013_tom,
                R.drawable.bg_pattern_014_tom, R.drawable.bg_pattern_015_tom, R.drawable.bg_pattern_016_tom};

        f48ctom = (AdapterView<?> parent, View view, int i, long id) -> {
            final TomTextActivitytom textActivity = TomTextActivitytom.this;

            if (textActivity.f57ltom == 1) {

                textActivity.typefacetom = Typeface.createFromAsset(textActivity.getAssets(), textActivity.f54itom[i]);
                textActivity.textViewtom.setTypeface(textActivity.typefacetom);

            } else if (textActivity.f57ltom == 2) {

                if (textActivity.f61ptom)
                    textActivity.f64stom = textActivity.colorAdaptertom.colorstom[i];
                else
                    textActivity.f65ttom = textActivity.colorAdaptertom.colorstom[i];
                if (textActivity.f62qtom)
                    TomTextActivitytom.m27atom(textActivity, textActivity.f64stom, textActivity.f64stom);
                else
                    TomTextActivitytom.m27atom(textActivity, textActivity.f64stom, textActivity.f65ttom);

            } else if (textActivity.f57ltom == 4) {
                if (textActivity.f61ptom)
                    textActivity.f66utom = textActivity.colorAdaptertom.colorstom[i];
                else
                    textActivity.f67vtom = textActivity.colorAdaptertom.colorstom[i];

                if (textActivity.f63rtom)
                    TomTextActivitytom.m31btom(textActivity, textActivity.f66utom, textActivity.f66utom);
                else
                    TomTextActivitytom.m31btom(textActivity, textActivity.f66utom, textActivity.f67vtom);
            }
        };

        f49dtom = (AdapterView<?> adapterView, View view, int i, long j) -> {

            final TomTextActivitytom textActivitytom = TomTextActivitytom.this;

            if (textActivitytom.f57ltom == 3) {
                textActivitytom.selectedColortom = textActivitytom.colorAdaptertom.colorstom[i];
                textActivitytom.textViewtom.getPaint().setShader(null);
                textActivitytom.textViewtom.setShadowLayer((float) textActivitytom.f60otom, (float) textActivitytom.f59ntom, (float) textActivitytom.f59ntom, Color.parseColor(textActivitytom.selectedColortom));
                textActivitytom.textViewtom.setTextColor(Color.parseColor(textActivitytom.f64stom));
                textActivitytom.textViewtom.invalidate();
                return;
            }
            textActivitytom.relativeLayouttom.setBackgroundResource(textActivitytom.f69xtom[i]);
        };
    }

    static void m27atom(TomTextActivitytom textActivity, String str, String str2) {
        float ftom = 0.0f;
        textActivity.textViewtom.getPaint().setShader(new LinearGradient(0.0f, (float) (textActivity.f58mtom), ftom, (float) (textActivity.f58mtom * 2), new int[]{Color.parseColor(str), Color.parseColor(str2)}, new float[]{0.0f, 1.0F}, TileMode.MIRROR));
        textActivity.textViewtom.getPaint().setStrokeWidth(5.0f);
        textActivity.textViewtom.invalidate();
    }

    static void m31btom(TomTextActivitytom textActivity, String str, String str2) {
        ShapeDrawable shapeDrawabletom = new ShapeDrawable(new RectShape());
        shapeDrawabletom.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) (textActivity.f58mtom * 3), Color.parseColor(str), Color.parseColor(str2), TileMode.REPEAT));
        textActivity.findViewById(R.id.textviewLayouttom).setBackgroundDrawable(shapeDrawabletom);
        textActivity.findViewById(R.id.textviewLayouttom).invalidate();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_tom);
        apptom = ((TomCollageApplication) getApplication());
        sharedpreferencestom = getSharedPreferences(mypreferencetom, MODE_PRIVATE);

        allLayouttom = findViewById(R.id.allLayouttom);

        findViewById(R.id.inputKettom).setOnClickListener(this);
        findViewById(R.id.btn_fonttom).setOnClickListener(this);
        findViewById(R.id.btn_textColortom).setOnClickListener(this);
        findViewById(R.id.btn_textStyletom).setOnClickListener(this);
        findViewById(R.id.btn_textBgtom).setOnClickListener(this);

        findViewById(R.id.btn_normalfonttom).setOnClickListener(this);
        findViewById(R.id.btn_boldfonttom).setOnClickListener(this);
        findViewById(R.id.btn_italicfonttom).setOnClickListener(this);
        findViewById(R.id.btn_bolditalicfonttom).setOnClickListener(this);
        findViewById(R.id.btn_singleColortom).setOnClickListener(this);
        findViewById(R.id.btn_multiColortom).setOnClickListener(this);
        findViewById(R.id.btn_BgsingleColortom).setOnClickListener(this);
        findViewById(R.id.btn_BgmultiColortom).setOnClickListener(this);
        findViewById(R.id.btn_donetom).setOnClickListener(this);
        findViewById(R.id.btn_canceltom).setOnClickListener(this);

        relativeLayouttom = findViewById(R.id.textviewLayouttom);
        textViewtom = findViewById(R.id.textViewtom);

        colorAdaptertom = new TomColorAdaptertom(this);

        f64stom = colorAdaptertom.colorstom[35];
        f65ttom = colorAdaptertom.colorstom[35];
        f66utom = colorAdaptertom.colorstom[6];
        f67vtom = colorAdaptertom.colorstom[6];
        selectedColortom = colorAdaptertom.colorstom[20];

        Gallery gallerytom = findViewById(R.id.fontGallerytom);
        gallerytom.setAdapter(new TomFontAdaptertom(this));
        gallerytom.setOnItemClickListener(f48ctom);

        gallerytom = findViewById(R.id.colorGallerytom);
        gallerytom.setAdapter(colorAdaptertom);
        gallerytom.setOnItemClickListener(f48ctom);

        gallerytom = findViewById(R.id.colorGalleryBgtom);
        gallerytom.setAdapter(colorAdaptertom);
        gallerytom.setOnItemClickListener(f48ctom);

        gallerytom = findViewById(R.id.shadowcolorGallerytom);
        gallerytom.setAdapter(colorAdaptertom);
        gallerytom.setOnItemClickListener(f49dtom);

        gallerytom = findViewById(R.id.patternGallerytom);
        gallerytom.setAdapter(new TomImageAdapter_Text(this));
        gallerytom.setOnItemClickListener(f49dtom);

        ((SeekBar) findViewById(R.id.seekBartom)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.textOpacitySeekBartom)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.paddingSeekBartom)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.shadwoXYSeekBartom)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.shadowRadiosSeekBartom)).setOnSeekBarChangeListener(this);

        String filetom = Environment.getExternalStorageDirectory().toString();
        new File(filetom + "/" + getString(R.string.app_name) + "/temp").mkdirs();

        if ("mounted".equals(Environment.getExternalStorageState())) {
            imageFiletom = new File(filetom + "/" + getString(R.string.app_name) + "/temp/", "Nature_1.jpg");
            try {
                FileOutputStream fileOutputStreamtom = new FileOutputStream(imageFiletom);
                fileOutputStreamtom.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            imageFiletom = new File(getFilesDir(), "Nature_1.jpg");

        findViewById(R.id.colorCheckBoxtom).setOnClickListener(v -> {
            final TomTextActivitytom f44a = TomTextActivitytom.this;
            if (((CheckBox) v).isChecked()) {
                f44a.f62qtom = false;
                TomTextActivitytom.m27atom(f44a, f44a.f64stom, f44a.f65ttom);
                return;
            }
            f44a.f62qtom = true;
            TomTextActivitytom.m27atom(f44a, f44a.f64stom, f44a.f64stom);
        });

        findViewById(R.id.BgcolorCheckBoxtom).setOnClickListener(v -> {
            final TomTextActivitytom f45atom = TomTextActivitytom.this;
            if (((CheckBox) v).isChecked()) {
                f45atom.f63rtom = false;
                TomTextActivitytom.m31btom(f45atom, f45atom.f66utom, f45atom.f67vtom);
                return;
            }
            f45atom.f63rtom = true;
            TomTextActivitytom.m31btom(f45atom, f45atom.f66utom, f45atom.f66utom);
        });

        f54itom = getResources().getStringArray(R.array.FontFamily);

        textViewtom.setOnClickListener(v -> {
            dialogtom = new Dialog(TomTextActivitytom.this, R.style.Theme_AppCompat_Translucent);
            dialogtom.requestWindowFeature(1);
            dialogtom.setContentView(R.layout.edit_text_dialog_tom);
            dialogtom.findViewById(R.id.btn_edittext_donetom).setOnClickListener(v1 -> {
                dialogtom.dismiss();
                textViewtom.setText(editTexttom.getText().toString());
            });
            dialogtom.findViewById(R.id.btn_edittext_canceltom).setOnClickListener(v12 -> dialogtom.dismiss());
            editTexttom = dialogtom.findViewById(R.id.editTJusiexttom);
            editTexttom.setText(textViewtom.getText().toString().trim());
            dialogtom.show();
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_canceltom:
                apptom.setTexttom(true);
                finish();
                break;

            case R.id.btn_donetom:
                try {
                    getBitmapFromView(relativeLayouttom);
                } catch (Exception e) {
                    try {
                        getBitmapFromView(relativeLayouttom, Color.TRANSPARENT);
                    } catch (Exception ex) {
                        m29btom();
                    }
                }
                View v1tom = findViewById(R.id.textviewLayouttom);
                v1tom.buildDrawingCache(true);
                v1tom.setDrawingCacheEnabled(true);
                Bitmap bitmaptom;
                try {
                    bitmaptom = getBitmapFromView(v1tom);
                } catch (Exception e) {
                    try {
                        bitmaptom = getBitmapFromView(v1tom, Color.TRANSPARENT);
                    } catch (Exception ex) {
                        bitmaptom = Bitmap.createBitmap(v1tom.getDrawingCache());
                    }
                }
                v1tom.setDrawingCacheEnabled(false);
                ByteArrayOutputStream streamtom = new ByteArrayOutputStream();
                bitmaptom.compress(CompressFormat.PNG, 100, streamtom);
                byte[] byteArraytom = streamtom.toByteArray();
                apptom.setTextIdtom(byteArraytom);
                finish();
                break;

            case R.id.inputKettom:
                allLayouttom.setVisibility(View.GONE);
                dialogtom = new Dialog(this, R.style.Theme_AppCompat_Translucent);
                dialogtom.requestWindowFeature(1);
                dialogtom.setContentView(R.layout.edit_text_dialog_tom);
                dialogtom.findViewById(R.id.btn_edittext_donetom).setOnClickListener(this);
                dialogtom.findViewById(R.id.btn_edittext_canceltom).setOnClickListener(this);
                editTexttom = dialogtom.findViewById(R.id.editTJusiexttom);
                editTexttom.setText(textViewtom.getText().toString().trim());
                dialogtom.show();
                break;

            case R.id.btn_fonttom:
                allLayouttom.setVisibility(View.VISIBLE);
                f57ltom = 1;
                m24aJusi();
                findViewById(R.id.fontLayouttom).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_fonttom).setBackgroundResource(R.drawable.btn_text_style_hover_tom);
                break;

            case R.id.btn_textColortom:
                allLayouttom.setVisibility(View.VISIBLE);
                f57ltom = 2;
                m24aJusi();
                findViewById(R.id.colorLayouttom).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textColortom).setBackgroundResource(R.drawable.btn_text_color_hover_tom);
                break;

            case R.id.btn_textStyletom:
                allLayouttom.setVisibility(View.VISIBLE);
                f57ltom = 3;
                m24aJusi();
                findViewById(R.id.textStyleLayouttom).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textStyletom).setBackgroundResource(R.drawable.btn_text_glow_hover_tom);
                break;

            case R.id.btn_textBgtom:
                allLayouttom.setVisibility(View.VISIBLE);
                f57ltom = 4;
                m24aJusi();
                findViewById(R.id.TextbackgroundLayouttom).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textBgtom).setBackgroundResource(R.drawable.btn_text_bg_hover_tom);
                break;

            case R.id.btn_normalfonttom:
                textViewtom.setTypeface(textViewtom.getTypeface(), Typeface.NORMAL);
                textViewtom.invalidate();
                findViewById(R.id.btn_normalfonttom).setBackgroundResource(R.drawable.btn_normal_hover_tom);
                findViewById(R.id.btn_boldfonttom).setBackgroundResource(R.drawable.btn_bold_tom);
                findViewById(R.id.btn_italicfonttom).setBackgroundResource(R.drawable.btn_italics_tom);
                findViewById(R.id.btn_bolditalicfonttom).setBackgroundResource(R.drawable.btn_bold_italics_tom);
                break;

            case R.id.btn_boldfonttom:
                textViewtom.setTypeface(textViewtom.getTypeface(), Typeface.BOLD);
                textViewtom.invalidate();
                findViewById(R.id.btn_normalfonttom).setBackgroundResource(R.drawable.btn_normal_tom);
                findViewById(R.id.btn_boldfonttom).setBackgroundResource(R.drawable.btn_bold_hover_tom);
                findViewById(R.id.btn_italicfonttom).setBackgroundResource(R.drawable.btn_italics_tom);
                findViewById(R.id.btn_bolditalicfonttom).setBackgroundResource(R.drawable.btn_bold_italics_tom);
                break;

            case R.id.btn_italicfonttom:
                textViewtom.setTypeface(textViewtom.getTypeface(), Typeface.ITALIC);
                textViewtom.invalidate();
                findViewById(R.id.btn_normalfonttom).setBackgroundResource(R.drawable.btn_normal_tom);
                findViewById(R.id.btn_boldfonttom).setBackgroundResource(R.drawable.btn_bold_tom);
                findViewById(R.id.btn_italicfonttom).setBackgroundResource(R.drawable.btn_italics_hover_tom);
                findViewById(R.id.btn_bolditalicfonttom).setBackgroundResource(R.drawable.btn_bold_italics_tom);
                break;

            case R.id.btn_bolditalicfonttom:
                textViewtom.setTypeface(textViewtom.getTypeface(), Typeface.BOLD_ITALIC);
                textViewtom.invalidate();
                findViewById(R.id.btn_normalfonttom).setBackgroundResource(R.drawable.btn_normal_tom);
                findViewById(R.id.btn_boldfonttom).setBackgroundResource(R.drawable.btn_bold_tom);
                findViewById(R.id.btn_italicfonttom).setBackgroundResource(R.drawable.btn_italics_tom);
                findViewById(R.id.btn_bolditalicfonttom).setBackgroundResource(R.drawable.btn_bold_italics_hover_tom);
                break;

            case R.id.btn_singleColortom:

            case R.id.btn_BgsingleColortom:
                f61ptom = true;
                findViewById(R.id.btn_BgsingleColortom).setBackgroundResource(R.drawable.btn_color1_hover_tom);
                findViewById(R.id.btn_BgmultiColortom).setBackgroundResource(R.drawable.btn_color2_tom);
                findViewById(R.id.btn_singleColortom).setBackgroundResource(R.drawable.btn_color1_hover_tom);
                findViewById(R.id.btn_multiColortom).setBackgroundResource(R.drawable.btn_color2_tom);
                break;

            case R.id.btn_multiColortom:

            case R.id.btn_BgmultiColortom:
                f61ptom = false;
                findViewById(R.id.btn_BgsingleColortom).setBackgroundResource(R.drawable.btn_color1_tom);
                findViewById(R.id.btn_BgmultiColortom).setBackgroundResource(R.drawable.btn_color2_hover_tom);
                findViewById(R.id.btn_singleColortom).setBackgroundResource(R.drawable.btn_color1_tom);
                findViewById(R.id.btn_multiColortom).setBackgroundResource(R.drawable.btn_color2_hover_tom);
                break;

            case R.id.btn_edittext_donetom:
                dialogtom.dismiss();
                textViewtom.setText(editTexttom.getText().toString());
                break;

            case R.id.btn_edittext_canceltom:
                allLayouttom.setVisibility(View.GONE);
                dialogtom.dismiss();
                break;
        }
    }

    private void m24aJusi() {
        findViewById(R.id.colorLayouttom).setVisibility(View.GONE);
        findViewById(R.id.fontLayouttom).setVisibility(View.GONE);
        findViewById(R.id.TextbackgroundLayouttom).setVisibility(View.GONE);
        findViewById(R.id.textStyleLayouttom).setVisibility(View.GONE);
        findViewById(R.id.btn_fonttom).setBackgroundResource(R.drawable.btn_text_style_tom);
        findViewById(R.id.btn_textColortom).setBackgroundResource(R.drawable.btn_text_color_tom);
        findViewById(R.id.btn_textStyletom).setBackgroundResource(R.drawable.btn_text_glow_tom);
        findViewById(R.id.btn_textBgtom).setBackgroundResource(R.drawable.btn_text_bg_tom);
    }

    private Bitmap m29btom() {
        relativeLayouttom.setDrawingCacheEnabled(true);
        relativeLayouttom.layout(0, 0, relativeLayouttom.getMeasuredWidth(), relativeLayouttom.getMeasuredHeight());
        Bitmap drawingCache = relativeLayouttom.getDrawingCache(true);
        try {
            drawingCache.compress(CompressFormat.PNG, 90, new FileOutputStream(imageFiletom));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return drawingCache;
    }

    @SuppressLint({"NewApi", "NonConstantResourceId"})
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {

        switch (seekBar.getId()) {

            case R.id.seekBartom:
                f58mtom = i;
                textViewtom.setTextSize((float) f58mtom);
                break;

            case R.id.textOpacitySeekBartom:
                textViewtom.setAlpha(((float) i) / 100.0f);
                break;

            case R.id.shadwoXYSeekBartom:
                f59ntom = (i / 5) - 10;
                textViewtom.setShadowLayer((float) f60otom, (float) f59ntom, (float) f59ntom, Color.parseColor(selectedColortom));
                textViewtom.invalidate();
                break;

            case R.id.shadowRadiosSeekBartom:
                f60otom = i / 5;
                textViewtom.setShadowLayer((float) f60otom, (float) f59ntom, (float) f59ntom, Color.parseColor(selectedColortom));
                textViewtom.invalidate();
                break;

            case R.id.paddingSeekBartom:
                f58mtom = i;
                relativeLayouttom.setPadding(i, i, i, i);
                break;

            default:
                break;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    protected void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        if (imageFiletom.exists())
            imageFiletom.delete();

        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
        apptom.setTexttom(true);
    }
}
