package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.SubLawAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.SubChapterLaw;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class SubChapterLawActivity extends AppCompatActivity {

    @BindView(R.id.sr_layout_sub_law)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tb_sub_law)
    Toolbar tbSubLaw;
    @BindView(R.id.rv_sub_chapter)
    RecyclerView rvSubLaw;

    private Intent mIntent;
    private int parentChapter;

    private SubLawAdapter subLawAdapter;
    private RealmResults<SubChapterLaw> listSubChapter;

    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_law);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
        initsViews();

        initData();
    }

    private void initData() {
        mIntent = getIntent();
        parentChapter = mIntent.getIntExtra(CommonVls.SUB_CHAPTER_LAW,1);
        getSupportActionBar().setTitle(getResources().getString(R.string.chapter) + " " + parentChapter);

        listSubChapter = realm.where(SubChapterLaw.class).equalTo("id",parentChapter).findAll();

        subLawAdapter = new SubLawAdapter(SubChapterLawActivity.this,listSubChapter);
        rvSubLaw.setAdapter(subLawAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubChapterLawActivity.this, LinearLayoutManager.VERTICAL, false);
        rvSubLaw.setLayoutManager(linearLayoutManager);
    }

    private void initsViews() {
        ButterKnife.bind(this);

        setSupportActionBar(tbSubLaw);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.chapter) + " " + parentChapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(SubChapterLawActivity.this)){
            EventBus.getDefault().register(SubChapterLawActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(SubChapterLawActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            subLawAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
