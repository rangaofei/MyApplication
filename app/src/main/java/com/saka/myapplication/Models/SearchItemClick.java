package com.saka.myapplication.Models;

import android.util.Log;

import com.saka.myapplication.CustomInterface.ItemClickListener;

/**
 * Created by saka on 2017/1/2.
 */

public class SearchItemClick {
    private static final String TAG = "SearchItemClick";
    private ItemClickListener listener;
    private int position;

    public SearchItemClick(int position) {
        this.position=position;
    }

    public void onClickItemBtn(int resId) {
        Log.d(TAG, String.valueOf(resId));
        listener.onItemButtonClick(resId);
    }

    public void onClickItem(){
        listener.onItemClick(position);
    }

    public void setListener(ItemClickListener listener){
        this.listener=listener;
    }
}
