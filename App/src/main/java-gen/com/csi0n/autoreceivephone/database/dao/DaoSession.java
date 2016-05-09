package com.csi0n.autoreceivephone.database.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.csi0n.autoreceivephone.database.dao.PhoneData;

import com.csi0n.autoreceivephone.database.dao.PhoneDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig phoneDataDaoConfig;

    private final PhoneDataDao phoneDataDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        phoneDataDaoConfig = daoConfigMap.get(PhoneDataDao.class).clone();
        phoneDataDaoConfig.initIdentityScope(type);

        phoneDataDao = new PhoneDataDao(phoneDataDaoConfig, this);

        registerDao(PhoneData.class, phoneDataDao);
    }
    
    public void clear() {
        phoneDataDaoConfig.getIdentityScope().clear();
    }

    public PhoneDataDao getPhoneDataDao() {
        return phoneDataDao;
    }

}
