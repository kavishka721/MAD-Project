package com.example.madproject.selections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.R;
import com.example.madproject.adapters.CPURecyclerAdapter;
import com.example.madproject.adapters.RAMRecyclerAdapter;
import com.example.madproject.classes.CPU;
import com.example.madproject.classes.RAM;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ramselection extends AppCompatActivity {

    TextView type;
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private com.example.madproject.classes.RAM ram = new RAM();
    private ArrayList<com.example.madproject.classes.RAM> RAMList = new ArrayList<>();
    private ArrayList<Uri> imageList = new ArrayList<>();
    private ArrayList<String> fileList = new ArrayList<>();
    private RecyclerView.Adapter Recycle;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuselection_new);
        type = findViewById(R.id.Type);
        Intent intent = getIntent();
        String Type = intent.getStringExtra("Type");
        type.setText(Type);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        final ProgressBar progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Sprite doubleBounce = new CubeGrid();
        progressBar.setIndeterminateDrawable(doubleBounce);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE);

                Log.i("Info C", "Nothing bn");
                if (dataSnapshot.hasChildren()) {
                    Log.d("Child Count :",""+dataSnapshot.child("RAM").getChildrenCount());
                    System.out.println("Hello CPUS");
                    for (DataSnapshot postSnapshot : dataSnapshot.child("RAM").getChildren()){
                        Log.d("KEY VALUE:",postSnapshot.getKey());
                        com.example.madproject.classes.RAM ram1 = postSnapshot.getValue(com.example.madproject.classes.RAM.class);
                        Log.d("CPU Lists",ram1.getName());
                        RAMList.add(ram1);
                    }
                    /*if (dataSnapshot.hasChildren()) {
                        CPU = dataSnapshot.child("CPUs").child("CPU1").getValue(CPUs.class);
                        System.out.println("Base Clock : " + CPU.getBase_Clock() + "GHz");
                        CPUList.add(CPU);
                        Log.i("CPU List", String.valueOf(CPUList.size()));*/
                        //Log.i("Values", dataSnapshot.child("CPU1").child("Name").getValue().toString());

                        //progressBar.setVisibility(View.VISIBLE);
                        Log.d("Image List", String.valueOf(mStorageRef.child("Ram Images").listAll()));
                        mStorageRef.child("Ram Images").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                // Got the download URL for 'users/me/profile.png'
                                for (StorageReference fileRef : listResult.getItems()){
                                  Log.d("File reference",fileRef.getName());
                                  fileList.add(fileRef.getName());
                                  //Log.d("Download Link", mStorageRef.);
                                    /*mStorageRef.child("CPU Images/"+fileRef.getName()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Got the download URL for 'users/me/profile.png'
                                            Log.d("Meka:",uri.toString());
                                        }
                                }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });*/
                                //Log.d("URL",uri.toString());
                                //imageList.add(uri);
                                /*progressBar.setVisibility(View.INVISIBLE);
                                recyclerView = findViewById(R.id.CPU_Recycle);
                                Recycle = new CPURecyclerAdapter(getBaseContext(), CPUList, imageList);
                                recyclerView.setAdapter(Recycle);
                                layoutManager = new LinearLayoutManager(getBaseContext());
                                recyclerView.setLayoutManager(layoutManager);*/
                            }

                                initImageList(fileList);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView = findViewById(R.id.CPU_Recycle);
                    Recycle = new RAMRecyclerAdapter(getBaseContext(), RAMList, imageList);
                    recyclerView.setAdapter(Recycle);
                    layoutManager = new LinearLayoutManager(getBaseContext());
                    recyclerView.setLayoutManager(layoutManager);

                    }

                }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
/*

        CPU.setName("RYZEN 7");
        CPU.setPrice(126000);
        CPUList.add(CPU);
*/

       /* recyclerView = findViewById(R.id.CPU_Recycle);
        Recycle = new CPURecyclerAdapter(this, CPUList);
        recyclerView.setAdapter(Recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initImageList(final ArrayList<String> fileList){

        for (String file : fileList) {
            mStorageRef.child("Ram Images/" + file).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Got the download URL for 'users/me/profile.png'
                    Log.d("URIs:", uri.toString());
                    imageList.add(uri);
                    if(imageList.size() == fileList.size())
                        Recycle.notifyDataSetChanged();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }
        Log.d("Image List",""+imageList.size());
        //Recycle.notifyDataSetChanged();
    }

}