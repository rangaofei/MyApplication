package com.saka.myapplication.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentHistoryBinding;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.saka.myapplication.BaseComponent.API;
import com.saka.myapplication.CustomAdapter.HistoryAdapter;
import com.saka.myapplication.CustomAdapter.NoAlphaItemAnimator;
import com.saka.myapplication.CustomInterface.ItemClickListener;
import com.saka.myapplication.HttpUtil.RequestServers;
import com.saka.myapplication.Models.HistoryModle;
import com.saka.myapplication.Utils.TimeUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements ItemClickListener {

    private FragmentHistoryBinding binding;
    private HistoryAdapter adapter;
    private HistoryModle.HistoryResult[] results;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvHistory.setItemAnimator(new NoAlphaItemAnimator());
        adapter = new HistoryAdapter(getActivity(), results);
        adapter.setItemClickListener(this);
        binding.rvHistory.setAdapter(adapter);

        startRequest();
    }

    private void startRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEHISTORYURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        final RequestServers requestServers = retrofit.create(RequestServers.class);
        Call<HistoryModle> call = requestServers.getHistory(API.HISTORYKEY, String.valueOf(1.0),
                TimeUtil.getCurrentMonth(), TimeUtil.getCurrentDay());
        call.enqueue(new Callback<HistoryModle>() {
            @Override
            public void onResponse(Call<HistoryModle> call, Response<HistoryModle> response) {
                HistoryModle modle = response.body();
                Log.d("fff", modle.toString());
                results = modle.getResults();
                adapter.refreshData(results);
            }

            @Override
            public void onFailure(Call<HistoryModle> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemButtonClick(int resId) {

    }

    @Override
    public void onItemClick(int resId) {
        Log.d("fff", "点击了事件" + resId);
        if (results[resId].getShowDetails() == View.GONE) {
            Log.d("fff", "显示条目");
            results[resId].setShowDetails(View.VISIBLE);
        } else {
            Log.d("fff", "隐藏条目");
            results[resId].setShowDetails(View.GONE);
        }
        adapter.notifyItemChanged(resId);
    }
}
