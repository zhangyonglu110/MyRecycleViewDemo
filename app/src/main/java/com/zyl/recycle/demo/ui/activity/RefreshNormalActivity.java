package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.ui.adapter.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class RefreshNormalActivity extends Activity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> datalist=new ArrayList<>();
    private int[] colorids=new int[]{R.color.colorAccent,R.color.colorAccent,R.color.colorAccent,R.color.colorAccent};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initdata();
        recyclerView=findViewById(R.id.recycle_view);
        swipeRefreshLayout=findViewById(R.id.swip_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setColorSchemeResources(colorids);
        recyclerView.setAdapter(new NormalAdapter(this,datalist));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

    }

    private void initdata() {
        for(int i=0;i<10;i++){
            datalist.add("item"+i);
        }
    }
}
