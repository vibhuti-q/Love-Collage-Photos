package com.lovephotos.collageeditor.collage_tom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.core.view.MotionEventCompat;

import com.lovephotos.collageeditor.R;

public class Tomcustom_imageviewtom extends androidx.appcompat.widget.AppCompatImageView implements OnTouchListener {

    boolean adjustToBoundstom;
    boolean allowExcedLimitsWhenMovingImagetom;
    boolean areValuesInitializedtom = false;
    boolean blockImageInTheMiddletom;
    boolean doubleClickAdjusttom;
    int doubleClickTimeInMillistom;
    float doubleClickZoomLeveltom;
    int heighttom;
    int imageHeighttom = 0;
    int imageWidthtom = 0;
    boolean isMaxZoomLevelRelativetom;
    boolean isMinZoomLevelRelativetom;
    Matrix matrixtom = new Matrix();
    float maxZoomLeveltom;
    PointF midtom = new PointF();
    float minZoomLeveltom;
    int modetom = 0;
    float oldDistancetom = 1.0f;
    Matrix savedMatrixtom = new Matrix();
    PointF starttom = new PointF();
    int widthtom;
    private float[] initialMatrixValuestom;
    private long lastClickTimeInMillistom = 0;
    private Handler longClickHandlertom;
    private View mViewtom;
    private Activity mainactivitytom;

    public Tomcustom_imageviewtom(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public Tomcustom_imageviewtom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        parseAttributestom();
    }

