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
 * 程序主活动
 * 继承了TabActivity
 */
public class MainActivity  extends TabActivity implements View.OnClickListener {
    public static String TAB_TAG_HOME = "home";
    public static String TAB_TAG_CHANNEL = "channel";
    public static String TAB_TAG_ACCOUNT = "account";
    public static String TAB_TAG_SEARCH = "search";
    public static String TAB_TAB_MORE = "more";
    public static TabHost mTabHost;//标签组件。只需要初始化一遍就好了
    static final int COLOR1 = Color.parseColor("#787878");
    static final int COLOR2 = Color.parseColor("#ffffff");
    ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
    TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;

    Intent mHomeItent, mChannelIntent, mSearchIntent, mAccountIntent,
            mMoreIntent;

    int mCurTabId = R.id.channel1;//当前激活的标签页子页 默认为首页

    // Animation 动画效果
    private Animation left_in, left_out;
    private Animation right_in, right_out;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        prepareAnim();//初始化动画
        prepareIntent();//初始化指向activity
        setupIntent();
        prepareView();
    }
    /**
     * 初始化动画
     */
    private void prepareAnim() {
        left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
        left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
        right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
        right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
    }

    /**
     * 初始化视图
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
     * 初始化指向activity
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
     * 初始化标签页指向activity
     */
    private void setupIntent() {
        mTabHost = getTabHost();//继承TabActivity，从TabActivity中用getTabHost()方法获取TabHost。只要定义具体Tab内容布局就行了
        //为标签tab增加标签页
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
     * 创建并返回tab标签子页
     * @param tag  标签页的标签
     * @param resLabel 标签页显示名字
     * @param resIcon 标签页对应图标
     * @param content 标签页指向activity
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
     * 设置当前激活的tab标签子页
     * @param tab
     */
    public static void setCurrentTabByTag(String tab) {
        mTabHost.setCurrentTabByTag(tab);
    }

    /**
     * 监听点击事件
     * 这里纯粹是更改选标签中子页的样式与渐入渐出的动画效果
     * 在最开始就已经初始化了标签各个子页的Intent
     * @param v
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (mCurTabId == v.getId()) {
            return;//如果当前点击的是已激活页 则不做任何操作 直接返回
        }
        //将tab的所有子页样式全部更改为未激活样式
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
        int checkedId = v.getId();//获得点击的id
        final boolean o;
        if (mCurTabId < checkedId)
            o = true;//如果当前激活页的id小于当前点击的,则为true
        else
            o = false;//否则为flas
        //这样做的目的是为了判断当前激活的子页与当前点击的子页的相对位置，从而确定调用哪个动画效果
        if (o)
            mTabHost.getCurrentView().startAnimation(left_out); //左出动画
        else
            mTabHost.getCurrentView().startAnimation(right_out);//又出动画
        switch (checkedId) {  	//根据选中id 更改选中的样式
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
        mCurTabId = checkedId;//设置当前选中id
    }
}
