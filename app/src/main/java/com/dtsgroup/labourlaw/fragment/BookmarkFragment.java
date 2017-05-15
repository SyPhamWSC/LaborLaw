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

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.BookmarkAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemBookmark;
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

public class BookmarkFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvBookmark;
    private BookmarkAdapter bookmarkAdapter;
    private List<JSonItemBookmark> listBookmark = new ArrayList<JSonItemBookmark>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmarks,container,false);

        inits();

        return view;
    }

    private void inits() {
        rvBookmark = (RecyclerView) view.findViewById(R.id.rv_bookmark);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_bookmark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllBookmark();
            }
        });

        bookmarkAdapter = new BookmarkAdapter(getActivity(),listBookmark);
        rvBookmark.setAdapter(bookmarkAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBookmark.setLayoutManager(linearLayoutManager);
        getAllBookmark();
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

    }

    public void getAllBookmark() {
        swipeRefreshLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemBookmark>> call = apiService.getBookmarks();
        call.enqueue(new Callback<List<JSonItemBookmark>>() {
            @Override
            public void onResponse(Call<List<JSonItemBookmark>> call, Response<List<JSonItemBookmark>> response) {
                List<JSonItemBookmark> listTemp = response.body();
                listBookmark.clear();
                for (int i = 0; i < listTemp.size(); i++) {
                    listBookmark.add(listTemp.get(i));
                }
                bookmarkAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<JSonItemBookmark>> call, Throwable t) {

            }
        });
    }
}
