package com.tandong.smarttvdemo.adapter;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tandong.smarttvdemo.R;
import com.tandong.smarttv.utils.Utils;
import com.tandong.smarttv.view.ScrollTextView;
import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;

import java.util.List;

/**
 * Created by office on 2018/3/21.
 */

public class ListAdapter extends BaseSmartAdapter<String> {
    private Context context;
    private FrameLayout.LayoutParams layoutParams;
    private int width = 0, height = 0;
    private TimeInterpolator timeInterpolator;

    public ListAdapter(Context context, int layoutId, List<String> lists) {
        super(context, layoutId, lists);
        this.context = context;
                width = (Utils.getScreenWidth(context) - Utils.dp2px(context, 150) - (Utils.getScreenWidth(context) / 10)) / 5;
        height = (int) ((width * 4.0) / 3.0);
        layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height);
        timeInterpolator = new OvershootInterpolator();
    }

    @Override
    public void bindData(final SmarViewHolder smarViewHolder, String s) {
        smarViewHolder.getView(R.id.fl_root).setLayoutParams(layoutParams);
        ((ScrollTextView) smarViewHolder.getView(R.id.tv_text)).setText(smarViewHolder.getLayoutPosition() + "  测试文字测试文字测试文字测试文字测试文字测试文字");
        Glide.with(context).load(R.mipmap.icon_img).into((ImageView) smarViewHolder.getView(R.id.iv_img));
        smarViewHolder.getView(R.id.fl_parent).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smarViewHolder.getView(R.id.fl_parent).animate().setInterpolator(timeInterpolator).scaleX(1.06f).scaleY(1.06f).setDuration(600).start();
                    ((ScrollTextView) smarViewHolder.getView(R.id.tv_text)).startScroll();
                    smarViewHolder.setVisible(R.id.tv_number);
                } else {
                    smarViewHolder.getView(R.id.fl_parent).animate().scaleX(1.0f).scaleY(1.0f).setDuration(600).start();
                    ((ScrollTextView) smarViewHolder.getView(R.id.tv_text)).stopScroll();
                    smarViewHolder.setGone(R.id.tv_number);
                }
            }
        });
    }
}
