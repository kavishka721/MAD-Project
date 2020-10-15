package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class cart extends AppCompatActivity {

    TextView txtname,txtprice,txtdes,pid;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtname = findViewById(R.id.pnametxt);
        txtprice = findViewById(R.id.pricetxt);
        txtdes = findViewById(R.id.destxt);
        image = findViewById(R.id.imgviewCart);
        pid = findViewById(R.id.pid);

        txtname.setText(getIntent().getStringExtra("partName"));
        txtprice.setText(getIntent().getStringExtra("partPrice"));
        txtdes.setText(getIntent().getStringExtra("partDescription"));
        pid.setText(getIntent().getStringExtra("pid"));

        System.out.println(getIntent().getStringExtra("partName"));
        System.out.println(getIntent().getStringExtra("partPrice"));
        System.out.println(getIntent().getStringExtra("partDescription"));
        System.out.println(getIntent().getStringExtra("pid"));

        String imgurl = getIntent().getStringExtra("imageUri");



        //====================load image into image view ====================
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(imgurl)
                .into(image);

        final String price = txtprice.toString();

//        // add to cart btn eka click klama price eka yanwa
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),activity.class); //activity.class ilagata yana class eka
//              //  intent.putExtra("price",price);
//                startActivity(intent);
//            }
//        });
//
//
//        //  txtprice kiyna text view ekta price eka set krna wdiya
//        txtpartprice.setText(getIntent().getStringExtra("price"));    //meka ilagata navigate wena activity eke java class eke dnna

    }
}