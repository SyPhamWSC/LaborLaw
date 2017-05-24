package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultQuizActivity extends AppCompatActivity{
    @BindView(R.id.tb_reusult_quiz)
    Toolbar tbResult;
    @BindView(R.id.tv_appease)
    TextView tvAppease;
    @BindView(R.id.tv_result_activity_in_quiz)
    TextView tvResult;
    @BindView(R.id.tv_try_quiz_again)
    TextView tvTryAgain;
    @BindView(R.id.tv_review_quiz)
    TextView tvReviewQuiz;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);

        initsView();
        mIntent = getIntent();
        int result = mIntent.getIntExtra(CommonVls.RESULT_QUIZ,0);
        tvResult.setText(result + "/10");
        tvTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage(CommonVls.RELOAD_QUIZ));
                finish();
            }
        });
        tvReviewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = mIntent.getExtras();
                Intent intent = new Intent(ResultQuizActivity.this,ReviewQuizActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(ResultQuizActivity.this)){
            EventBus.getDefault().register(ResultQuizActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ResultQuizActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
    }

    private void initsView() {
        ButterKnife.bind(this);
        setSupportActionBar(tbResult);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.result));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
