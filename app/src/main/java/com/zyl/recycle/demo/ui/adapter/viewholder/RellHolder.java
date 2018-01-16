package com.zyl.recycle.demo.ui.adapter.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zyl.myview.zrecycleview.base.BaseViewHolder;
import com.zyl.recycle.demo.R;
import com.zyl.recycle.demo.model.AgTechnologInfor;

/**
 * Created by Administrator on 2018/1/10.
 */

public class RellHolder extends BaseViewHolder<AgTechnologInfor> {
    private TextView agTechtitletv;
    private TextView datetv;
    private Context mcontext;
    public RellHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setdata(AgTechnologInfor object) {
        super.setdata(object);
        if(!TextUtils.isEmpty(object.getTitle())) agTechtitletv.setText(object.getTitle().replaceAll(" ","").trim());
//        if((!TextUtils.isEmpty(object.getCategory()))&&(object.getCategory().equals(mcontext.getString(R.string.ag_kp)))){
//            datetv.setVisibility(View.GONE);
//        }
        else if(TextUtils.isEmpty(object.getCategory())){
            datetv.setVisibility(View.GONE);

//            if(!TextUtils.isEmpty(object.getReleaseTime())){
//                datetv.setText(object.getIntime().length() > 8 ? object.getIntime().substring(0, 9) : object.getIntime());
//            }
        }else{
            if(!TextUtils.isEmpty(object.getIntime())){
                datetv.setText(object.getIntime().length() > 8 ? object.getIntime().substring(0, 9) : object.getIntime());
            }
        }

    }

    @Override
    protected void init() {
        super.init();
        agTechtitletv=mitenView.findViewById(R.id.tv_ag_technology_title);
        datetv=mitenView.findViewById(R.id.tv_ag_technology_date);
    }
}
