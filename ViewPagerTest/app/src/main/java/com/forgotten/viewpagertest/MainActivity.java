package com.forgotten.viewpagertest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // ViewPager2控件对象
    private ViewPager2 viewpager;

    // 设置Tab的控件
    private LinearLayout[] tabs = new LinearLayout[4];

    // Tab的4个图像
    private ImageView[] tabImgs = new ImageView[4];
    // 保存当前tab
    private ImageView currentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化Tab
        initTabView();

        // 初始化ViewPager
        initViewPager();
    }

    private void initTabView() {
        // 寻找tab控件ID
        tabs[0] = findViewById(R.id.tab_lt);
        tabs[1] = findViewById(R.id.tab_txl);
        tabs[2] = findViewById(R.id.tab_fx);
        tabs[3] = findViewById(R.id.tab_wd);

        // 寻找tab图片控件ID
        tabImgs[0] = findViewById(R.id.iv_tab_lt);
        tabImgs[1] = findViewById(R.id.iv_tab_txl);
        tabImgs[2] = findViewById(R.id.iv_tab_fx);
        tabImgs[3] = findViewById(R.id.iv_tab_wd);

        // 编写一个点击事件监听
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tab_lt:
                        // 设置第二个参数可以让其不经过中间页面
                        viewpager.setCurrentItem(0,false);
                        break;
                    case R.id.tab_txl:
                        viewpager.setCurrentItem(1,false);

                        break;
                    case R.id.tab_fx:
                        viewpager.setCurrentItem(2,false);

                        break;
                    case R.id.tab_wd:
                        viewpager.setCurrentItem(3,false);

                        break;
                }
            }
        };

        // 对每个tab进行注册点击事件
        for (LinearLayout tab : tabs) {
            tab.setOnClickListener(listener);
        }

        // 设置当前选中tab（默认选中第一个tab）
        currentTab = tabImgs[0];
        // 设置选中
        currentTab.setSelected(true);

    }

    private void initViewPager() {
        // 获取ID赋值
        viewpager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        // fragments
        fragments.add(BlankFragment.newInstance("聊天"));
        fragments.add(BlankFragment.newInstance("通讯录"));
        fragments.add(BlankFragment.newInstance("发现"));
        fragments.add(BlankFragment.newInstance("我"));

        // 实例化 自定义Adapter
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        // 设置Adapter
        viewpager.setAdapter(myFragmentPagerAdapter);
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    /**
     * 随着pager改变更改tab显示
     *
     * @param position pager下标
     */
    private void changeTab(int position) {
        // 设置当前选中tab为不选中
        currentTab.setSelected(false);
        // 设置新的当前tab
        currentTab = tabImgs[position];
        // 选中当前tab
        currentTab.setSelected(true);
    }
}