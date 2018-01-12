package com.xzh.douyuapp.view.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.xzh.baserecyclerviewadapter.BaseViewHolder;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.model.logic.video.bean.VideoOtherColumnList;
import com.xzh.douyuapp.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.xzh.douyuapp.utils.CalculationUtils;


import java.util.ArrayList;
import java.util.List;


public class VideoOtherColumnListAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private List<VideoOtherColumnList> mLiveList;
    private Context context;

    public VideoOtherColumnListAdapter(Context context) {
        this.context = context;
        this.mLiveList = new ArrayList<VideoOtherColumnList>();
    }

    public void getLiveOtherColumnList(List<VideoOtherColumnList> mLiveList) {
        this.mLiveList.clear();
        this.mLiveList.addAll(mLiveList);
        notifyDataSetChanged();
    }

    public void getLiveOtherColumnLoadMore(List<VideoOtherColumnList> mLiveList) {
        this.mLiveList.addAll(mLiveList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new LiveOtherColumnListHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return new LiveOtherColumnListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof LiveOtherColumnListHolder) {
            bindLiveAll((LiveOtherColumnListHolder) holder, position);
        }
    }

    private void bindLiveAll(LiveOtherColumnListHolder holder, int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(mLiveList.get(position).getVideo_cover()));
        holder.tv_column_item_nickname.setText(mLiveList.get(position).getVideo_title());
        holder.tv_nickname.setText(mLiveList.get(position).getNickname());
        holder.tv_online_num.setText(CalculationUtils.getOnLine(Integer.parseInt(mLiveList.get(position).getView_num())));
    }

    @Override
    public int getAdapterItemCount() {
        return this.mLiveList.size();
    }
    public class LiveOtherColumnListHolder extends BaseViewHolder {
        //        图片
        public SimpleDraweeView img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;
        //        在线人数
        public TextView tv_online_num;
        //        昵称
        public TextView tv_nickname;

        public LiveOtherColumnListHolder(View view) {
            super(view);
            img_item_gridview = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        }
    }
}
