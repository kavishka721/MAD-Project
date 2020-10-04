package com.example.placemyorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class productDetails extends AppCompatActivity {


    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_details );


        addToCart=(Button) findViewById(R.id.button);
        addToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent( getApplicationContext() ,orderForm.class );
                startActivity( i );
            }
        } );

    }
}