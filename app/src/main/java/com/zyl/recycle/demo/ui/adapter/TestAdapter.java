package com.zyl.recycle.demo.ui.adapter;

import android.content.Context;
import android.view.View;

import com.zyl.recycle.demo.ui.adapter.viewholder.BaseViewHolder;
import com.zyl.recycle.demo.ui.adapter.viewholder.TestHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class TestAdapter extends BaseRecycleAdapter<String> {
    public TestAdapter(Context context, List<String> list, View itemview) {
        super(context, list, itemview);
    }
    public TestAdapter(Context context, List<String> list, int layoutid) {
        super(context, list, layoutid);
    }
    @Override
    public BaseViewHolder<String> getViewHolder(View itemview) {
        return new TestHolder(itemview);
    }
}
