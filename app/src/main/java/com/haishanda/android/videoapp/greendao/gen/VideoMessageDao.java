package com.haishanda.android.videoapp.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.haishanda.android.videoapp.Bean.VideoMessage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_MESSAGE".
*/
public class VideoMessageDao extends AbstractDao<VideoMessage, Long> {

    public static final String TABLENAME = "VIDEO_MESSAGE";

    /**
     * Properties of entity VideoMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ParentDir = new Property(1, String.class, "parentDir", false, "PARENT_DIR");
        public final static Property VideoPath = new Property(2, String.class, "videoPath", false, "VIDEO_PATH");
        public final static Property AddTime = new Property(3, String.class, "addTime", false, "ADD_TIME");
        public final static Property IconPath = new Property(4, String.class, "iconPath", false, "ICON_PATH");
    }


    public VideoMessageDao(DaoConfig config) {
        super(config);
    }
    
    public VideoMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_MESSAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PARENT_DIR\" TEXT," + // 1: parentDir
                "\"VIDEO_PATH\" TEXT," + // 2: videoPath
                "\"ADD_TIME\" TEXT," + // 3: addTime
                "\"ICON_PATH\" TEXT);"); // 4: iconPath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_MESSAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String parentDir = entity.getParentDir();
        if (parentDir != null) {
            stmt.bindString(2, parentDir);
        }
 
        String videoPath = entity.getVideoPath();
        if (videoPath != null) {
            stmt.bindString(3, videoPath);
        }
 
        String addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindString(4, addTime);
        }
 
        String iconPath = entity.getIconPath();
        if (iconPath != null) {
            stmt.bindString(5, iconPath);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String parentDir = entity.getParentDir();
        if (parentDir != null) {
            stmt.bindString(2, parentDir);
        }
 
        String videoPath = entity.getVideoPath();
        if (videoPath != null) {
            stmt.bindString(3, videoPath);
        }
 
        String addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindString(4, addTime);
        }
 
        String iconPath = entity.getIconPath();
        if (iconPath != null) {
            stmt.bindString(5, iconPath);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoMessage readEntity(Cursor cursor, int offset) {
        VideoMessage entity = new VideoMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // parentDir
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // videoPath
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // addTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // iconPath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoMessage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setParentDir(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setVideoPath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIconPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoMessage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoMessage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoMessage entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
