package com.lovephotos.collageeditor.Utilities_tom;

import java.io.Serializable;

public class TomImgItem implements Serializable {

    private static final long serialVersionUIDtom = 1L;
    String ab_txt_imgtom;

    public TomImgItem() {
    }

    public TomImgItem(String ab_txt_img) {
        this.ab_txt_imgtom = ab_txt_img;
    }

    public static long getSerialversionuid() {
        return serialVersionUIDtom;
    }

    public String getAb_txt_imgtom() {
        return ab_txt_imgtom;
    }

    public void setAb_txt_img(String ab_txt_img) {
        this.ab_txt_imgtom = ab_txt_img;
    }
}
