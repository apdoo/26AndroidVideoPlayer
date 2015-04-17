package com.hexor.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * �������
 * �̳���TabActivity
 */
public class MainActivity  extends TabActivity implements View.OnClickListener {
    public static String TAB_TAG_HOME = "home";
    public static String TAB_TAG_CHANNEL = "channel";
    public static String TAB_TAG_ACCOUNT = "account";
    public static String TAB_TAG_SEARCH = "search";
    public static String TAB_TAB_MORE = "more";
    public static TabHost mTabHost;//��ǩ�����ֻ��Ҫ��ʼ��һ��ͺ���
    static final int COLOR1 = Color.parseColor("#787878");
    static final int COLOR2 = Color.parseColor("#ffffff");
    ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
    TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;

    Intent mHomeItent, mChannelIntent, mSearchIntent, mAccountIntent,
            mMoreIntent;

    int mCurTabId = R.id.channel1;//��ǰ����ı�ǩҳ��ҳ Ĭ��Ϊ��ҳ

    // Animation ����Ч��
    private Animation left_in, left_out;
    private Animation right_in, right_out;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر���
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        prepareAnim();//��ʼ������
        prepareIntent();//��ʼ��ָ��activity
        setupIntent();
        prepareView();
    }
    /**
     * ��ʼ������
     */
    private void prepareAnim() {
        left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
        left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
        right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
        right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
    }

    /**
     * ��ʼ����ͼ
     */
    private void prepareView() {
        mBut1 = (ImageView) findViewById(R.id.imageView1);
        mBut2 = (ImageView) findViewById(R.id.imageView2);
        mBut3 = (ImageView) findViewById(R.id.imageView3);
        mBut4 = (ImageView) findViewById(R.id.imageView4);
        mBut5 = (ImageView) findViewById(R.id.imageView5);
        findViewById(R.id.channel1).setOnClickListener(this);
        findViewById(R.id.channel2).setOnClickListener(this);
        findViewById(R.id.channel3).setOnClickListener(this);
        findViewById(R.id.channel4).setOnClickListener(this);
        findViewById(R.id.channel5).setOnClickListener(this);
        mCateText1 = (TextView) findViewById(R.id.textView1);
        mCateText2 = (TextView) findViewById(R.id.textView2);
        mCateText3 = (TextView) findViewById(R.id.textView3);
        mCateText4 = (TextView) findViewById(R.id.textView4);
        mCateText5 = (TextView) findViewById(R.id.textView5);
    }

    /**
     * ��ʼ��ָ��activity
     */
    private void prepareIntent() {
        mHomeItent = new Intent(this, HomeActivity.class);
        mChannelIntent = new Intent(this, ChannelActivity.class);
        mAccountIntent = new Intent(this, AccountActivity.class);
        mSearchIntent = new Intent(this, SearchActivity.class);
        mMoreIntent = new Intent(this, MoreActivity.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mBut1.performClick();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * ��ʼ����ǩҳָ��activity
     */
    private void setupIntent() {
        mTabHost = getTabHost();//�̳�TabActivity����TabActivity����getTabHost()������ȡTabHost��ֻҪ�������Tab���ݲ��־�����
        //Ϊ��ǩtab���ӱ�ǩҳ
        mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.category_home,
                R.drawable.icon_1_n, mHomeItent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_CHANNEL,
                R.string.category_channel, R.drawable.icon_2_n, mChannelIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.category_search,
                R.drawable.icon_3_n, mSearchIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAG_ACCOUNT,
                R.string.category_account, R.drawable.icon_4_n, mAccountIntent));
        mTabHost.addTab(buildTabSpec(TAB_TAB_MORE, R.string.category_more,
                R.drawable.icon_5_n, mMoreIntent));
    }

    /**
     * ����������tab��ǩ��ҳ
     * @param tag  ��ǩҳ�ı�ǩ
     * @param resLabel ��ǩҳ��ʾ����
     * @param resIcon ��ǩҳ��Ӧͼ��
     * @param content ��ǩҳָ��activity
     * @return
     */
    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
                                         final Intent content) {
        return mTabHost
                .newTabSpec(tag)
                .setIndicator(getString(resLabel),
                        getResources().getDrawable(resIcon))
                .setContent(content);
    }

    /**
     * ���õ�ǰ�����tab��ǩ��ҳ
     * @param tab
     */
    public static void setCurrentTabByTag(String tab) {
        mTabHost.setCurrentTabByTag(tab);
    }

    /**
     * ��������¼�
     * ���﴿���Ǹ���ѡ��ǩ����ҳ����ʽ�뽥�뽥���Ķ���Ч��
     * ���ʼ���Ѿ���ʼ���˱�ǩ������ҳ��Intent
     * @param v
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (mCurTabId == v.getId()) {
            return;//�����ǰ��������Ѽ���ҳ �����κβ��� ֱ�ӷ���
        }
        //��tab��������ҳ��ʽȫ������Ϊδ������ʽ
        mBut1.setImageResource(R.drawable.icon_1_n);
        mBut2.setImageResource(R.drawable.icon_2_n);
        mBut3.setImageResource(R.drawable.icon_3_n);
        mBut4.setImageResource(R.drawable.icon_4_n);
        mBut5.setImageResource(R.drawable.icon_5_n);
        mCateText1.setTextColor(COLOR1);
        mCateText2.setTextColor(COLOR1);
        mCateText3.setTextColor(COLOR1);
        mCateText4.setTextColor(COLOR1);
        mCateText5.setTextColor(COLOR1);
        int checkedId = v.getId();//��õ����id
        final boolean o;
        if (mCurTabId < checkedId)
            o = true;//�����ǰ����ҳ��idС�ڵ�ǰ�����,��Ϊtrue
        else
            o = false;//����Ϊflas
        //��������Ŀ����Ϊ���жϵ�ǰ�������ҳ�뵱ǰ�������ҳ�����λ�ã��Ӷ�ȷ�������ĸ�����Ч��
        if (o)
            mTabHost.getCurrentView().startAnimation(left_out); //�������
        else
            mTabHost.getCurrentView().startAnimation(right_out);//�ֳ�����
        switch (checkedId) {  	//����ѡ��id ����ѡ�е���ʽ
            case R.id.channel1:
                mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
                mBut1.setImageResource(R.drawable.icon_1_c);
                mCateText1.setTextColor(COLOR2);
                break;
            case R.id.channel2:
                mTabHost.setCurrentTabByTag(TAB_TAG_CHANNEL);
                mBut2.setImageResource(R.drawable.icon_2_c);
                mCateText2.setTextColor(COLOR2);
                break;
            case R.id.channel3:
                mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
                mBut3.setImageResource(R.drawable.icon_3_c);
                mCateText3.setTextColor(COLOR2);
                break;
            case R.id.channel4:
                mTabHost.setCurrentTabByTag(TAB_TAG_ACCOUNT);
                mBut4.setImageResource(R.drawable.icon_4_c);
                mCateText4.setTextColor(COLOR2);
                break;
            case R.id.channel5:
                mTabHost.setCurrentTabByTag(TAB_TAB_MORE);
                mBut5.setImageResource(R.drawable.icon_5_c);
                mCateText5.setTextColor(COLOR2);
                break;
            default:
                break;
        }

        if (o)
            mTabHost.getCurrentView().startAnimation(left_in);
        else
            mTabHost.getCurrentView().startAnimation(right_in);
        mCurTabId = checkedId;//���õ�ǰѡ��id
    }
}
