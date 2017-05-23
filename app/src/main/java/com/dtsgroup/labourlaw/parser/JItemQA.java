package com.dtsgroup.labourlaw.parser;

import com.dtsgroup.labourlaw.model.ItemQA;
import com.dtsgroup.labourlaw.model.JSonItemQA;

public class JItemQA {

    public static ItemQA getItemQA(JSonItemQA jSonItemQA){
        ItemQA itemQA = new ItemQA();
        itemQA.setId(jSonItemQA.getId());
        itemQA.setAnswerEn(jSonItemQA.getAnswerEn());
        itemQA.setAnswerVi(jSonItemQA.getAnswerVi());
        itemQA.setQuestionEn(jSonItemQA.getQuestionEn());
        itemQA.setQuestionVi(jSonItemQA.getQuestionVi());
        return itemQA;
    }
}
