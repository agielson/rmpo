package com.example.lab4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyView> {

    private Context context;
    private ArrayList id, name_of_cpu, laptop_diagonal, availability_of_video_card, volume_hd,operating_system, price;
    CustomAdapter(Context context, ArrayList id,ArrayList name_of_cpu,ArrayList laptop_diagonal,ArrayList availability_of_video_card,ArrayList volume_hd,ArrayList operating_system,ArrayList price){
        this.context = context;
        this.id = id;
        this.name_of_cpu = name_of_cpu;
        this.laptop_diagonal = laptop_diagonal;
        this.availability_of_video_card = availability_of_video_card;
        this.volume_hd = volume_hd;
        this.operating_system = operating_system;
        this.price = price;

    }
    @NonNull
    @Override
    public CustomAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.all_row,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyView holder, int position) {
        Log.d("onbindviewholder", String.valueOf(id.size() ));
        Log.d("onbindviewholder", String.valueOf(position ));
        holder.id_t.setText(String.valueOf(this.id.get(position)));
        holder.CPU.setText(String.valueOf(this.name_of_cpu.get(position)));
        holder.Diagonal.setText(String.valueOf(this.laptop_diagonal.get(position)));
        holder.Card.setText(String.valueOf(this.availability_of_video_card.get(position)));
        holder.Volume.setText(String.valueOf(this.volume_hd.get(position)));
        holder.OS.setText(String.valueOf(this.operating_system.get(position)));
        holder.Price.setText(String.valueOf(this.price.get(position)));


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        TextView id_t, CPU, Diagonal, Card, Volume,OS, Price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            id_t = itemView.findViewById(R.id.id);
            CPU = itemView.findViewById(R.id.CPU);
            Diagonal = itemView.findViewById(R.id.Diagonal);
            Card = itemView.findViewById(R.id.Card);
            Volume = itemView.findViewById(R.id.Volume);
            OS = itemView.findViewById(R.id.OS);
            Price = itemView.findViewById(R.id.Price);


        }
    }

}
