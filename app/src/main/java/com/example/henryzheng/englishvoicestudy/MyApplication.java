package com.example.henryzheng.englishvoicestudy;

import android.app.Activity;
import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by henryzheng on 2017/2/9.
 */


public class MyApplication extends Application {
    static MyApplication myApplication;
    public static List<Activity> activitys;

    public static Application getContext() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        activitys = new ArrayList<>();
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=589bc7bb");
//        Bmob.initialize(this, "17e62c54cd0f8bf4866394ef59e78ca6");
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("17e62c54cd0f8bf4866394ef59e78ca6")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(2048 * 2048)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(25000)
                .build();

        Bmob.initialize(config);
    }

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }
}
