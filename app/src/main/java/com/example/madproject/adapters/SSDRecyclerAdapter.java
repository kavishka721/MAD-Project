package com.example.madproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madproject.ConfPC;
import com.example.madproject.R;
import com.example.madproject.classes.SSD;

import java.util.ArrayList;

public class SSDRecyclerAdapter extends RecyclerView.Adapter<SSDRecyclerAdapter.ViewHolder> {

    private static final String TAG = "CPU Recycler Adapter";
    ArrayList<SSD> SSDList;
    ArrayList<Uri> imageList;
    Context context;

    public SSDRecyclerAdapter(Context context, ArrayList<SSD> SSDList, ArrayList<Uri> imageList) {
        this.SSDList = SSDList;
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateView Called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cpu_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBind Called");
        final String SSDName = SSDList.get(position).getName();
        final int SSDPrice = SSDList.get(position).getPrice();
        holder.CPUName.setText(SSDList.get(position).getName());
        holder.CPUPrice.setText("Rs "+SSDList.get(position).getPrice().toString());
        holder.CPUSpec.setText("SSD Size : "+SSDList.get(position).getSize()+"\nMSR : "+SSDList.get(position).getMsr()+"\nMSW : "
                +SSDList.get(position).getMsw()+"\nCache : "+SSDList.get(position).getCache()+ "MB\nWarranty : "+SSDList.get(position).getWarranty().toString()+"Yrs");
        if(imageList.size() != 0) {
            Log.d("Image LIST SIZE", ""+imageList.size());
            holder.Progress.setVisibility(View.INVISIBLE);
            Glide.with(context)
                    .asBitmap()
                    .load(imageList.get(position))
                    .into(holder.CPUImage);
        }
        holder.CPUSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConfPC.class);
                intent.putExtra("SSDName",SSDName);
                intent.putExtra("SSDPrice",SSDPrice);
                intent.putExtra("SSD Object",SSDList.get(position));
                Context c = v.getContext();
                ((Activity)c).setResult(Activity.RESULT_OK,intent);
                ((Activity)c).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return SSDList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView CPUName;
        TextView CPUSpec;
        TextView CPUPrice;
        RelativeLayout relativeLayout;
        ImageView CPUImage;
        Button CPUSelect;
        ProgressBar Progress;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            CPUName = itemView.findViewById(R.id.CPU_Name);
            CPUSpec = itemView.findViewById(R.id.CPU_Detail);
            CPUPrice = itemView.findViewById(R.id.CPU_Price);
            CPUImage = itemView.findViewById(R.id.CPU_Image);
            CPUSelect = itemView.findViewById(R.id.CPUSelect);
            relativeLayout = itemView.findViewById(R.id.cpu_layout);
            Progress = itemView.findViewById(R.id.spin_kit);

        }
    }

}
