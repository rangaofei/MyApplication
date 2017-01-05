package com.saka.myapplication.Models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by saka on 2017/1/2.
 */

public class CardInfo extends BaseObservable{
    private String area;
    private String sex;
    private String birthday;
    private String idNum;

    @Bindable
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
        notifyPropertyChanged(BR.area);
    }
    @Bindable
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        notifyPropertyChanged(BR.birthday);
    }
    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    @Bindable
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
//        notifyPropertyChanged(BR.idNum);
    }
}
