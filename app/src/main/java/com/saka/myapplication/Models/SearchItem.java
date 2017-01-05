package com.saka.myapplication.Models;

import android.view.View;

/**
 * Created by saka on 2017/1/2.
 */

public class SearchItem {
    private int drawableResId;
    private int textResId;
    private String buttontext="点击查询";
    private boolean showDetail;
    private String details;
    private int viewState;


    public SearchItem() {
    }

    public SearchItem(int drawableResId, int textResId, boolean showDetail) {
        this.drawableResId = drawableResId;
        this.textResId = textResId;
        this.showDetail = showDetail;
        this.details="显示细节";
        if(showDetail){
            viewState= View.VISIBLE;
        }else {
            viewState=View.GONE;
        }
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isShowDetail() {
        return showDetail;
    }

    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getViewState() {
        return viewState;
    }

    public void setViewState(int viewState) {
        this.viewState = viewState;
    }
}
