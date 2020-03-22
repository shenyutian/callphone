package com.syt.cellphone.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.syt.cellphone.pojo.PhoneRecommend;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PHONE_RECOMMEND".
*/
public class PhoneRecommendDao extends AbstractDao<PhoneRecommend, Long> {

    public static final String TABLENAME = "PHONE_RECOMMEND";

    /**
     * Properties of entity PhoneRecommend.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property RecommendId = new Property(0, Long.class, "recommendId", true, "_id");
        public final static Property PhoneId = new Property(1, Integer.class, "phoneId", false, "PHONE_ID");
        public final static Property RecommendReimage = new Property(2, String.class, "recommendReimage", false, "RECOMMEND_REIMAGE");
        public final static Property RecommendName = new Property(3, String.class, "recommendName", false, "RECOMMEND_NAME");
    }


    public PhoneRecommendDao(DaoConfig config) {
        super(config);
    }
    
    public PhoneRecommendDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PHONE_RECOMMEND\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: recommendId
                "\"PHONE_ID\" INTEGER," + // 1: phoneId
                "\"RECOMMEND_REIMAGE\" TEXT," + // 2: recommendReimage
                "\"RECOMMEND_NAME\" TEXT);"); // 3: recommendName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_RECOMMEND\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PhoneRecommend entity) {
        stmt.clearBindings();
 
        Long recommendId = entity.getRecommendId();
        if (recommendId != null) {
            stmt.bindLong(1, recommendId);
        }
 
        Integer phoneId = entity.getPhoneId();
        if (phoneId != null) {
            stmt.bindLong(2, phoneId);
        }
 
        String recommendReimage = entity.getRecommendReimage();
        if (recommendReimage != null) {
            stmt.bindString(3, recommendReimage);
        }
 
        String recommendName = entity.getRecommendName();
        if (recommendName != null) {
            stmt.bindString(4, recommendName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PhoneRecommend entity) {
        stmt.clearBindings();
 
        Long recommendId = entity.getRecommendId();
        if (recommendId != null) {
            stmt.bindLong(1, recommendId);
        }
 
        Integer phoneId = entity.getPhoneId();
        if (phoneId != null) {
            stmt.bindLong(2, phoneId);
        }
 
        String recommendReimage = entity.getRecommendReimage();
        if (recommendReimage != null) {
            stmt.bindString(3, recommendReimage);
        }
 
        String recommendName = entity.getRecommendName();
        if (recommendName != null) {
            stmt.bindString(4, recommendName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PhoneRecommend readEntity(Cursor cursor, int offset) {
        PhoneRecommend entity = new PhoneRecommend( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // recommendId
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // phoneId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // recommendReimage
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // recommendName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PhoneRecommend entity, int offset) {
        entity.setRecommendId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPhoneId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRecommendReimage(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRecommendName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PhoneRecommend entity, long rowId) {
        entity.setRecommendId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PhoneRecommend entity) {
        if(entity != null) {
            return entity.getRecommendId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PhoneRecommend entity) {
        return entity.getRecommendId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
