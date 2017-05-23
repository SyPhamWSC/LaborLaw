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
import com.dtsgroup.labourlaw.adapter.EnterGuideLvAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.ChapterLaw;
import com.dtsgroup.labourlaw.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.Realm;
import io.realm.RealmResults;

public class EnterGuideFragment extends Fragment {

    private static final String TAG = "EnterGuideFragment";
    private RealmResults<ChapterLaw> listChapter;
    private RecyclerView recyclerView;
    private EnterGuideLvAdapter enterGuideLvAdapter;
    private View view;
    private Realm realm = Realm.getDefaultInstance();

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
        listChapter = realm.where(ChapterLaw.class).findAll();


        enterGuideLvAdapter = new EnterGuideLvAdapter(getActivity(), listChapter);
        recyclerView.setAdapter(enterGuideLvAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
