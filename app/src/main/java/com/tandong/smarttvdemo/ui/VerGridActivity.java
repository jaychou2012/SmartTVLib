package com.tandong.smarttvdemo.ui;

import android.os.Bundle;

import com.tandong.smarttvdemo.R;
import com.tandong.smarttvdemo.adapter.ListAdapter;
import com.tandong.smarttvdemo.base.BaseActivity;
import com.tandong.smarttv.view.AutoVerticalGridView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerGridActivity extends BaseActivity {
    @BindView(R.id.verticalgridview)
    AutoVerticalGridView verticalGridView;
    private ListAdapter listAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_grid);
        ButterKnife.bind(this);
        list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }
        listAdapter = new ListAdapter(this, R.layout.item_list, list);
        verticalGridView.requestFocus();
        verticalGridView.setNumColumns(5);
        verticalGridView.setAdapter(listAdapter);
    }
}
