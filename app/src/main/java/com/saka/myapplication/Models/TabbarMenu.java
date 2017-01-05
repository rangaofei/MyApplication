package com.saka.myapplication.Models;

import android.content.Context;

import com.example.saka.myapplication.R;

/**
 * Created by saka on 2016/12/31.
 */

public class TabbarMenu {
    private String first;
    private String second;
    private String third;
    private String fourth;



    private Context context;

    public TabbarMenu(Context context) {
        this.context=context;
        setFirst(context.getString(R.string.tabbar_serch));
        setSecond(context.getString(R.string.tabbar_joke));
        setThird(context.getString(R.string.tabbar_news));
        setFourth(context.getString(R.string.tabbar_setting));
    }

    public TabbarMenu(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    public TabbarMenu(int resId1,int resId2,int resId3){
        this.first=context.getString(resId1);
        this.second=context.getString(resId2);
        this.third=context.getString(resId3);
    }


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }
}
