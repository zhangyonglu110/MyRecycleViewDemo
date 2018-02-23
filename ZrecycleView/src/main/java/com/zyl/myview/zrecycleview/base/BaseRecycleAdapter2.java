package com.zyl.myview.zrecycleview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public abstract class BaseRecycleAdapter2<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected List<T> dataList;
    protected Context mcontext;
    protected View headerview,footerview;
    public final static int ITEM_TYPE=0X01;
    public final static int ITEM_HEADER=0X02;
    public final static int ITEM_FOOTER=0X03;
    protected OnLoadMoreListener monLoadMoreListener;
    protected boolean canloadMore=true,misshowfooter=false;
    protected View cachFooterView;
    protected OnZRecycleViewItemClickListener monZRecycleViewItemClickListener;
    protected int mlayoutid;
    protected View mItemView;

    public BaseRecycleAdapter2(Context context){
        this.mcontext=context;

    }
    public BaseRecycleAdapter2(Context context, List<T> list){
        this.mcontext=context;
        this.dataList=list;

    }
    public BaseRecycleAdapter2(Context context, List<T> list, int layoutid){
        this.mcontext=context;
        this.dataList=list;
        this.mlayoutid=layoutid;

    }
    public BaseRecycleAdapter2(Context context, List<T> list, View itemview){
        this.mcontext=context;
        this.dataList=list;
        this.mItemView=itemview;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE:
                /**
                 * recycleview 加载每个item都需要 new出一个新view   所以遇到加载不全的问题就在这里
                 */
                if(mlayoutid!=0){
                //  View mItemView= LayoutInflater.from(mcontext).inflate(mlayoutid,null);  //横向宽度不够  显示不全
                  View mItemView= LayoutInflater.from(mcontext).inflate(mlayoutid,parent,false);
                  return getViewHolder(mItemView);

                }
            case ITEM_HEADER:
                return new HeaderHolder(headerview);
            case ITEM_FOOTER:
                if(misshowfooter) {
                    return new FooterHolder(footerview);
                }

        }


        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       // Log.i("sss",getItemCount()+""+"position------------>"+position);
        if(holder instanceof BaseViewHolder){
            final BaseViewHolder<T> normalHolder= (BaseViewHolder<T>) holder;
            if(headerview!=null&&footerview!=null){
                if(position!=0&&position!=getItemCount()-1) {
                   // Log.i("sss","pos------------->"+position);
                    normalHolder.setdata(dataList.get(position - 1));
                }
            }
            if(headerview!=null&&footerview==null){
                if(position!=0) {
                    normalHolder.setdata(dataList.get(position - 1));
                }
            }
            if(headerview==null&&footerview!=null){
                if(position!=getItemCount()-1)
                normalHolder.setdata(dataList.get(position));
            }
            if(headerview==null&&footerview==null){
                normalHolder.setdata(dataList.get(position));
            }

            normalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  // if(normalHolder!=null) normalHolder.OnZItemClick(position);
                   if(monZRecycleViewItemClickListener!=null) monZRecycleViewItemClickListener.onItemClick(position);
                }
            });

        }else if(holder instanceof HeaderHolder){


        }else if(holder instanceof FooterHolder){
          //  if(monLoadMoreListener!=null) monLoadMoreListener.loadMore();

        }

    }



    @Override
    public int getItemCount() {
        int itemcount=0;
         if(headerview!=null&&footerview!=null&&misshowfooter){
             itemcount=2;
         }
         if(headerview!=null&&footerview==null){
             itemcount=1;
         }
        if(headerview==null&&footerview!=null&&misshowfooter){
            itemcount=1;
        }
        return dataList.size()+itemcount;
    }


    @Override
    public int getItemViewType(int position) {
        Log.i("ppp","is showfooted--------->"+misshowfooter);
        if(headerview!=null&&footerview==null) {
            if (position == 0) {
                return ITEM_HEADER;
            }
        }

        if(headerview==null&&footerview!=null){
               if(position==getItemCount()-1&&misshowfooter){
                   return ITEM_FOOTER;
               }
        }

        if(headerview!=null && footerview!=null){
            if (position == 0) {
                return ITEM_HEADER;
            }else if(position==getItemCount()-1&&misshowfooter){

                    return ITEM_FOOTER;
                }

        }


        return ITEM_TYPE;
    }

    public void addHeader(View view){
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.headerview=view;
        notifyDataSetChanged();

    }
    public void addFooter(View view){
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        this.footerview=view;
        this.cachFooterView=footerview;
        notifyDataSetChanged();

    }

    public void removeHeader(View view){
        this.headerview=null;
        notifyDataSetChanged();

    }
    public void removeFooter(){
        this.footerview=null;
        notifyDataSetChanged();

    }

 public class HeaderHolder<T> extends RecyclerView.ViewHolder{

      public HeaderHolder(View itemView) {
          super(itemView);
      }
  }
 public    class FooterHolder<T> extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {

            super(itemView);


        }


    }

    public void clear(){
        this.dataList.clear();
       // misshowfooter=false;
     // notifyDataSetChanged();
    }
    public void clearnotify(){
        this.dataList.clear();
        // misshowfooter=false;
       notifyDataSetChanged();
    }

    public void addAll(List<T> list){
         int startpos=getItemCount()-1;
        this.dataList.addAll(list);
       notifyDataSetChanged();

    }


    public interface OnLoadMoreListener{
        void loadMore();
    }
    public void  setMonLoadMoreListener(OnLoadMoreListener listener){
        this.monLoadMoreListener=listener;

    }

    public void  startLoadMore(){
        if(this.footerview==null)
        this.footerview=cachFooterView;
        notifyDataSetChanged();

    }
    public void stopLoadMore(){
        this.footerview=null;
        notifyDataSetChanged();
    }

    public View getFooterview(){
        return this.footerview;
    }
    public View getHeaderview(){
        return  this.headerview;
    }

    public interface OnZRecycleViewItemClickListener{
       void onItemClick(int position);
    }

    public void setOnItemClickListener(OnZRecycleViewItemClickListener onItemClickListener){
        this.monZRecycleViewItemClickListener=onItemClickListener;

    }

    public abstract RecyclerView.ViewHolder getViewHolder(View itemview);


    public void isShowFooter(boolean isshowfooter){
        this.misshowfooter=isshowfooter;
        if(misshowfooter) {
            footerview.setVisibility(View.VISIBLE);
        }else{
            footerview.setVisibility(View.GONE);

        }
       notifyDataSetChanged();

    }
}
