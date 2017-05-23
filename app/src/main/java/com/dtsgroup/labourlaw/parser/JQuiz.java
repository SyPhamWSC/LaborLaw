package com.dtsgroup.labourlaw.parser;


import com.dtsgroup.labourlaw.model.ItemQuiz;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;

public class JQuiz {
    public static ItemQuiz getItemQuiz(JSonItemQuiz jSonItemQuiz){
        ItemQuiz itemQuiz = new ItemQuiz();
        itemQuiz.setId(jSonItemQuiz.getId());
        itemQuiz.setQuestionVi(jSonItemQuiz.getQuestionVi());
        itemQuiz.setQuestionEn(jSonItemQuiz.getQuestionEn());
        itemQuiz.setAnsaEn(jSonItemQuiz.getAnsaEn());
        itemQuiz.setAnsaVi(jSonItemQuiz.getAnsaVi());
        itemQuiz.setAnsbEn(jSonItemQuiz.getAnsbEn());
        itemQuiz.setAnsbVi(jSonItemQuiz.getAnsbVi());
        itemQuiz.setAnscEn(jSonItemQuiz.getAnscEn());
        itemQuiz.setAnscVi(jSonItemQuiz.getAnscVi());
        itemQuiz.setAnsdEn(jSonItemQuiz.getAnsdEn());
        itemQuiz.setAnsdVi(jSonItemQuiz.getAnsdVi());
        itemQuiz.setAnswer(jSonItemQuiz.getAnswer());

        return itemQuiz;
    }
}
