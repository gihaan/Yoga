package com.example.gihan.yoga.Data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.yoga.R;
import com.example.gihan.yoga.Show;

import java.util.ArrayList;

/**
 * Created by Gihan on 8/23/2017.
 */



public class RecyclewViewAdapter extends RecyclerView.Adapter<RecyclerViewHoder>  {


    private ArrayList<Exercise>mList;
    private Context mContext;

    public RecyclewViewAdapter (ArrayList<Exercise>mList,Context mContext){
        this.mContext=mContext;
        this.mList=mList;

    }

    @Override
    public RecyclerViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item,parent,false);
        return new RecyclerViewHoder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHoder holder, final int position) {

        holder.mImage.setImageResource(mList.get(position).getImage_id());
        holder.mTitle.setText(mList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showExercise=new Intent(v.getContext(), Show.class);
                showExercise.putExtra("image_id",mList.get(position).getImage_id());
                showExercise.putExtra("name",mList.get(position).getName());
                v.getContext().startActivity(showExercise);


            }
        });




    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RecyclerViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{

     TextView mTitle;
    ImageView mImage;

    public RecyclerViewHoder(View itemView) {
        super(itemView);

        mTitle=(TextView)itemView.findViewById(R.id.exe_name);
        mImage=(ImageView)itemView.findViewById(R.id.exe_image);


    }

    @Override
    public void onClick(View v) {



    }
}