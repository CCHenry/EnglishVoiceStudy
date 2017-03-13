package com.example.henryzheng.englishvoicestudy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.henryzheng.englishvoicestudy.M.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class VoiceTestActivity extends AppCompatActivity {
    private RecognizerDialog mIatDialog;
//    private SharedPreferences mSharedPreferences;
    private SpeechRecognizer mIat;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_test);
        FlowerCollector.onEvent(VoiceTestActivity.this, "iat_recognize");
        // 设置参数
        mIatDialog = new RecognizerDialog(VoiceTestActivity.this, mInitListener);
        mIat = SpeechRecognizer.createRecognizer(VoiceTestActivity.this, mInitListener);

        setParam();
//        if (isShowDialog) {
            // 显示听写对话框

//        }
//        else {
//            // 不显示听写对话框
//            ret = mIat.startListening(mRecognizerListener);
//            if (ret != ErrorCode.SUCCESS) {
//                showTip("听写失败,错误码：" + ret);
//            } else {
//                showTip(getString(R.string.text_begin));
//            }
//        }
        }

    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
//
//        String lag = mSharedPreferences.getString("iat_language_preference",
//                "mandarin");
//        if (lag.equals("en_us")) {
//            // 设置语言
//            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
//        } else {
//            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, "zh_cn");
//        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS,  "4000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS,  "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            printResult(results);
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));
        }

    };
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        showTip(resultBuffer.toString());
    }
    private void showTip(final String str) {
        Log.i("cctag",str);
    }
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d("cctag", "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };
    public void StartRecord(View view){
        mIatDialog.setListener(mRecognizerDialogListener);
        mIatDialog.show();
        showTip(getString(R.string.text_begin));
    }
}
