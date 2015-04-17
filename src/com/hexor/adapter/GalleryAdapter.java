package com.hexor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.hexor.activity.R;

/**
 * �Զ���ͼƬ�ֲ������������
 * 1��Gallery��Android���õ�һ���ؼ��������Լ̳�����ͼƬ�����������ؼ�
 * 2��Gallery�Դ��˹�������ͼƬ���ܣ��˹���������ͨ��ģ������ҷ���������ֻ�����ק��֤
 *3��Gallery��Ҫ���������������ݣ����������Ϥ�����������ģʽ�������Խ����������Ϊĳ���̵ĵ�����������ֻҪ������̵������ͺŵĵ��Զ���ʹ�ø���������Ҳ����˵��������ͺŵ���ʱ������Ϊ��Ҳ��ʹ��������������ֻҪ�ڽ��ն�ʵ����ν��ܵ�Դ�Ϳ����ˣ������������������������ͺŵĵ�����ʹ��������ֻ���ṩ��Դ���ɡ�
 */
public class GalleryAdapter extends BaseAdapter {
	Context mContext;  //��ֵ����Ϊ�˴���Activity
	//���ͼƬID�����飬ÿ��ID���Ա�ImageView�����ã��Ӷ���ʾͼƬ
	int[] res = new int[] { R.drawable.t1, R.drawable.t2,
			R.drawable.t3, R.drawable.t1, R.drawable.t2,
			R.drawable.t3, R.drawable.t1, R.drawable.t2,
			R.drawable.t3 };

	/**
	 * ���췽��
	 * @param cnt
	 */
	public GalleryAdapter(Context cnt) {
		this.mContext = cnt;
	}

	/**
	 * �������������
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
			 * �˴�ͨ��LayoutInflater���ز��ֹ�������gallery_item.xml�Ĳ���ת��Ϊview����java����ʶ��
			 */
			arg1 = LayoutInflater.from(mContext).inflate(R.layout.gallery_item,
					null);
		}
		/**
		 * gallery_item.xml���ҵ�ImageView���������ظ�java���봦��
		 */
		ImageView img = (ImageView) arg1.findViewById(R.id.home_img);
		//��ImageView��������µ�ͼƬ�ļ�
		img.setImageResource(res[arg0]);
		//���ظ���ͼ
		return arg1;
	}
}
