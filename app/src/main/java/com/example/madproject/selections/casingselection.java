package com.example.madproject.selections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.R;
import com.example.madproject.adapters.CasingRecyclerAdapter;
import com.example.madproject.classes.Casing;
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


public class casingselection extends AppCompatActivity {

    TextView type;
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private Casing CPU = new Casing();
    private ArrayList<Casing> CPUList = new ArrayList<>();
    private ArrayList<Uri> imageList = new ArrayList<>();
    private ArrayList<String> fileList = new ArrayList<>();
    private RecyclerView.Adapter Recycle;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuselection_new);
        type = findViewById(R.id.Type);

        //Intent intent = getIntent();
        //String Type = intent.getStringExtra("Type");
        type.setText("Casing");

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
                    Log.d("Child Count :",""+dataSnapshot.child("Casings").getChildrenCount());
                    System.out.println("Hello CPUS");
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Casings").getChildren()){
                        Casing CPU1 = postSnapshot.getValue(Casing.class);
                        Log.d("CPU Lists",CPU1.getName());
                        CPUList.add(CPU1);
                    }
                    /*if (dataSnapshot.hasChildren()) {
                        CPU = dataSnapshot.child("CPUs").child("CPU1").getValue(CPUs.class);
                        System.out.println("Base Clock : " + CPU.getBase_Clock() + "GHz");
                        CPUList.add(CPU);
                        Log.i("CPU List", String.valueOf(CPUList.size()));*/
                        //Log.i("Values", dataSnapshot.child("CPU1").child("Name").getValue().toString());

                        progressBar.setVisibility(View.VISIBLE);
                        Log.d("Image List", String.valueOf(mStorageRef.child("Casing Images").listAll()));
                        mStorageRef.child("Casing Images").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                // Got the download URL for 'users/me/profile.png'
                                for (StorageReference fileRef : listResult.getItems()){
                                  Log.d("File reference",fileRef.getName());
                                  //Log.d("Download Link", mStorageRef.);
                                    fileList.add(fileRef.getName());
                                //Log.d("URL",uri.toString());
                                //imageList.add(uri);

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
                    Recycle = new CasingRecyclerAdapter(getBaseContext(), CPUList, imageList);
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

    public void initImageList(final ArrayList<String> fileList){

        for (String file : fileList) {
            mStorageRef.child("Casing Images/" + file).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*public void initFirebase() throws InterruptedException {
        Log.i("Info A", "Nothing bn");
        new Thread() {
            public void run() {
                Looper.prepare();
                final Handler handler = new Handler();
                Log.i("Info C", "Nothing bn");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        databaseReference = FirebaseDatabase.getInstance().getReference();

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.i("Info B", "Nothing bn");

                                    if (dataSnapshot.hasChildren()) {
                                        CPU = dataSnapshot.child("CPUs").child("CPU1").getValue(CPUs.class);
                                        System.out.println("Base Clock : " + CPU.getBase_Clock() + "GHz");
                                        CPUList.add(CPU);
                                        Log.i("CPU List", String.valueOf(CPUList.size()));
                                        //Log.i("Values", dataSnapshot.child("CPU1").child("Name").getValue().toString());
                                    }
                                }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        handler.removeCallbacks(this);
                        Looper.myLooper().quit();
                    }
                }, 0);
                Looper.loop();
            }
        }.start();
    }*/

}