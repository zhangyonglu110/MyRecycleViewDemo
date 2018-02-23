package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zyl.myview.zrecycleview.base.BaseRecycleAdapter;
import com.zyl.myview.zrecycleview.base.BaseRecycleAdapter2;
import com.zyl.myview.zrecycleview.base.BaseViewHolder;
import com.zyl.myview.zrecycleview.widget.ZRecycleView;
import com.zyl.recycle.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class ComplexLayoutActivity extends Activity {
   // private ZRecycleView zRecycleView;
    private ZRecycleView zRecycleView;
    private BaseRecycleAdapter<String> adapter;
    private List<String> dataList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_layout);
        for(int i=0;i<5;i++){
            dataList.add(i+"");
        }
        zRecycleView=findViewById(R.id.zrecycleview);
        zRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new BaseRecycleAdapter<String>(this,dataList,R.layout.layout_complex_list) {
            @Override
            public BaseViewHolder<String> getViewHolder(View itemview) {
                return new BaseViewHolder(itemview){
                    @Override
                    public void setdata(Object s) {
                        super.setdata(s);
                    }
                };
            }
        };

        adapter.setOnItemClickListener(new BaseRecycleAdapter.OnZRecycleViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ComplexLayoutActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        zRecycleView.setAdapter(adapter);

    }
}
