package com.zyl.recycle.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.model.AgTechnologInfor;
import com.zyl.recycle.demo.ui.adapter.HeaderFooterAdapter;
import com.zyl.recycle.demo.ui.adapter.ReallAdapter;
import com.zyl.recycle.demo.util.DensityUtil;
import com.zyl.recycle.demo.util.OkHttpUtils;
import com.zyl.recycle.demo.util.ZGridItemDecoration;
import com.zyl.recycle.demo.util.ZItemDecoration;
import com.zyl.recycle.demo.widget.ZRecycleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/10.
 */

public class RellyListActivity extends Activity implements ZRecycleView.OnZfreshListener, Callback {
    private int page = 0;
    private List<AgTechnologInfor> agTechnologInforList = new ArrayList<>();
    private ReallAdapter reallAdapter;
    ZRecycleView zRecycleView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zRecycleView = new ZRecycleView(this);
        setContentView(zRecycleView);
        zRecycleView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        zRecycleView.setLayoutManager(gridLayoutManager=new GridLayoutManager(this,3));
        ZGridItemDecoration zItemDecoration=new ZGridItemDecoration(this,LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,1),getResources().getColor(R.color.colorAccent));
        zRecycleView.setZItemDecoration(zItemDecoration);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(reallAdapter.getItemViewType(position)== HeaderFooterAdapter.ITEM_HEADER||reallAdapter.getItemViewType(position)== HeaderFooterAdapter.ITEM_FOOTER){
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });

        zRecycleView.setSchemeColors(R.color.colorAccent);
        zRecycleView.setRefreshListener(this);
        reallAdapter = new ReallAdapter(this, agTechnologInforList, R.layout.layout_ag_technology_list);
        reallAdapter.addFooter(LayoutInflater.from(RellyListActivity.this).inflate(R.layout.layout_recycle_footer, null));
        zRecycleView.setAdapter(reallAdapter);
        //reallAdapter.isShowFooter(true);

        zRecycleView.setLoadMoreListener(new ZRecycleView.LoadMoreListener() {
            @Override
            public void loadMore() {
                zRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        if(page<=9) {
                            getdata();
                        }else{
                            reallAdapter.isShowFooter(false);
                        }
                    }
                }, 3000);

            }
        });
        getdata();


    }

    private void getdata() {
        OkHttpUtils.post("http://guanli.yn316.cn/ExpertTec/YN_Nongyejishu/GridPageJsonS", initBuild().build(), this);
    }

    private FormBody.Builder initBuild() {

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("rows", "15");
        builder.add("page", String.valueOf(page));
        builder.add("sord", "desc");
        builder.add("UserId", "c24400eb-8c8c-42c0-baa0-0621f51bcb9d");
        builder.add("Key", "KEY69B5B31F-8355-4234-B9C6-B6AA1EF92EA7");
        builder.add("sidx", "intime");
        builder.add("type", "种植技术");
        return builder;
    }


    @Override
    public void refresh() {
        reallAdapter.isShowFooter(false);
        page = 0;
        reallAdapter.clear();
        //reallAdapter.isShowFooter(true);

        getdata();

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        handler.sendMessage(handler.obtainMessage(0, response.body().string()));
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("ppp", "page----------->" + page);
            String string = (String) msg.obj;
            if (!TextUtils.isEmpty(string)) {
                try {

                    List<AgTechnologInfor> list = new Gson().fromJson(new JSONObject(string).getString("rows"), new TypeToken<List<AgTechnologInfor>>() {
                    }.getType());
                    Log.i("ppp", "list size--------->" + list.size());
                    if (list.size() > 0) {
                        reallAdapter.addAll(list);
                        if (page == 0) {
                            Log.i("ppp","refresh after----------setfooter  true");
                            reallAdapter.isShowFooter(true);
                        }


                    } else {
                        Log.i("rrr","adapter count ------>"+reallAdapter.getItemCount());
                        reallAdapter.isShowFooter(false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            super.handleMessage(msg);
        }
    };
}
