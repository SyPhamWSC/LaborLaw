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
import com.dtsgroup.labourlaw.model.JSonItemBookmark;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;
import com.dtsgroup.labourlaw.service.APIService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CommonVls.GET_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);

                Call<JSonItemBookmark> call = apiService.bookmark(chapterLaw.getChapterNameVi(),chapterLaw.getChapterNameEn(),
                        chapterLaw.getChapterTitleVi(),chapterLaw.getChapterTitleEn(),chapterLaw.getChapterDesVi(),
                        chapterLaw.getChapterDesEn(),chapterLaw.getSubChapter());
                call.enqueue(new Callback<JSonItemBookmark>() {
                    @Override
                    public void onResponse(Call<JSonItemBookmark> call, Response<JSonItemBookmark> response) {
                        Toast.makeText(getApplicationContext(),"Bookmarked!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<JSonItemBookmark> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Can not bookmarked!",Toast.LENGTH_SHORT).show();
                    }
                });
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
