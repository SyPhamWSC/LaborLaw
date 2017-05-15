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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroduceActivity extends AppCompatActivity {

    @BindView(R.id.tb_introduce)
    Toolbar tbIntroduce;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        ButterKnife.bind(this);
        setSupportActionBar(tbIntroduce);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mIntent = getIntent();

        initContent();



    }

    private void initContent() {
        String lang = LanguageHelper.getLanguage(getApplicationContext());
        if(lang.equals(CommonVls.ENGLISH)){
            tvIntroduce.setText(mIntent.getStringExtra(CommonVls.INTRODUCE_EN));
        } else if(lang.equals(CommonVls.VIETNAMESE)){
            tvIntroduce.setText(mIntent.getStringExtra(CommonVls.INTRODUCE_VI));
        }
        getSupportActionBar().setTitle(getResources().getString(R.string.introduce));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
                Intent intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateViews(String language) {
        LanguageHelper.setLocale(this,language);
        initContent();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(IntroduceActivity.this)){
            EventBus.getDefault().register(IntroduceActivity.this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(IntroduceActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {

    }
}
