package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        Intent intent = new Intent(this, PCConf.class);
=======
        Intent intent = new Intent(this ,Home.class);
>>>>>>> b9ced50... Computer Parts
        startActivity(intent);
    }
}