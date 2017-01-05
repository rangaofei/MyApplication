package com.saka.myapplication.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by saka on 2017/1/1.
 */

public class ShiningText extends TextView {

    private Paint rectOne;
    private Paint rectTwo;
    private int viewWidth;
    private int viewHeight;
    private Paint textPaint;
    private LinearGradient linearGradient;
    private Matrix gradientMatrix;
    private int translate;

    public ShiningText(Context context) {
        super(context);
    }

    public ShiningText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initViews();
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),rectOne);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,rectTwo);
        canvas.save();
        canvas.translate(20,20);
        super.onDraw(canvas);
        canvas.restore();
        if(gradientMatrix!=null){
            translate+=viewWidth/5;
            if(translate>2*viewWidth){
                translate=-viewWidth;
            }
            gradientMatrix.setTranslate(translate,0);
            linearGradient.setLocalMatrix(gradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(viewWidth==0){
            viewWidth=getMeasuredWidth();
        }
        if(viewWidth>0){
            textPaint=getPaint();
            linearGradient=new LinearGradient(0,0,viewWidth,0,
                    new int[]{Color.BLUE,0xFFFFFFFF,Color.BLACK},null, Shader.TileMode.CLAMP);
            textPaint.setShader(linearGradient);
            gradientMatrix=new Matrix();
        }
    }

    private void initViews(){
        rectOne=new Paint();
        rectOne.setColor(Color.RED);
        rectOne.setStyle(Paint.Style.FILL);
        rectTwo=new Paint();
        rectTwo.setColor(Color.BLUE);
        rectTwo.setStyle(Paint.Style.FILL);
    }
}
