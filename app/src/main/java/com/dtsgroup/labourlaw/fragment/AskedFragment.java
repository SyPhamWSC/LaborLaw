package com.dtsgroup.labourlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.QAAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.JSonItemQA;
import com.dtsgroup.labourlaw.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AskedFragment extends Fragment {

    private List<JSonItemQA> itemQAList;
    private RecyclerView recyclerView;
    private View view;
    private QAAdapter qaAdapter;
    private SwipeRefreshLayout mSrLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_asked, container, false);

        initViews();

        return view;
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_qa);
        mSrLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_layout_qa);
        itemQAList = new ArrayList<JSonItemQA>();

        mSrLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllQA();
            }
        });

        qaAdapter = new QAAdapter(getContext(), itemQAList);
        recyclerView.setAdapter(qaAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getAllQA();
    }

    public void getAllQA() {
        mSrLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemQA>> call = apiService.getALlQA();
        call.enqueue(new Callback<List<JSonItemQA>>() {
            @Override
            public void onResponse(Call<List<JSonItemQA>> call, Response<List<JSonItemQA>> response) {
                mSrLayout.setRefreshing(false);
                List<JSonItemQA> list = response.body();
                itemQAList.clear();
                for (int i = 0; i < list.size(); i++) {
                    itemQAList.add(list.get(i));
                }
                qaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JSonItemQA>> call, Throwable t) {
                Toast.makeText(getActivity(), "Get data fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
