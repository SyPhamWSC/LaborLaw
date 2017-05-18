/**
 * Generated by smali2java 1.0.0.558
 * Copyright (C) 2013 Hensence.com
 */

package com.dtsgroup.labourlaw.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingsFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.tv_push_notification)
    TextView tvPushNotification;
    @BindView(R.id.cb_push_notification)
    CheckBox cbPushNotification;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_english)
    TextView tvEnglish;
    @BindView(R.id.cb_english)
    CheckBox cbEnglish;
    @BindView(R.id.tv_vietnamese)
    TextView tvVietnamese;
    @BindView(R.id.cb_vietnamese)
    CheckBox cbVietnamese;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.cb_push_notification)
    public void onCbPushNotificationClicked() {
    }

    @OnClick(R.id.cb_english)
    public void onCbEnglishClicked() {
    }

    @OnClick(R.id.cb_vietnamese)
    public void onCbVietnameseClicked() {
    }

    @OnClick(R.id.tv_share)
    public void onTvShareClicked() {
    }

    @OnClick(R.id.tv_feedback)
    public void onTvFeedbackClicked() {
    }

    @OnClick(R.id.tv_about)
    public void onTvAboutClicked() {
    }
}
