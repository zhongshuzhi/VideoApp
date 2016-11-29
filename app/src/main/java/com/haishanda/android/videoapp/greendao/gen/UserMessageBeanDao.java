package com.haishanda.android.videoapp.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.haishanda.android.videoapp.Bean.UserMessageBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_MESSAGE_BEAN".
*/
public class UserMessageBeanDao extends AbstractDao<UserMessageBean, Long> {

    public static final String TABLENAME = "USER_MESSAGE_BEAN";

    /**
     * Properties of entity UserMessageBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property NickName = new Property(0, String.class, "nickName", false, "NICK_NAME");
        public final static Property PortraitUrl = new Property(1, String.class, "portraitUrl", false, "PORTRAIT_URL");
        public final static Property Id = new Property(2, long.class, "id", true, "_id");
    }


    public UserMessageBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserMessageBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_MESSAGE_BEAN\" (" + //
                "\"NICK_NAME\" TEXT," + // 0: nickName
                "\"PORTRAIT_URL\" TEXT," + // 1: portraitUrl
                "\"_id\" INTEGER PRIMARY KEY NOT NULL );"); // 2: id
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_USER_MESSAGE_BEAN__id ON USER_MESSAGE_BEAN" +
                " (\"_id\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_MESSAGE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserMessageBean entity) {
        stmt.clearBindings();
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(1, nickName);
        }
 
        String portraitUrl = entity.getPortraitUrl();
        if (portraitUrl != null) {
            stmt.bindString(2, portraitUrl);
        }
        stmt.bindLong(3, entity.getId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserMessageBean entity) {
        stmt.clearBindings();
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(1, nickName);
        }
 
        String portraitUrl = entity.getPortraitUrl();
        if (portraitUrl != null) {
            stmt.bindString(2, portraitUrl);
        }
        stmt.bindLong(3, entity.getId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 2);
    }    

    @Override
    public UserMessageBean readEntity(Cursor cursor, int offset) {
        UserMessageBean entity = new UserMessageBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // nickName
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // portraitUrl
            cursor.getLong(offset + 2) // id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserMessageBean entity, int offset) {
        entity.setNickName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setPortraitUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserMessageBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserMessageBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserMessageBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}