package com.hexor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * [分类]
 */
public class ChannelActivity extends Activity {
	TextView mTitleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.channel_activity);
		prepareView();
		mTitleView.setText(R.string.category_channel);
	}

	/**
	 * 初始化header的标题
	 */
	private void prepareView() {
		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
