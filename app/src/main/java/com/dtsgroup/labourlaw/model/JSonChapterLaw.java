package com.dtsgroup.labourlaw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSonChapterLaw {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sname_chapter_vi")
    @Expose
    private String snameChapterVi;
    @SerializedName("sname_chapter_en")
    @Expose
    private String snameChapterEn;
    @SerializedName("description_vi")
    @Expose
    private String descriptionVi;
    @SerializedName("description_en")
    @Expose
    private String descriptionEn;
    @SerializedName("type_chapter")
    @Expose
    private String typeChapter;
    @SerializedName("num_chapter")
    @Expose
    private String numChapter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSnameChapterVi() {
        return snameChapterVi;
    }

    public void setSnameChapterVi(String snameChapterVi) {
        this.snameChapterVi = snameChapterVi;
    }

    public String getSnameChapterEn() {
        return snameChapterEn;
    }

    public void setSnameChapterEn(String snameChapterEn) {
        this.snameChapterEn = snameChapterEn;
    }

    public String getDescriptionVi() {
        return descriptionVi;
    }

    public void setDescriptionVi(String descriptionVi) {
        this.descriptionVi = descriptionVi;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getTypeChapter() {
        return typeChapter;
    }

    public void setTypeChapter(String typeChapter) {
        this.typeChapter = typeChapter;
    }

    public String getNumChapter() {
        return numChapter;
    }

    public void setNumChapter(String numChapter) {
        this.numChapter = numChapter;
    }


}
