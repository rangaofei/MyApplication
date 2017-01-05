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
import com.saka.myapplication.Models.Joke;
import com.saka.myapplication.Models.SearchItemClick;

/**
 * Created by Administrator on 2017/1/4.
 */

public class JokeRecyclerAdapter extends RecyclerView.Adapter<JokeRecyclerAdapter.JokeHolder> {

    private Joke.Data[] list;
    private Context context;
    private static final String TAG = "Adapter";
    private SearchItemClick searchItemClick;
    private ItemClickListener listener;


    public JokeRecyclerAdapter(Context context, Joke.Data[] list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return JokeHolder.createViewHolder(parent, R.layout.cardview_joke);
    }

    @Override
    public void onBindViewHolder(JokeHolder holder, int position) {
        holder.binding.setVariable(BR.joke, list[position]);
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.length;
    }

    public void refreshData(Joke.Data[] data) {
        this.list = data;
        Log.d(TAG, "list.length=" + list.length);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    static class JokeHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public JokeHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static JokeHolder createViewHolder(ViewGroup container, int layoutId) {
            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(container.getContext()), layoutId, container, false);
            return new JokeHolder(binding);
        }
    }
}



