package com.tandong.smarttvdemo.ui;

import android.os.Bundle;

import com.tandong.smarttvdemo.R;
import com.tandong.smarttvdemo.adapter.HorListAdapter;
import com.tandong.smarttvdemo.base.BaseActivity;
import com.tandong.smarttv.view.AutoHorizontalGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorGridActivity extends BaseActivity {
    @BindView(R.id.horizontalgridview)
    AutoHorizontalGridView horizontalGridView;
    private HorListAdapter listAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_grid);
        ButterKnife.bind(this);
        list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }
        listAdapter = new HorListAdapter(this, R.layout.item_list, list);
        horizontalGridView.setAdapter(listAdapter);
        horizontalGridView.setNumRows(2);
        horizontalGridView.setAnimateChildLayout(true);
    }
}
