package com.forgotten.viewpagertest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyFragmentPagerAdapter
 * @Description //TODO
 * @Author Palmer
 * @Date 2021/12/11
 **/
public class MyFragmentPagerAdapter extends FragmentStateAdapter {

    /**
     * 存放实例化的fragment列表；方便切换
     */
    List<Fragment> fragmentList = new ArrayList<>();
    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        // 对fragment列表进行赋值
        fragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 返回相应下标的fragment
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        // 返回fragmentList存放的个数
        return fragmentList.size();
    }
}
