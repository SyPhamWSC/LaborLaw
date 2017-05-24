package com.dtsgroup.labourlaw.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemQuiz extends RealmObject{

    @PrimaryKey
    private int id;
    private String questionVi;
    private String questionEn;
    private String ansaVi;
    private String ansaEn;
    private String ansbVi;
    private String ansbEn;
    private String anscVi;
    private String anscEn;
    private String ansdVi;
    private String ansdEn;
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
