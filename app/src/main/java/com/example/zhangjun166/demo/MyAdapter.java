package com.example.zhangjun166.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by ZHANGJUN166 on 2015-10-20.
 */
public class MyAdapter extends RecyclerView.Adapter {


    private int count;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        ViewHolder holder1 = new ViewHolder(v);
        return holder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tv.setText("这是" + position);
    }

    @Override
    public int getItemCount() {
        return count == 0 ? 40 : count;
    }

    public void setCount(int arg1) {
        this.count = arg1;
        notifyDataSetChanged();
        Handler handler = new Handler();
        Message message = Message.obtain();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ViewHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.tv);
        }

        public TextView getTv() {
            return tv;
        }
    }

}
