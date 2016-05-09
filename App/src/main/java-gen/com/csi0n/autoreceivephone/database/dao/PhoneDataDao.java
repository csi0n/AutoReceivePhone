package com.csi0n.autoreceivephone.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.csi0n.autoreceivephone.database.dao.PhoneData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PHONE_DATA".
*/
public class PhoneDataDao extends AbstractDao<PhoneData, Void> {

    public static final String TABLENAME = "PHONE_DATA";

    /**
     * Properties of entity PhoneData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property PhoneNumber = new Property(0, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property Time = new Property(1, java.util.Date.class, "time", false, "TIME");
    };


    public PhoneDataDao(DaoConfig config) {
        super(config);
    }
    
    public PhoneDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PHONE_DATA\" (" + //
                "\"PHONE_NUMBER\" TEXT," + // 0: phoneNumber
                "\"TIME\" INTEGER);"); // 1: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PhoneData entity) {
        stmt.clearBindings();
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(1, phoneNumber);
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(2, time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public PhoneData readEntity(Cursor cursor, int offset) {
        PhoneData entity = new PhoneData( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // phoneNumber
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)) // time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PhoneData entity, int offset) {
        entity.setPhoneNumber(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(PhoneData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(PhoneData entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
