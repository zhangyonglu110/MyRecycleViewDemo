package com.zyl.recycle.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.logging.Handler;

/**
 * Created by Administrator on 2018/1/4.
 */

public class ZRecycleView extends LinearLayout {
    private Context mcontext;
   private SwipeRefreshLayout swipeRefreshLayout;
   private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
   private boolean isloading=false;
   private LoadMoreListener mloadMoreListener;

    public ZRecycleView(Context context) {
        super(context);
        this.mcontext=context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        swipeRefreshLayout=new SwipeRefreshLayout(mcontext);
        swipeRefreshLayout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        recyclerView=new RecyclerView(mcontext);
        recyclerView.setLayoutParams(new LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT, SwipeRefreshLayout.LayoutParams.WRAP_CONTENT));
        swipeRefreshLayout.addView(recyclerView);
        addView(swipeRefreshLayout);
        initevent();
    }

    private void initevent() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int itemcount=layoutManager.getChildCount();
                int lastposition=0;
                if(layoutManager instanceof LinearLayoutManager) {
                     lastposition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }else if(layoutManager instanceof GridLayoutManager){
                    lastposition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();

                }

                if(itemcount==(lastposition+1)&&!isloading){
                     new android.os.Handler().post(new Runnable() {
                         @Override
                         public void run() {
                             isloading=true;
                             if(mloadMoreListener!=null) mloadMoreListener.loadMore();
                             isloading=false;

                         }
                     });


                       Log.i("ooo","isloading-------------->"+isloading);
                }
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    public ZRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mcontext=context;
        init();


    }

    public ZRecycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext=context;
        init();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        this.layoutManager=layoutManager;
        recyclerView.setLayoutManager(layoutManager);
    }
    public void setAdapter(RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);

    }

    public void setSchemeColors(int[] colcors){
        swipeRefreshLayout.setColorSchemeResources(colcors);
    }
    public void setSchemeColors(int colcor){

        swipeRefreshLayout.setColorSchemeResources(new int[]{colcor,colcor,colcor,colcor});
    }
    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.mloadMoreListener=loadMoreListener;

    }
   public interface  LoadMoreListener{
        void loadMore();
    }


    public void setRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener){
      swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void stopRefresh(){
        swipeRefreshLayout.setRefreshing(false);
    }
    public void startRefresh(){
        swipeRefreshLayout.setRefreshing(true);
    }


}
