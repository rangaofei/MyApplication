package com.saka.myapplication.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saka.myapplication.R;
import com.saka.myapplication.CustomInterface.TopBarClickListener;

/**
 * Created by saka on 2017/1/1.
 */

public class TopBar extends RelativeLayout {
    private static final String TAG = "topbar";
    private TypedArray ta;
    private Context context;
    private AttributeSet attrs;
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;
    private float leftTextSize;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;
    private float rightTextSize;

    private int titleColor;
    private String titleText;
    private float titleSize;

    private Button leftButton;
    private Button rightButton;
    private TextView title;
    private LayoutParams leftLayoutParams;
    private LayoutParams rightLayoutParams;
    private LayoutParams titleLayoutParams;
    private TopBarClickListener listener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context) {
        super(context);
        this.context = context;
        initViews(context);
        setViews(context);
        setListeners(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        initViews(context);
        setViews(context);
        setListeners(context);
    }

    private void initViews(Context context) {
        ta = getContext().obtainStyledAttributes(attrs, R.styleable.topbar);
        leftText = ta.getString(R.styleable.topbar_leftText);
        leftTextColor = ta.getColor(R.styleable.topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.topbar_leftBackground);
        leftTextSize = ta.getDimension(R.styleable.topbar_leftTextSize, 10);
        rightText = ta.getString(R.styleable.topbar_rightText);
        rightTextColor = ta.getColor(R.styleable.topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.topbar_rightBackground);
        rightTextSize = ta.getDimension(R.styleable.topbar_rightTextSize, 10);
        titleText = ta.getString(R.styleable.topbar_title);
        titleColor = ta.getColor(R.styleable.topbar_titleTextColor, 0);
        titleSize = ta.getDimension(R.styleable.topbar_titleSize, 10);
        ta.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setViews(Context context) {
        leftButton = new Button(getContext());
        rightButton = new Button(getContext());
        title = new TextView(getContext());
        leftButton.setBackground(leftBackground);
        leftButton.setTextColor(leftTextColor);
        leftButton.setTextSize(leftTextSize);
        leftButton.setText(leftText);
        rightButton.setText(rightText);
        rightButton.setTextSize(rightTextSize);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        title.setText(titleText);
        title.setTextColor(titleColor);
        title.setTextSize(titleSize);
        title.setGravity(Gravity.CENTER);
        leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftLayoutParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftLayoutParams);
        rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightLayoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightLayoutParams);
        titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(title, titleLayoutParams);
    }

    private void setListeners(Context context) {
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
                Log.d(TAG, "点击了左键");
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了右键");
                listener.rightClick();
            }
        });
    }

    public void setOnTopBarClickListener(TopBarClickListener listener) {
        this.listener = listener;
    }

    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                leftButton.setVisibility(VISIBLE);
            } else {
                rightButton.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                leftButton.setVisibility(GONE);
            } else {
                rightButton.setVisibility(GONE);
            }
        }
    }

    public void setLeftText(){

    }
}
