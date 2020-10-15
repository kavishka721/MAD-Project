package com.example.madproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.R;
import com.example.madproject.classes.Parts;
import com.example.madproject.updatedelete_part;


import java.util.ArrayList;

public class selectPartRecyclerAdapter extends RecyclerView.Adapter<selectPartRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Parts> partlist;

    public selectPartRecyclerAdapter(Context context,ArrayList<Parts> partlist) {
        this.context = context;
        this.partlist = partlist;

    }


    @NonNull
    @Override
    public selectPartRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectpart_layout,parent,false);
       // ViewHolder viewHolder = new ViewHolder();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull selectPartRecyclerAdapter.ViewHolder holder, int position) {


        final String pname = partlist.get(position).getpName();
        final String pcategory = partlist.get(position).getCategory();
        final String pprice = partlist.get(position).getpPrice();
        final String pdescription = partlist.get(position).getpDescription();
        final String imgurl = partlist.get(position).getImageUri();
        final String key = partlist.get(position).getKey();

        System.out.println(partlist.size());

        System.out.println("name = "+pname);
        System.out.println("des = "+pdescription);
        System.out.println("price = "+pprice);
        System.out.println("url = "+imgurl);
        System.out.println("key = "+key);

        System.out.println("select part adapter============== category====="+pcategory);

        holder.selectpartName.setText(partlist.get(position).getpName());

        //pass part data to update/delete activity
        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), updatedelete_part.class);

                intent.putExtra("pcategory",pcategory);
                intent.putExtra("pname",pname);
                intent.putExtra("pprice",pprice);
                intent.putExtra("pdescription",pdescription);
                intent.putExtra("imgurl",imgurl);
                intent.putExtra("key",key);

                Context c = v.getContext();
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return partlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView selectpartName;
        Button selectBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            selectpartName = itemView.findViewById(R.id.selectedPartnametxt);
            selectBtn = itemView.findViewById(R.id.selecttoupdatedeleteBtn);

        }
    }
}
