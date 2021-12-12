package com.forgotten.materialdesigntest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.forgotten.materialdesigntest.entity.ShowImg;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 侧滑菜单
    private DrawerLayout mDrawerLayout;

    // 数据源
    private List<ShowImg> showimgList;
    // 列表adapter
    private ShowImgAdapter showImgAdapter;
    // 刷新组件
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFab();
        initCard();
        initRefresh();
    }

    private void initRefresh() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.purple_500);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.
                OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
    }

    private void refreshList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initList();
                        showImgAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    private void initCard() {
        showimgList = new ArrayList<>();
        initList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        showImgAdapter = new ShowImgAdapter(showimgList);
        recyclerView.setAdapter(showImgAdapter);
    }

    private void initList() {
        ShowImg[] imgs = {new ShowImg("图片1", R.drawable.img_1), new ShowImg("图片2", R.drawable.img_2), new ShowImg("图片3", R.drawable.img_3), new ShowImg("图片4", R.drawable.img_4), new ShowImg("图片5", R.drawable.img_5), new ShowImg("图片6", R.drawable.img_6), new ShowImg("图片7", R.drawable.img_7), new ShowImg("图片8", R.drawable.img_8), new ShowImg("图片9", R.drawable.img_9), new ShowImg("图片10", R.drawable.img_10), new ShowImg("图片11", R.drawable.img_11), new ShowImg("图片12", R.drawable.img_12), new ShowImg("图片13", R.drawable.img_13), new ShowImg("图片14", R.drawable.img_14), new ShowImg("图片15", R.drawable.img_15), new ShowImg("图片16", R.drawable.img_16), new ShowImg("图片17", R.drawable.img_17), new ShowImg("图片18", R.drawable.img_18), new ShowImg("图片19", R.drawable.img_19), new ShowImg("图片20", R.drawable.img_20)};
        Random random = new Random();
        showimgList.clear();
        for (int i = 0; i < 50; i++) {
            showimgList.add(imgs[random.nextInt(imgs.length)]);
        }
    }

    private void initFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出一条提示
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
                        // 设置额外的行动（undo按钮）和点击按钮后的点击事件
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        // 展示出来
                        .show();
            }
        });
    }

    private void initToolbar() {
        // 获取Toobar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 设置为ActionBar
        setSupportActionBar(toolbar);

        // 找到侧滑菜单DrawerLayout实例
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 获得actionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // setDisplayHomeAsUpEnabled()方法让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // 获取nav导航控件
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        // 设置默认选中
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // 在这里编写菜单选中事件
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "You clicked Nav_call", Toast.LENGTH_SHORT).
                                show();
                        break;
                    case R.id.nav_shopping_cart:
                        Toast.makeText(MainActivity.this, "You clicked Nav_shopping_cart", Toast.LENGTH_SHORT).
                                show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this, "You clicked Nav_location", Toast.LENGTH_SHORT).
                                show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "You clicked Nav_mail", Toast.LENGTH_SHORT).
                                show();
                        break;
                    default:
                }
                // 关闭导航页面
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).
                        show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).
                        show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).
                        show();
                break;
            case android.R.id.home:
                // 将滑动菜单展示出来
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}