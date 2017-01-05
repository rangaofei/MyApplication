package com.saka.myapplication.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentJokeBinding;
import com.saka.myapplication.BaseComponent.API;
import com.saka.myapplication.BaseComponent.BaseFragment;
import com.saka.myapplication.CustomAdapter.JokeRecyclerAdapter;
import com.saka.myapplication.HttpUtil.RequestServers;
import com.saka.myapplication.Models.Joke;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class JokeFragment extends BaseFragment {
    private static final String TAG = "jokefragment";

    private FragmentJokeBinding binding;
    private Joke.Data[] list=new Joke.Data[]{};
    private Joke joke;
    private JokeRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joke, container, false);
        binding.jokeFragmentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new JokeRecyclerAdapter(getContext(), list);
        binding.jokeFragmentRv.setAdapter(adapter);
        initList();
//        starthttp();

        return binding.getRoot();
    }

    private void initList() {
        Log.d(TAG, "http with retrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEJOKEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServers servers = retrofit.create(RequestServers.class);
        long time=System.currentTimeMillis()/1000;
        Log.d(TAG,time+"");
        retrofit2.Call<Joke> call = servers.getJoke(API.JOKEKEY, 20, String.valueOf(time-10));
        call.enqueue(new retrofit2.Callback<Joke>() {
            @Override
            public void onResponse(retrofit2.Call<Joke> call, retrofit2.Response<Joke> response) {
                joke = response.body();
                Log.d(TAG, joke.getReason() + "----" );
                list = joke.getResult().getData();
                for (Joke.Data data : list) {
                    Log.d(TAG, "joke的数量=" + data.toString());
                }
                if (list.length > 0) {
                    adapter.refreshData(list);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Joke> call, Throwable t) {
                Log.d(TAG, "error occured" + t.toString());
            }
        });

    }

    private void starthttp() {
        Log.d(TAG, "start with okhttp");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API.BASEJOKEURL + "joke/content/list.from" + "?key=" + API.JOKEKEY + "&pagesize=20" + "&time=1483521950")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "error occured" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.body().string());
            }
        });
    }


}
