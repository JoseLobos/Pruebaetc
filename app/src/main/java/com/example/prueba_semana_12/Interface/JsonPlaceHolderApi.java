package com.example.prueba_semana_12.Interface;

import com.example.prueba_semana_12.Model.Albums;
import com.example.prueba_semana_12.Model.Comments;
import com.example.prueba_semana_12.Model.Photos;
import com.example.prueba_semana_12.Model.Posts;
import com.example.prueba_semana_12.Model.Todos;
import com.example.prueba_semana_12.Model.Users;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts") Call<List<Posts>> getPosts();
    @GET("comments") Call<List<Comments>> getComments();
    @GET("albums") Call<List<Albums>> getAlbums();
    @GET("todos") Call<List<Todos>> getTodos();
    @GET("users") Call<List<Users>> getUsers();
    @GET("photos") Call<List<Photos>> getPhotos();




}
