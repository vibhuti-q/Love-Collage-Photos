package com.lovephotos.collageeditor.model;

public class PermissionInstructionModel {

    private String titleJusi;
    private String descriptionJusi;
    private int iconJusi;

    public PermissionInstructionModel(String title, String description, int icon) {
        this.titleJusi = title;
        this.descriptionJusi = description;
        this.iconJusi = icon;
    }

    public String getTitle() {
        return titleJusi;
    }

    public void setTitle(String title) {
        this.titleJusi = title;
    }

    public String getDescription() {
        return descriptionJusi;
    }

    public void setDescriptionJusi(String description) {
        this.descriptionJusi = description;
    }

    public int getIcon() {
        return iconJusi;
    }

    public void setIconJusi(int icon) {
        this.iconJusi = icon;
    }
}
