package com.haishanda.android.videoapp.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.haishanda.android.videoapp.Bean.MonitorConfigBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MONITOR_CONFIG_BEAN".
*/
public class MonitorConfigBeanDao extends AbstractDao<MonitorConfigBean, Long> {

    public static final String TABLENAME = "MONITOR_CONFIG_BEAN";

    /**
     * Properties of entity MonitorConfigBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MachineId = new Property(0, long.class, "machineId", true, "_id");
        public final static Property BoatName = new Property(1, String.class, "boatName", false, "BOAT_NAME");
        public final static Property IsSwitchOn = new Property(2, boolean.class, "isSwitchOn", false, "IS_SWITCH_ON");
    }


    public MonitorConfigBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MonitorConfigBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MONITOR_CONFIG_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE ," + // 0: machineId
                "\"BOAT_NAME\" TEXT," + // 1: boatName
                "\"IS_SWITCH_ON\" INTEGER NOT NULL );"); // 2: isSwitchOn
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MONITOR_CONFIG_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MonitorConfigBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMachineId());
 
        String boatName = entity.getBoatName();
        if (boatName != null) {
            stmt.bindString(2, boatName);
        }
        stmt.bindLong(3, entity.getIsSwitchOn() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MonitorConfigBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getMachineId());
 
        String boatName = entity.getBoatName();
        if (boatName != null) {
            stmt.bindString(2, boatName);
        }
        stmt.bindLong(3, entity.getIsSwitchOn() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public MonitorConfigBean readEntity(Cursor cursor, int offset) {
        MonitorConfigBean entity = new MonitorConfigBean( //
            cursor.getLong(offset + 0), // machineId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // boatName
            cursor.getShort(offset + 2) != 0 // isSwitchOn
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MonitorConfigBean entity, int offset) {
        entity.setMachineId(cursor.getLong(offset + 0));
        entity.setBoatName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsSwitchOn(cursor.getShort(offset + 2) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MonitorConfigBean entity, long rowId) {
        entity.setMachineId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MonitorConfigBean entity) {
        if(entity != null) {
            return entity.getMachineId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MonitorConfigBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
