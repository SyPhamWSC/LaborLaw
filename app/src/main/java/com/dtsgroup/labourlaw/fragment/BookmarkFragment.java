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
import com.dtsgroup.labourlaw.adapter.BookmarkAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemBookmark;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.Realm;
import io.realm.RealmResults;

public class BookmarkFragment extends Fragment {

    private static final String TAG = "BookmarkFragment";
    private RecyclerView rvBookmark;
    private BookmarkAdapter bookmarkAdapter;
    private RealmResults<ItemBookmark> listBookmark;
    private View view;
    private Realm realm = Realm.getDefaultInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmarks,container,false);

        inits();

        return view;
    }

    private void inits() {
        rvBookmark = (RecyclerView) view.findViewById(R.id.rv_bookmark);
        listBookmark = realm.where(ItemBookmark.class).findAll();

        bookmarkAdapter = new BookmarkAdapter(getActivity(),listBookmark);
        rvBookmark.setAdapter(bookmarkAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBookmark.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(BookmarkFragment.this)){
            EventBus.getDefault().register(BookmarkFragment.this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(BookmarkFragment.this)){
            EventBus.getDefault().unregister(BookmarkFragment.this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            bookmarkAdapter.notifyDataSetChanged();
        }
        if ((ev.getAction().equals(CommonVls.UPDATE_ADAPTER))){
            listBookmark.clear();
            listBookmark =  realm.where(ItemBookmark.class).findAll();
            bookmarkAdapter.notifyDataSetChanged();
        }

    }

}
