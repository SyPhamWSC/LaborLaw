package com.dtsgroup.labourlaw.model;

public class ItemEnterGuide {

    private int chapter;
    private String titleVi;
    private String shortDescreptionVi;
    private String titleEn;
    private String shortDescreptionEn;

    public ItemEnterGuide(int chapter, String titleVi, String shortDescreptionVi, String titleEn, String shortDescreptionEn) {
        this.chapter = chapter;
        this.titleVi = titleVi;
        this.shortDescreptionVi = shortDescreptionVi;
        this.titleEn = titleEn;
        this.shortDescreptionEn = shortDescreptionEn;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getTitleVi() {
        return titleVi;
    }

    public void setTitleVi(String title) {
        this.titleVi = title;
    }

    public String getShortDescreptionVi() {
        return shortDescreptionVi;
    }

    public void setShortDescreptionVi(String shortDescreption) {
        this.shortDescreptionVi = shortDescreption;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getShortDescreptionEn() {
        return shortDescreptionEn;
    }
}
