package com.example.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

/**
 * Created by crxc on 2016/11/27.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private String[] data;
    private LayoutInflater inflater;
    public MyRecyclerAdapter(Context context,String[] data) {
        this.data=data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView view = (TextView) holder.getViewByID(R.id.text);
        view.setText(data[position]);
    }


}
