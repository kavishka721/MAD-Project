package com.example.madproject;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.madproject.adapters.selectPartRecyclerAdapter;
        import com.example.madproject.classes.Parts;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class selectPart extends AppCompatActivity {

    Spinner categorylist;
    String categoryName;
    DatabaseReference mydbref;
    ArrayList<Parts> selectpartlist;
    Context context;
    RecyclerView recyclerView;
    selectPartRecyclerAdapter selectPartRecyclerAdapter;
    Button backtoadminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_part);

        categorylist =  findViewById(R.id.spinnerCategory);
        recyclerView = findViewById(R.id.selectPartRecyclerView);
        backtoadminBtn = findViewById(R.id.backtoadminBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorylist.setAdapter(adapter);

        mydbref = FirebaseDatabase.getInstance().getReference("parts");

        System.out.println("db REF"+mydbref);

        //====== retreive part list mrthod call
        getPartList();

        backtoadminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectPart.this,AdminPage.class);
                startActivity(intent);
            }
        });

    }

    //======= change datalist when select item in spinner method===========================
    public void getPartList(){

        categorylist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryName = categorylist.getSelectedItem().toString();

                clearAll();
                Query query = mydbref.orderByChild("category").equalTo(categoryName);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datasp : snapshot.getChildren()){

                            Parts part = new Parts();
                            Log.d("SET","set url and name");
                            part.setImageUri(datasp.child("imageUri").getValue().toString());
                            part.setpName(datasp.child("pName").getValue().toString());
                            part.setpPrice(datasp.child("pPrice").getValue().toString());
                            part.setpDescription(datasp.child("pDescription").getValue().toString());
                            part.setCategory(datasp.child("category").getValue().toString());
                            part.setKey(datasp.getKey().toString());

                            System.out.println("before display part data");
                            System.out.println(part.getImageUri());
                            System.out.println(part.getpName());
                            System.out.println(part.getpPrice());
                            System.out.println(part.getpDescription());
                            System.out.println(part.getCategory());

                            selectpartlist.add(part);
                        }

                        selectPartRecyclerAdapter = new selectPartRecyclerAdapter(getApplicationContext(),selectpartlist);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
                        recyclerView.setAdapter(selectPartRecyclerAdapter);
                        recyclerView.setHasFixedSize(true);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        clearAll();
        getPartList();
    }

    private void clearAll(){
        if (selectpartlist != null){
            selectpartlist.clear();
        }
        selectpartlist = new ArrayList<>();
    }


}