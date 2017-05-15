package com.dtsgroup.labourlaw.service;


import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemBookmark;
import com.dtsgroup.labourlaw.model.JSonItemQA;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("getlaws")
    Call<List<JSonChapterLaw>> getAllChapterLaw();

    @GET("list_question_answer")
    Call<List<JSonItemQA>> getALlQA();

    @GET("list_question")
    Call<List<JSonItemQuiz>> getAllQiz();
    @GET("{num_chapter}")
    Call<List<JSonItemSubChapterLaw>> getAllSubChapterLaw(@Path("num_chapter") int num_chapter);

    @FormUrlEncoded
    @POST("bookmark")
    Call<JSonItemBookmark> bookmark(@Field("name_vi") String nameVi, @Field("name_en") String nameEn,
                                    @Field("title_vi") String titleVi, @Field("title_en") String titleEn,
                                    @Field("description_vi") String descVi, @Field("description_en") String descEn, @Field("chapter") String chapter);

    @FormUrlEncoded
    @POST("search_laws")
    Call<List<JSonItemSubChapterLaw>> getAllSearch(@Field("s") String search);

    @GET("1")
    Call<List<JSonItemSubChapterLaw>> getAllSubChapter1Law();

    @GET("bookmarks")
    Call<List<JSonItemBookmark>> getBookmarks();

    @FormUrlEncoded
    @DELETE("bookmarks")
    Call<JSonItemBookmark> deleteitem(@Field("id") int id);
}
