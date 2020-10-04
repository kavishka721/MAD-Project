package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PCConf extends AppCompatActivity {

    Button button;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_c_conf);
        button = findViewById(R.id.button13);
        button2 = findViewById(R.id.button20);
    }

    @Override
    protected void onResume() {
        super.onResume();
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PCConf.this, ConfPC.class);
            startActivity(intent);
        }
    });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PCConf.this, savedConfigNew.class);
                startActivity(intent);
            }
        });
    }
}