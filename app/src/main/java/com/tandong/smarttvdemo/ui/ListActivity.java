package com.tandong.smarttvdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tandong.smarttvdemo.R;
import com.tandong.smarttvdemo.adapter.LeftAdapter;
import com.tandong.smarttvdemo.adapter.ListAdapter;
import com.tandong.smarttvdemo.base.BaseActivity;
import com.tandong.smarttv.view.AutoVerticalGridView;
import com.tandong.smarttv.view.BaseGridView;
import com.tandong.smarttv.view.OnChildSelectedListener;
import com.tandong.smarttv.view.OnChildViewHolderSelectedListener;
import com.tandong.smarttv.view.ScrollTextView;
import com.whatjay.recyclerview.adapter.BaseSmartAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity {
    @BindView(R.id.avgv)
    AutoVerticalGridView autoVerticalGridView;
    @BindView(R.id.avgv_list)
    AutoVerticalGridView autoVerticalGridViewList;
    private ListAdapter listAdapter;
    private LeftAdapter leftAdapter;
    private List<String> list;
    private List<String> leftList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        leftList = new ArrayList<String>();
        list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            leftList.add("左侧分类菜单");
        }
        for (int i = 0; i < 100; i++) {
            list.add("左侧分类菜单");
        }
        listAdapter = new ListAdapter(this, R.layout.item_list, list);
        autoVerticalGridViewList.requestFocus();
        autoVerticalGridViewList.setNumColumns(5);
        autoVerticalGridViewList.setAdapter(listAdapter);
        autoVerticalGridViewList.setFocusScrollStrategy(BaseGridView.FOCUS_SCROLL_ITEM);
        leftAdapter = new LeftAdapter(this, R.layout.item_left, leftList);
        autoVerticalGridView.setNumColumns(1);
        autoVerticalGridView.setAdapter(leftAdapter);
        autoVerticalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                System.out.println("选中：onChildSelected " + position);

            }
        });
        autoVerticalGridView.setOnChildViewHolderSelectedListener(new OnChildViewHolderSelectedListener() {
            @Override
            public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelected(parent, child, position, subposition);
                System.out.println("选中：onChildViewHolderSelected " + position + "  " + subposition);

            }

            @Override
            public void onChildViewHolderSelectedAndPositioned(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelectedAndPositioned(parent, child, position, subposition);
                System.out.println("选中：onChildViewHolderSelectedAndPositioned " + position + "  " + subposition);
                ((ScrollTextView) child.itemView.findViewById(R.id.stv)).setTextColor(R.color.white);
            }
        });
        listAdapter.setOnItemClickListener(new BaseSmartAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ListActivity.this, HorGridActivity.class);
                startActivity(intent);
            }
        });
    }
}
