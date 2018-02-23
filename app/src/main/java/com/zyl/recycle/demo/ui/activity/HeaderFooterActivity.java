package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.ui.adapter.HeaderFooterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class HeaderFooterActivity extends Activity {
    private RecyclerView recyclerView;
    private List<String> datalist=new ArrayList<>();
    private HeaderFooterAdapter headerFooterAdapter;
    private View headerview,footerview;
    private LinearLayoutManager linearLayoutManager;
    private int totalItemCount,lastVisibleItem,visibleThreshold;
    private boolean isloading=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycleview);
        initdata();
        headerview= LayoutInflater.from(this).inflate(R.layout.layout_recycle_header,recyclerView);
        footerview= LayoutInflater.from(this).inflate(R.layout.layout_recycle_footer,recyclerView);
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(linearLayoutManager=new LinearLayoutManager(this));
        headerFooterAdapter=new HeaderFooterAdapter(this,datalist);
        headerFooterAdapter.addHeader(headerview);
        headerFooterAdapter.addFooter(footerview);
        recyclerView.setAdapter(headerFooterAdapter);


    }

    private void initdata() {
        for(int i=0;i<20;i++){
            datalist.add("item"+i);
        }
    }
}
