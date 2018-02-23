package com.zyl.recycle.demo.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/22.
 */

public class NormalHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    public NormalHolder(View itemView) {
        super(itemView);
       // textView= (TextView) itemView;
    }

    public void setdata(String s){
       // textView.setText(s);

    }

}
