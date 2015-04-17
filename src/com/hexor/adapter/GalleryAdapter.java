package com.hexor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.hexor.activity.R;

/**
 * 自定义图片轮播组件的适配器
 * 1、Gallery是Android内置的一个控件，它可以继承若干图片甚至是其他控件
 * 2、Gallery自带了滚动播放图片功能，此功能您可以通过模拟器拖曳鼠标或者在手机上拖拽验证
 *3、Gallery需要适配器来传输数据，如果您不熟悉“适配器设计模式”，可以将适配器理解为某厂商的电脑适配器，只要这个厂商的所有型号的电脑都能使用该适配器，也就是说，设计新型号电脑时，我们为了也能使用这种适配器，只要在接收端实现如何接受电源就可以了，而这种适配器不关心哪种型号的电脑在使用它，它只需提供电源即可。
 */
public class GalleryAdapter extends BaseAdapter {
	Context mContext;  //该值仅仅为了传递Activity
	//存放图片ID的数组，每个ID可以被ImageView所调用，从而显示图片
	int[] res = new int[] { R.drawable.t1, R.drawable.t2,
			R.drawable.t3, R.drawable.t1, R.drawable.t2,
			R.drawable.t3, R.drawable.t1, R.drawable.t2,
			R.drawable.t3 };

	/**
	 * 构造方法
	 * @param cnt
	 */
	public GalleryAdapter(Context cnt) {
		this.mContext = cnt;
	}

	/**
	 * 返回数组的总数
	 * @return
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return res.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (arg1 == null) {
			/**
			 * 此处通过LayoutInflater加载布局管理器将gallery_item.xml的布局转化为view对象被java代码识别
			 */
			arg1 = LayoutInflater.from(mContext).inflate(R.layout.gallery_item,
					null);
		}
		/**
		 * gallery_item.xml中找到ImageView这个组件返回给java代码处理
		 */
		ImageView img = (ImageView) arg1.findViewById(R.id.home_img);
		//给ImageView组件设置新的图片文件
		img.setImageResource(res[arg0]);
		//返回该视图
		return arg1;
	}
}
