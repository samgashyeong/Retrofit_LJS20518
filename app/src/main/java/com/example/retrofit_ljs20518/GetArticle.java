package com.example.retrofit_ljs20518;

import com.example.retrofit_ljs20518.Data.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetArticle {
    @GET("/api/articles")
    Call<Data> getArticle();
}
