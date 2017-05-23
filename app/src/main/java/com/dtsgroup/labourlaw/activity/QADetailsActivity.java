package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.ItemQA;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class QADetailsActivity extends AppCompatActivity{

    @BindView(R.id.tb_qa)
    Toolbar tb;
    @BindView(R.id.tv_title_qa)
    TextView tvQuestion;
    @BindView(R.id.tv_detail_qa)
    TextView tvDetail;

    private static final String TAG = "QADetailsActivity";
    private ItemQA itemQA;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_details);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);

        inits();
    }

    private void inits() {
        ButterKnife.bind(this);

        tb.setTitle(getResources().getString(R.string.title_tb_qa));
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra(CommonVls.ITEM_QA,0);
        itemQA = realm.where(ItemQA.class).equalTo("id",id).findFirst();
        setView();
        Log.i(TAG,itemQA.getAnswerVi());
    }

    private void setView() {
        String lang = LanguageHelper.getLanguage(this);

        if (lang.equals(CommonVls.ENGLISH)) {
            tvQuestion.setText(itemQA.getQuestionEn());
            tvDetail.setText(itemQA.getAnswerEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            tvQuestion.setText(itemQA.getQuestionVi());
            tvDetail.setText(itemQA.getAnswerVi());
        }
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
