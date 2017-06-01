package com.example.administrator.newsdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.model.WeixinData;
import com.example.administrator.newsdemo.ui.fragment.ShowNewsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class WeixinAdapter extends RecyclerView.Adapter<WeixinAdapter.ViewHolder>{
    private List<WeixinData.ResultBean.ListBean> mListBeen;
    private Context mContext;


    public WeixinAdapter(List<WeixinData.ResultBean.ListBean> mListBeen,Context mContext){
        this.mListBeen=mListBeen;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
       final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.weicharView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                WeixinData.ResultBean.ListBean data=mListBeen.get(position);
                String url=data.getUrl();
                Intent intent=new Intent();
                intent.setClass(mContext, ShowNewsActivity.class);
                intent.putExtra("url",url);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeixinData.ResultBean.ListBean dataBean=mListBeen.get(position);
        holder.mTextView_title.setText(dataBean.getTitle());
        holder.mTextView_id.setText(dataBean.getId());
        holder.mTextView_source.setText(dataBean.getSource());
        Glide.with(holder.mImageView_firstImg.getContext()).load(dataBean.getFirstImg()).into(holder.mImageView_firstImg);
    }

    @Override
    public int getItemCount() {
        return mListBeen.size();
    }

    public void setChang(List<WeixinData.ResultBean.ListBean> listBeen) {
            mListBeen=listBeen;
            notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView_title;
        TextView mTextView_id;
        TextView mTextView_source;
        ImageView mImageView_firstImg;
        View weicharView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView_title= (TextView) itemView.findViewById(R.id.textview_title);
            mTextView_id= (TextView) itemView.findViewById(R.id.textview_date);
            mTextView_source= (TextView) itemView.findViewById(R.id.author_name);
            mImageView_firstImg= (ImageView) itemView.findViewById(R.id.thumbnail_pic);
            weicharView=itemView;
        }
    }
}
