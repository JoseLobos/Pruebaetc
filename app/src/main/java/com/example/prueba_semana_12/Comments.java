package com.example.prueba_semana_12;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.prueba_semana_12.Interface.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments extends AppCompatActivity {
    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mJsonTxtView=findViewById(R.id.jsonTextComments);
        getComments();
    }
    private void getComments(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<com.example.prueba_semana_12.Model.Comments>> call = jsonPlaceHolderApi.getComments();
        call.enqueue(new Callback<List<com.example.prueba_semana_12.Model.Comments>>() {


            @Override
            public void onResponse(Call<List<com.example.prueba_semana_12.Model.Comments>> call, Response<List<com.example.prueba_semana_12.Model.Comments>> response) {
                if(! response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }
                List<com.example.prueba_semana_12.Model.Comments> commentsList = response.body();
                for(com.example.prueba_semana_12.Model.Comments comments: commentsList){
                    String content = "";
                    content += "postId:"+ comments.getPostId() + "\n";
                    content += "id:"+ comments.getId() + "\n";
                    content += "name:" + comments.getName() + "\n";
                    content += "email:" + comments.getEmail() + "\n\n";
                    content += "body:" + comments.getBody() + "\n\n";
                    mJsonTxtView.append(content);

                }
            }


            @Override
            public void onFailure(Call<List<com.example.prueba_semana_12.Model.Comments>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

