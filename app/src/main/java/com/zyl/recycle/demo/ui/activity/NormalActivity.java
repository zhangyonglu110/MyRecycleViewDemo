package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zyl.myview.zrecycleview.util.DensityUtil;
import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.ui.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class NormalActivity extends Activity {
    private RecyclerView recyclerView;
    private List<String> datalist=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycleview);
        initdata();
        recyclerView=findViewById(R.id.recycle_view);

        recyclerView.setLayoutManager(linearLayoutManager=new LinearLayoutManager(this));

        recyclerView.setAdapter(new NormalAdapter(this,datalist));
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                RecyclerView.State state=new RecyclerView.State();

                Log.i("nnn","recycleview height---------------->"+ DensityUtil.px2dip(NormalActivity.this,recyclerView.computeVerticalScrollRange()));
            }
        });



    }

    private void initdata() {
        for(int i=0;i<1;i++){
            datalist.add("item"+i);
        }
    }
}
