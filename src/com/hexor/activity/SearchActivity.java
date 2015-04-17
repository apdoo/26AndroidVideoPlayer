package com.hexor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import com.hexor.adapter.GridAdapter;

/**
 * [搜索]
 */
public class SearchActivity extends Activity {
	GridView mHotGridView, mHistoryGridView; //GridView 网格视图 可用来实现九宫格

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		prepareView();
	}

	private void prepareView() {
		mHotGridView = (GridView) findViewById(R.id.hot_search_grid);
		mHotGridView.setAdapter(new GridAdapter(this)); //设置初始数据
		mHistoryGridView = (GridView) findViewById(R.id.history_search_grid);
		mHistoryGridView.setAdapter(new GridAdapter(this));
	}
}
