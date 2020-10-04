package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.adapters.BuildRecyclerAdapter;
import com.example.madproject.classes.CPUBuild;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class savedConfigNew extends AppCompatActivity {

    Button updatebtn;
    FloatingActionButton AddFab;

    private DatabaseReference databaseReference;;
    private StorageReference mStorageRef;
    private static RecyclerView recyclerView;
    private CPUBuild CPU = new CPUBuild();
    private static ArrayList<CPUBuild> CPUList = new ArrayList<>();
    private static ArrayList<String> BuildKeys = new ArrayList<>();
    private HashMap<String, CPUBuild> BUILDList = new HashMap<>();
    private static RecyclerView.Adapter Recycle;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_config_new);
        updatebtn = findViewById(R.id.Update_Btn);
        AddFab = findViewById(R.id.floatingActionButton);
        Intent intent = getIntent();
        if(intent.getIntExtra("New Build",0)==1)
            Toasty.success(this, "New Build Added", Toast.LENGTH_SHORT, true).show();
        else if(intent.getIntExtra("New Build",0) == 2)
            Toasty.warning(this, "The Build Has Been Updated", Toast.LENGTH_SHORT, true).show();

        AddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(savedConfigNew.this, ConfPC.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        final ProgressBar progressBar = findViewById(R.id.progressBar);
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
                    Log.d("Child Count :",""+dataSnapshot.child("CPUBuilds").getChildrenCount());
                    System.out.println("Hello CPUS");
                    for (DataSnapshot postSnapshot : dataSnapshot.child("CPUBuilds").getChildren()){
                        CPUBuild CPU1 = postSnapshot.getValue(CPUBuild.class);
                        BuildKeys.add(postSnapshot.getKey());
                        //Log.d("CPU Lists",CPU1.getCasing());
                        CPUList.add(CPU1);
                        BUILDList.put(postSnapshot.getKey(),CPU1);

                    }

                    progressBar.setVisibility(View.VISIBLE);
                    Log.d("Image List", String.valueOf(mStorageRef.child("CPU Images").listAll()));
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerView = findViewById(R.id.Build_Recycle);
                        Recycle = new BuildRecyclerAdapter(getBaseContext(), CPUList, BuildKeys, BUILDList);
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
    protected void onPause() {
        super.onPause();
        Log.d("TAG PAUSE","onPause Called");
        CPUList.clear();
        BuildKeys.clear();

    }

    public void RemoveItem(int position){

        CPUList.remove(position);
        BuildKeys.remove(position);
        //recyclerView.removeViewAt(position);
        Recycle.notifyItemRemoved(position);
        Recycle.notifyItemRangeChanged(position, CPUList.size());

    }

}