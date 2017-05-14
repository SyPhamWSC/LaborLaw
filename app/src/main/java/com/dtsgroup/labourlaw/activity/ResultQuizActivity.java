package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultQuizActivity extends AppCompatActivity{
    @BindView(R.id.tb_reusult_quiz)
    Toolbar tbResult;
    @BindView(R.id.tv_appease)
    TextView tvAppease;
    @BindView(R.id.tv_result_activity_in_quiz)
    TextView tvResult;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);

        initsView();
        mIntent = getIntent();
        int result = mIntent.getIntExtra(CommonVls.RESULT_QUIZ,0);
        tvResult.setText(result + "/10");

    }

    private void initsView() {
        ButterKnife.bind(this);
    }
}
