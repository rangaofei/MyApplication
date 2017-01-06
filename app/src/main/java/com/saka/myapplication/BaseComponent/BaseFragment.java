package com.saka.myapplication.BaseComponent;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by saka on 2016/12/31.
 */

public class BaseFragment extends Fragment {
    public ProgressDialog progressDialog;
    public ObservableTransformer<Observable, ObservableSource> composeFunction;
    private static final long RETRY_TIMES = 1;
    private boolean showLoading = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(getActivity());
        composeFunction = new ObservableTransformer<Observable, ObservableSource>() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable.retry(RETRY_TIMES)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (showLoading) {
                                    if (progressDialog != null &&progressDialog.isShowing()){
                                        progressDialog.show();
                                    }else {
                                        Log.d("rxjava","正在进行");
                                    }
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
