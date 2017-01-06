package com.saka.myapplication.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/1/6.
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private Context context;
    private Disposable disposable;
    private static final int SUCCESS_CODE=0;

    public BaseObserver(Context context) {
        this.context = context;

    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        if(tBaseEntity.getError_code()==SUCCESS_CODE){
            T t=tBaseEntity.getResult();
            onHandleSucess(t);
        }else {
            onHandleError(tBaseEntity.getError_code(),tBaseEntity.getReason());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d("rxjava","发生错误");
        Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        Log.d("rxjava","完成");
    }

    public abstract void onHandleSucess(T t);
    void onHandleError(int code,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
