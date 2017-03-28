package com.hill.customview.glidedemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hill.customview.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by hill on 17/3/28.
 */

public class ProductListViewAdapter extends BaseAdapter {
    private ArrayList<Product> dataList;
    private WeakReference<Context> mContext;

    public ProductListViewAdapter(ArrayList<Product> list, Context context) {
        this.dataList = list;
        this.mContext = new WeakReference<Context>(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewItemHolder item;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext.get()).inflate(R.layout.layout_listview_item, null);

            item = new ListViewItemHolder();
            item.imgIv = (RoundedImageView) convertView.findViewById(R.id.imageView1);
            item.nameIv = (TextView) convertView.findViewById(R.id.textView1);
            item.priceIv = (TextView) convertView.findViewById(R.id.textView2);

            convertView.setTag(item);
        } else {
            item = (ListViewItemHolder) convertView.getTag();
        }

        Glide.with((Activity)mContext.get())
                        .load(dataList.get(position)
                        .getImgUrl())
                        .crossFade(200)
                        //.transform(new RotateTransformation(mContext.get(), 50))
                        .into(item.imgIv);
        item.nameIv.setText(dataList.get(position).getName());
        item.priceIv.setText(dataList.get(position).getPrice() + " $");

        return convertView;
    }

    private class ListViewItemHolder {
        RoundedImageView imgIv;
        TextView nameIv;
        TextView priceIv;
    }
}
