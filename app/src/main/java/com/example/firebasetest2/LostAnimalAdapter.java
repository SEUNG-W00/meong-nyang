package com.example.firebasetest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LostAnimalAdapter extends RecyclerView.Adapter<LostAnimalAdapter.LostAnimalViewHolder> {

    private ArrayList<LostAnimal> arrayList;
    private Context context;

    public LostAnimalAdapter(ArrayList<LostAnimal> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LostAnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lostanimalitemlist, parent, false);
        LostAnimalViewHolder holder = new LostAnimalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LostAnimalViewHolder holder, int position) {

        Glide.with(holder.image)
                .load(arrayList.get(position).getImage())
                .into(holder.image);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());
        holder.lostlocation.setText(arrayList.get(position).getLostlocation());
        holder.lostdate.setText(arrayList.get(position).getLostdate());
        holder.losttime.setText(arrayList.get(position).getLosttime());
    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class LostAnimalViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView lostlocation;
        TextView lostdate;
        TextView losttime;
        ImageView image;
        public LostAnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.losttitle);
            this.content = itemView.findViewById(R.id.lostcontent);
            this.lostlocation = itemView.findViewById(R.id.lostlocation);
            this.lostdate = itemView.findViewById(R.id.lostdate);
            this.losttime = itemView.findViewById(R.id.losttime);
            this.image = itemView.findViewById(R.id.lostimage);
        }
    }
}
