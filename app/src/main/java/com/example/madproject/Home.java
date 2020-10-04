package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //casing select button
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = button3.getHint().toString();
                selectImgBtn(categoryName);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = button4.getHint().toString();
                selectImgBtn(categoryName);
            }
        });

    }

    private void selectImgBtn(String categoryName) {

        Intent intent = new Intent(this,viewparts.class);
        intent.putExtra("categoryName",categoryName);
        startActivity(intent);
    }


}