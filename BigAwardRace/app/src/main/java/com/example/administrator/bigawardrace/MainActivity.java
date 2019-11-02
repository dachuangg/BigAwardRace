package com.example.administrator.bigawardrace;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.bigawardrace.fragment.DiscoverFragment;
import com.example.administrator.bigawardrace.fragment.EmporiumFragment;
import com.example.administrator.bigawardrace.fragment.HomeFragment;
import com.example.administrator.bigawardrace.fragment.MimeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitle;
    private List<Fragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置初始数据
        initDate();
        //初始化控件
        initView();
    }

    private void initView() {

        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //设置一个适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                //设置选中的item
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                //返回item个数
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                //设置标题
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDate() {

        mTitle = new ArrayList<>();
        mTitle.add("首页");
        mTitle.add("发现");
        mTitle.add("商城");
        mTitle.add("我的");

        //mTabLayout.getTabAt(2).setIcon(R.mipmap.add_else);

        mFragment = new ArrayList<>();
        mFragment.add(new HomeFragment());
        mFragment.add(new DiscoverFragment());
        mFragment.add(new EmporiumFragment());
        mFragment.add(new MimeFragment());
    }
}
