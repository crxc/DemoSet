package com.example.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;

/**
 * Created by crxc on 2016/11/27.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private View v;
    private SparseArray<View> views;
    public MyViewHolder(View itemView) {
        super(itemView);
        v=itemView;
        views=new SparseArray<>();
    }

    public View getViewByID(int id) {
        View view1 = views.get(id);
        if(view1==null){
            View view = v.findViewById(id);
            views.put(id,view);
            return view;
        }
        return view1;
    }
}
