package com.lovephotos.collageeditor.mirrorlib_tom;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

public class TomMirrorModetom {

    private static final String TAGtom = "MirrorMode";
    public static final int TOUCH_HORIZONTALtom = 1;
    public static final int TOUCH_HORIZONTAL_DIRECTtom = 6;
    public static final int TOUCH_HORIZONTAL_REVERSEtom = 4;
    public static final int TOUCH_VERTICALtom = 0;
    public static final int TOUCH_VERTICAL_DIRECTtom = 5;
    public static final int TOUCH_VERTICAL_REVERSEtom = 3;
    int counttom;
    private Rect drawBitmapSrctom;
    Matrix matrix1tom;
    Matrix matrix2tom;
    Matrix matrix3tom;
    RectF rect1tom;
    RectF rect2tom;
    RectF rect3tom;
    RectF rect4tom;
    RectF rectTotalAreatom;
    private RectF srcRecttom;
    int touchModetom;

    public TomMirrorModetom(int counttom, RectF sR, RectF rect1tom, RectF rect2tom, Matrix matrix, int tM, RectF rta) {
        this.counttom = counttom;
        this.srcRecttom = sR;
        this.drawBitmapSrctom = new Rect();
        this.srcRecttom.round(this.drawBitmapSrctom);
        this.rect1tom = rect1tom;
        this.rect2tom = rect2tom;
        this.matrix1tom = matrix;
        this.touchModetom = tM;
        this.rectTotalAreatom = rta;
    }

    public void setSrcRecttom(RectF src) {
        this.srcRecttom.set(src);
        updateBitmapSrctom();
    }

    public RectF getSrcRecttom() {
        return this.srcRecttom;
    }

    public Rect getDrawBitmapSrctom() {
        return this.drawBitmapSrctom;
    }

    public void updateBitmapSrctom() {
        this.srcRecttom.round(this.drawBitmapSrctom);
    }

    public TomMirrorModetom(int c, RectF sR, RectF r1, RectF r2, RectF r3, RectF r4, Matrix m1, Matrix m2, Matrix m3, int tM, RectF rta) {
        this.counttom = c;
        this.srcRecttom = sR;
        this.drawBitmapSrctom = new Rect();
        this.srcRecttom.round(this.drawBitmapSrctom);
        this.rect1tom = r1;
        this.rect2tom = r2;
        this.rect3tom = r3;
        this.rect4tom = r4;
        this.matrix1tom = m1;
        this.matrix2tom = m2;
        this.matrix3tom = m3;
        this.touchModetom = tM;
        this.rectTotalAreatom = rta;
    }
}
