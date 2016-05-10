package com.csi0n.autoreceivephone.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chqss on 2016/5/10 0010.
 */
public class TimeUtils {
    public static String getTimeByCurrentTimeMillis(long time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date d1=new Date(time);
        String t1=format.format(d1);
        return t1;
    }
}
