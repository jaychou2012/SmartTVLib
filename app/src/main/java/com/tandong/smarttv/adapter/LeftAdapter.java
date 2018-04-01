package com.tandong.smarttv.adapter;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import com.tandong.smarttv.R;
import com.tandong.smarttv.view.ScrollTextView;
import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;

import java.util.List;

/**
 * Created by office on 2018/3/21.
 */

public class LeftAdapter extends BaseSmartAdapter<String> {
    private Context context;
    private FrameLayout.LayoutParams layoutParams;
    private int width = 0, height = 0;
    private TimeInterpolator timeInterpolator;
    private TimeInterpolator linearInterpolator;

    public LeftAdapter(Context context, int layoutId, List<String> lists) {
        super(context, layoutId, lists);
        this.context = context;
        timeInterpolator = new OvershootInterpolator();
        linearInterpolator = new LinearInterpolator();
    }

    @Override
    public void bindData(final SmarViewHolder smarViewHolder, String s) {
        ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setTextStateColor(R.color.menu_selector);
        ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setTextGravity(Gravity.CENTER);
        ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setTextSize(20);
        ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setText(smarViewHolder.getLayoutPosition() + "菜单菜单菜单菜单菜单");
        smarViewHolder.getView(R.id.fl_parent).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smarViewHolder.getView(R.id.fl_parent).animate().scaleX(1.02f).scaleY(1.02f).setDuration(500).start();
                    ((ScrollTextView) smarViewHolder.getView(R.id.stv)).startScroll();
                    ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setTextColor(R.color.white);
                } else {
                    smarViewHolder.getView(R.id.fl_parent).animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
                    ((ScrollTextView) smarViewHolder.getView(R.id.stv)).stopScroll();
                    ((ScrollTextView) smarViewHolder.getView(R.id.stv)).setTextColor(R.color.green);
                }
            }
        });
    }
}
