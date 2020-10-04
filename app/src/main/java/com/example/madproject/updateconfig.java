package com.example.madproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madproject.classes.CPUBuild;
import com.example.madproject.classes.CPUs;
import com.example.madproject.classes.Casing;
import com.example.madproject.selections.casingselection;
import com.example.madproject.selections.cpuselectionNew;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateconfig extends AppCompatActivity {

    EditText CasingText;
    EditText CPUText;
    TextView TotalBuild;
    Button CasingSelect;
    Button CPUSelect;
    Button SaveBtn;
    Integer Total = 0;
    Integer LAUNCH_SECOND_ACTIVITY = 1;
    Integer LAUNCH_THIRD_ACTIVITY = 1;
    Casing casing;
    CPUBuild cpuBuild;
    CPUs cpUs;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateconfig);

        CasingText = findViewById(R.id.CasingText);
        CPUText = findViewById(R.id.CPUText);
        TotalBuild = findViewById(R.id.TotalBuild);
        CPUSelect = findViewById(R.id.CPUSelect);
        CasingSelect = findViewById(R.id.CasingSelect);
        SaveBtn = findViewById(R.id.SaveBtn);

        Intent intent = getIntent();
        cpuBuild = (CPUBuild) intent.getSerializableExtra("Build Object");
        final String BuildKey = intent.getStringExtra("Build Key");

        CasingText.setText(cpuBuild.getCasing().getName());
        CPUText.setText(cpuBuild.getCpu().getName());
        Total = cpuBuild.getCpu().getPrice()+cpuBuild.getCasing().getPrice();
        //TotalBuild.setText(Total);

        CPUSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, cpuselectionNew.class);
                startActivityForResult(intent,LAUNCH_THIRD_ACTIVITY);
            }
        });

        CasingSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, casingselection.class);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBuild(casing,cpUs,BuildKey);
                Intent intent = new Intent(v.getContext(),savedConfigNew.class);
                startActivity(intent);
            }
        });
    }

    private void updateBuild(Casing casing, CPUs cpu, String BuildKey) {
        if (casing != null)
            cpuBuild.setCasing(casing);
        if (cpu != null)
            cpuBuild.setCpu(cpu);

        databaseReference.child("CPUBuilds").child(BuildKey).setValue(cpuBuild);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAGS","onActivity Called1");
        if (requestCode == LAUNCH_SECOND_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CasingPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CasingPrice",0);
                    Log.d("Casing Price",""+data.getIntExtra("CasingPrice",0));
                    casing = (Casing) data.getSerializableExtra("Casing Object");
                    Log.d("Casing Object",casing.getName());

                }
                if (data.getStringExtra("CasingName") != null){
                    Log.d("Casing name 2:",data.getStringExtra("CasingName"));
                    CasingText.setText(data.getStringExtra("CasingName"));
                }
            }
        }
        if (requestCode == LAUNCH_THIRD_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CPUPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CPUPrice",0);
                    cpUs = (CPUs)data.getSerializableExtra("CPU Object");
                    Log.d("CPU Object",cpUs.getName());
                }
                if (data.getStringExtra("CPUName") != null){
                    CPUText.setText(data.getStringExtra("CPUName"));
                }
            }
        }
    }
}