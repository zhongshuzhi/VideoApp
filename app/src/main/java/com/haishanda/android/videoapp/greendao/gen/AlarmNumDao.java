package com.haishanda.android.videoapp.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.haishanda.android.videoapp.Bean.AlarmNum;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ALARM_NUM".
*/
public class AlarmNumDao extends AbstractDao<AlarmNum, Void> {

    public static final String TABLENAME = "ALARM_NUM";

    /**
     * Properties of entity AlarmNum.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property AlarmNum = new Property(0, int.class, "alarmNum", false, "ALARM_NUM");
    }


    public AlarmNumDao(DaoConfig config) {
        super(config);
    }
    
    public AlarmNumDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALARM_NUM\" (" + //
                "\"ALARM_NUM\" INTEGER NOT NULL UNIQUE );"); // 0: alarmNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALARM_NUM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AlarmNum entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getAlarmNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AlarmNum entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getAlarmNum());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public AlarmNum readEntity(Cursor cursor, int offset) {
        AlarmNum entity = new AlarmNum( //
            cursor.getInt(offset + 0) // alarmNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AlarmNum entity, int offset) {
        entity.setAlarmNum(cursor.getInt(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(AlarmNum entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(AlarmNum entity) {
        return null;
    }

    @Override
    public boolean hasKey(AlarmNum entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
