package com.zyl.recycle.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zyl.recycle.demo.ui.activity.GridLayoutActivity;
import com.zyl.recycle.demo.ui.activity.HeaderFooterActivity;
import com.zyl.recycle.demo.ui.activity.MyRecycleViewActivity;
import com.zyl.recycle.demo.ui.activity.NormalActivity;
import com.zyl.recycle.demo.ui.activity.RefreshMoreNormalActivity;
import com.zyl.recycle.demo.ui.activity.RefreshNormalActivity;
import com.zyl.recycle.demo.ui.activity.RellyListActivity;
import com.zyl.recycle.demo.ui.activity.RollPagerHeaderActivity;
import com.zyl.recycle.demo.ui.adapter.HeaderFooterAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.item_normal).setOnClickListener(this);
        findViewById(R.id.item_header_footer).setOnClickListener(this);
        findViewById(R.id.item_refresh).setOnClickListener(this);
        findViewById(R.id.item_refresh_more).setOnClickListener(this);
        findViewById(R.id.item_rollpage_header).setOnClickListener(this);
        findViewById(R.id.item_my_recycleview).setOnClickListener(this);
        findViewById(R.id.item_grid_layout).setOnClickListener(this);
        findViewById(R.id.item_example).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_normal:
                startActivity(new Intent(this, NormalActivity.class));
                break;
            case R.id.item_header_footer:
                startActivity(new Intent(this, HeaderFooterActivity.class));

                break;
            case R.id.item_refresh:
                startActivity(new Intent(this, RefreshNormalActivity.class));

                break;
            case R.id.item_refresh_more:
                startActivity(new Intent(this, RefreshMoreNormalActivity.class));

                break;
            case R.id.item_rollpage_header:
                startActivity(new Intent(this, RollPagerHeaderActivity.class));

                break;
            case R.id.item_my_recycleview:
                startActivity(new Intent(this, MyRecycleViewActivity.class));

                break;
            case R.id.item_grid_layout:
                startActivity(new Intent(this, GridLayoutActivity.class));

                break;
            case R.id.item_example:
                startActivity(new Intent(this, RellyListActivity.class));

                break;
        }

    }
}
