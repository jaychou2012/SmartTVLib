package com.tandong.smarttvdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tandong.smarttvdemo.R;
import com.tandong.smarttvdemo.adapter.ListAdapter;
import com.tandong.smarttvdemo.base.BaseActivity;
import com.tandong.smarttv.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<String> list;
    private GridLayoutManager gridLayoutManager;
    private boolean isScale = false;
    private boolean up = false;
    private boolean scroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }
        listAdapter = new ListAdapter(this, R.layout.item_list, list);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setPreserveFocusAfterLayout(false);
        recyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setWillNotDraw(true);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        // Disable change animation by default on leanback.
        // Change animation will create a new view and cause undesired
        // focus animation between the old view and new view.
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {

            }
        });
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_SETTLING://先执行，后执行onScrolled
                        int fvip = gridLayoutManager.findFirstVisibleItemPosition();//可见范围内的第一项的位置
                        int lvip = gridLayoutManager.findLastVisibleItemPosition();//可见范围内的最后一项的位置
                        int ic = gridLayoutManager.getItemCount();//recyclerview中的item的所有的数目
                        int lcvip = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                        int fcvip = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                        if (up) {
                            System.out.println("recyclerview正在依靠惯性滚动  up   " + System.currentTimeMillis() + "  " + fvip + "   " + lvip + "  " + lcvip + "   " + fcvip);
                            gridLayoutManager.scrollToPosition(gridLayoutManager.findFirstVisibleItemPosition());
//                                gridLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), firstVisibleItemPosition);
                        } else {
                            System.out.println("recyclerview正在依靠惯性滚动  down " + System.currentTimeMillis() + "  " + fvip + "   " + lvip + "  " + lcvip + "   " + fcvip);
                            gridLayoutManager.scrollToPosition(lcvip + 1);
//                                gridLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), lastVisibleItemPosition);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                up = true;
                if (Utils.isQuickClick()) {
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                up = false;
                if (Utils.isQuickClick()) {
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
