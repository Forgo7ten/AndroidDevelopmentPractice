package com.forgotten.materialdesigntest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.forgotten.materialdesigntest.entity.ShowImg;

import java.util.List;

/**
 * @ClassName ShowImgAdapter
 * @Description //TODO
 * @Author Palmer
 * @Date 2021/12/12
 **/
public class ShowImgAdapter extends RecyclerView.Adapter<ShowImgAdapter.ViewHolder> {
    private Context mContext;
    private List<ShowImg> imgList;

    public ShowImgAdapter(List<ShowImg> imgList) {
        this.imgList = imgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_showimg,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                ShowImg fruit = imgList.get(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(DetailActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowImg img = imgList.get(position);
        holder.imgName.setText(img.getName());
        Glide.with(mContext).load(img.getImageId()).into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgImage;
        TextView imgName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imgImage = (ImageView) view.findViewById(R.id.item_image);
            imgName = (TextView) view.findViewById(R.id.item_name);
        }
    }

}
