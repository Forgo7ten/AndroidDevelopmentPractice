package com.forgotten.viewpagertest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class BlankFragment extends Fragment {

    private static final String ARG_TEXT = "text";

    private String textInfo;

    /**
     * Fragment的根布局
     */
    private View rootView;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String text) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {   // 获取传入的参数
            textInfo = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 当根布局为空的时候才进行获得值
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        initViews();
        return rootView;
    }

    private void initViews() {
        TextView textView = rootView.findViewById(R.id.f_textView);
        // 将传入的textInfo设置进去，演示不同的fragment实例
        textView.setText(textInfo);
    }
}