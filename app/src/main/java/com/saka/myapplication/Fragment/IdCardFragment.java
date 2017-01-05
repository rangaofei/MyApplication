package com.saka.myapplication.Fragment;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentIdCardBinding;
import com.saka.myapplication.BaseComponent.API;
import com.saka.myapplication.HttpUtil.RequestServers;
import com.saka.myapplication.Models.CardInfo;
import com.saka.myapplication.Models.IdForSearch;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class IdCardFragment extends Fragment {
    private static final String TAG = "IdCardFragment";
    private FragmentIdCardBinding binding;
    private CardInfo cardInfo;
    private IdForSearch idforsearch;
    private AnimatedVectorDrawable anim1,anim2,anim3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_id_card, container, false);

        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        cardInfo = new CardInfo();
        binding.setIdresult(cardInfo);
        binding.setButtonclick(new searchClickListener());

        anim1= (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim1);
        anim2= (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim2);
        anim3= (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3);
    }

    private void startRequest() {
        Log.d(TAG, "start request");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API.API + "?key=" + API.IDCARDKEY + "&cardno=" + cardInfo.getIdNum())
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

    private void retrofitStart() {
        Log.d(TAG, "http with retrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServers servers = retrofit.create(RequestServers.class);
        retrofit2.Call<IdForSearch> call = servers.getIdCardInfo(API.IDCARDKEY, binding.txtId.getText().toString());
        call.enqueue(new retrofit2.Callback<IdForSearch>() {
            @Override
            public void onResponse(retrofit2.Call<IdForSearch> call, retrofit2.Response<IdForSearch> response) {
                idforsearch = response.body();
                cardInfo = idforsearch.getResult();
                if (cardInfo != null) {
                    binding.setIdresult(cardInfo);
                    Log.d(TAG, idforsearch.getReason());
                    Toast.makeText(getActivity(), cardInfo.getSex(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<IdForSearch> call, Throwable t) {
                Log.d(TAG, "error occured" + t.toString());
            }
        });
    }

    public class searchClickListener {
        private static final String TAG = "searchClickListener";
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public void onClickButton() {
            Log.d(TAG, "start request" + binding.txtId.getText().toString());
            retrofitStart();
            binding.img.setImageDrawable(anim2);
            anim2.start();
        }
    }
}

