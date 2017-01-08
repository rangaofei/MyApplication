package com.saka.myapplication.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentPhoneBinding;
import com.saka.myapplication.BaseComponent.API;
import com.saka.myapplication.BaseComponent.BaseFragment;
import com.saka.myapplication.Models.BaseObserver;
import com.saka.myapplication.Models.PhoneModel;
import com.saka.myapplication.HttpUtil.RetroFactory;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneFragment extends BaseFragment {
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
        Observable observable = RetroFactory.getInstance().getPhoneInfo(phonenum, API.PHONEKEY);
        observable.compose(composeFunction).subscribe(new BaseObserver<PhoneModel.PhoneResult>(getActivity()) {
            @Override
            public void onHandleSucess(PhoneModel.PhoneResult phoneResult) {
                Log.d(TAG, "成功" + phoneResult.toString());
                binding.setPhonemodel(phoneResult);
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
