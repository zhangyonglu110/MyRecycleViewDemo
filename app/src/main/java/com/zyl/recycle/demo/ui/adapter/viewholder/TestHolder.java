package com.zyl.recycle.demo.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.zyl.myview.zrecycleview.base.BaseViewHolder;
import com.zyl.recycle.demo.R;

/**
 * Created by Administrator on 2018/1/9.
 */

public class TestHolder extends BaseViewHolder<String> {
    private TextView testtv;
    public TestHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void setdata(String s) {
        super.setdata(s);
        testtv.setText(s);

    }

    @Override
    protected void init() {
        testtv=mitenView.findViewById(R.id.tv_item);


    }
}
