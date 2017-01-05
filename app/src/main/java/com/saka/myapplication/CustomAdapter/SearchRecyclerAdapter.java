package com.saka.myapplication.CustomAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.saka.myapplication.R;
import com.saka.myapplication.CustomInterface.ItemClickListener;
import com.saka.myapplication.Models.SearchItem;
import com.saka.myapplication.Models.SearchItemClick;

import java.util.List;

/**
 * Created by saka on 2017/1/2.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchHolder> {

    private List<SearchItem> list;
    private Context context;
    private static final String TAG = "Adapter";
    private SearchItemClick searchItemClick;
    private ItemClickListener listener;


    public SearchRecyclerAdapter(Context context, List<SearchItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchHolder.createViewHolder(parent, R.layout.carview_search);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.binding.setVariable(BR.searchitem, list.get(position));
        searchItemClick = new SearchItemClick(position);
        searchItemClick.setListener(listener);
        holder.binding.setVariable(BR.buttonclick, searchItemClick);
        holder.binding.executePendingBindings();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    Log.d(TAG, String.valueOf(v.getId()));
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<SearchItem> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    static class SearchHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public SearchHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static SearchHolder createViewHolder(ViewGroup container, int layoutId) {
            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(container.getContext()), layoutId, container, false);
            return new SearchHolder(binding);
        }
    }

}


