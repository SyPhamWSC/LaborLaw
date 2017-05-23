package com.dtsgroup.labourlaw.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ItemQA extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String questionVi;
    private String questionEn;
    private String answerVi;
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
