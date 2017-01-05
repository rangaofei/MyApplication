package com.saka.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.ActivitySingleFragmentBinding;
import com.saka.myapplication.BaseComponent.BaseActivity;
import com.saka.myapplication.CustomInterface.TopBarClickListener;
import com.saka.myapplication.Fragment.HistoryFragment;
import com.saka.myapplication.Fragment.IdCardFragment;
import com.saka.myapplication.Fragment.JokeFragment;
import com.saka.myapplication.Fragment.PhoneFragment;

public class SingleFragmentActivity extends BaseActivity implements
        TopBarClickListener {
    private static final String TAG = "SingleFragmentActivity";
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ActivitySingleFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        initTopBar();
        initViews();
    }

    private void initViews() {
        fm = getSupportFragmentManager();
        int resId = getIntent().getIntExtra("targetfragment", 0);
        Log.d(TAG, "resId=" + resId);
        switch (resId) {
            case R.string.idcard:
                Log.d(TAG, "加载身份证查询");
                IdCardFragment fragment = new IdCardFragment();
                addFragment(fragment);
                break;
            case R.string.phonenum:
                addFragment(new PhoneFragment());
                break;
            case R.string.history:
                Log.d(TAG,"加载离殇上的今天");
                addFragment(new HistoryFragment());
                break;
            case R.string.tabbar_joke:
                JokeFragment jokeFragment = new JokeFragment();
                addFragment(jokeFragment);
                break;
        }
    }

    private void initTopBar() {
        binding.topbarSingle.setOnTopBarClickListener(this);
    }

    private void addFragment(Fragment fragment) {
        ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.fragment_container_singleactivity, fragment).commitNowAllowingStateLoss();
    }

    @Override
    public void leftClick() {
        this.finish();
    }

    @Override
    public void rightClick() {

    }
}
