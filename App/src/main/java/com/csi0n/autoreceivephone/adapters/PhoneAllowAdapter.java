package com.csi0n.autoreceivephone.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csi0n.autoreceivephone.R;
import com.csi0n.autoreceivephone.database.dao.PhoneAllow;
import com.csi0n.autoreceivephone.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chqss on 2016/5/10 0010.
 */
public class PhoneAllowAdapter extends BaseAdapter {
    private Context mContext;
    public List<PhoneAllow> datas;

    public PhoneAllowAdapter(Context mContext) {
        this.mContext = mContext;
        datas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public PhoneAllow getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.view_adapter_phone_allow_item, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
            holder.tvPhone.setText(getItem(position).getPhoneNumber());
            holder.tvTime.setText(TimeUtils.getTimeByCurrentTimeMillis(getItem(position).getTime().getTime()));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_phone)
        TextView tvPhone;
        @Bind(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
