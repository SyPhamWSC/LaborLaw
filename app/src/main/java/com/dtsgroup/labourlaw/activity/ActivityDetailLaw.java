package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemBookmark;
import com.dtsgroup.labourlaw.model.SubChapterLaw;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityDetailLaw extends AppCompatActivity {

    private static final String TAG = "ActivityDetailLaw";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SubChapterLaw chapterLaw;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_law);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmResults<ItemBookmark> list = realm.where(ItemBookmark.class).findAll();
                final ItemBookmark itemBookmark = new ItemBookmark();
                itemBookmark.setChapter(chapterLaw.getSubChapter());
                itemBookmark.setTitlevi(chapterLaw.getChapterTitleVi());
                itemBookmark.setTitleEn(chapterLaw.getChapterTitleEn());
                itemBookmark.setDescreptionEn(chapterLaw.getChapterDesEn());
                itemBookmark.setDescreptionVi(chapterLaw.getChapterDesVi());
                itemBookmark.setNameEn(chapterLaw.getChapterNameEn());
                itemBookmark.setNameVi(chapterLaw.getChapterNameVi());
                itemBookmark.setId(list.size());
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(itemBookmark);
                        Toast.makeText(ActivityDetailLaw.this,"Bookmarked!!!",Toast.LENGTH_SHORT).show();
                    }
                });
                EventBus.getDefault().post(new EventMessage(CommonVls.UPDATE_ADAPTER));
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String subChapter = intent.getStringExtra(CommonVls.BUNDLE_DETAIL_LAW);
            chapterLaw = realm.where(SubChapterLaw.class).equalTo("subChapter",subChapter).findFirst();
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
    public void onMessageEvent(EventMessage ev) {

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

}
