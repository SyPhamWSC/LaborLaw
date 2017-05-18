package com.dtsgroup.labourlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ActivityDetailLaw;
import com.dtsgroup.labourlaw.adapter.EnterGuideLvAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.service.APIService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterGuideFragment extends Fragment {

    private static final String TAG = "EnterGuideFragment";
    private List<JSonChapterLaw> listChapter;
    private RecyclerView recyclerView;
    private EnterGuideLvAdapter enterGuideLvAdapter;
    private View view;
    private SwipeRefreshLayout mSrlLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enter_guide, container, false);

        initDataView();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(EnterGuideFragment.this)){
            EventBus.getDefault().register(EnterGuideFragment.this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(EnterGuideFragment.this)){
            EventBus.getDefault().unregister(EnterGuideFragment.this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            enterGuideLvAdapter.notifyDataSetChanged();
        }

    }

    private void initDataView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_enter_guide);
        mSrlLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_layout);
        listChapter = new ArrayList<JSonChapterLaw>();

        mSrlLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllChapterOfLaw();
            }
        });

        enterGuideLvAdapter = new EnterGuideLvAdapter(getActivity(), listChapter);
        recyclerView.setAdapter(enterGuideLvAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getAllChapterOfLaw();

    }

    public void getAllChapterOfLaw() {
        mSrlLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonChapterLaw>> call = apiService.getAllChapterLaw();
        call.enqueue(new Callback<List<JSonChapterLaw>>() {
            @Override
            public void onResponse(Call<List<JSonChapterLaw>> call, Response<List<JSonChapterLaw>> response) {
                listChapter.clear();
                List<JSonChapterLaw> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    listChapter.add(list.get(i));
                }
                enterGuideLvAdapter.notifyDataSetChanged();
                mSrlLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<JSonChapterLaw>> call, Throwable t) {
                //Toast.makeText(getActivity(), "Get data fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
