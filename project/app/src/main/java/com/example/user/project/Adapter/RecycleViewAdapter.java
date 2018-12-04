package com.example.user.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.project.Model.Exercise;
import com.example.user.project.Interface.ItemClickListener;
import com.example.user.project.R;
import com.example.user.project.viewExercise;

import java.util.List;

class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;


    public RecycleViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.ex_img);
        text = (TextView) itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());

    }
}

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private List<Exercise> exerciseList;
    private Context context;


    public RecycleViewAdapter(List<Exercise> exerciseList,Context context){
        this.exerciseList = exerciseList;
        this.context = context;
    }


    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);

        return new RecycleViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {

        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

               Intent intent = new Intent(context,viewExercise.class);
               intent.putExtra("image_id",exerciseList.get(position).getImage_id());
               intent.putExtra("name",exerciseList.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return exerciseList.size();
    }
}
