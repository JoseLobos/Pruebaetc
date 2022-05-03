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

public class Albums extends AppCompatActivity {

    private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mJsonTxtView=findViewById(R.id.jsonTextAlbums);
        getAlbums();
    }
    private void getAlbums(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<com.example.prueba_semana_12.Model.Albums>> call = jsonPlaceHolderApi.getAlbums();
        call.enqueue(new Callback<List<com.example.prueba_semana_12.Model.Albums>>() {
            @Override
            public void onResponse(Call<List<com.example.prueba_semana_12.Model.Albums>> call, Response<List<com.example.prueba_semana_12.Model.Albums>> response) {

                if(! response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }
                List<com.example.prueba_semana_12.Model.Albums> albumsList = response.body();
                for(com.example.prueba_semana_12.Model.Albums albums: albumsList){
                    String content = "";
                    content += "userId:"+ albums.getUserId() + "\n";
                    content += "id:"+ albums.getId() + "\n";
                    content += "title:" + albums.getTitle() + "\n\n";

                    mJsonTxtView.append(content);

                }
            }
            @Override
            public void onFailure(Call<List<com.example.prueba_semana_12.Model.Albums>> call, Throwable t) {
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

