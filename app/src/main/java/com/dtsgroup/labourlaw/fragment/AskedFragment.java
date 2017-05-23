package com.dtsgroup.labourlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.QAAdapter;
import com.dtsgroup.labourlaw.model.ItemQA;

import io.realm.Realm;
import io.realm.RealmResults;

public class AskedFragment extends Fragment {

    private RealmResults itemQAList;
    private RecyclerView recyclerView;
    private Realm realm = Realm.getDefaultInstance();
    private View view;
    private QAAdapter qaAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_asked, container, false);

        initViews();

        return view;
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_qa);
        itemQAList = realm.where(ItemQA.class).findAll();

        qaAdapter = new QAAdapter(getActivity(), itemQAList);
        recyclerView.setAdapter(qaAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
