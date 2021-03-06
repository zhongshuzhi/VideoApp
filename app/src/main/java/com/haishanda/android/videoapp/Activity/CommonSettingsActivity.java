package com.haishanda.android.videoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.haishanda.android.videoapp.config.Constant;
import com.haishanda.android.videoapp.R;
import com.haishanda.android.videoapp.fragment.VerifyCodeFragment;
import com.haishanda.android.videoapp.service.LoginService;
import com.haishanda.android.videoapp.VideoApplication;
import com.haishanda.android.videoapp.views.MaterialDialog;
import com.haishanda.android.videoapp.greendao.gen.AlarmVoBeanDao;
import com.haishanda.android.videoapp.greendao.gen.MonitorConfigBeanDao;
import com.hyphenate.chat.EMClient;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通用设置界面
 * Created by Zhongsz on 2016/11/12.
 */

public class CommonSettingsActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_settings);
        ButterKnife.bind(this);
    }

    public Activity getCommonSettingsActivity() {
        return this;
    }

    @OnClick(R.id.back_to_my_btn)
    public void backToMyPage() {
        this.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @OnClick(R.id.modify_password_layout)
    public void modifyPassword() {
        VerifyCodeFragment verifyCodeFragment = new VerifyCodeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
        fragmentTransaction.replace(R.id.common_settings_layout, verifyCodeFragment);
        fragmentTransaction.commit();

    }

    @OnClick(R.id.clear_buffer_layout)
    public void clearBuffer() {
        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setTitle("确定清除缓存吗？");
        materialDialog.setMessage("清除缓存将会删除使用过程中产生的临时文件，以减小对手机储存空间的占用，不会对您的使用造成影响。");
        materialDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
            }
        });
        materialDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
            }
        });
        materialDialog.show();
    }

    @OnClick(R.id.about_us_layout)
    public void skipToAboutUsPage() {

    }

    @OnClick(R.id.help_and_feedback_layout)
    public void skipToHelpAndFeedbackLayout() {
        Intent intent = new Intent(CommonSettingsActivity.this, HelpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @OnClick(R.id.logout_btn)
    public void logOut() {
        final MaterialDialog dialog = new MaterialDialog(this);
        dialog.setMessage("确认退出吗?");
        dialog.setTitle("提示");
        dialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //监控数目重置
                AlarmVoBeanDao alarmVoBeanDao = VideoApplication.getApplication().getDaoSession().getAlarmVoBeanDao();
                alarmVoBeanDao.deleteAll();
                //清除监控配置信息
                MonitorConfigBeanDao monitorConfigBeanDao = VideoApplication.getApplication().getDaoSession().getMonitorConfigBeanDao();
                monitorConfigBeanDao.deleteAll();
                //报警数目归零
                SharedPreferences alarmPreferences = getSharedPreferences(Constant.ALARM_MESSAGE, MODE_PRIVATE);
                SharedPreferences.Editor alarmEditor = alarmPreferences.edit();
                alarmEditor.remove(Constant.ALARM_MESSAGE_NUMBER);
                alarmEditor.apply();
                //清除登录信息
                SharedPreferences preferences = getSharedPreferences(Constant.USER_PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove(Constant.USER_PREFERENCE_ID);
                editor.remove(Constant.USER_PREFERENCE_USERNAME);
                editor.remove(Constant.USER_PREFERENCE_TOKEN);
                editor.apply();
                //重置VideoApplication
                VideoApplication.getApplication().setCurrentBoatName(null);
                VideoApplication.getApplication().setCurrentMachineId(-1);
                VideoApplication.getApplication().setSelectedId(0);
                //退出环信
                Thread emThread = new Thread(new EMThread());
                emThread.start();
                //返回欢迎页
                Intent intent = new Intent(getCommonSettingsActivity(), WelcomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                getCommonSettingsActivity().finish();
                MainActivity.getInstance().finish();
                Intent serviceIntent = new Intent(getCommonSettingsActivity(), LoginService.class);
                stopService(serviceIntent);
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    class EMThread implements Runnable {
        @Override
        public void run() {
            EMClient.getInstance().logout(true);
        }
    }
}
