package com.csi0n.autoreceivephone.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import com.csi0n.autoreceivephone.database.dao.PhoneData;
import com.csi0n.autoreceivephone.event.PhoneListUpdateEvent;
import com.csi0n.autoreceivephone.utils.DbManager;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by csi0n on 5/8/16.
 */
public class MyReceive extends BroadcastReceiver {
    public final static String B_PHONE_STATE = TelephonyManager.ACTION_PHONE_STATE_CHANGED;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if (action.equals(B_PHONE_STATE)){
            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            int state = telephony.getCallState();
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    if (!TextUtils.isEmpty(phoneNumber)){
                        if (DbManager.findPhoneInAllow(phoneNumber)){
                            doReceiver(context);
                            PhoneData phoneData=new PhoneData(phoneNumber,new Date(System.currentTimeMillis()));
                            DbManager.insertPhone(phoneData);
                            EventBus.getDefault().post(new PhoneListUpdateEvent(phoneData));
                        }else {
                            Toast.makeText(context, "此号码没有在拦截库中", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    void doReceiver(Context mContext) {
        try {
            Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, new Object[]{"phone"});
            ITelephony telephony = ITelephony.Stub.asInterface(binder);
            telephony.answerRingingCall();
        } catch (NoSuchMethodException e) {
            Log.d("Sandy", "", e);
        } catch (ClassNotFoundException e) {
            Log.d("Sandy", "", e);
        } catch (Exception e) {
            Log.d("Sandy", "", e);
            try {
                Log.e("Sandy", "for version 4.1 or larger");
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
                intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                mContext.sendOrderedBroadcast(intent, "android.permission.CALL_PRIVILEGED");
            } catch (Exception e2) {
                Log.d("Sandy", "", e2);
                Intent meidaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
                KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
                meidaButtonIntent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
                mContext.sendOrderedBroadcast(meidaButtonIntent, null);
            }
        }
    }
}
