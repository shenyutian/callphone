package com.syt.cellphone.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.syt.cellphone.pojo.PhoneTrademark;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PHONE_TRADEMARK".
*/
public class PhoneTrademarkDao extends AbstractDao<PhoneTrademark, String> {

    public static final String TABLENAME = "PHONE_TRADEMARK";

    /**
     * Properties of entity PhoneTrademark.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property TrademarkId = new Property(0, String.class, "trademarkId", true, "TRADEMARK_ID");
        public final static Property TrademarkName = new Property(1, String.class, "trademarkName", false, "TRADEMARK_NAME");
        public final static Property TrademarkLog = new Property(2, String.class, "trademarkLog", false, "TRADEMARK_LOG");
    }


    public PhoneTrademarkDao(DaoConfig config) {
        super(config);
    }
    
    public PhoneTrademarkDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PHONE_TRADEMARK\" (" + //
                "\"TRADEMARK_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: trademarkId
                "\"TRADEMARK_NAME\" TEXT," + // 1: trademarkName
                "\"TRADEMARK_LOG\" TEXT);"); // 2: trademarkLog
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_TRADEMARK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PhoneTrademark entity) {
        stmt.clearBindings();
 
        String trademarkId = entity.getTrademarkId();
        if (trademarkId != null) {
            stmt.bindString(1, trademarkId);
        }
 
        String trademarkName = entity.getTrademarkName();
        if (trademarkName != null) {
            stmt.bindString(2, trademarkName);
        }
 
        String trademarkLog = entity.getTrademarkLog();
        if (trademarkLog != null) {
            stmt.bindString(3, trademarkLog);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PhoneTrademark entity) {
        stmt.clearBindings();
 
        String trademarkId = entity.getTrademarkId();
        if (trademarkId != null) {
            stmt.bindString(1, trademarkId);
        }
 
        String trademarkName = entity.getTrademarkName();
        if (trademarkName != null) {
            stmt.bindString(2, trademarkName);
        }
 
        String trademarkLog = entity.getTrademarkLog();
        if (trademarkLog != null) {
            stmt.bindString(3, trademarkLog);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public PhoneTrademark readEntity(Cursor cursor, int offset) {
        PhoneTrademark entity = new PhoneTrademark( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // trademarkId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // trademarkName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // trademarkLog
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PhoneTrademark entity, int offset) {
        entity.setTrademarkId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTrademarkName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTrademarkLog(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(PhoneTrademark entity, long rowId) {
        return entity.getTrademarkId();
    }
    
    @Override
    public String getKey(PhoneTrademark entity) {
        if(entity != null) {
            return entity.getTrademarkId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PhoneTrademark entity) {
        return entity.getTrademarkId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}