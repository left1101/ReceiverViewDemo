package com.pvkeep.wjdh.receiverviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luke on 2016/12/9.
 */
public class MyGallerActivity extends Activity {

    private RecyclerView recyclerView;
    private List<Integer> mDatas;
    private int width;
    private int height;

    int mOnePageWidth;

    int mCurrentItemOffset = 0;
    int mCurrentItemPos = 0;

    float mScale = 0.9F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_galler_activity_layout);

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        mOnePageWidth = (int) (width * 0.8);

        recyclerView = (RecyclerView) findViewById(R.id.my_galler_recycleview);
        /*mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.welcom_icon1,
                R.drawable.welcom_icon2, R.drawable.welcom_icon3, R.drawable.welcom_icon1, R.drawable.welcom_icon2,
                R.drawable.welcom_icon3, R.drawable.welcom_icon1, R.drawable.welcom_icon2, R.drawable.welcom_icon3));*/
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerView.setLayoutManager(layoutManager);
        //new LinearSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(new MyGallerAdapter(this, mDatas, width, height));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mCurrentItemOffset += dx;
                computeCurrentItemPos();
                onScrolledChangedCallback();
            }
        });
    }

    private void computeCurrentItemPos(){
        mCurrentItemPos = Math.abs(mCurrentItemOffset / mOnePageWidth);
        Log.i("luke", "mCurrentItemPos = " + mCurrentItemPos);
    }

    /**
     * RecyclerView位移事件监听, view大小随位移事件变化
     */
    private void onScrolledChangedCallback() {
        int offset = mCurrentItemOffset - mCurrentItemPos * mOnePageWidth/* - (int) (width * 0.1)*/;
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.0001);

        View leftView = null;
        View currentView;
        View rightView = null;
        if (mCurrentItemPos > 0) {
            leftView = recyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 1);
        }
        currentView = recyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos);
        if (mCurrentItemPos < recyclerView.getAdapter().getItemCount() - 1) {
            rightView = recyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 1);
        }

        if (leftView != null) {
            // y = (1 - mScale)x + mScale
            leftView.setScaleY((1 - mScale) * percent + mScale);
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setScaleY((mScale - 1) * percent + 1);
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.setScaleY((1 - mScale) * percent + mScale);
        }
    }
}
