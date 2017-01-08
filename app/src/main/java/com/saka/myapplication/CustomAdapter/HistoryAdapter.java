package com.saka.myapplication.CustomAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.saka.myapplication.R;
import com.saka.myapplication.CustomInterface.ItemClickListener;
import com.saka.myapplication.Models.HistoryModle;
import com.saka.myapplication.Models.SearchItemClick;

/**
 * Created by Administrator on 2017/1/5.
 */


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private HistoryModle.HistoryResult[] historyResults;
    private Context context;
    private static final String TAG = "Adapter";
    private SearchItemClick searchItemClick;
    private ItemClickListener listener;


    public HistoryAdapter(Context context, HistoryModle.HistoryResult[] list) {
        this.historyResults = list;
        this.context = context;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HistoryHolder.createViewHolder(parent, R.layout.cardview_history);
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        holder.binding.setVariable(BR.historymodel, historyResults[position]);
        searchItemClick=new SearchItemClick(position);
        searchItemClick.setListener(listener);

        holder.binding.setVariable(BR.historyclickevent,searchItemClick);
        holder.binding.executePendingBindings();

    }


    @Override
    public int getItemCount() {
        return historyResults == null ? 0 : historyResults.length;
    }

    public void refreshData(HistoryModle.HistoryResult[] data) {
        this.historyResults = data;
        Log.d(TAG, "list.length=" + historyResults.length);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    static class HistoryHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        public HistoryHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static HistoryHolder createViewHolder(ViewGroup container, int layoutId) {
            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(container.getContext()), layoutId, container, false);
            return new HistoryHolder(binding);
        }
    }
}

