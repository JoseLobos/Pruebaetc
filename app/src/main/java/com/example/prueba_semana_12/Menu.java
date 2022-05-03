package com.example.prueba_semana_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Button Posts = (Button)this.findViewById(R.id.btnPosts);
        Button Comments= (Button)this.findViewById(R.id.btnComments);
        Button Albums = (Button)this.findViewById(R.id.btnAlbums);
        Button Photos = (Button)this.findViewById(R.id.btnPhotos);
        Button Todos = (Button)this.findViewById(R.id.btnTodos);
        Button Users = (Button)this.findViewById(R.id.btnUsers);

        Posts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent Menu= new Intent(Menu.this,Posts.class);
                startActivity(Menu);
            }
        });

        Comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this,Comments.class);
                startActivity(Menu);
            }
        });

        Albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this,Albums.class);
                startActivity(Menu);
            }
        });

       Photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this,Photos.class);
                startActivity(Menu);
            }
        });

        Todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this,Todos.class);
                startActivity(Menu);
            }
        });

        Users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this,Users.class);
                startActivity(Menu);
            }
        });


    }

}

