package com.example.administrator.newsdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newsdemo.R;

/**
 * Created by Administrator on 2017/5/18.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView mTextView_title;
    TextView mTextView_date;
    TextView mTextView_author_name;
    ImageView mImageView_thumbnail_pic;
    View mView;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mTextView_title= (TextView) itemView.findViewById(R.id.textview_title);
        mTextView_date= (TextView) itemView.findViewById(R.id.textview_date);
        mTextView_author_name= (TextView) itemView.findViewById(R.id.author_name);
        mImageView_thumbnail_pic= (ImageView) itemView.findViewById(R.id.thumbnail_pic);
        mView=itemView;
    }
}
