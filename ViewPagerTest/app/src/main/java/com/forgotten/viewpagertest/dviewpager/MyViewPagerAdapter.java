package com.forgotten.viewpagertest.dviewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forgotten.viewpagertest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyViewPagerAdapter
 * @Description //TODO
 * @Author Palmer
 * @Date 2021/12/12
 **/
public class MyViewPagerAdapter extends RecyclerView.Adapter<MyViewPagerAdapter.ViewPagerViewHolder> {
    // 给每个布局Text设置的标题
    private List<String> titles = new ArrayList<>();

    public MyViewPagerAdapter() {
        // 也可以构造方法传入titles
        titles.add("第一");
        titles.add("第二");
        titles.add("第三");
        titles.add("第四");
        titles.add("第五");

    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载布局，新建一个自定义ViewHolder
        ViewPagerViewHolder viewPagerViewHolder = new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewpager, parent, false));
        return viewPagerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        // 当选中某个Pager页时候，进行的操作（给Text设置相应的文字
        holder.tvPager.setText(titles.get(position));
    }


    @Override
    public int getItemCount() {
        // 这里返回数据源的长度
        return 5;
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView tvPager;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            // 获取控件ID
            tvPager = itemView.findViewById(R.id.tv_pager);
        }
    }
}
