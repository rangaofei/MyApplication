package com.saka.myapplication.CustomView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.saka.myapplication.BR;
import com.saka.myapplication.CustomInterface.TabBarClickListener;
import com.saka.myapplication.Models.ClickListener;
import com.saka.myapplication.Models.TabbarMenu;
import com.example.saka.myapplication.R;

/**
 * Created by saka on 2016/12/31.
 */

public class TabBar extends LinearLayout {

    private LayoutInflater layoutInflater;
    private ViewDataBinding dataBinding;
    private ClickListener listener;

    public TabBar(Context context) {
        super(context);
        initViews(context);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        layoutInflater = LayoutInflater.from(context);
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.bar_tab, this, true);
        TabbarMenu menu = new TabbarMenu(getContext());
        dataBinding.setVariable(BR.menu, menu);
        listener=new ClickListener();
        dataBinding.setVariable(BR.clicklistener, listener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 获取属性设置的宽度，当为内容包裹时，最小为200
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 获取高度
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void setTabBarListener(TabBarClickListener listener) {
        this.listener.setOnClickTabListener(listener);
    }
}
