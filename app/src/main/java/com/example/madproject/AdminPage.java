package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    Button homeBtn,addBtn,selectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addBtn = findViewById(R.id.btn_adminAddPart);
        homeBtn = findViewById(R.id.btn_adminHome);
        selectBtn = findViewById(R.id.btn_adminSelectPart);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AdminPage.this,Home.class);
                startActivity(homeIntent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPartIntent = new Intent(AdminPage.this,addPart.class);
                startActivity(addPartIntent);
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectPartIntent = new Intent(AdminPage.this,selectPart.class);
                startActivity(selectPartIntent);
            }
        });


    }
}