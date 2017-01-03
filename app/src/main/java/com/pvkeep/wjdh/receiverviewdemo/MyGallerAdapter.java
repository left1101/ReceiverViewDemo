package com.pvkeep.wjdh.receiverviewdemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by luke on 2016/12/9.
 */
public class MyGallerAdapter extends RecyclerView.Adapter<MyGallerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private int width;
    private int height;

    public MyGallerAdapter(Context context, List<Integer> datats, int width, int height) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
        this.width = width;
        this.height = height;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImg.setImageResource(mDatas.get(position));
        holder.mImg.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.mImg.getLayoutParams();
        layoutParams.height = (int) (height * 0.8);
        layoutParams.width = (int) (width * 0.7);
        layoutParams.topMargin = (int) (height * 0.1);
        layoutParams.leftMargin = position == 0 ? (int) (width * 0.15) : (int) (width * 0.05);
        layoutParams.rightMargin = position == mDatas.size() - 1 ? (int) (width * 0.15) : (int) (width * 0.05);
        layoutParams.bottomMargin = (int) (height * 0.1);
        //layoutParams. = Gravity.CENTER;
//        if (position != 0){
//            holder.mImg.setScaleY(0.9F);
//        }
        //
        holder.mImg.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.galler_adapter_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.gallery_item_image);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
    }
}
