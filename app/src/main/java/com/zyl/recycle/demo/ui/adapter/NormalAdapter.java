package com.zyl.recycle.demo.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.recycle.demo.ui.adapter.viewholder.NormalHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalHolder>{
    private List<String> dataList;
    private Context mcontext;
    public NormalAdapter(Context context){
        this.mcontext=context;

    }
    public NormalAdapter(Context context,List<String> list){
        this.mcontext=context;
        this.dataList=list;

    }
    @Override
    public NormalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView=new TextView(mcontext);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15);

        return new NormalHolder(textView);
    }



    @Override
    public void onBindViewHolder(NormalHolder holder, final int position) {
        holder.setdata(dataList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,position+"",Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
