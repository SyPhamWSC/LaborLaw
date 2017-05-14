package com.dtsgroup.labourlaw.service;


import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemQA;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getlaws")
    Call<List<JSonChapterLaw>> getAllChapterLaw();

    @GET("list_question_answer")
    Call<List<JSonItemQA>> getALlQA();

    @GET("list_question")
    Call<List<JSonItemQuiz>> getAllQiz();

    @GET("1")
    Call<List<JSonItemSubChapterLaw>> getAllSubChapter1Law();

    @GET("2")
    Call<List<JSonItemSubChapterLaw>> getAllSubChapter2Law();

    @GET("3")
    Call<List<JSonItemSubChapterLaw>> getAllSubChapter3Law();
}
