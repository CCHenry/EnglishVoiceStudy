package com.example.henryzheng.englishvoicestudy.C.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/12.
 */
public abstract class BaseFragment extends Fragment {
    private View mRootView;
    public Context context;
    public abstract int getContentViewId();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mRootView =inflater.inflate(getContentViewId(),container,false);
        ButterKnife.bind(this,mRootView);//绑定framgent
        this.context = getActivity();
        return mRootView;
    }
}
