package com.dtsgroup.labourlaw.model;

public class ItemLvDrawer {
    private int imgUnSelected;
    private String description;
    private int imgSelected;

    public ItemLvDrawer(String description, int imgUnSelected, int imgSelected){
        this.description = description;
        this.imgUnSelected = imgUnSelected;
        this.imgSelected = imgSelected;
    }

    public int getImg() {
        return imgUnSelected;
    }

    public void setImg(int img) {
        this.imgUnSelected = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgSelected() {
        return imgSelected;
    }

    public void setImgSelected(int imgSelected) {
        this.imgSelected = imgSelected;
    }
}
