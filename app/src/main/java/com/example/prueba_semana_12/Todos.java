package com.example.prueba_semana_12;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba_semana_12.Interface.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Todos extends AppCompatActivity {

    private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        mJsonTxtView=findViewById(R.id.jsonTextTodos);
        getTodos();
    }
    private void getTodos(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<com.example.prueba_semana_12.Model.Todos>> call = jsonPlaceHolderApi.getTodos();
        call.enqueue(new Callback<List<com.example.prueba_semana_12.Model.Todos>>() {
            @Override
            public void onResponse(Call<List<com.example.prueba_semana_12.Model.Todos>> call, Response<List<com.example.prueba_semana_12.Model.Todos>> response) {

                if(! response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
            }
                List<com.example.prueba_semana_12.Model.Todos> todosList = response.body();
                for(com.example.prueba_semana_12.Model.Todos todos: todosList){
                    String content = "";
                    content += "userId:"+ todos.getUserId() + "\n";
                    content += "id:"+ todos.getId() + "\n";
                    content += "title:" + todos.getTitle() + "\n";
                    content += "completed:" + todos.getCompleted() + "\n\n";
                    mJsonTxtView.append(content);

            }
            }


            @Override
            public void onFailure(Call<List<com.example.prueba_semana_12.Model.Todos>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
}
}