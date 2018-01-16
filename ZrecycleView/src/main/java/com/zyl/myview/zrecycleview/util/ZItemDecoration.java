/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.zyl.myview.zrecycleview.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


public class ZItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private static final String TAG = "DividerItem";
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    private Drawable mDivider,mDivider2;
    private int mheight,mcolor;

    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;

    private final Rect mBounds = new Rect();

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
     *
     *
     * @param context Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    public ZItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);

        mDivider = a.getDrawable(0);
        mDivider.setColorFilter(new LightingColorFilter(0xFFFFFFFF,0xFFAA0000));
        if (mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        setOrientation(orientation);
    }
    public ZItemDecoration(Context context, int orientation,int height) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mheight=height;

        mDivider = a.getDrawable(0);
        if (mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        setOrientation(orientation);
    }
    public ZItemDecoration(Context context, int orientation,int height,int color) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mheight=height;
        this.mcolor=color;
        ColorDrawable colorDrawable=new ColorDrawable(color);
        ColorDrawable colorDrawable2=new ColorDrawable(Color.RED);
        mDivider=colorDrawable;
        mDivider2=colorDrawable2;
        if (mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        setOrientation(orientation);
    }
    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }

        mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        if (mOrientation == VERTICAL) {

                drawVertical(c, parent);


        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            Log.i("zzz","left------->"+left+"|right--------------->"+right);
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        Log.i("zzz","child count--------->"+childCount);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());//四舍五入 计算的是该View在Y轴的偏移量。初始值为0，向上偏移为负，向下偏移为证。
//            final int top = bottom - mDivider.getIntrinsicHeight();
            final int top = bottom - mheight;
            Log.i("zzz","top------->"+top+"|bottom--------------->"+bottom);
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }


    private void drawHorizontal(Canvas canvas, RecyclerView parent) {

        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();

            Log.i("zzz","top------->"+top+"|bottom--------------->"+bottom);
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            final int right = mBounds.right + Math.round(child.getTranslationX());
            final int left = right - mheight;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (mDivider == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        if (mOrientation == VERTICAL) {
//            int visillblechidldcount=parent.getLayoutManager().getChildCount();
//            int totalcount=parent.getAdapter().getItemCount();
//            int childIndex=parent.getChildAdapterPosition(view);
////            Log.i("zzz","spanct count--------->"+getSpanCount(parent));
////            Log.i("zzz","total item  --------->"+parent.getAdapter().getItemCount());
////            Log.i("zzz","item count index---------->"+parent.getChildPosition(view));
//            Log.i("zzz","visible---------->"+visillblechidldcount+"------index------>"+childIndex+"---------total------->"+totalcount);
//
//           // if(parent.getAdapter().getItemViewType(parent.getChildLayoutPosition(view))==BaseRecycleAdapter.ITEM_FOOTER)mheight=0;

            int childAdapterPosition = parent.getChildAdapterPosition(view);//获取item 位置

            int lastCount = parent.getAdapter().getItemCount() - 1;//获取最后一条item 位置
            int childItemType=parent.getAdapter().getItemViewType(childAdapterPosition);
            if(parent.getLayoutManager() instanceof LinearLayoutManager){

                    if (childAdapterPosition == lastCount) {
                        outRect.set(0, 0, 0, 0);
                        return;
                    }


            }

            outRect.set(0, 0, 0,mheight);



        } else {

            outRect.set(0, 0, mheight, 0);
        }
    }



    public int getSpanCount(RecyclerView recyclerView){
        int spancount=-1;
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            spancount=((GridLayoutManager)layoutManager).getSpanCount();

        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            spancount=((StaggeredGridLayoutManager)layoutManager).getSpanCount();
        }

        return spancount;

    }

    public boolean isLastRow(RecyclerView parent,int itempos,int chidcount,int spancount){
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){

        }else if(layoutManager instanceof StaggeredGridLayoutManager){

        }
        else{

        }
        return true;
    }
}
