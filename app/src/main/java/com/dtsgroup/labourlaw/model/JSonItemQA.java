package com.dtsgroup.labourlaw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JSonItemQA implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_vi")
    @Expose
    private String questionVi;
    @SerializedName("question_en")
    @Expose
    private String questionEn;
    @SerializedName("answer_vi")
    @Expose
    private String answerVi;
    @SerializedName("answer_en")
    @Expose
    private String answerEn;

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

    public String getAnswerVi() {
        return answerVi;
    }

    public void setAnswerVi(String answerVi) {
        this.answerVi = answerVi;
    }

    public String getAnswerEn() {
        return answerEn;
    }

    public void setAnswerEn(String answerEn) {
        this.answerEn = answerEn;
    }

}
