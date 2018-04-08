package com.zyl.myview.zrecycleview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zyl.myview.zrecycleview.base.BaseRecycleAdapter;


/**
 * Created by Administrator on 2018/1/4.
 */

public class ZRecycleView extends LinearLayout {
    private Context mcontext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isloading=false,canload=true,isfresh=false;
    private LoadMoreListener mloadMoreListener;
    private BaseRecycleAdapter baseRecycleAdapter;
    private OnZfreshListener monZfreshListener;


    public ZRecycleView(Context context) {
        super(context);
        this.mcontext=context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        // setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        swipeRefreshLayout=new SwipeRefreshLayout(mcontext);
        swipeRefreshLayout.setEnabled(false);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //swipeRefreshLayout.setPadding(20,0,20,0);
        swipeRefreshLayout.setLayoutParams(params);
        recyclerView=new RecyclerView(mcontext);
        recyclerView.setNestedScrollingEnabled(false);


        recyclerView.setLayoutParams(new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT, SwipeRefreshLayout.LayoutParams.WRAP_CONTENT));
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

                if(itemcount<=(lastposition+1)&&!isloading&&canload&&!isfresh){
                    new android.os.Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            isloading=true;
                            if(baseRecycleAdapter!=null)
                                if(mloadMoreListener!=null){
                                    baseRecycleAdapter.isShowFooter(true);
                                    mloadMoreListener.loadMore();
                                }
                            isloading=false;

                        }
                    });

                }
                super.onScrolled(recyclerView, dx, dy);

            }
        });


        if(monZfreshListener!=null) {

        }

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
    public void setAdapter(BaseRecycleAdapter adapter){
        this.baseRecycleAdapter=adapter;
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

    public void setcanrefresh(boolean canrefresh){
        this.canload=canrefresh;

    }

    public void stopLoadMore(){
        if(baseRecycleAdapter!=null&&baseRecycleAdapter.getFooterview()!=null) baseRecycleAdapter.isShowFooter(true);
        canload=false;
    }

    public void startLoadMore(){
        if(baseRecycleAdapter!=null) baseRecycleAdapter.isShowFooter(false);
        canload=true;

    }
    public void setRefreshListener(OnZfreshListener onZfreshListener){
        this.monZfreshListener=onZfreshListener;
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isloading) {
                    isfresh = true;
                    //   if(baseRecycleAdapter!=null) baseRecycleAdapter.isShowFooter(true);
                    monZfreshListener.refresh();
                }
                isfresh = false;
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public interface OnZfreshListener{
        void refresh();
    }

    /**
     * set divider line
     * @param itemDecoration
     */
    public void setZItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void setZItemAnimator(RecyclerView.ItemAnimator itemAnimator){
        recyclerView.setItemAnimator(itemAnimator);
    }

    public RecyclerView getRecyclerView(){
        if(recyclerView==null){
            try {
                throw new Exception("recyclerView is null");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return recyclerView;
    }


    public SwipeRefreshLayout getSwipeRefreshLayout(){
        if(swipeRefreshLayout==null){
            try {
                throw new Exception("swipeRefreshLayout is null");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return swipeRefreshLayout;
    }
}
