package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfPC extends AppCompatActivity {

    Button cpubtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_p_c);
        cpubtn = findViewById(R.id.button9);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, cpuselection.class);
                startActivity(intent);
            }
        });
    }
}