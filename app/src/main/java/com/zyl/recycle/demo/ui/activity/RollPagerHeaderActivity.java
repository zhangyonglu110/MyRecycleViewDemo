package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zyl.myview.rollpagerview.adapter.ZPagerAdapter;
import com.zyl.myview.rollpagerview.banner.ZBanner;
import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.ui.adapter.HeaderFooterAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class RollPagerHeaderActivity extends Activity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> datalist=new ArrayList<>(),testlist=new ArrayList<>();
    private int[] colorids=new int[]{R.color.colorAccent,R.color.colorAccent,R.color.colorAccent,R.color.colorAccent};
    private HeaderFooterAdapter headerFooterAdapter;
    private View headerview,footerview;
    private int totalItemCount,lastVisibleItem,visibleThreshold;
    private boolean isloading=false,isrefresh=false;
    private LinearLayoutManager linearLayoutManager;
    private int page=0;
    private List<Integer> imgids=new ArrayList<>();
    private int[] imgid=new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initdata();
        initheader();
        footerview= LayoutInflater.from(this).inflate(R.layout.layout_recycle_footer,recyclerView);
        recyclerView=findViewById(R.id.recycle_view);
        swipeRefreshLayout=findViewById(R.id.swip_refresh);
        recyclerView.setLayoutManager(linearLayoutManager=new LinearLayoutManager(this));
        swipeRefreshLayout.setColorSchemeResources(colorids);
        headerFooterAdapter=new HeaderFooterAdapter(this,datalist);
        headerFooterAdapter.addHeader(headerview);
        headerFooterAdapter.addFooter(footerview);
        recyclerView.setAdapter(headerFooterAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.i("sss","newState---------------->"+newState);
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.i("sss","dx---------------->"+dx+"dy--------------"+dy);

                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isloading && totalItemCount==(lastVisibleItem+1)&&!isrefresh) {
                    Log.i("vvv","loading-------------------");

                        isloading = true;
                     if(headerFooterAdapter.getFooterview()==null) headerFooterAdapter.startLoadMore();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(page<3) {
                                    headerFooterAdapter.addAll(testlist);
                                    page++;
                                }else{
                                    Toast.makeText(RollPagerHeaderActivity.this,"no more data",Toast.LENGTH_SHORT).show();
                        headerFooterAdapter.stopLoadMore();
                                }
                                isloading = false;

                            }
                        },3000);

                    }
//                    else{
//                        Toast.makeText(RefreshMoreNormalActivity.this,"no more data",Toast.LENGTH_SHORT).show();
//                        headerFooterAdapter.stopLoadMore();
//                    }


            }});

        headerFooterAdapter.setMonLoadMoreListener(new HeaderFooterAdapter.OnLoadMoreListener() {
            @Override
            public void loadMore() {
              //  headerFooterAdapter.addAll(testlist);

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=0;
                isrefresh=true;
                headerFooterAdapter.startLoadMore();
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                        headerFooterAdapter.clear();
                        headerFooterAdapter.addAll(testlist);
                        isrefresh=false;
                    }
                },3000);
            }
        });

    }

    private void initheader() {
        imgids.add(R.mipmap.ic_launcher);
        imgids.add(R.mipmap.ic_launcher);
        imgids.add(R.mipmap.ic_launcher);
        headerview= LayoutInflater.from(this).inflate(R.layout.layout_rollapage_header,recyclerView);
        ZBanner zBanner=headerview.findViewById(R.id.zbanner);
        ZPagerAdapter zPagerAdapter=new ZPagerAdapter(this, imgid);
        zBanner.setadapter(zPagerAdapter);

    }

    private void initdata() {
        for(int i=0;i<10;i++){
            datalist.add("item"+i);
        }
        testlist.addAll(datalist);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
