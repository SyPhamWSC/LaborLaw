package com.dtsgroup.labourlaw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dtsgroup.labourlaw.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.tb_search)
    Toolbar tbSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inits();
    }

    private void inits() {
        ButterKnife.bind(this);
        setSupportActionBar(tbSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String temp = getResources().getString(R.string.search);
        getSupportActionBar().setTitle(getResources().getString(R.string.search));
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
