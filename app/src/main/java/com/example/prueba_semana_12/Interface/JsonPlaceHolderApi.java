package com.example.prueba_semana_12.Interface;

import com.example.prueba_semana_12.Model.Posts;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Posts>> getPosts();
}
