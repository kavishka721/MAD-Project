package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class cart extends AppCompatActivity {

    TextView txtname,txtprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtname = findViewById(R.id.pnametxt);
        txtprice = findViewById(R.id.pricetxt);

        txtname.setText(getIntent().getStringExtra("pname").toString());
        txtprice.setText(getIntent().getStringExtra("price").toString());
    }
}