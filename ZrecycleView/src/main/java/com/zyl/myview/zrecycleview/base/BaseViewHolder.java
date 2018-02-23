package com.zyl.myview.zrecycleview.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
        Log.i("sss",mitenView.getMeasuredHeight()+"--------------------");

        init();


    }


    public    void setdata(T s){

    }
//    public void OnZItemClick(int position){
//
//    }
    protected  void init(){}

}
