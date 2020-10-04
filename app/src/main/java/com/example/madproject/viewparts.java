package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madproject.adapters.partRecyclerViewAdapter;
import com.example.madproject.classes.Parts;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class viewparts extends AppCompatActivity {

    private ArrayList<Parts> partList;
    private Context myCongext;
    RecyclerView recyclerView;
    partRecyclerViewAdapter adapter;
    DatabaseReference databaseReference;
    Button selectBtn;
    TextView categorytxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewparts);

        categorytxt = findViewById(R.id.categorynametxt);
        selectBtn = findViewById(R.id.imgselectbtn);

        Intent newIntent = getIntent();
        final String cName = newIntent.getStringExtra("categoryName");

        categorytxt.setText(cName);
        System.out.println(cName);
        Log.d("TAG",cName);
        partList = new ArrayList<>();

        recyclerView = findViewById(R.id.partRecycleView);
        databaseReference = FirebaseDatabase.getInstance().getReference("parts");
        clearAll();

        Log.d("TAG","before query");
        Query ref = databaseReference.orderByChild("category").equalTo(cName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    System.out.println(dataSnapshot);
                    Parts part = new Parts();
                    Log.d("SET","set url and name");
                 part.setImageUri(dataSnapshot.child("imageUri").getValue().toString());
                 part.setpName(dataSnapshot.child("pName").getValue().toString());
                 part.setpPrice(dataSnapshot.child("pPrice").getValue().toString());

                 System.out.println(part.getImageUri());
                 System.out.println(part.getpName());
                 System.out.println(part.getpPrice());


                 partList.add(part);

//                 selectBtn.setOnClickListener(new View.OnClickListener() {
//                     @Override
//                     public void onClick(View v) {
//                         selectImage();
//                     }
//                 });

                }

                adapter = new partRecyclerViewAdapter(getApplicationContext(),partList);
                recyclerView.setLayoutManager(new LinearLayoutManager(myCongext));
                recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void selectImage(){
        Intent intent = new Intent(this,cart.class);
        intent.putExtra("pname",partList.get(3).toString());
        intent.putExtra("price",partList.get(1).toString());
        System.out.println(intent.getExtras());
        intent.putExtra("description",partList.get(4).toString());
        startActivity(intent);
    }

    private void clearAll(){
        if (partList != null){
            partList.clear();
        }
        partList = new ArrayList<>();
    }

}