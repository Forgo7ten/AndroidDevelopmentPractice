package com.forgotten.materialdesigntest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "detail_name";
    public static final String FRUIT_IMAGE_ID = "detail_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // 获取intent
        Intent intent = getIntent();
        // 读取信息
        String detailName = intent.getStringExtra(FRUIT_NAME);
        int detailImgId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        // 获得控件对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView detailImageView = (ImageView) findViewById(R.id.detail_image_view);
        TextView detailContentText = (TextView) findViewById(R.id.detail_content_text);
        // 将toolbar添加成actionBar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 启用asUp按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // 为toolbar设置标题
        collapsingToolbar.setTitle(detailName);
        // 设置图片
        Glide.with(this).load(detailImgId).into(detailImageView);
        // 取得内容
        String detailContent = generateDetailContent(detailName);
        // 设置内容
        detailContentText.setText(detailContent);
    }

    private String generateDetailContent(String detailName) {

        StringBuilder detailContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            detailContent.append(detailName);
        }
        return detailContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}