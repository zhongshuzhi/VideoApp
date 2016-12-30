package com.haishanda.android.videoapp.Utils;

import android.os.Environment;

import com.haishanda.android.videoapp.Bean.BoatMessage;
import com.haishanda.android.videoapp.Bean.ImageMessage;
import com.haishanda.android.videoapp.Bean.VideoMessage;
import com.haishanda.android.videoapp.VideoApplication;
import com.haishanda.android.videoapp.greendao.gen.BoatMessageDao;
import com.haishanda.android.videoapp.greendao.gen.ImageMessageDao;
import com.haishanda.android.videoapp.greendao.gen.VideoMessageDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.List;

/**
 * Created by Zhongsz on 2016/12/30.
 */

public class DaoUtil {
    public static void renameBoat(String boatNewName, String originalBoatName, long machineId) {
        ImageMessageDao imageMessageDao = VideoApplication.getApplication().getDaoSession().getImageMessageDao();
        BoatMessageDao boatMessageDao = VideoApplication.getApplication().getDaoSession().getBoatMessageDao();
        VideoMessageDao videoMessageDao = VideoApplication.getApplication().getDaoSession().getVideoMessageDao();
        QueryBuilder<BoatMessage> boatQuery = boatMessageDao.queryBuilder();
        QueryBuilder<ImageMessage> queryBuilder = imageMessageDao.queryBuilder();
        QueryBuilder<VideoMessage> videoMessageQueryBuilder = videoMessageDao.queryBuilder();
        List<BoatMessage> boatMessages = boatQuery.where(BoatMessageDao.Properties.MachineId.eq(machineId)).list();
        List<ImageMessage> imageMessageList = queryBuilder.where(ImageMessageDao.Properties.ParentDir.eq(originalBoatName)).list();
        List<VideoMessage> videoMessageList = videoMessageQueryBuilder.where(VideoMessageDao.Properties.ParentDir.eq(originalBoatName)).list();
        for (BoatMessage boatMessage : boatMessages
                ) {
            String oldIconPath = boatMessage.getCameraImagePath();
            String newIconPath = oldIconPath.replace(originalBoatName, boatNewName);
            boatMessage.setCameraImagePath(newIconPath);
            boatMessageDao.update(boatMessage);
        }
        for (ImageMessage imageMessage : imageMessageList
                ) {
            imageMessage.setParentDir(boatNewName);
            imageMessageDao.update(imageMessage);
        }
        for (VideoMessage videoMessage : videoMessageList
                ) {
            videoMessage.setParentDir(boatNewName);
            videoMessageDao.update(videoMessage);
        }
        File originalDir = new File(Environment.getExternalStorageDirectory().getPath() + "/VideoApp/" + originalBoatName);
        File newDir = new File(Environment.getExternalStorageDirectory().getPath() + "/VideoApp/" + boatNewName);
        originalDir.renameTo(newDir);
        VideoApplication.getApplication().setCurrentBoatName(boatNewName);
    }
}