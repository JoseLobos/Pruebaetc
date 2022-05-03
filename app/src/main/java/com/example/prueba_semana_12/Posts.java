package com.example.prueba_semana_12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.prueba_semana_12.Interface.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Posts extends AppCompatActivity {

    private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mJsonTxtView=findViewById(R.id.jsonText);
        getPosts();
    }
    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<com.example.prueba_semana_12.Model.Posts>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<com.example.prueba_semana_12.Model.Posts>>() {
            @Override
            public void onResponse(Call<List<com.example.prueba_semana_12.Model.Posts>> call, Response<List<com.example.prueba_semana_12.Model.Posts>> response) {

                if(! response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
            }
                List<com.example.prueba_semana_12.Model.Posts> postsList = response.body();
                for(com.example.prueba_semana_12.Model.Posts post: postsList){
                    String content = "";
                    content += "userId:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "title:" + post.getTitle() + "\n";
                    content += "body:" + post.getBody() + "\n\n";
                    mJsonTxtView.append(content);

            }
            }


            @Override
            public void onFailure(Call<List<com.example.prueba_semana_12.Model.Posts>> call, Throwable t) {
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
