package com.tandong.smarttv.ui;

import android.os.Bundle;

import com.tandong.smarttv.R;
import com.tandong.smarttv.adapter.ListAdapter;
import com.tandong.smarttv.base.BaseActivity;
import com.tandong.smarttv.view.AutoVerticalGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerGridActivity extends BaseActivity {
    @Bind(R.id.verticalgridview)
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
