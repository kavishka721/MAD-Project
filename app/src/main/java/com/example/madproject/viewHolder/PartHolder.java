package com.example.madproject.viewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.R;
import com.example.madproject.interfaces.ItemClickListner;

public class PartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pname;
    public ImageView img;

    private ItemClickListner itemClickListner;

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public PartHolder(@NonNull View itemView) {
        super(itemView);

        pname = (TextView) itemView.findViewById(R.id.imgPartName);
        img = (ImageView) itemView.findViewById(R.id.recycleImg);

    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
