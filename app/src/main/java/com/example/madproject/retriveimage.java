package com.example.madproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class retriveimage extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    ImageView imgfromphone;
    Button choose;
    Uri filepath;

    private static final String STORAGE_PATH_UPLOADS = "Images/";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retriveimage);

        imgfromphone = findViewById(R.id.imgfromphone);
        choose = findViewById(R.id.btn_imgchoose);



    }

    public void openImg(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select image"),SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO){
            //Uri selectImg = data.getData();
           // InputStream inputStream = null;

//            // Open a specific media item using InputStream.
//            ContentResolver resolver = getApplicationContext()
//                    .getContentResolver();
//            try (InputStream inputStream = resolver.openInputStream(selectImg)) {
//                // Perform operations on "stream".
//            }catch (IOException e){
//                e.printStackTrace();
//            }

         /*   try {
                assert selectImg != null;
                inputStream = getContentResolver().openInputStream(selectImg);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }   */

            //BitmapFactory.decodeStream(inputStream);

                Uri filepath = MediaStore.Images.Media.getContentUri("Download");
                imgfromphone.setImageURI(filepath);

        }
    }
}