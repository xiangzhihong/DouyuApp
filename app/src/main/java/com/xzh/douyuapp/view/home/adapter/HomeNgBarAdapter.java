package com.xzh.douyuapp.view.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.model.logic.home.bean.HomeRecommendHotCate;
import com.xzh.douyuapp.view.home.activity.HomeColumnMoreListActivity;


import java.util.List;


public class HomeNgBarAdapter extends BaseAdapter {

    private List<HomeRecommendHotCate> mHomeCate;
    private LayoutInflater mInflater;
    private Context context;
    //    页数下标
    private int mIndex;
    //    每页显示多少
    private int mPagerSize;

    public HomeNgBarAdapter(Context context, List<HomeRecommendHotCate> homeCate, int mIndex, int mPageSize) {
        this.context = context;
        this.mHomeCate = homeCate;
        this.mIndex = mIndex;
        this.mPagerSize = mPageSize;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mHomeCate.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (mHomeCate.size() - mIndex * mPagerSize);
    }

    @Override
    public Object getItem(int position) {
        return mHomeCate.get(position + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPagerSize;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_gridview, viewGroup, false);
            holder = new ViewHolder();
            holder.proName = (TextView) view.findViewById(R.id.tv_item_name);
            holder.imgIcon = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final int pos = position + mIndex * mPagerSize;
        if (pos == 15) {
            holder.proName.setText("全部分类");
            holder.imgIcon.setImageURI(Uri.parse("res://com.team.zhuoke/" + R.mipmap.more_icon));
        } else {
            holder.proName.setText(mHomeCate.get(pos).getTag_name());
            holder.imgIcon.setImageURI(Uri.parse(mHomeCate.get(pos).getIcon_url()));
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos == 15) {
                    Toast.makeText(context, "你点击了" + "全部分类", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(context, HomeColumnMoreListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", mHomeCate.get(pos).getTag_name());
                    bundle.putString("cate_id", mHomeCate.get(pos).getTag_id());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
        return view;
    }
    /**
     * 定义ViewHodle
     */
    class ViewHolder {
        private TextView proName;

        private SimpleDraweeView imgIcon;

    }

}
