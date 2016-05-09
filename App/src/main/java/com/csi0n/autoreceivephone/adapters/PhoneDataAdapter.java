package com.csi0n.autoreceivephone.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csi0n.autoreceivephone.R;
import com.csi0n.autoreceivephone.database.dao.PhoneData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by csi0n on 5/8/16.
 */
public class PhoneDataAdapter extends BaseAdapter {
    private Context mContext;
    public List<PhoneData> datas;

    public PhoneDataAdapter(Context mContext) {
        this.mContext = mContext;
        datas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public PhoneData getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.view_adapter_phone_data_item, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else
        holder=(ViewHolder)view.getTag();
        holder.tvPhone.setText(getItem(i).getPhoneNumber());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_phone)
        TextView tvPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
