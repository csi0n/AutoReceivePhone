package com.csi0n.autoreceivephone.utils;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.csi0n.autoreceivephone.App;
import com.csi0n.autoreceivephone.Constants;
import com.csi0n.autoreceivephone.database.dao.DaoMaster;
import com.csi0n.autoreceivephone.database.dao.DaoSession;
import com.csi0n.autoreceivephone.database.dao.PhoneAllow;
import com.csi0n.autoreceivephone.database.dao.PhoneAllowDao;
import com.csi0n.autoreceivephone.database.dao.PhoneData;
import com.csi0n.autoreceivephone.database.dao.PhoneDataDao;

import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by csi0n on 5/8/16.
 */
public class DbManager {
    static DaoSession daoSession;
    static SQLiteDatabase db;
    static DaoMaster.DevOpenHelper helper;
    static DaoMaster daoMaster;

    public static void initDb() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(App.getInstance(), Constants.DB_NAME, null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    static DaoSession getDaoSession() {
        if (daoSession != null)
            return daoSession;
        else
            throw new RuntimeException("please init DbManager!");
    }

    static SQLiteDatabase getDb() {
        if (db != null)
            return db;
        else
            throw new RuntimeException("please init DbManager");
    }
    public static void insertPhone(PhoneData phoneData){
        getDaoSession().getPhoneDataDao().insert(phoneData);
    }

    public static List<PhoneData> getPhoneData(){
        return getDaoSession().getPhoneDataDao().queryBuilder()
                .orderDesc(PhoneDataDao.Properties.Time)
                .build()
                .list();
    }
    public static boolean insertPhoneAllow(PhoneAllow phoneAllow) {
        try {
            getDaoSession().getPhoneAllowDao().insert(phoneAllow);
            return true;
        } catch (SQLiteConstraintException sqLiteConstraintException) {
            return false;
        }
    }

    public static List<PhoneAllow> getPhoneAllowData() {
        return getDaoSession().getPhoneAllowDao().queryBuilder()
                .orderDesc(PhoneAllowDao.Properties.Time)
                .build()
                .list();
    }

    public static boolean findPhoneInAllow(String phoneNumber) {
        Query query = getDaoSession().getPhoneAllowDao().queryBuilder()
                .where(PhoneAllowDao.Properties.PhoneNumber.eq(phoneNumber))
                .build();
        if (query.list().size() != 0)
            return true;
        else
            return false;
    }

    public static void clearPhoneData() {
        QueryBuilder<PhoneData> qb = getDaoSession().getPhoneDataDao().queryBuilder();
        DeleteQuery<PhoneData> bd = qb.where(PhoneDataDao.Properties.Time.isNotNull()).buildDelete();
        bd.executeDeleteWithoutDetachingEntities();
    }

    public static void clearPhoneAllow() {
        QueryBuilder<PhoneAllow> qb1 = getDaoSession().getPhoneAllowDao().queryBuilder();
        DeleteQuery<PhoneAllow> bd1 = qb1.where(PhoneAllowDao.Properties.Time.isNotNull()).buildDelete();
        bd1.executeDeleteWithoutDetachingEntities();
    }
    public static void clearAll(){
        QueryBuilder<PhoneData> qb = getDaoSession().getPhoneDataDao().queryBuilder();
        DeleteQuery<PhoneData> bd = qb.where(PhoneDataDao.Properties.Time.isNotNull()).buildDelete();
        bd.executeDeleteWithoutDetachingEntities();
        QueryBuilder<PhoneAllow> qb1 = getDaoSession().getPhoneAllowDao().queryBuilder();
        DeleteQuery<PhoneAllow> bd1 = qb1.where(PhoneAllowDao.Properties.Time.isNotNull()).buildDelete();
        bd1.executeDeleteWithoutDetachingEntities();
    }
}
