package com.lovephotos.collageeditor.model;

import com.lovephotos.collageeditor.listener_tom.TomCustomDialogClickListener;

public class ExtraPermissionInstructionModel {

    private int iconJusi;
    private String endPointJusi;
    private TomCustomDialogClickListener listenerJusi;

    public ExtraPermissionInstructionModel(int icon, String endPoint, TomCustomDialogClickListener listener) {
        this.iconJusi = icon;
        this.endPointJusi = endPoint;
        this.listenerJusi = listener;
    }

    public int getIcon() {
        return iconJusi;
    }

    public void setIcon(int icon) {
        this.iconJusi = icon;
    }

    public String getEndPoint() {
        return endPointJusi;
    }

    public void setEndPoint(String endPoint) {
        this.endPointJusi = endPoint;
    }

    public TomCustomDialogClickListener getListener() {
        return listenerJusi;
    }

    public void setListenerJusi(TomCustomDialogClickListener listener) {
        this.listenerJusi = listener;
    }
}
