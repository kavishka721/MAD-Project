package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class savedConfig extends AppCompatActivity {

    Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_config);
        updatebtn = findViewById(R.id.Update_Btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(savedConfig.this, updateconfig.class);
                startActivity(intent);
            }
        });
    }

}