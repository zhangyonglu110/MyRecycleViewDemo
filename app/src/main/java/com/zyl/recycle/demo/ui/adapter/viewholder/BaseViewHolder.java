package com.zyl.recycle.demo.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{
    protected View mitenView;
    protected List<T> mdata;
//    public BaseViewHolder(List<T> list,View itemView) {
//        super(itemView);
//        this.mitenView=itemView;
//        this.mdata=list;
//        init();
//        setdata(mdata.get(getPosition()));
//
//    }
    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mitenView=itemView;
        init();


    }


    public    void setdata(T s){

    }
    protected  void init(){};

}
