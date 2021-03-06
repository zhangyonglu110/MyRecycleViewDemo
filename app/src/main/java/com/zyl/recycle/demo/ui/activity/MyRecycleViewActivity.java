package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zyl.myview.zrecycleview.base.BaseRecycleAdapter;
import com.zyl.myview.zrecycleview.widget.ZRecycleView;
import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.ui.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MyRecycleViewActivity extends Activity {
    private List<String> datalist=new ArrayList<>();
    private List<String> testlist=new ArrayList<>();
    private TestAdapter headerFooterAdapter;
    private int page=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final ZRecycleView zRecycleView=new ZRecycleView(this);
        setContentView(zRecycleView);
        initdata();
        zRecycleView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
        zRecycleView.setLayoutManager(new LinearLayoutManager(this));
        zRecycleView.setSchemeColors(R.color.colorAccent);
        headerFooterAdapter=new TestAdapter(this,datalist,R.layout.layout_test_item);
        headerFooterAdapter.addFooter(LayoutInflater.from(this).inflate(R.layout.layout_recycle_footer,null));
        zRecycleView.setAdapter(headerFooterAdapter);
        zRecycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                zRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        headerFooterAdapter.clear();
                        zRecycleView.stopRefresh();
                       // headerFooterAdapter.startLoadMore();
                        page=0;
                        datalist.clear();
                        headerFooterAdapter.addAll(datalist);
                    }
                },3000);

            }
        });

        zRecycleView.setLoadMoreListener(new ZRecycleView.LoadMoreListener() {
            @Override
            public void loadMore() {


                Log.i("ooo","loadmore--------------->"+page);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(headerFooterAdapter.getFooterview()==null)  headerFooterAdapter.startLoadMore();

                            page++;
                            if(page<=4){
                                testlist.clear();
                                inittestdata();
                                Log.i("ppp","load data-----------"+"---current page----->"+page);
                                headerFooterAdapter.addAll(testlist);
                            }else{
                                Log.i("ooo", "stop------------->");
                                zRecycleView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        headerFooterAdapter.stopLoadMore();

                                    }
                                });
                            }
                        }
                    },3000);


            }
        });


        headerFooterAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnZRecycleViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MyRecycleViewActivity.this,position+"",Toast.LENGTH_SHORT).show();

            }
        });


        super.onCreate(savedInstanceState);
    }

    private void initdata() {
        for(int i=0;i<10;i++){
            datalist.add("item"+i);

        }
    }
    private void inittestdata() {
        for(int i=0;i<10;i++){
           testlist.add("item"+i);

        }
    }
}
