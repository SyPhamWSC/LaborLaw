package com.dtsgroup.labourlaw.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemBookmark;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailBookmarkActivity extends AppCompatActivity{

    @BindView(R.id.tv_title_detail_bookmark)
    TextView tvTitle;
    @BindView(R.id.tv_description_detail_bookmark)
    TextView tvDescription;
    @BindView(R.id.toolbar_detail_bookmark)
    Toolbar toolbar;
    private ItemBookmark itemBookmark;
    private Realm realm = Realm.getDefaultInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bookmark);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra(CommonVls.BUNDLE_DETAIL_LAW,0);
            itemBookmark = realm.where(ItemBookmark.class).equalTo("id",id).findFirst();
        }

        initContent();
    }

    private void initContent() {
        String lang = LanguageHelper.getLanguage(this);
        if (lang.equals(CommonVls.ENGLISH)) {
            tvTitle.setText(itemBookmark.getTitleEn());
            tvDescription.setText(itemBookmark.getDescreptionEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            tvTitle.setText(itemBookmark.getTitlevi());
            tvDescription.setText(itemBookmark.getDescreptionVi());
        }
        String title = getResources().getString(R.string.chapter) +" "+itemBookmark.getChapter();
        getSupportActionBar().setTitle(title);
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

                EventBus.getDefault().post(new EventMessage(CommonVls.ACTION_UPDATE_LANGUAGE));
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


    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(DetailBookmarkActivity.this)){
            EventBus.getDefault().register(DetailBookmarkActivity.this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(DetailBookmarkActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {

    }

}
