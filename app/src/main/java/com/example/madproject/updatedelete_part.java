package com.example.madproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madproject.adapters.selectPartRecyclerAdapter;
import com.example.madproject.classes.CPUBuild;
import com.example.madproject.classes.Parts;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class updatedelete_part extends AppCompatActivity {

    EditText partname, partprice, partdes;
    Spinner category;
    ImageView partimg;
    Button updatebtn, updateImgBtn, deleteBtn;
    String imgurl,categoryName,imgpath;
    private static final int pick_image_request = 71;
    DatabaseReference reference;
    String pname,pdes,pprice,key;
    Uri partimgpath;
    StorageReference storageref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete_part);

        reference = FirebaseDatabase.getInstance().getReference("parts");

        updatebtn = findViewById(R.id.updateBtn);
        updateImgBtn = findViewById(R.id.updateImgBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        partname = findViewById(R.id.editText_partName2);
        partprice = findViewById(R.id.editText_partPrice2);
        partdes = findViewById(R.id.editText_partdes2);
        partimg = findViewById(R.id.partimgupdatedelete);
        category = findViewById(R.id.updatedeletespinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        Intent updatedeleteIntent = getIntent();

        System.out.println(updatedeleteIntent.getStringExtra("pname"));
        System.out.println(updatedeleteIntent.getStringExtra("pprice"));
        System.out.println(updatedeleteIntent.getStringExtra("pdescription"));
        System.out.println(updatedeleteIntent.getStringExtra("imgurl"));
        System.out.println(updatedeleteIntent.getStringExtra("pcategory"));

        partname.setText(updatedeleteIntent.getStringExtra("pname"));
        partprice.setText(updatedeleteIntent.getStringExtra("pprice"));
        partdes.setText(updatedeleteIntent.getStringExtra("pdescription"));
        imgurl = updatedeleteIntent.getStringExtra("imgurl");
        categoryName = updatedeleteIntent.getStringExtra("pcategory");
        key = updatedeleteIntent.getStringExtra("key");

        System.out.println(key);
        System.out.println(categoryName);

       // category.setSelected(true);

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(imgurl)
                .into(partimg);


        pname = updatedeleteIntent.getStringExtra("pname");
        pdes = updatedeleteIntent.getStringExtra("pdescription");
        pprice = updatedeleteIntent.getStringExtra("pprice");

        imgpath = reference.child(key).child("imageUri").toString();

        int n1=9,n2=6;
        cal(n1 , n2);

        System.out.println("update java db name  value" + pname);
        System.out.println("update java db price value" + pprice);

        //storageref = FirebaseStorage.getInstance().getReference("pc parts/");

        //================= set delete data method ==============================
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(v);

            }
        });
        //============== end delete method=============

        //==================== set update image method ===========================
        updateImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateImage(v);

            }
        });
        //===================================

        //============ set update data method ====================
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData(v);
            }
        });
        //===================================================


    }

    public void cal(int num1,int num2){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pick_image_request && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            partimgpath = data.getData();

            Log.e("FPATH",partimgpath.toString());// path name of the image in android device

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),partimgpath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Picasso.get().load(filePath).into(partimgview);
            //partimgview.setImageURI(filePath);
            partimg.setImageBitmap(bitmap);
        }
    }

    public void chooseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), pick_image_request);


    }
    //==================== delete data method ===============================
    public void deleteData(View view){

        //reference.child(key);
        reference.child(key).removeValue();
        Toast.makeText(updatedelete_part.this, "Delete data successfully", Toast.LENGTH_SHORT).show();

        Intent myintent = new Intent(updatedelete_part.this,selectPart.class);
        startActivity(myintent);
    }

    public void updateImage(View view){

        if (partimgpath != null){

            storageref = FirebaseStorage.getInstance().getReference("pc parts/").child(categoryName).child(UUID.randomUUID().toString());
            storageref.putFile(partimgpath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> downloadUrl = storageref.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            String imageReference = uri.toString();
                            reference.child(key).child("imageUri").setValue(imageReference);
                            // part.setImageUri(imageReference);


                        }
                    });
//                    if (!imgpath.equals(reference.child(key).child("imageUri"))){
                        Toast.makeText(updatedelete_part.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(updatedelete_part.this, "Image is same and cannot be uploaded", Toast.LENGTH_SHORT).show();
//                    }

                }

            });
        }

    }


    public void UpdateData(View view){

        if(isNameChanged() || isPriceChanged() || isDesChanged()){


//            reference.child(key).child("pName").setValue(partname.getEditableText().toString());
//            reference.child(key).child("pPrice").setValue(partprice.getEditableText().toString());
//            reference.child(key).child("pDescription").setValue(partdes.getEditableText().toString());

            Toast.makeText(this, "Data Updated!!", Toast.LENGTH_SHORT).show();

            Intent myintent = new Intent(updatedelete_part.this,selectPart.class);
            startActivity(myintent);

        }
        else {
            Toast.makeText(this, "Data is same cannot be updated!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isDesChanged() {
        if (!pdes.equals(partdes.getEditableText().toString())){
            reference.child(key).child("pDescription").setValue(partdes.getEditableText().toString());
            partdes.setText(partdes.getEditableText().toString());
            return true;
        }
        else {
            return false;
        }

    }

    private boolean isPriceChanged() {
        if (!pprice.equals(partprice.getEditableText().toString())){
            reference.child(key).child("pPrice").setValue(partprice.getEditableText().toString());
            partprice.setText(partprice.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if (! pname.equals(partname.getEditableText().toString())){
            reference.child(key).child("pName").setValue(partname.getEditableText().toString());
            partname.setText(partname.getEditableText().toString());
            return true;
        }
        else {
            return false;
        }
    }

}