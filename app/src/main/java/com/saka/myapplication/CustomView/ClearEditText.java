package com.saka.myapplication.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by saka on 2017/1/2.
 */

public class ClearEditText extends EditText {
    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
