package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.SubLawAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;
import com.dtsgroup.labourlaw.service.APIService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private List<JSonItemSubChapterLaw> listSubChapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_law);

        initsViews();

        initData();
    }

    private void initData() {
        mIntent = getIntent();
        parentChapter = mIntent.getIntExtra(CommonVls.SUB_CHAPTER_LAW,1);
        tbSubLaw.setTitle(getResources().getString(R.string.chapter) + " " + parentChapter);

        listSubChapter = new ArrayList<JSonItemSubChapterLaw>();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllSubChapterLaw();
            }
        });

        subLawAdapter = new SubLawAdapter(SubChapterLawActivity.this,listSubChapter);
        rvSubLaw.setAdapter(subLawAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubChapterLawActivity.this, LinearLayoutManager.VERTICAL, false);
        rvSubLaw.setLayoutManager(linearLayoutManager);
        getAllSubChapterLaw();
    }

    private void initsViews() {
        ButterKnife.bind(this);

        setSupportActionBar(tbSubLaw);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void getAllSubChapterLaw() {
        swipeRefreshLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL+ "getchapters/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemSubChapterLaw>> call = null;
        if(parentChapter == 1){
            call = apiService.getAllSubChapter1Law();
        } if (parentChapter == 2){
            call = apiService.getAllSubChapter2Law();
        } if(parentChapter == 3){
            call = apiService.getAllSubChapter3Law();
        }
        call.enqueue(new Callback<List<JSonItemSubChapterLaw>>() {
            @Override
            public void onResponse(Call<List<JSonItemSubChapterLaw>> call, Response<List<JSonItemSubChapterLaw>> response) {
                swipeRefreshLayout.setRefreshing(false);
                listSubChapter.clear();
                List<JSonItemSubChapterLaw> listTemp = response.body();
                for (int i = 0; i < listTemp.size(); i++) {
                    listSubChapter.add(listTemp.get(i));
                }
                subLawAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JSonItemSubChapterLaw>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(SubChapterLawActivity.this,"Load fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}