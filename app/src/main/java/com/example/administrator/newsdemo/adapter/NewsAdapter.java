package com.example.administrator.newsdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.model.NewData;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    //数据源
    private List<NewData.ResultBean.DataBean> mDataBeanList;

    public NewsAdapter(List<NewData.ResultBean.DataBean> mDataBeanList){
        this.mDataBeanList=mDataBeanList;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        final NewsViewHolder viewHolder=new NewsViewHolder(view);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                NewData.ResultBean.DataBean data=mDataBeanList.get(position);
                String url=data.getUrl();

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewData.ResultBean.DataBean dataBean=mDataBeanList.get(position);
        holder.mTextView_title.setText(dataBean.getTitle());
        holder.mTextView_date.setText(dataBean.getDate());
        holder.mTextView_author_name.setText(dataBean.getAuthor_name());
        Glide.with(holder.mImageView_thumbnail_pic.getContext()).load(dataBean.getThumbnail_pic_s()).into(holder.mImageView_thumbnail_pic);
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }



    public void setChang(List<NewData.ResultBean.DataBean> dataBeanList) {
        mDataBeanList=dataBeanList;
        notifyDataSetChanged();
    }
}
