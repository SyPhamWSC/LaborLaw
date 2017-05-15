package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDetailLaw extends AppCompatActivity {

    private static final String TAG = "ActivityDetailLaw";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private JSonItemSubChapterLaw chapterLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_law);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(CommonVls.BUNDLE_DETAIL_LAW);
            chapterLaw = (JSonItemSubChapterLaw) bundle.getSerializable(CommonVls.KEY_DETAIL_LAW);
        }

        initContent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(ActivityDetailLaw.this)){
            EventBus.getDefault().register(ActivityDetailLaw.this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(ActivityDetailLaw.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String s) {
        Log.e(TAG, "onMessageEvent from ActivityDetailLaw: " + s);
    }

    private void initContent() {
        String lang = LanguageHelper.getLanguage(this);
        if (lang.equals(CommonVls.ENGLISH)) {
            tvTitle.setText(chapterLaw.getChapterTitleEn());
            tvDescription.setText(chapterLaw.getChapterDesEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            tvTitle.setText(chapterLaw.getChapterTitleVi());
            tvDescription.setText(chapterLaw.getChapterDesVi());
        }
        String title = getResources().getString(R.string.chapter) +" "+chapterLaw.getSubChapter();
        toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_translate:
                String lang = LanguageHelper.getLanguage(this);
                if (lang.equals(CommonVls.ENGLISH)) {
                    updateViews(CommonVls.VIETNAMESE);
                } else {
                    updateViews(CommonVls.ENGLISH);
                }

                EventBus.getDefault().post("Listen change for language");
                break;
            case R.id.menu_search:
                Intent mIntent = new Intent(this, SearchActivity.class);
                startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateViews(String languageCode) {
        LanguageHelper.setLocale(this, languageCode);
        initContent();
    }

}
