package com.dtsgroup.labourlaw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.AppendixAdpater;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.Appendix;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;
import com.dtsgroup.labourlaw.service.APIService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vantr on 5/15/2017.
 */

public class ActivityAppendix extends AppCompatActivity {

    @BindView(R.id.tb_main)
    Toolbar tbMain;
    @BindView(R.id.sw_refesh_layout)
    SwipeRefreshLayout swRefeshLayout;
    @BindView(R.id.ryc_appendix)
    RecyclerView rycAppendix;
    private AppendixAdpater appendixAdpater;
    private List<Appendix> listAppendix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appendix);
        ButterKnife.bind(this);

        initView();
        listAppendix = new ArrayList<>();
        rycAppendix.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rycAppendix.setLayoutManager(layoutManager);
        appendixAdpater = new AppendixAdpater(this, listAppendix);
        rycAppendix.setAdapter(appendixAdpater);
        swRefeshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataAppendix();
            }
        });
        loadDataAppendix();
    }

    private void loadDataAppendix() {
        swRefeshLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Appendix>> call = apiService.getAllAppendix();
        call.enqueue(new Callback<List<Appendix>>() {
            @Override
            public void onResponse(Call<List<Appendix>> call, Response<List<Appendix>> response) {

                listAppendix.clear();
                List<Appendix> listTemp = response.body();
                for (int i = 0; i < listTemp.size(); i++) {
                    listAppendix.add(listTemp.get(i));
                }
                appendixAdpater.notifyDataSetChanged();
                swRefeshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Appendix>> call, Throwable t) {
                swRefeshLayout.setRefreshing(false);
                Toast.makeText(ActivityAppendix.this,"Load fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.appendix));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(ActivityAppendix.this)){
            EventBus.getDefault().register(ActivityAppendix.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ActivityAppendix.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            appendixAdpater.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
