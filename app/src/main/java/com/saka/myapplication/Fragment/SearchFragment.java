package com.saka.myapplication.Fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.FragmentSearchBinding;
import com.saka.myapplication.BaseComponent.BaseFragment;
import com.saka.myapplication.CameraActivity;
import com.saka.myapplication.CustomAdapter.SearchRecyclerAdapter;
import com.saka.myapplication.CustomInterface.ItemClickListener;
import com.saka.myapplication.Models.SearchButton;
import com.saka.myapplication.Models.SearchItem;
import com.saka.myapplication.SingleFragmentActivity;
import com.saka.myapplication.animator.NoAlphaItemAnimator;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements ItemClickListener {

    private FragmentSearchBinding binding;
    private List<SearchItem> list;
    private SearchRecyclerAdapter adapter;
    private static final String TAG = "searchfragment";

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setSerachbutton(new SearchButton());
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        initViews();
        adapter = new SearchRecyclerAdapter(getActivity(), list);
        adapter.setItemClickListener(this);
        binding.rvSearch.setAdapter(adapter);
        binding.rvSearch.setItemAnimator(new NoAlphaItemAnimator());
        return binding.getRoot();
    }

    private void initViews() {
        list = new ArrayList<>();
        list.add(new SearchItem(R.mipmap.ic_launcher, R.string.idcard, false));
        list.add(new SearchItem(R.mipmap.ic_launcher, R.string.phonenum, false));
        list.add(new SearchItem(R.mipmap.ic_launcher, R.string.history, false));
        list.add(new SearchItem(R.mipmap.ic_launcher, R.string.camera, false));
    }


    @Override
    public void onItemButtonClick(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
        switch (resId) {
            case R.string.idcard:
                startTargetFragment(resId);
                break;
            case R.string.phonenum:
                startTargetFragment(resId);
                break;
            case R.string.tabbar_joke:
                startTargetFragment(resId);
                break;
            case R.string.history:
                startTargetFragment(resId);
                break;
            case R.string.camera:
                Intent intent=new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void startTargetFragment(int resId) {
        Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
        intent.putExtra("targetfragment", resId);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int resId) {
        Log.d(TAG, "点击了隐藏---" + resId);
        if (list.get(resId).getViewState() == View.GONE) {
            Log.d(TAG, "显示");
            list.get(resId).setViewState(View.VISIBLE);
            adapter.notifyItemChanged(resId);
        } else {
            Log.d(TAG, "隐藏");
            list.get(resId).setViewState(View.GONE);
            adapter.notifyItemChanged(resId);
        }

    }
}
