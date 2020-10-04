package com.example.madproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.classes.CPUBuild;
import com.example.madproject.R;
import com.example.madproject.savedConfigNew;
import com.example.madproject.updateconfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildRecyclerAdapter extends RecyclerView.Adapter<BuildRecyclerAdapter.ViewHolder> {

    private static final String TAG = "Build Recycler Adapter";
    ArrayList<CPUBuild> BuildList;
    Context context;
    ArrayList<String> BuildKeys;
    HashMap<String,CPUBuild> BUILDList;
    DatabaseReference databaseReference;
    com.example.madproject.savedConfigNew savedConfigNew = new savedConfigNew();

    public BuildRecyclerAdapter(Context context, ArrayList<CPUBuild> BuildList,ArrayList<String> BuildKeys, HashMap<String, CPUBuild> BUILDList) {
        this.BuildList = BuildList;
        this.context = context;
        this.BuildKeys = BuildKeys;
        this.BUILDList = BUILDList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateView Called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.build_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBind Called");
        final String BuildKey = BuildKeys.get(position);
        Log.d("Build Keys",BuildKey);
        final int Position = position;
        //final int CPUPrice = CasingList.get(position).getPrice();
        //holder.CPUName.setText(CasingList.get(position).getName());
        holder.BuildDetail.setText("Casing: "+BuildList.get(position).getCasing().getName()+"\nCPU : "+BuildList.get(position).getCpu().getName());
        /*holder.CPUSpec.setText("CPU Cores : "+CasingList.get(position).getCPU_Cores().toString()+"\nThreads : "+CasingList.get(position).getThreads().toString()+"\nBase Clock : "+CasingList.get(position).getBase_Clock().toString()+"GHz\nMax Boost Clock : "+CasingList.get(position).getMax_Boost_Clock().toString()+
                "GHz\nL1 Cache : "+CasingList.get(position).getL1_Cache().toString()+"MB\nL2 Cache : "+CasingList.get(position).getL2_Cache().toString()+"MB\nL3 Cache : "+CasingList.get(position).getL3_Cache().toString()+"MB\nWarranty : "+CasingList.get(position).getWarranty().toString()+"Yrs");
        *//*Glide.with(context)
                .asBitmap()
                .load(imageList.get(position))
                .into(holder.CPUImage);*/
        /*holder.CPUSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConfPC.class);
                //intent.putExtra("CasingName",CPUName);
                //Log.d("Casing Name:",CPUName);
                //intent.putExtra("CasingPrice",CPUPrice);
                Context c = v.getContext();
                ((Activity)c).setResult(Activity.RESULT_OK,intent);
                ((Activity)c).finish();
                //v.getContext().startActivity(intent);
            }
        });*/
        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Build KEY",BuildKey);
                Log.d("POSITION KEY",""+Position);
                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("CPUBuilds").child(BuildKey).removeValue();
                savedConfigNew.RemoveItem(Position);
            }
        });

        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), updateconfig.class);
                intent.putExtra("Build Object",BuildList.get(position));
                intent.putExtra("Build Key",BuildKeys.get(position));
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return BuildList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView BuildName;
        TextView BuildTot;
        TextView BuildDetail;
        RelativeLayout relativeLayout;
        Button DeleteBtn;
        Button UpdateBtn;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            BuildName = itemView.findViewById(R.id.Build_No);
            BuildTot = itemView.findViewById(R.id.Build_Tot);
            BuildDetail = itemView.findViewById(R.id.Build_Detail);
            relativeLayout = itemView.findViewById(R.id.build_layout);
            DeleteBtn = itemView.findViewById(R.id.Delete_Btn);
            UpdateBtn = itemView.findViewById(R.id.Update_Btn);
        }
    }

}
