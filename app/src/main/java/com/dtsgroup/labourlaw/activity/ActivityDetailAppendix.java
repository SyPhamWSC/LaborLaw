package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemAppendix;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class ActivityDetailAppendix extends AppCompatActivity {
    @BindView(R.id.tb_main)
    Toolbar tbMain;
    @BindView(R.id.img_pic_detail)
    ImageView imgPicDetail;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ItemAppendix appendix;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_appendix);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
        ButterKnife.bind(this);
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra(CommonVls.BUNDLE_APPENDIX,0);
            appendix = realm.where(ItemAppendix.class).equalTo("id",id).findFirst();
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(appendix.getImg(),0,appendix.getImg().length);
        imgPicDetail.setImageBitmap(bitmap);
        initViews();
    }

    private void initViews() {

        String title = getResources().getString(R.string.appendix) + " " + appendix.getId();
        getSupportActionBar().setTitle(title);
        String lang = LanguageHelper.getLanguage(this);
        if (lang.equals(CommonVls.ENGLISH)) {
            tvTitle.setText(appendix.getTitleEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            tvTitle.setText(appendix.getTitleVi());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(ActivityDetailAppendix.this)) {
            EventBus.getDefault().register(ActivityDetailAppendix.this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(ActivityDetailAppendix.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {

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
        initViews();
    }
}
