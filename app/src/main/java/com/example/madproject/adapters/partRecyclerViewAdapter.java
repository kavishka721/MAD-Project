package com.example.madproject.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madproject.R;
import com.example.madproject.classes.Parts;

import java.util.ArrayList;

public class partRecyclerViewAdapter extends RecyclerView.Adapter<partRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "partRecyclerViewAdapter";

    private ArrayList<Parts> plist ;
    private Context myContext;

    public partRecyclerViewAdapter( Context myContext,ArrayList<Parts> plist) {

        this.myContext = myContext;
        this.plist = plist;
    }

    @NonNull
    @Override
    public partRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_singlerowpart,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");

       // final Parts parts = plist.get(position);
        //set the name of image
        holder.partimgName.setText(plist.get(position).getpName());//get part name of myImages array
        holder.partPrice.setText(plist.get(position).getpPrice());//egt part price

        Glide.with(myContext)
                .asBitmap()
                .load(plist.get(position).getImageUri())
                .into(holder.img);

//               // Intent intent = new Intent(myContext,listpart)

    }

    @Override
    public int getItemCount() {
        return plist.size();   //get the size of myImages array list
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView partimgName,partPrice;
        Button imgselectbtn;
        RelativeLayout parentRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.recycleImg);
            partimgName = itemView.findViewById(R.id.imgPartName);
            partPrice = itemView.findViewById(R.id.imgpartPrice);
            imgselectbtn = itemView.findViewById(R.id.imgselectbtn);
            parentRelativeLayout = itemView.findViewById(R.id.parentRelativeLayout);

        }
    }
}