    public Tomcustom_imageviewtom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        parseAttributestom();
    }

    public void setMainactivitytom(Activity mainactivity) {
        this.mainactivitytom = mainactivity;
    }

    public void setLongClickHandlertom(Handler longClickHandler) {
        this.longClickHandlertom = longClickHandler;
    }

    public void parseAttributestom() {
        this.maxZoomLeveltom = 4.0f;
        this.minZoomLeveltom = 0.1f;
        this.doubleClickZoomLeveltom = 2.0f;
        this.isMaxZoomLevelRelativetom = true;
        this.isMinZoomLevelRelativetom = true;
        this.adjustToBoundstom = true;
        this.doubleClickAdjusttom = true;
        this.allowExcedLimitsWhenMovingImagetom = false;
        this.blockImageInTheMiddletom = true;
        this.doubleClickTimeInMillistom = 100;
    }

    public boolean onTouch(View view, MotionEvent event) {
        if (!this.areValuesInitializedtom) {
            initializeValuestom();
        }
        if (this.modetom != 4) {
            switch (event.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.mViewtom = view;
                    sendMessagetom();
                    this.savedMatrixtom.set(this.matrixtom);
                    this.starttom.set(event.getX(), event.getY());
                    this.modetom = 1;
                    break;
                case 1:
                    checkClicktom(event);
                    if (this.modetom != 4) {
                        postAdjusttom();
                        break;
                    }
                    break;
                case 2:
                    if (this.modetom == 1) {
                        moveImagetom(event);
                    } else if (this.modetom == 2) {
                        scaleImagetom(event);
                    }
                    preAdjusttom();
                    break;
                case 5:
                    this.oldDistancetom = spacingtom(event);
                    if (this.oldDistancetom > 10.0f) {
                        this.savedMatrixtom.set(this.matrixtom);
                        midPointtom(this.midtom, event);
                        this.modetom = 2;
                        break;
                    }
                    break;
                case 6:
                    setModeToNoneIfNotAdjustingOrCenteringViewtom();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void initializeValuestom() {
        initWidthAndHeightValuestom();
        initMaxAndMinZoomLeveltom();
        initMatrixtom();
    }

    public void initializeValues2() {
        this.modetom = 0;
        this.oldDistancetom = 1.0f;
    }

    public void initializeValues3tom() {
        initWidthAndHeightValuestom();
        this.starttom = new PointF();
        this.midtom = new PointF();
        this.modetom = 0;
        this.matrixtom.set(getImageMatrix());
        setImageMatrix(this.matrixtom);
        setScaleType(ScaleType.CENTER_CROP);
        this.oldDistancetom = 1.0f;
        this.areValuesInitializedtom = false;
    }

    private void initWidthAndHeightValuestom() {
        this.imageWidthtom = getDrawable().getIntrinsicWidth();
        this.imageHeighttom = getDrawable().getIntrinsicHeight();
        this.widthtom = getWidth();
        this.heighttom = getHeight();
    }

    private void initMaxAndMinZoomLeveltom() {
        this.matrixtom.set(getImageMatrix());
        this.initialMatrixValuestom = new float[9];
        this.matrixtom.getValues(this.initialMatrixValuestom);
    }

    private void initMatrixtom() {
        setImageMatrix(this.matrixtom);
        setScaleType(ScaleType.MATRIX);
        this.areValuesInitializedtom = true;
    }

    private void setModeToNoneIfNotAdjustingOrCenteringViewtom() {
        int i = (this.modetom == 4 || this.modetom == 3) ? this.modetom : 0;
        this.modetom = i;
    }

    private void checkClicktom(MotionEvent event) {
        int yDiff = (int) Math.abs(event.getY() - this.starttom.y);
        if (((int) Math.abs(event.getX() - this.starttom.x)) < 8 && yDiff < 8) {
            clicktom();
            checkIfDoubleClicktom();
        }
    }

    private void checkIfDoubleClicktom() {
        if (System.currentTimeMillis() - this.lastClickTimeInMillistom < ((long) this.doubleClickTimeInMillistom)) {
            doubleClicktom();
            this.lastClickTimeInMillistom = 0;
            return;
        }
        this.lastClickTimeInMillistom = System.currentTimeMillis();
    }

    private void doubleClicktom() {
        if (this.doubleClickAdjusttom) {
            float[] ftom = new float[9];
            this.matrixtom.getValues(ftom);
            this.modetom = 4;
            if (areTheSameValuestom(ftom, this.initialMatrixValuestom)) {
                interpolateMatrixToValuetom(calculateMatrixValuesAfterAdjustingWindowZoomtom(this.doubleClickZoomLeveltom));
            } else {
                interpolateMatrixToValuetom(this.initialMatrixValuestom);
            }
        }
    }

    private boolean areTheSameValuestom(float[] f1, float[] f2) {
        return f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] && f1[3] == f2[3] && f1[4] == f2[4] && f1[5] == f2[5] && f1[6] == f2[6] && f1[7] == f2[7] && f1[8] == f2[8];
    }

    private void clicktom() {
    }

    private void moveImagetom(MotionEvent event) {
        if (spacingtom(event.getX(), this.starttom.x, event.getY(), this.starttom.y) >= 10.0f) {
            this.matrixtom.set(this.savedMatrixtom);
            this.matrixtom.postTranslate(event.getX() - this.starttom.x, event.getY() - this.starttom.y);
            setImageMatrix(this.matrixtom);
        }
    }

    private void scaleImagetom(MotionEvent event) {
        float newDistancetom = spacingtom(event);
        if (newDistancetom > 10.0f) {
            this.matrixtom.set(this.savedMatrixtom);
            float scaletom = newDistancetom / this.oldDistancetom;
            this.matrixtom.postScale(scaletom, scaletom, this.midtom.x, this.midtom.y);
            setImageMatrix(this.matrixtom);
        }
    }

    private void preAdjusttom() {
        if (this.adjustToBoundstom && !this.allowExcedLimitsWhenMovingImagetom) {
            this.matrixtom.setValues(calculateObjectiveMatrixtom());
            setImageMatrix(this.matrixtom);
        }
    }

    private void postAdjusttom() {
        if (this.adjustToBoundstom && this.allowExcedLimitsWhenMovingImagetom) {
            this.modetom = 3;
            interpolateMatrixToValuetom(calculateObjectiveMatrixtom());
        }
    }

    private float[] calculateObjectiveMatrixtom() {
        float[] objectiveMatrixValuestom = calculateMatrixValuesAfterAdjustingWindowZoomtom();
        PointF imagePositiontom = new PointF(objectiveMatrixValuestom[2], objectiveMatrixValuestom[5]);
        float horizontalMargintom = calculateWidthMargintom(objectiveMatrixValuestom);
        float verticalMargintom = calculateHeightMargintom(objectiveMatrixValuestom);
        if (!imageWidthBiggerThanViewtom(objectiveMatrixValuestom)) {
            float marginRighttom = horizontalMargintom;
            float marginLefttom = 0.0f;
            if (this.blockImageInTheMiddletom) {
                marginLefttom = (((float) this.widthtom) - (((float) this.imageWidthtom) * objectiveMatrixValuestom[0])) / 2.0f;
                marginRighttom = -marginLefttom;
            }
            if ((-imagePositiontom.x) < marginRighttom) {
                objectiveMatrixValuestom[2] = -marginRighttom;
            } else if (imagePositiontom.x < marginLefttom) {
                objectiveMatrixValuestom[2] = marginLefttom;
            }
        } else if ((-imagePositiontom.x) > horizontalMargintom) {
            objectiveMatrixValuestom[2] = -horizontalMargintom;
        } else if (imagePositiontom.x > 0.0f) {
            objectiveMatrixValuestom[2] = 0.0f;
        }
        if (!imageHeightBiggerThanViewtom(objectiveMatrixValuestom)) {
            float marginBottomtom = verticalMargintom;
            float marginToptom = 0.0f;
            if (this.blockImageInTheMiddletom) {
                marginToptom = (((float) this.heighttom) - (((float) this.imageHeighttom) * objectiveMatrixValuestom[0])) / 2.0f;
                marginBottomtom = -marginToptom;
            }
            if ((-imagePositiontom.y) < marginBottomtom) {
                objectiveMatrixValuestom[5] = -marginBottomtom;
            } else if (imagePositiontom.y < marginToptom) {
                objectiveMatrixValuestom[5] = marginToptom;
            }
        } else if ((-imagePositiontom.y) > verticalMargintom) {
            objectiveMatrixValuestom[5] = -verticalMargintom;
        } else if (imagePositiontom.y > 0.0f) {
            objectiveMatrixValuestom[5] = 0.0f;
        }
        return objectiveMatrixValuestom;
    }

    private float[] calculateMatrixValuesAfterAdjustingWindowZoomtom() {
        float[] ftom = new float[9];
        Matrix auxtom = new Matrix();
        auxtom.set(this.matrixtom);
        auxtom.getValues(ftom);
        if (ftom[0] > this.maxZoomLeveltom) {
            auxtom.postScale(this.maxZoomLeveltom / ftom[0], this.maxZoomLeveltom / ftom[0], this.midtom.x, this.midtom.y);
        }
        if (ftom[0] < this.minZoomLeveltom) {
            auxtom.postScale(this.minZoomLeveltom / ftom[0], this.minZoomLeveltom / ftom[0], this.midtom.x, this.midtom.y);
        }
        auxtom.getValues(ftom);
        return ftom;
    }

    private float[] calculateMatrixValuesAfterAdjustingWindowZoomtom(float scale) {
        float[] ftom = new float[9];
        Matrix auxtom = new Matrix();
        auxtom.set(this.matrixtom);
        auxtom.getValues(ftom);
        auxtom.postScale(scale, scale, (float) (this.widthtom / 2), (float) (this.heighttom / 2));
        auxtom.getValues(ftom);
        return ftom;
    }

    private boolean imageWidthBiggerThanViewtom(float[] objectiveMatrixValues) {
        return objectiveMatrixValues[0] * ((float) this.imageWidthtom) >= ((float) getWidth());
    }

    private boolean imageHeightBiggerThanViewtom(float[] objectiveMatrixValues) {
        return objectiveMatrixValues[0] * ((float) this.imageHeighttom) >= ((float) this.heighttom);
    }

    private float calculateWidthMargintom(float[] objectiveMatrixValues) {
        return (((float) this.imageWidthtom) * objectiveMatrixValues[0]) - ((float) this.widthtom);
    }

    private float calculateHeightMargintom(float[] objectiveMatrixValues) {
        return (((float) this.imageHeighttom) * objectiveMatrixValues[0]) - ((float) this.heighttom);
    }

    private void interpolateMatrixToValuetom(float[] destinyMatrixValues) {
        final float[] originMatrixValuestom = new float[9];
        this.matrixtom.getValues(originMatrixValuestom);
        final Interpolator interpolatortom = new AccelerateDecelerateInterpolator();
        final long startTimetom = System.currentTimeMillis();
        final float[] fArrtom = destinyMatrixValues;
        post(new Runnable() {
            public void run() {
                float[] tempMatrixtom = new float[9];
                float ttom = ((float) (System.currentTimeMillis() - startTimetom)) / 400.0f;
                if (ttom > 1.0f) {
                    ttom = 1.0f;
                }
                float interpolatedRatiotom = interpolatortom.getInterpolation(ttom);
                for (int itom = 0; itom < 9; itom++) {
                    tempMatrixtom[itom] = originMatrixValuestom[itom] + ((fArrtom[itom] - originMatrixValuestom[itom]) * interpolatedRatiotom);
                }
                Tomcustom_imageviewtom.this.matrixtom.setValues(tempMatrixtom);
                Tomcustom_imageviewtom.this.setImageMatrix(Tomcustom_imageviewtom.this.matrixtom);
                if (ttom < 1.0f && (Tomcustom_imageviewtom.this.modetom == 3 || Tomcustom_imageviewtom.this.modetom == 4)) {
                    Tomcustom_imageviewtom.this.post(this);
                } else if (Tomcustom_imageviewtom.this.modetom == 4 || Tomcustom_imageviewtom.this.modetom == 3) {
                    Tomcustom_imageviewtom.this.modetom = 0;
                }
            }
        });
    }

    private float spacingtom(MotionEvent event) {
        return spacingtom(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
    }

    private float spacingtom(float x0, float x1, float y0, float y1) {
        float atom = x0 - x1;
        float btom = y0 - y1;
        return (float) Math.sqrt((atom * atom) + (btom * btom));
    }

    private void midPointtom(PointF point, MotionEvent event) {
        point.set((event.getX(0) + event.getX(1)) / 2.0f, (event.getY(0) + event.getY(1)) / 2.0f);
    }

    public void sendMessagetom
            () {
        Message msg1tom = new Message();
        Bundle btom = new Bundle();
        switch (this.mViewtom.getId()) {
            case R.id.pic0tom:
                btom.putInt("KEY", 0);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic1tom:
                btom.putInt("KEY", 1);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic2tom:
                btom.putInt("KEY", 2);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic3tom:
                btom.putInt("KEY", 3);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic4tom:
                btom.putInt("KEY", 4);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic5tom:
                btom.putInt("KEY", 5);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic6tom:
                btom.putInt("KEY", 6);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic7tom:
                btom.putInt("KEY", 7);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic8tom:
                btom.putInt("KEY", 8);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            case R.id.pic9tom:
                btom.putInt("KEY", 9);
                msg1tom.setData(btom);
                this.longClickHandlertom.sendMessage(msg1tom);
                return;
            default:
                return;
        }
    }
}
