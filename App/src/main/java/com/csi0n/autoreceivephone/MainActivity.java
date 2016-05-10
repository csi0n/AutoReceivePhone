package com.csi0n.autoreceivephone;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.csi0n.autoreceivephone.adapters.PhoneDataAdapter;
import com.csi0n.autoreceivephone.database.dao.PhoneData;
import com.csi0n.autoreceivephone.event.PhoneListUpdateEvent;
import com.csi0n.autoreceivephone.receive.MyReceive;
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
                if (button.getText().equals(REG)) {
                    button.setText(UNREG);
                    registerThis();
                } else if (button.getText().equals(UNREG)) {
                    button.setText(REG);
                    unregisterThis();
                }
                break;
            case R.id.clean_his:
                DbManager.clearAll();
                adapter.datas.clear();
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private MyReceive mBroadcastReceiver;
    private String REG = "开启服务";
    private String UNREG = "关闭服务";
    private PhoneDataAdapter adapter;

    @Override
    protected void setRoot() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button.setText(UNREG);
        registerThis();
        adapter=new PhoneDataAdapter(this);
        mList.setAdapter(adapter);
        //DbManager.insertPhone(new PhoneData("111111",new Date(System.currentTimeMillis())));
        List<PhoneData> tempDatas= DbManager.getPhoneData();
        if (tempDatas!=null)
            adapter.datas=tempDatas;
        adapter.notifyDataSetChanged();
    }

    //按钮1-注册广播
    public void registerThis() {
        mBroadcastReceiver = new MyReceive();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyReceive.B_PHONE_STATE);
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public void unregisterThis() {
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterThis();
    }
    @Subscribe
    public void onEvent(PhoneListUpdateEvent phoneListUpdateEvent){
        adapter.datas.add(phoneListUpdateEvent.phoneData);
        adapter.notifyDataSetChanged();
    }

}
