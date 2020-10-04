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

public class updateconfig extends AppCompatActivity {

    EditText CasingText;
    EditText CPUText;
    EditText RAMSelect;
    EditText MBSelect;
    EditText SSDSelect;
    EditText GPUSelect;
    EditText HDDSelect;
    EditText COOLSelect;
    EditText PSSelect;
    TextView TotalBuild;
    Button CasingSelect;
    Button CPUSelect;
    Button SaveBtn;
    Button rambtn;
    Button motherboard;
    Button gpubtn;
    Button hddbtn;
    Button ssdbtn;
    Button psbtn;
    Button coolbtn;
    Integer Total = 0;
    Integer LAUNCH_SECOND_ACTIVITY = 1;
    Integer LAUNCH_THIRD_ACTIVITY = 1;
    Integer LAUNCH_FOURTH_ACTIVITY = 1;
    Integer LAUNCH_FIFTH_ACTIVITY = 1;
    Integer LAUNCH_SIXTH_ACTIVITY = 1;
    Integer LAUNCH_SEVENTH_ACTIVITY = 1;
    Integer LAUNCH_EIGHTH_ACTIVITY = 1;
    Integer LAUNCH_NINTH_ACTIVITY = 1;
    Integer LAUNCH_TENTH_ACTIVITY = 1;
    CPUBuild cpuBuild;
    CPU CPU;
    Casing casing;
    RAM ram;
    MotherBoard motherBoard;
    SSD ssd;
    GraphicCard graphicCard;
    HDD hdd;
    Cooling cooling;
    PowerSupply powerSupply;
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
        RAMSelect = findViewById(R.id.RAMText);
        GPUSelect = findViewById(R.id.GPUText);
        MBSelect = findViewById(R.id.MBText);
        HDDSelect = findViewById(R.id.HDDText);
        SSDSelect = findViewById(R.id.SSDText);
        PSSelect = findViewById(R.id.PSText);
        COOLSelect = findViewById(R.id.CoolingText);
        rambtn = findViewById(R.id.RAMSelect);
        motherboard = findViewById(R.id.MBSelect);
        gpubtn = findViewById(R.id.GPUSelect);
        hddbtn = findViewById(R.id.HDDSelect);
        ssdbtn = findViewById(R.id.SSDSelect);
        psbtn = findViewById(R.id.PSSelect);
        coolbtn = findViewById(R.id.COOLSelect);


        Intent intent = getIntent();
        cpuBuild = (CPUBuild) intent.getSerializableExtra("Build Object");
        final String BuildKey = intent.getStringExtra("Build Key");
        TotalBuild.setText("Rs "+cpuBuild.getTotal());
        Total = cpuBuild.getTotal();

        if (cpuBuild.getCasing() != null)
            CasingText.setText(cpuBuild.getCasing().getName());;
        if (cpuBuild.getCpu() != null)
            CPUText.setText(cpuBuild.getCpu().getName());;
        if (cpuBuild.getGraphicCard() != null)
            GPUSelect.setText(cpuBuild.getGraphicCard().getName());;
        if (cpuBuild.getMotherBoard() != null)
            MBSelect.setText(cpuBuild.getMotherBoard().getName());;
        if (cpuBuild.getHdd() != null)
            HDDSelect.setText(cpuBuild.getHdd().getName());;
        if (cpuBuild.getSsd() != null)
            SSDSelect.setText(cpuBuild.getSsd().getName());;
        if (cpuBuild.getPowerSupply() != null)
            PSSelect.setText(cpuBuild.getPowerSupply().getName());;
        if (cpuBuild.getCooling() != null)
            COOLSelect.setText(cpuBuild.getCooling().getName());;
        if (cpuBuild.getRam() != null)
            RAMSelect.setText(cpuBuild.getRam().getName());;


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

        rambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, ramselection.class);
                startActivityForResult(intent, LAUNCH_FOURTH_ACTIVITY);
            }
        });

        motherboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, mbselection.class);
                startActivityForResult(intent, LAUNCH_FIFTH_ACTIVITY);
            }
        });

        gpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, gpuselection.class);
                startActivityForResult(intent, LAUNCH_SEVENTH_ACTIVITY);
            }
        });

        hddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, hddselection.class);
                startActivityForResult(intent, LAUNCH_EIGHTH_ACTIVITY);
            }
        });

        ssdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, ssdselection.class);
                startActivityForResult(intent, LAUNCH_SIXTH_ACTIVITY);
            }
        });

        psbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, psselection.class);
                startActivityForResult(intent, LAUNCH_TENTH_ACTIVITY);
            }
        });

        coolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateconfig.this, coolselection.class);
                startActivityForResult(intent, LAUNCH_NINTH_ACTIVITY);
            }
        });

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBuild(casing, CPU, graphicCard, motherBoard, hdd, ssd, powerSupply, cooling, ram, BuildKey);
                Intent intent = new Intent(v.getContext(),savedConfigNew.class);
                intent.putExtra("New Build",2);
                startActivity(intent);
            }
        });

    }

    private void updateBuild(Casing casing, CPU cpu, GraphicCard graphicCard, MotherBoard motherBoard, HDD hdd, SSD ssd, PowerSupply powerSupply, Cooling cooling, RAM ram,String BuildKey) {
        if (casing != null)
            cpuBuild.setCasing(casing);
        if (cpu != null)
            cpuBuild.setCpu(cpu);
        if (graphicCard != null)
            cpuBuild.setGraphicCard(graphicCard);
        if (motherBoard != null)
            cpuBuild.setMotherBoard(motherBoard);
        if (hdd != null)
            cpuBuild.setHdd(hdd);
        if (ssd != null)
            cpuBuild.setSsd(ssd);
        if (powerSupply != null)
            cpuBuild.setPowerSupply(powerSupply);
        if (cooling != null)
            cpuBuild.setCooling(cooling);
        if (ram != null)
            cpuBuild.setRam(ram);
        if (Total != null)
            cpuBuild.setTotal(Total);

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
                    //Total = Total + data.getIntExtra("CasingPrice",0);
                    Log.d("Casing Price",""+data.getIntExtra("CasingPrice",0));
                    casing = (Casing) data.getSerializableExtra("Casing Object");
                    if (cpuBuild.getCasing() != null && casing != cpuBuild.getCasing())
                        Total = Total - cpuBuild.getCasing().getPrice() + casing.getPrice();
                    else
                        Total = Total + data.getIntExtra("CasingPrice",0);
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
                    //Total = Total + data.getIntExtra("CPUPrice",0);
                    CPU = (CPU)data.getSerializableExtra("CPU Object");
                    if (cpuBuild.getCpu() != null && CPU != cpuBuild.getCpu())
                        Total = Total - cpuBuild.getCpu().getPrice() + CPU.getPrice();
                    else
                        Total = Total + data.getIntExtra("CPUPrice",0);
                    Log.d("CPU Object", CPU.getName());
                }
                if (data.getStringExtra("CPUName") != null){
                    CPUText.setText(data.getStringExtra("CPUName"));
                }
            }
        }
        if (requestCode == LAUNCH_FOURTH_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("RAMPrice",0) !=  0){
                    //Total = Total + data.getIntExtra("RAMPrice",0);
                    if (ram == null)
                        ram = (RAM)data.getSerializableExtra("RAM Object");

                    if (cpuBuild.getRam() != null && ram != cpuBuild.getRam()){
                        Total = Total - cpuBuild.getRam().getPrice() + ram.getPrice();
                        ram = (RAM)data.getSerializableExtra("RAM Object");
                    }
                    else if (cpuBuild.getRam() != null && ram != (RAM)data.getSerializableExtra("RAM Object")){
                        Total = Total - cpuBuild.getRam().getPrice() + ram.getPrice();
                        ram = (RAM)data.getSerializableExtra("RAM Object");
                    }
                    else if (cpuBuild.getRam() == null && ram != (RAM)data.getSerializableExtra("RAM Object")){
                        Total = Total + data.getIntExtra("RAMPrice",0);
                        ram = (RAM)data.getSerializableExtra("RAM Object");
                    }
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
                   //Total = Total + data.getIntExtra("MBPrice",0);
                    motherBoard = (MotherBoard)data.getSerializableExtra("MB Object");
                    if (cpuBuild.getMotherBoard() != null || motherBoard != cpuBuild.getMotherBoard())
                        Total = Total - cpuBuild.getMotherBoard().getPrice() + motherBoard.getPrice();
                    else
                        Total = Total + data.getIntExtra("MBPrice",0);
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
                   // Total = Total + data.getIntExtra("SSDPrice",0);
                    ssd = (SSD)data.getSerializableExtra("SSD Object");
                    if (cpuBuild.getSsd() != null || ssd != cpuBuild.getSsd())
                        Total = Total - cpuBuild.getSsd().getPrice() + ssd.getPrice();
                    else
                        Total = Total + data.getIntExtra("SSDPrice",0);
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
                    //Total = Total + data.getIntExtra("GPUPrice",0);
                    graphicCard = (GraphicCard)data.getSerializableExtra("GPU Object");
                    if (cpuBuild.getGraphicCard() != null || graphicCard != cpuBuild.getGraphicCard())
                        Total = Total - cpuBuild.getGraphicCard().getPrice() + graphicCard.getPrice();
                    else
                        Total = Total + data.getIntExtra("GPUPrice",0);
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
                    //Total = Total + data.getIntExtra("HDDPrice",0);
                    hdd = (HDD)data.getSerializableExtra("HDD Object");
                    if (cpuBuild.getHdd() != null || hdd != cpuBuild.getHdd())
                        Total = Total - cpuBuild.getHdd().getPrice() + hdd.getPrice();
                    else
                        Total = Total + data.getIntExtra("HDDPrice",0);
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
                    //Total = Total + data.getIntExtra("CoolPrice",0);
                    cooling = (Cooling)data.getSerializableExtra("Cool Object");
                    if (cpuBuild.getCooling() != null || cooling != cpuBuild.getCooling())
                        Total = Total - cpuBuild.getCooling().getPrice() + cooling.getPrice();
                    else
                        Total = Total + data.getIntExtra("CoolPrice",0);
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
                    //Total = Total + data.getIntExtra("PSPrice",0);
                    powerSupply = (PowerSupply)data.getSerializableExtra("PS Object");
                    if (cpuBuild.getPowerSupply() != null || powerSupply != cpuBuild.getPowerSupply())
                        Total = Total - cpuBuild.getPowerSupply().getPrice() + powerSupply.getPrice();
                    else
                        Total = Total + data.getIntExtra("PSPrice",0);
                    Log.d("PS Object",powerSupply.getName());
                }
                if (data.getStringExtra("PSName") != null){
                    PSSelect.setText(data.getStringExtra("PSName"));
                }
            }
        }
        TotalBuild.setText(Total.toString());
    }
}