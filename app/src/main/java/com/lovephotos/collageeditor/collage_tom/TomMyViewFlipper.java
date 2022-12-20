package com.lovephotos.collageeditor.collage_tom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class TomMyViewFlipper extends ViewFlipper {

    public TomMyViewFlipper(Context context) {
        super(context);
    }

    public TomMyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
