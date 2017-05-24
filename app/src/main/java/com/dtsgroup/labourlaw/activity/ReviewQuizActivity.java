package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.ReviewQuizAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.ItemQuiz;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ReviewQuizActivity extends AppCompatActivity {

    @BindView(R.id.tb_review_quiz)
    Toolbar tbReviewQuiz;
    @BindView(R.id.rv_review_quiz)
    RecyclerView recyclerView;
    ReviewQuizAdapter rvAdapter;

    private Intent mIntent;
    private List<ItemQuiz> quizs = new ArrayList<ItemQuiz>();
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_quiz);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);

        inits();

    }

    private void inits() {
        ButterKnife.bind(this);

        setSupportActionBar(tbReviewQuiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.result));
        mIntent = getIntent();

        getListQuiz();

        rvAdapter = new ReviewQuizAdapter(this, quizs);
        recyclerView.setAdapter(rvAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getListQuiz() {
        Bundle bundle = mIntent.getExtras();

        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_1)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_2)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_3)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_4)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_5)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_6)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_7)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_8)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_9)));
        quizs.add(getQuestion(bundle.getInt(CommonVls.ID_QUIZ_10)));

    }
    private ItemQuiz getQuestion(int id){
        ItemQuiz itemQuiz = new ItemQuiz();
        itemQuiz.setId(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getId());
        itemQuiz.setAnswer(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnswer());
        itemQuiz.setQuestionVi(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getQuestionVi());
        itemQuiz.setAnsdVi(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsdVi());
        itemQuiz.setQuestionEn(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getQuestionEn());
        itemQuiz.setAnsdEn(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsdEn());
        itemQuiz.setAnsaEn(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsaEn());
        itemQuiz.setAnsaVi(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsaVi());
        itemQuiz.setAnsbEn(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsbEn());
        itemQuiz.setAnsbVi(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnsbVi());
        itemQuiz.setAnscEn(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnscEn());
        itemQuiz.setAnscVi(realm.where(ItemQuiz.class).equalTo("id",id).findFirst().getAnscVi());

        return itemQuiz;
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
