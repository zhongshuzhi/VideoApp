package com.haishanda.android.videoapp.Activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haishanda.android.videoapp.Api.ApiManage;
import com.haishanda.android.videoapp.Bean.CameraLive;
import com.haishanda.android.videoapp.Bean.ImageMessage;
import com.haishanda.android.videoapp.Config.SmartResult;
import com.haishanda.android.videoapp.R;
import com.haishanda.android.videoapp.Utils.CustomMediaController;
import com.haishanda.android.videoapp.Utils.SaveImageToLocalUtil;
import com.haishanda.android.videoapp.VideoApplication;
import com.haishanda.android.videoapp.greendao.gen.ImageMessageDao;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.Log;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhongsz on 2016/10/19.
 */

public class PlayVideoActivity extends Activity {
    @BindView(R.id.test_play_video)
    VideoView videoView;
    @BindView(R.id.test_print)
    ImageView testPrint;

    private static final String TAG = "PlayVideoActivity";
    private ImageMessageDao imageMessageDao;
    private CustomMediaController mCustomMediaController;
    private Bundle extra;
    private int liveId = -1;
    private String boatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        imageMessageDao = VideoApplication.getApplication().getDaoSession().getImageMessageDao();
        Vitamio.isInitialized(getApplicationContext());
        ButterKnife.bind(this);
        extra = getIntent().getExtras();
        int cameraId = extra.getInt("cameraId");
        boatName = extra.getString("boatName");
        String path = getLiveUrl(cameraId);
        if (path!=null) {
//        String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
            videoView.setVideoPath(path);//设置播放地址
            MediaController mMediaController = new MediaController(this);
            mCustomMediaController = new CustomMediaController(this, videoView, this);
            mMediaController.show(5000);//控制器显示5s后自动隐藏
            videoView.setMediaController(mCustomMediaController);//绑定控制器
            videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
            videoView.requestFocus();//取得焦点
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDestroy() {
        SaveImageToLocalUtil.saveAction(videoView.getCurrentFrame(), boatName + "/Cameras");
        ApiManage.getInstence().getLiveApiService().stopLiveStream(liveId);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.printscreen_btn)
    public void printScreen(View view) {
        Log.i(TAG, "printScreen");
        SaveImageToLocalUtil.saveAction(videoView.getCurrentFrame(), boatName);
        Log.i("PlayVideo", " 截图成功");
    }

    @OnClick(R.id.record_btn)
    public void recordVideo(View view) {
        Log.i(TAG, "recordVideo");
    }

    @OnClick(R.id.back_to_boat_btn)
    public void backToBoat(View view) {
        Intent intent = new Intent(PlayVideoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public String getLiveUrl(int cameraId) {
        final String[] liveUrlCopy = new String[1];
        final int[] liveIdCopy = new int[1];
        ApiManage.getInstence().getLiveApiService().getLiveStream(cameraId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SmartResult<CameraLive>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SmartResult<CameraLive> cameraLiveSmartResult) {
                        String liveUrl = cameraLiveSmartResult.getData().getLiveUrl();
                        int liveId = cameraLiveSmartResult.getData().getLiveId();
                        liveUrlCopy[0] = liveUrl;
                        liveIdCopy[0] = liveId;
                    }
                });
        this.liveId = liveIdCopy[0];
        return liveUrlCopy[0];
    }

}
