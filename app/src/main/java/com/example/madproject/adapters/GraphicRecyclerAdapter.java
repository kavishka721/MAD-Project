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
import com.example.madproject.classes.GraphicCard;
import com.example.madproject.R;

import java.util.ArrayList;

public class GraphicRecyclerAdapter extends RecyclerView.Adapter<GraphicRecyclerAdapter.ViewHolder> {

    private static final String TAG = "CPU Recycler Adapter";
    ArrayList<GraphicCard> GraphicList;
    ArrayList<Uri> imageList;
    Context context;

    public GraphicRecyclerAdapter(Context context, ArrayList<GraphicCard> GraphicList, ArrayList<Uri> imageList) {
        this.GraphicList = GraphicList;
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
        final String GraphicName = GraphicList.get(position).getName();
        final int GraphicPrice = GraphicList.get(position).getPrice();
        holder.CPUName.setText(GraphicList.get(position).getName());
        holder.CPUPrice.setText("Rs "+GraphicList.get(position).getPrice().toString());
        holder.CPUSpec.setText("GPU Size : "+GraphicList.get(position).getSize().toString()+"GB\nWarranty : "+GraphicList.get(position).getWarranty().toString()+"Yrs");
        if(imageList.size() != 0) {
            holder.Progress.setVisibility(View.INVISIBLE);
            Log.d("Image LIST SIZE", ""+imageList.size());
            Glide.with(context)
                    .asBitmap()
                    .load(imageList.get(position))
                    .into(holder.CPUImage);
        }
        holder.CPUSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConfPC.class);
                intent.putExtra("GPUName",GraphicName);
                intent.putExtra("GPUPrice",GraphicPrice);
                intent.putExtra("GPU Object",GraphicList.get(position));
                Context c = v.getContext();
                ((Activity)c).setResult(Activity.RESULT_OK,intent);
                ((Activity)c).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return GraphicList.size();
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
