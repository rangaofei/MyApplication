package com.saka.myapplication.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.saka.myapplication.CustomInterface.TabBarClickListener;

/**
 * Created by saka on 2016/12/31.
 */

public class ClickListener {
    private static final String TAG = "ClickListener";
    private TabBarClickListener listener;
    public void OnClickTab(String name) {
        Log.d(TAG, name);
        listener.onClickTab(name);
    }
    public void setOnClickTabListener(TabBarClickListener listener){
        this.listener=listener;
    }
}
