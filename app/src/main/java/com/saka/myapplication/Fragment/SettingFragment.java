package com.saka.myapplication.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentSettingBinding;
import com.saka.myapplication.BaseComponent.BaseFragment;

public class SettingFragment extends BaseFragment {

    private FragmentSettingBinding binding;


    public SettingFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_setting, container, false);
        return binding.getRoot();
    }



}
