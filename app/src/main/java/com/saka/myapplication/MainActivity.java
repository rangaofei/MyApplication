package com.saka.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.ActivityMainBinding;
import com.saka.myapplication.BaseComponent.BaseActivity;
import com.saka.myapplication.CustomInterface.TabBarClickListener;
import com.saka.myapplication.CustomInterface.TopBarClickListener;
import com.saka.myapplication.Fragment.JokeFragment;
import com.saka.myapplication.Fragment.NewsFragment;
import com.saka.myapplication.Fragment.SearchFragment;
import com.saka.myapplication.Fragment.SettingFragment;

public class MainActivity extends BaseActivity implements
        TopBarClickListener, TabBarClickListener {

    private ActivityMainBinding binding;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private static final String TAG = "mainactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initTopBar();
        initFragment();
        initTabBar();
    }

    private void initTopBar() {
        binding.topbar.setOnTopBarClickListener(this);
        binding.topbar.setButtonVisable(0, true);
        binding.topbar.setButtonVisable(1, false);
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        SearchFragment fragment = new SearchFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, fragment).commit();
    }

    private void initTabBar() {
        binding.tabBar.setTabBarListener(this);
    }

    @Override
    public void leftClick() {
        Toast.makeText(this, "点击了左键", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightClick() {
        Toast.makeText(this, "点击了右键", Toast.LENGTH_SHORT).show();
    }

    private void addFragmentWithAmatied(Fragment fragment) {
        ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.fragment_container, fragment).commitNowAllowingStateLoss();
    }

    @Override
    public void onClickTab(String string) {
        Log.d(TAG, string+getCurrentFragment());
        if (string.equals("新闻")) {
            if (!getCurrentFragment().equals("NewsFragment")) {
                addFragmentWithAmatied(new NewsFragment());
            }
            return;
        }
        if (string.equals("查询")) {
            if (!getCurrentFragment().equals("SearchFragment")) {
                addFragmentWithAmatied(new SearchFragment());
            }
            return;
        }
        if (string.equals("设置")) {
            if (!getCurrentFragment().equals("SettingFragment")) {
                addFragmentWithAmatied(new SettingFragment());
            }
            return;
        }
        if (string.equals("笑话")) {
            if (!getCurrentFragment().equals("JokeFragment")) {
                addFragmentWithAmatied(new JokeFragment());
            }
            return;
        }
    }

    private String getCurrentFragment() {
        String currentFragment = fm.findFragmentById(R.id.fragment_container).getClass().getSimpleName();
        return currentFragment;
    }
}
