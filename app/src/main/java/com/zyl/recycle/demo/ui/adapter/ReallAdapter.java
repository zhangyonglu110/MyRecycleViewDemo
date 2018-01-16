package com.zyl.recycle.demo.ui.adapter;

import android.content.Context;
import android.view.View;

import com.zyl.myview.zrecycleview.base.BaseRecycleAdapter;
import com.zyl.myview.zrecycleview.base.BaseViewHolder;
import com.zyl.recycle.demo.model.AgTechnologInfor;
import com.zyl.recycle.demo.ui.adapter.viewholder.RellHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class ReallAdapter extends BaseRecycleAdapter<AgTechnologInfor> {
    public ReallAdapter(Context context, List<AgTechnologInfor> list, int layoutid) {
        super(context, list, layoutid);
    }

    @Override
    public BaseViewHolder<AgTechnologInfor> getViewHolder(View itemview) {
        return new RellHolder(itemview);
    }
}
