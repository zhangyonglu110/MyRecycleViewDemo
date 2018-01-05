package com.zyl.recycle.demo.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.recycle.demo.ui.adapter.viewholder.NormalHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> dataList;
    private Context mcontext;
    private View headerview,footerview;
    private final static int ITEM_TYPE=0X01;
    private final static int ITEM_HEADER=0X02;
    private final static int ITEM_FOOTER=0X03;
    private OnLoadMoreListener monLoadMoreListener;
    private boolean canloadMore=true;
    private View cachFooterView;
    private OnZRecycleViewItemClickListener monZRecycleViewItemClickListener;

    public HeaderFooterAdapter(Context context){
        this.mcontext=context;

    }
    public HeaderFooterAdapter(Context context, List<String> list){
        this.mcontext=context;
        this.dataList=list;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE:
                TextView textView = new TextView(mcontext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80));
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(15);

                return new NormalHolder(textView);
            case ITEM_HEADER:
                return new HeaderHolder(headerview);
            case ITEM_FOOTER:

                return new FooterHolder(footerview);

        }


        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("sss",getItemCount()+""+"position------------>"+position);
        if(holder instanceof NormalHolder){
            NormalHolder normalHolder= (NormalHolder) holder;
            if(headerview!=null&&footerview!=null){
                if(position!=0&&position!=getItemCount()-1) {
                    Log.i("sss","pos------------->"+position);
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
         if(headerview!=null&&footerview!=null){
             itemcount=2;
         }
         if(headerview!=null&&footerview==null){
             itemcount=1;
         }
        if(headerview==null&&footerview!=null){
            itemcount=1;
        }
        return dataList.size()+itemcount;
    }


    @Override
    public int getItemViewType(int position) {
        if(headerview!=null&&footerview==null) {
            if (position == 0) {
                return ITEM_HEADER;
            }
        }

        if(headerview==null&&footerview!=null){
               if(position==getItemCount()-1){
                   return ITEM_FOOTER;
               }
        }

        if(headerview!=null && footerview!=null){
            if (position == 0) {
                return ITEM_HEADER;
            }else if(position==getItemCount()-1){

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

  class HeaderHolder extends RecyclerView.ViewHolder{

      public HeaderHolder(View itemView) {
          super(itemView);
      }
  }
    class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {

            super(itemView);


        }


    }

    public void clear(){
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<String> list){
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
}
