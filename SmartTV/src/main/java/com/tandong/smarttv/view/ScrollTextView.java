package com.tandong.smarttv.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.tandong.smarttv.R;

/**
 * Created by office on 2018/3/22.
 */

public class ScrollTextView extends HorizontalScrollView implements Animation.AnimationListener {
    private TextView tv_out;
    private TextView tv_in;
    private View rootView;
    private TranslateAnimation outAnim, inAnim;
    private boolean isChange = false;
    private Runnable runnable;

    public ScrollTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public ScrollTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_scrolltextview, this);
        tv_in = rootView.findViewById(R.id.tv_textin);
        tv_out = rootView.findViewById(R.id.tv_textout);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setFocusable(false);
        setBackgroundColor(getResources().getColor(R.color.gray_alpha));
        clearFocus();
        initInAnimation();
        initOutAnimation();
    }

    private void initOutAnimation() {
        outAnim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        outAnim.setDuration(8000);
        outAnim.setInterpolator(new LinearInterpolator());
        outAnim.setFillAfter(true);
        outAnim.setAnimationListener(this);
    }

    private void initInAnimation() {
        inAnim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        inAnim.setDuration(8000);
        inAnim.setInterpolator(new LinearInterpolator());
        inAnim.setFillAfter(true);
    }

    private void delayInAnim() {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (getWidth() <= tv_out.getWidth()) {
                        tv_in.setVisibility(View.VISIBLE);
                        if (isChange) {
                            tv_in.startAnimation(outAnim);
                            tv_out.startAnimation(inAnim);
                        } else {
                            tv_in.startAnimation(inAnim);
                            tv_out.startAnimation(outAnim);
                        }
                        isChange = !isChange;
                    }
                }
            };
        }
        postDelayed(runnable, 1000);
    }

    private void inAnim() {
        post(runnable);
    }

    public void startScroll() {
        delayInAnim();
    }

    public void stopScroll() {
        stopAnim();
    }

    public void setText(String text) {
        tv_in.setText(text);
        tv_out.setText(text);
    }

    public void setTextStateColor(int color) {
        tv_in.setTextColor(getResources().getColorStateList(color));
        tv_out.setTextColor(getResources().getColorStateList(color));
    }

    public void setBgColor(int color) {
        setBackgroundColor(getResources().getColor(color));
    }

    public void setTextGravity(int gravity) {
        tv_in.setGravity(gravity);
        tv_out.setGravity(gravity);
    }

    public void setTextColor(int color) {
        tv_in.setTextColor(getResources().getColor(color));
        tv_out.setTextColor(getResources().getColor(color));
    }

    public void setTextSize(float size) {
        tv_in.setTextSize(size);
        tv_out.setTextSize(size);
    }

    public void setFocus(boolean focus) {
        if (focus) {
            tv_in.requestFocus();
            tv_out.requestFocus();
        } else {
            tv_in.clearFocus();
            tv_out.clearFocus();
        }
    }

    private void stopAnim() {
        isChange = false;
        removeCallbacks(runnable);
        runnable = null;
        tv_in.setVisibility(View.GONE);
        inAnim.cancel();
        outAnim.cancel();
        tv_in.clearAnimation();
        tv_out.clearAnimation();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        removeCallbacks(runnable);
        inAnim();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
