package com.example.madproject.selections;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.R;
import com.example.madproject.adapters.CasingRecyclerAdapter;
import com.example.madproject.adapters.RAMRecyclerAdapter;
import com.example.madproject.classes.Casing;
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

    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    private RecyclerView recyclerView;
    private RAM ram = new RAM();
    private ArrayList<RAM> RAMList = new ArrayList<>();
    private ArrayList<Uri> imageList = new ArrayList<>();
    private ArrayList<String> fileList = new ArrayList<>();
    private RecyclerView.Adapter Recycle;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuselection_new);

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
                    Log.d("Child Count :",""+dataSnapshot.child("RAM").getChildrenCount());
                    System.out.println("Hello CPUS");
                    for (DataSnapshot postSnapshot : dataSnapshot.child("RAM").getChildren()){
                        RAM ram1 = postSnapshot.getValue(RAM.class);
                        Log.d("CPU Lists",ram1.getName());
                        RAMList.add(ram1);
                    }

                        progressBar.setVisibility(View.VISIBLE);
                        Log.d("Image List", String.valueOf(mStorageRef.child("RAM Images").listAll()));
                        mStorageRef.child("CPU Images").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                            @Override
                            public void onSuccess(ListResult listResult) {
                                // Got the download URL for 'users/me/profile.png'
                                for (StorageReference fileRef : listResult.getItems()){
                                  Log.d("File reference",fileRef.getName());
                                  fileList.add(fileRef.getName());
                                  //Log.d("Download Link", mStorageRef.);
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

                    }
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView = findViewById(R.id.CPU_Recycle);
                recyclerView.setAdapter(Recycle);
                Recycle = new RAMRecyclerAdapter(getBaseContext(), RAMList, imageList);
                layoutManager = new LinearLayoutManager(getBaseContext());
                recyclerView.setLayoutManager(layoutManager);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initImageList(final ArrayList<String> fileList){

        for (String file : fileList) {
            mStorageRef.child("RAM Images/" + file).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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