package com.csi0n.autoreceivephone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.csi0n.autoreceivephone.adapters.PhoneAllowAdapter;
import com.csi0n.autoreceivephone.database.dao.PhoneAllow;
import com.csi0n.autoreceivephone.utils.DbManager;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by chqss on 2016/5/10 0010.
 */
public class AllowListActivity extends BaseActivity {
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.mList)
    ListView mList;

    PhoneAllowAdapter adapter;

    @Override
    protected void setRoot() {
        setContentView(R.layout.aty_allow_list);
    }
    private void init() {
        adapter = new PhoneAllowAdapter(this);
        List<PhoneAllow> phoneAllows = DbManager.getPhoneAllowData();
        if (phoneAllows != null)
            adapter.datas.addAll(phoneAllows);
        mList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @OnClick(R.id.btn_add)
    public void onClick() {
        String phoneNumber = editText.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(AllowListActivity.this, "请输入手机号码!", Toast.LENGTH_SHORT).show();
        } else {
                PhoneAllow phoneAllow = new PhoneAllow(phoneNumber, new Date(System.currentTimeMillis()));
                if (DbManager.insertPhoneAllow(phoneAllow)){
                    List<PhoneAllow> phoneAllows = DbManager.getPhoneAllowData();
                    if (phoneAllows != null)
                        adapter.datas = phoneAllows;
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(AllowListActivity.this, "号码已经存在", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
