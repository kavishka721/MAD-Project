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
import com.example.madproject.classes.CPU;
import com.example.madproject.classes.Casing;
import com.example.madproject.classes.Cooling;
import com.example.madproject.classes.GraphicCard;
import com.example.madproject.classes.HDD;
import com.example.madproject.classes.MotherBoard;
import com.example.madproject.classes.PowerSupply;
import com.example.madproject.classes.RAM;
import com.example.madproject.classes.SSD;
import com.example.madproject.selections.casingselection;
import com.example.madproject.selections.coolselection;
import com.example.madproject.selections.cpuselectionNew;
import com.example.madproject.selections.gpuselection;
import com.example.madproject.selections.hddselection;
import com.example.madproject.selections.mbselection;
import com.example.madproject.selections.psselection;
import com.example.madproject.selections.ramselection;
import com.example.madproject.selections.ssdselection;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfPC extends AppCompatActivity {

    EditText CPUSelect;
    EditText CasingSelect;
    EditText RAMSelect;
    EditText HDDSelect;
    EditText SSDSelect;
    EditText COOLSelect;
    EditText PSSelect;
    EditText MBSelect;
    EditText GPUSelect;
    Button cpubtn;
    Button casingbtn;
    Button rambtn;
    Button motherboard;
    Button gpubtn;
    Button hddbtn;
    Button ssdbtn;
    Button psbtn;
    Button coolbtn;
    Button savebtn;
    Integer Total=0;
    TextView BuildTotal;
    int LAUNCH_SECOND_ACTIVITY = 1;
    int LAUNCH_THIRD_ACTIVITY = 1;
    int LAUNCH_FOURTH_ACTIVITY = 1;
    int LAUNCH_FIFTH_ACTIVITY = 1;
    int LAUNCH_SIXTH_ACTIVITY = 1;
    int LAUNCH_SEVENTH_ACTIVITY = 1;
    int LAUNCH_EIGHTH_ACTIVITY = 1;
    int LAUNCH_NINTH_ACTIVITY = 1;
    int LAUNCH_TENTH_ACTIVITY = 1;
    CPU CPU;
    Casing casing;
    RAM ram;
    MotherBoard motherBoard;
    SSD ssd;
    GraphicCard graphicCard;
    HDD hdd;
    Cooling cooling;
    PowerSupply powerSupply;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_p_c);
        cpubtn = findViewById(R.id.CPUSelect);
        rambtn = findViewById(R.id.RAMSelect);
        motherboard = findViewById(R.id.MBSelect);
        gpubtn = findViewById(R.id.GPUSelect);
        hddbtn = findViewById(R.id.HDDSelect);
        ssdbtn = findViewById(R.id.SSDSelect);
        psbtn = findViewById(R.id.PSSelect);
        coolbtn = findViewById(R.id.COOLSelect);
        casingbtn = findViewById(R.id.CasingSelect);
        savebtn = findViewById(R.id.SaveBtn);
        CPUSelect = findViewById(R.id.CPUText);
        CasingSelect = findViewById(R.id.CasingText);
        RAMSelect = findViewById(R.id.RAMText);
        MBSelect = findViewById(R.id.MBText);
        GPUSelect = findViewById(R.id.GPUText);
        HDDSelect = findViewById(R.id.HDDText);
        SSDSelect = findViewById(R.id.SSDText);
        PSSelect = findViewById(R.id.PSText);
        COOLSelect = findViewById(R.id.CoolingText);
        BuildTotal = findViewById(R.id.TotalBuild);

        databaseReference = FirebaseDatabase.getInstance().getReference();


    }

    private void writeNewBuild(Casing casing, CPU cpu, GraphicCard graphicCard, MotherBoard motherBoard, HDD hdd, SSD ssd, PowerSupply powerSupply, Cooling cooling, RAM ram, Integer BuildTotal) {
        CPUBuild cpuBuild = new CPUBuild(casing, cpu, ram, graphicCard, powerSupply, hdd, ssd, cooling, motherBoard, BuildTotal);

       databaseReference.child("CPUBuilds").push().setValue(cpuBuild);

    }


    @Override
    protected void onResume() {
        super.onResume();

        cpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, cpuselectionNew.class);
                intent.putExtra("Type","Processors");
                startActivityForResult(intent,LAUNCH_THIRD_ACTIVITY);
            }
        });

        casingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, casingselection.class);
                intent.putExtra("Type","Casings");
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });


        rambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, ramselection.class);
                intent.putExtra("Type","RAM Modules");
                startActivityForResult(intent, LAUNCH_FOURTH_ACTIVITY);
            }
        });

        motherboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, mbselection.class);
                intent.putExtra("Type","Motherboards");
                startActivityForResult(intent, LAUNCH_FIFTH_ACTIVITY);
            }
        });

        gpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, gpuselection.class);
                intent.putExtra("Type","GPUs");
                startActivityForResult(intent, LAUNCH_SEVENTH_ACTIVITY);
            }
        });

        hddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, hddselection.class);
                intent.putExtra("Type","HDDs");
                startActivityForResult(intent, LAUNCH_EIGHTH_ACTIVITY);
            }
        });

        ssdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, ssdselection.class);
                intent.putExtra("Type","SSDs");
                startActivityForResult(intent, LAUNCH_SIXTH_ACTIVITY);
            }
        });

        psbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, psselection.class);
                intent.putExtra("Type","Power Supply");
                startActivityForResult(intent, LAUNCH_TENTH_ACTIVITY);
            }
        });

        coolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, coolselection.class);
                intent.putExtra("Type","Cooling Systems");
                startActivityForResult(intent, LAUNCH_NINTH_ACTIVITY);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewBuild(casing, CPU, graphicCard, motherBoard, hdd, ssd, powerSupply, cooling, ram, Total);
                Intent intent = new Intent(v.getContext(),savedConfigNew.class);
                intent.putExtra("New Build",1);
                startActivity(intent);
            }
        });


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
                    CasingSelect.setText(data.getStringExtra("CasingName"));
                }
            }
        }
        if (requestCode == LAUNCH_THIRD_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CPUPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CPUPrice",0);
                    CPU = (CPU)data.getSerializableExtra("CPU Object");
                    Log.d("CPU Object", CPU.getName());
                }
                if (data.getStringExtra("CPUName") != null){
                    CPUSelect.setText(data.getStringExtra("CPUName"));
                }
            }
        }
        if (requestCode == LAUNCH_FOURTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("RAMPrice",0) !=  0){
                    Total = Total + data.getIntExtra("RAMPrice",0);
                    ram = (RAM)data.getSerializableExtra("RAM Object");
                    Log.d("RAM Object",ram.getName());
                }
                if (data.getStringExtra("RAMName") != null){
                    RAMSelect.setText(data.getStringExtra("RAMName"));
                }
            }
        }
        if (requestCode == LAUNCH_FIFTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("MBPrice",0) !=  0){
                    Total = Total + data.getIntExtra("MBPrice",0);
                    motherBoard = (MotherBoard)data.getSerializableExtra("MB Object");
                    Log.d("MB Object",motherBoard.getName());
                }
                if (data.getStringExtra("MBName") != null){
                    MBSelect.setText(data.getStringExtra("MBName"));
                }
            }
        }
        if (requestCode == LAUNCH_SIXTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("SSDPrice",0) !=  0){
                    Total = Total + data.getIntExtra("SSDPrice",0);
                    ssd = (SSD)data.getSerializableExtra("SSD Object");
                    Log.d("SSD Object",ssd.getName());
                }
                if (data.getStringExtra("SSDName") != null){
                    SSDSelect.setText(data.getStringExtra("SSDName"));
                }
            }
        }
        if (requestCode == LAUNCH_SEVENTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("GPUPrice",0) !=  0){
                    Total = Total + data.getIntExtra("GPUPrice",0);
                    graphicCard = (GraphicCard)data.getSerializableExtra("GPU Object");
                    Log.d("GPU Object",graphicCard.getName());
                }
                if (data.getStringExtra("GPUName") != null){
                    GPUSelect.setText(data.getStringExtra("GPUName"));
                }
            }
        }
        if (requestCode == LAUNCH_EIGHTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("HDDPrice",0) !=  0){
                    Total = Total + data.getIntExtra("HDDPrice",0);
                    hdd = (HDD)data.getSerializableExtra("HDD Object");
                    Log.d("HDD Object",hdd.getName());
                }
                if (data.getStringExtra("HDDName") != null){
                    HDDSelect.setText(data.getStringExtra("HDDName"));
                }
            }
        }
        if (requestCode == LAUNCH_NINTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CoolPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CoolPrice",0);
                    cooling = (Cooling)data.getSerializableExtra("Cool Object");
                    Log.d("Cool Object",cooling.getName());
                }
                if (data.getStringExtra("CoolName") != null){
                    COOLSelect.setText(data.getStringExtra("CoolName"));
                }
            }
        }
        if (requestCode == LAUNCH_TENTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("PSPrice",0) !=  0){
                    Total = Total + data.getIntExtra("PSPrice",0);
                    powerSupply = (PowerSupply)data.getSerializableExtra("PS Object");
                    Log.d("PS Object",powerSupply.getName());
                }
                if (data.getStringExtra("PSName") != null){
                    PSSelect.setText(data.getStringExtra("PSName"));
                }
            }
        }

        BuildTotal.setText(Total.toString());
    }
}