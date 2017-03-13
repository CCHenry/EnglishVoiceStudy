package com.example.henryzheng.englishvoicestudy.C.submitData;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.henryzheng.englishvoicestudy.C.base.BaseFragment;
import com.example.henryzheng.englishvoicestudy.M.utils.CCLog;
import com.example.henryzheng.englishvoicestudy.M.utils.MySharepreferenceUtil;
import com.example.henryzheng.englishvoicestudy.M.bean.submitData.Word;
import com.example.henryzheng.englishvoicestudy.R;

import org.json.JSONArray;

import java.util.Iterator;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitDataFragment extends BaseFragment {
    String submitSharePreferenceName = "submitSharePreferenceName";
    String errorIndex = "errorIndex";
    String hasFinsh = "hasFinsh";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_submit_data;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (!(new MySharepreferenceUtil(getActivity(), submitSharePreferenceName).getBoolean
//                (hasFinsh))) {
//
//            //转json对象
//            SubmitDataRootBean submitDataRootBean = new Gson().fromJson(FileUtil.readFileFromAssets(getActivity(),"words.txt"),
//                    SubmitDataRootBean.class);
//            //设置Id
//            Iterator<Word> iteratorItems1 = submitDataRootBean.getWordbook().getItem().iterator();
//            int i = 0;
//            while (iteratorItems1.hasNext()) {
//                Word word = iteratorItems1.next();
//                word.setId(i++);
//            }
//            //获取开始Id
//            int startIndex = new MySharepreferenceUtil(getActivity(), submitSharePreferenceName)
//                    .getInt(errorIndex);
//            CCLog.e("之前暂停，上传失败的Id：" + startIndex);
//
//            //获取迭代到错误的Index之前的一个
//            Iterator<Word> iteratorItems2 = submitDataRootBean.getWordbook().getItem().iterator();
//            if (startIndex != 0) {
//                while (true) {
//                    if (iteratorItems2.next().getId() == (startIndex - 1)) {
//                        break;
//                    }
//                }
//            }
//            iteraToSubmitData(iteratorItems2);
//        } else {
//            CCLog.e("trans finsh");
//        }

    }

    private void iteraToSubmitData(final Iterator<Word> items) {
        if (!items.hasNext()) {
            new MySharepreferenceUtil(getActivity(), submitSharePreferenceName)
                    .putBoolean(hasFinsh, true);
            return;
        }
        final Word word = items.next();

        word.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    CCLog.i("上传的word:"+word.getWord()+"成功,Id: " + word.getId()+"");
                    iteraToSubmitData(items);
                } else {
                    CCLog.e("错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                    CCLog.e("上传的失败Id: " + word.getId());
                    new MySharepreferenceUtil(getActivity(), submitSharePreferenceName)
                            .putInt(errorIndex, word.getId());
                }
            }
        });
    }

    private void rankDataShow() {
        String bql = "select word from CET-4EASYWords  order by +word COLLATE " +
                "Chinese_PRC_CI_AI_WS";
        new BmobQuery<Word>().doStatisticQuery(bql, new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray result, BmobException e) {
                if (e == null) {
                    JSONArray ary = (JSONArray) result;
                    if (ary != null) {//开发者需要根据返回结果自行解析数据

                    } else {
//                        showToast("查询成功，无数据");
                    }
                } else {
                    Log.i("cctag", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        });
    }


    private void iteratorToSubmitOneData(final Iterator<Word> items, final Word word) {
        word.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    iteraToSubmitData(items);
                } else {
                    Log.i("cctag", "iteratorToSubmitOneData创建数据失败：" + e.getMessage());
                    iteratorToSubmitOneData(items, word);
                }
            }
        });
    }



}
