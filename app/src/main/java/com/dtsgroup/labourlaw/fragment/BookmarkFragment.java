package com.dtsgroup.labourlaw.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.BookmarkAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.interaction.IClickListener;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemBookmark;
import com.dtsgroup.labourlaw.parser.JBookmark;
import com.dtsgroup.labourlaw.service.APIService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookmarkFragment extends Fragment implements IClickListener {

    private static final String TAG = "BookmarkFragment";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvBookmark;
    private BookmarkAdapter bookmarkAdapter;
    private List<JSonItemBookmark> listBookmark = new ArrayList<>();
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
        if ((ev.getAction().equals(CommonVls.UPDATE_ADAPTER))){
            getAllBookmark();
        }

    }

    public void getAllBookmark() {
        swipeRefreshLayout.setRefreshing(true);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(CommonVls.GET_URL+"bookmarks/", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.e(TAG, "onSuccess: " + response.toString() );
                    int code = response.getInt("code");
                    if(code == 200){
                        JSONArray jsonArray  = response.getJSONArray("data");
                        listBookmark.clear();
                        listBookmark = JBookmark.getBookmark(jsonArray);
                        bookmarkAdapter = new BookmarkAdapter(getActivity(),listBookmark);
                        bookmarkAdapter.setClickListener(BookmarkFragment.this);
                        rvBookmark.setAdapter(bookmarkAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        rvBookmark.setLayoutManager(linearLayoutManager);
                        bookmarkAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }else{
                        swipeRefreshLayout.setRefreshing(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                swipeRefreshLayout.setRefreshing(false);
                Log.e(TAG, "onFailure: "+ responseString );
            }
        });
    }

    @Override
    public void onRemoveBookmark(final String chapter) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(getActivity().getResources().getString(R.string.delete_item));
        alertDialogBuilder.setPositiveButton(getActivity().getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
                removeBookmark(chapter);
            }
        });

        alertDialogBuilder.setNegativeButton(getActivity().getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void removeBookmark(String chapter){
        swipeRefreshLayout.setRefreshing(true);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("chapter",chapter);
        httpClient.post(CommonVls.GET_URL+"remove_chapter/",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                listBookmark.clear();
                try {
                    Log.e(TAG, "onSuccess: " + response.toString() );
                    int code = response.getInt("code");
                    if(code == 200){
                        JSONArray jsonArray = response.getJSONArray("data");

                        listBookmark = JBookmark.getBookmark(jsonArray);
                        bookmarkAdapter = new BookmarkAdapter(getActivity(),listBookmark);
                        bookmarkAdapter.setClickListener(BookmarkFragment.this);
                        rvBookmark.setAdapter(bookmarkAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        rvBookmark.setLayoutManager(linearLayoutManager);
                        swipeRefreshLayout.setRefreshing(false);
                    }else{
                        swipeRefreshLayout.setRefreshing(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                bookmarkAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                swipeRefreshLayout.setRefreshing(false);
                Log.e(TAG, "onFailure: "+ responseString );
            }
        });
    }
}
