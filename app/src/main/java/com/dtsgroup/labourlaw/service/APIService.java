package com.dtsgroup.labourlaw.service;


import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemQA;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getlaws")
    Call<List<JSonChapterLaw>> getAllChapterLaw();

    @GET("list_question_answer")
    Call<List<JSonItemQA>> getALlQA();
}
