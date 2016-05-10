package com.csi0n.autoreceivephone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.csi0n.autoreceivephone.adapters.PhoneDataAdapter;
import com.csi0n.autoreceivephone.database.dao.PhoneData;
import com.csi0n.autoreceivephone.event.PhoneListUpdateEvent;
import com.csi0n.autoreceivephone.utils.DbManager;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(value = R.id.button)
    Button button;
    @Bind(value = R.id.mlist)
    ListView mList;

    @OnClick(value = {R.id.button,R.id.clean_his})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startPhoneAllowList();
                break;
            case R.id.clean_his:
                DbManager.clearPhoneData();
                adapter.datas.clear();
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
    private PhoneDataAdapter adapter;

    @Override
    protected void setRoot() {
        setContentView(R.layout.aty_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new PhoneDataAdapter(this);
        mList.setAdapter(adapter);
        List<PhoneData> tempDatas= DbManager.getPhoneData();
        if (tempDatas!=null)
            adapter.datas=tempDatas;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Subscribe
    public void onEvent(PhoneListUpdateEvent phoneListUpdateEvent){
        adapter.datas.add(phoneListUpdateEvent.phoneData);
        adapter.notifyDataSetChanged();
    }

    private void startPhoneAllowList() {
        startActivity(new Intent(this, AllowListActivity.class));
    }
}
