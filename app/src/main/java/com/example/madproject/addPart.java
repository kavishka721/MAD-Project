package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madproject.R;
import com.example.madproject.classes.Parts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class addPart extends AppCompatActivity {

    Spinner dropDownList;
    Button img1Btn, img2Btn, img3Btn, img4Btn, addBtn;
    EditText edtpname, edtpPrice, edtpDes ;
    Uri filePath ;
    Parts part;
    ImageView partimgview;
    String imageUri;

    private  static final int pick_image_request = 71;


   // FirebaseDatabase database;
    DatabaseReference databaseRef;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        dropDownList = (Spinner) findViewById(R.id.spinnerSelectCategory);
        edtpname = (EditText) findViewById(R.id.editText_partName);
        edtpPrice = (EditText) findViewById(R.id.editText_partPrice);
        edtpDes = (EditText) findViewById(R.id.editText_partDes);
        img1Btn = (Button) findViewById(R.id.btn_choose1);
//        img2Btn = findViewById(R.id.btn_choose2);
//        img3Btn = findViewById(R.id.btn_choose3);
//        img4Btn = findViewById(R.id.btn_choose4);
        addBtn = (Button) findViewById(R.id.btn_addPart);
        partimgview = (ImageView) findViewById(R.id.partimgview);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownList.setAdapter(adapter);

        //database = FirebaseDatabase.getInstance().getReference().child("parts");
        databaseRef = FirebaseDatabase.getInstance().getReference();


        storageReference = FirebaseStorage.getInstance().getReference("pc parts/");


        //save data in Firebase database
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DB REF",""+databaseRef);
                System.out.println("SSSSSSSSSSSSSSS");

                String categoryName  = dropDownList.getSelectedItem().toString();
                System.out.println("CAT:"+categoryName);
                String pName = edtpname.getText().toString();
                System.out.println("NAME:"+pName);
                String pDescription = edtpDes.getText().toString();
                System.out.println("DES:"+pDescription);
                String pPrice = edtpPrice.getText().toString();

               // int quantity = 10;

               // String imageUrl;

              /*  HashMap<String,String> part = new HashMap<>();

                part.put("cName",categoryName);
                part.put("pName",pName);
                part.put("pDes",pDescription);
                part.put("price",pPrice);   */

                part = new Parts(categoryName,pName,pDescription,pPrice,imageUri);
                System.out.println("OBJECT"+part.getImageUri());

                //-----------------------upload image-------------------------------------------------------

                if(filePath != null){
//                    final ProgressDialog progressDialog = new ProgressDialog(this);
//                    progressDialog.setTitle("Uploading...");
//                    progressDialog.show();

                    String category = dropDownList.getSelectedItem().toString();
                    final StorageReference ref = storageReference.child( categoryName).child(UUID.randomUUID().toString());
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                                    Task<Uri> downloadUrl = ref.getDownloadUrl();
                                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {

                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageReference = uri.toString();
                                            databaseRef.child("parts").child(part.getKey()).child("imageUri").setValue(imageReference);
                                            part.setImageUri(imageReference);
                                        }
                                    });


                                    //get image url and it to imgUri variable
//                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    HashMap<String,String> hashMap = new HashMap<>();
//                                    hashMap.put("imgUrl",String.valueOf(uri));
//                                    part.setImgUrl(hashMap.toString());
//                                    System.out.println(hashMap.toString());
//                                    Log.d("IMAGE_URL",imageUri);
//
//                                }
//                            });


                                  //  progressDialog.dismiss();
                                    Toast.makeText(addPart.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(addPart.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                                            .getTotalByteCount());
//                                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
//                                }
//                            });
                }
                //------------------------------------------------------------------------------------------

                DatabaseReference dataref = databaseRef.child("parts").push();
                part.setImageUri("");
                dataref.setValue(part);
                String key = dataref.getKey();
                part.setKey(key);


//                DatabaseReference specimenReference = reference.child("specimens").push();
//                specimenDTO.setImageUrl(" ");
//                specimenReference.setValue(specimenDTO);
//                // update the DTO with the Firebase generated key.
//                String key = specimenReference.getKey();
//                specimenDTO.setKey(key);

                Toast.makeText(addPart.this, "Part addedd successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }

/*
    public void getValue(){
       // part.setPid(databaseRef.push().getKey());
        part.setCategory(dropDownList.getSelectedItem().toString());
        part.setpName(edtpname.getText().toString());
        part.setpDescription(edtpDes.getText().toString());
        part.setpPrice(Float.parseFloat(edtpPrice.getText().toString()));

    }
*/

    //------------chooaseImage method-------------------------
  public void chooseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), pick_image_request);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pick_image_request && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();

            Log.e("FPATH",filePath.toString());

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Picasso.get().load(filePath).into(partimgview);
            //partimgview.setImageURI(filePath);
            partimgview.setImageBitmap(bitmap);
        }
    }

    //----------get image file extension-------------------
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //------------uploadImage method-----------------
    public void uploadImage(){



    }

}