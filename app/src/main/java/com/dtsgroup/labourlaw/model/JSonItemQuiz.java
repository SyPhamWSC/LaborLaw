package com.dtsgroup.labourlaw.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JSonItemQuiz implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_vi")
    @Expose
    private String questionVi;
    @SerializedName("question_en")
    @Expose
    private String questionEn;
    @SerializedName("ansa_vi")
    @Expose
    private String ansaVi;
    @SerializedName("ansa_en")
    @Expose
    private String ansaEn;
    @SerializedName("ansb_vi")
    @Expose
    private String ansbVi;
    @SerializedName("ansb_en")
    @Expose
    private String ansbEn;
    @SerializedName("ansc_vi")
    @Expose
    private String anscVi;
    @SerializedName("ansc_en")
    @Expose
    private String anscEn;
    @SerializedName("ansd_vi")
    @Expose
    private String ansdVi;
    @SerializedName("ansd_en")
    @Expose
    private String ansdEn;
    @SerializedName("answer")
    @Expose
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionVi() {
        return questionVi;
    }

    public void setQuestionVi(String questionVi) {
        this.questionVi = questionVi;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }

    public String getAnsaVi() {
        return ansaVi;
    }

    public void setAnsaVi(String ansaVi) {
        this.ansaVi = ansaVi;
    }

    public String getAnsaEn() {
        return ansaEn;
    }

    public void setAnsaEn(String ansaEn) {
        this.ansaEn = ansaEn;
    }

    public String getAnsbVi() {
        return ansbVi;
    }

    public void setAnsbVi(String ansbVi) {
        this.ansbVi = ansbVi;
    }

    public String getAnsbEn() {
        return ansbEn;
    }

    public void setAnsbEn(String ansbEn) {
        this.ansbEn = ansbEn;
    }

    public String getAnscVi() {
        return anscVi;
    }

    public void setAnscVi(String anscVi) {
        this.anscVi = anscVi;
    }

    public String getAnscEn() {
        return anscEn;
    }

    public void setAnscEn(String anscEn) {
        this.anscEn = anscEn;
    }

    public String getAnsdVi() {
        return ansdVi;
    }

    public void setAnsdVi(String ansdVi) {
        this.ansdVi = ansdVi;
    }

    public String getAnsdEn() {
        return ansdEn;
    }

    public void setAnsdEn(String ansdEn) {
        this.ansdEn = ansdEn;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
