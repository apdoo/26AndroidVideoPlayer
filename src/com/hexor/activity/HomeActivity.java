package com.hexor.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import com.hexor.adapter.GalleryAdapter;
import com.hexor.widget.FlowIndicator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * [首页]
 */
public class HomeActivity extends Activity {
	static final int SCROLL_ACTION = 0;
	ExpandableListView mExpandableListView; //带箭头标记的展开收起的列表 ExpandableListView是一个垂直滚动显示两级列表项的视图，与ListView不同的是，它可以有两层：每一层都能够被独立的展开并显示其子项
	int[] tags = new int[] { 0, 0, 0, 0, 0 };
	//分组
	String[] groups = new String[] { "同步剧场", "奇艺出品", "热播电影", "3月片花速递", "动漫乐园" };
	String[][] childs = new String[5][10];
	Gallery mGallery;//实现多张图片自动循环播放的一个组件
	GalleryAdapter mGalleryAdapter; //Gallery组件的适配器
	FlowIndicator mMyView;
	Timer mTimer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);//设置该活动对应的视图
		prepareView(); //初始化视图
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new MyTask(), 0, 5000);
	}

	/**
	 * 初始化视图
	 */
	private void prepareView() {
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		MyListAdapter adapter = new MyListAdapter(); //创建 mExpandableListView的适配器
		/**
		 * 此处通过LayoutInflater加载布局管理器将header_view.xml的布局转化为view对象被java代码识别
		 */
		View header = LayoutInflater.from(this).inflate(R.layout.header_view,//.from方法加载布局管理器 .inflate方法将xml布局转换为view对象
				null);
		mGallery = (Gallery) header.findViewById(R.id.home_gallery);//利用view对象，找到布局中的图片轮播组件
		mMyView = (FlowIndicator) header.findViewById(R.id.myView); //找到自定义组件
		mGalleryAdapter = new GalleryAdapter(this); //创建图片轮播组件的适配器
		mMyView.setCount(mGalleryAdapter.getCount()); //给自定义组件的属性赋值
		mGallery.setAdapter(mGalleryAdapter);
		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {   //监听图片选中事件
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//设置选中
				mMyView.setSeletion(arg2);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		mExpandableListView.addHeaderView(header);
		mExpandableListView.setAdapter(adapter);
		mExpandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {
					@Override
					public void onGroupExpand(int arg0) {
						// TODO Auto-generated method stub
						tags[arg0] = 1;
					}
				});
		mExpandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					@Override
					public void onGroupCollapse(int arg0) {
						// TODO Auto-generated method stub
						tags[arg0] = 0;
					}
				});
	}

	private class MyTask extends TimerTask {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(SCROLL_ACTION);
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SCROLL_ACTION:
				// int curPos = mGallery.getSelectedItemPosition();
				// if (curPos < mGalleryAdapter.getCount() - 1) {
				// curPos++;
				// } else {
				// curPos = 0;
				// }
				// // mGallery.setLayoutAnimation(new LayoutAnimationController(
				// // AnimationUtils.loadAnimation(HomeActivity.this,
				// // R.anim.gallery_in)));
				// mGallery.setSelection(curPos, true);
				MotionEvent e1 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN,
						89.333336f, 265.33334f, 0);
				MotionEvent e2 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_UP,
						300.0f, 238.00003f, 0);

				mGallery.onFling(e1, e2, -1300, 0);
				break;

			default:
				break;
			}
		}
	};

	/**
	 * 内部类 -ExpandableListView的适配器
	 */
	class MyListAdapter extends BaseExpandableListAdapter {

		public MyListAdapter() {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					childs[i][j] = "child" + i + "_" + j;
				}
			}
		}

		@Override
		public String getChild(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return childs[arg0][arg1];
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
				ViewGroup arg4) {
			// TODO Auto-generated method stub
			if (arg3 == null) {
				arg3 = LayoutInflater.from(HomeActivity.this).inflate(
						R.layout.list_child_item, null);
			}
			return arg3;
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			return 10;
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return groups[arg0];
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return groups.length;
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		class GroupHolder {
			ImageView img;
			TextView title;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			// TODO Auto-generated method stub
			GroupHolder groupHolder;
			if (arg2 == null) {
				arg2 = LayoutInflater.from(HomeActivity.this).inflate(
						R.layout.list_group_item, null);
				groupHolder = new GroupHolder();
				groupHolder.img = (ImageView) arg2.findViewById(R.id.tag_img);
				groupHolder.title = (TextView) arg2
						.findViewById(R.id.title_view);
				arg2.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) arg2.getTag();
			}
			if (tags[arg0] == 0) {
				groupHolder.img
						.setImageResource(R.drawable.list_indecator_button);
			} else {
				groupHolder.img
						.setImageResource(R.drawable.list_indecator_button_down);
			}
			groupHolder.title.setText(groups[arg0]);

			return arg2;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}

	}

}
