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
import com.example.madproject.adapters.PSRecyclerAdapter;
import com.example.madproject.adapters.SSDRecyclerAdapter;
import com.example.madproject.classes.PowerSupply;
import com.example.madproject.classes.SSD;
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


public class psselection extends AppCompatActivity {

    TextView type;
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private PowerSupply powerSupply = new PowerSupply();
    private ArrayList<PowerSupply> PSList = new ArrayList<>();
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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE);

                Log.i("Info C", "Nothing bn");
                if (dataSnapshot.hasChildren()) {
                    Log.d("Child Count :",""+dataSnapshot.child("PowerSupply").getChildrenCount());
                    System.out.println("Hello CPUS");
                    for (DataSnapshot postSnapshot : dataSnapshot.child("PowerSupply").getChildren()){
                        Log.d("KEY VALUE:",postSnapshot.getKey());
                        PowerSupply powerSupply1 = postSnapshot.getValue(PowerSupply.class);
                        Log.d("CPU Lists",powerSupply1.getName());
                        PSList.add(powerSupply1);
                    }
                        Log.d("Image List", String.valueOf(mStorageRef.child("PS Images").listAll()));
                        mStorageRef.child("PS Images").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                // Got the download URL for 'users/me/profile.png'
                                for (StorageReference fileRef : listResult.getItems()){
                                  Log.d("File reference",fileRef.getName());
                                  fileList.add(fileRef.getName());
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
                    Recycle = new PSRecyclerAdapter(getBaseContext(), PSList, imageList);
                    recyclerView.setAdapter(Recycle);
                    layoutManager = new LinearLayoutManager(getBaseContext());
                    recyclerView.setLayoutManager(layoutManager);

                    }

                }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initImageList(final ArrayList<String> fileList){

        for (String file : fileList) {
            mStorageRef.child("PS Images/" + file).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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