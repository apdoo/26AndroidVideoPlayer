package com.hexor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * [����]
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
	 * ��ʼ��header�ı���
	 */
	private void prepareView() {
		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
