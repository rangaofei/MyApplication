package com.saka.myapplication.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentPhoneBinding;
import com.saka.myapplication.BaseComponent.API;
import com.saka.myapplication.HttpUtil.RequestServers;
import com.saka.myapplication.Models.PhoneModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneFragment extends Fragment {
    public static final String TAG = "phonefragment";
    private FragmentPhoneBinding binding;
    private PhoneModel.PhoneResult phoneModel;

    public PhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone, container, false);
        binding.setPhonemodel(phoneModel);
        binding.setClickevent(new ClickEvent());
        return binding.getRoot();
    }

    public void getRusult(final String phonenum) {
        Log.d(TAG, "start with retrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestServers requestServers = retrofit.create(RequestServers.class);
        Call<PhoneModel> call = requestServers.getPhoneInfo(phonenum, API.PHONEKEY);
        call.enqueue(new Callback<PhoneModel>() {
            @Override
            public void onResponse(Call<PhoneModel> call, Response<PhoneModel> response) {
                PhoneModel phone = response.body();
                Log.d(TAG, phone.toString());
                phoneModel = phone.getResult();
                if (phoneModel != null) {
                    Log.d(TAG, phoneModel.toString());
                    binding.setPhonemodel(phoneModel);
                }else{
                    Toast.makeText(getActivity(), phone.getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhoneModel> call, Throwable t) {

            }
        });
    }

    public class ClickEvent {
        public void onCLickButton() {
            String phonenum = binding.etPhone.getText().toString();
            Log.d(TAG, "开始查询---" + phonenum);
            getRusult(phonenum);
        }
    }

}
