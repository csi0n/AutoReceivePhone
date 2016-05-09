package com.csi0n.autoreceivephone.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by chqss on 2016/2/21 0021.
 */
public class SystemUtils {
    public static void restartApplication(Application application) {
        final Intent intent = application.getPackageManager().getLaunchIntentForPackage(application.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        application.startActivity(intent);
    }
}
