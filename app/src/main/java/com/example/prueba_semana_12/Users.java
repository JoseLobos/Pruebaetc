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

public class Users extends AppCompatActivity {

    private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mJsonTxtView=findViewById(R.id.jsonTextUsers);
        getUsers();
    }
    private void getUsers(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<com.example.prueba_semana_12.Model.Users>> call = jsonPlaceHolderApi.getUsers();
        call.enqueue(new Callback<List<com.example.prueba_semana_12.Model.Users>>() {
            @Override
            public void onResponse(Call<List<com.example.prueba_semana_12.Model.Users>> call, Response<List<com.example.prueba_semana_12.Model.Users>> response) {

                if(! response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
            }
                List<com.example.prueba_semana_12.Model.Users> usersList = response.body();
                for(com.example.prueba_semana_12.Model.Users users: usersList){
                    String content = "";
                    content += "id:"+ users.getId() + "\n";
                    content += "name:"+ users.getName() + "\n";
                    content += "username:" + users.getUsername() + "\n";
                    content += "email:" + users.getEmail() + "\n";
                    content += "address:" + users.getAddress() + "\n";
                    content += "suite:" + users.getSuite() + "\n";
                    content += "city:" + users.getCity() + "\n";

                    content += "zipcode:" + users.getZipcode() + "\n";
                    content += "geo:" + users.getGeo() + "\n";
                    content += "lat:" + users.getLat() + "\n";
                    content += "lng:" + users.getLng() + "\n";
                    content += "phone:" + users.getPhone() + "\n";
                    content += "website:" + users.getWebsite() + "\n";
                    content += "company:" + users.getCompany() + "\n";
                    content += "catchPhrase:" + users.getCatchPhrase() + "\n";
                    content += "bs:" + users.getBs() + "\n\n";
                    mJsonTxtView.append(content);

            }
            }


            @Override
            public void onFailure(Call<List<com.example.prueba_semana_12.Model.Users>> call, Throwable t) {
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
