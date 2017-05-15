package com.dtsgroup.labourlaw.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_search)
    AutoCompleteTextView tvSearch;
    @BindView(R.id.img_close_search)
    ImageView imgCloseSearch;
    @BindView(R.id.rv_enter_guide)
    RecyclerView rycResultSearch;
    @BindView(R.id.sr_layout)
    SwipeRefreshLayout srLayout;
    private SubLawAdapter searchAdapter;
    private List<JSonItemSubChapterLaw> listChapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        ButterKnife.bind(this);
        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        tvSearch.addTextChangedListener(this);

        inits();
    }

    private void performSearch() {
        srLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemSubChapterLaw>> call = apiService.getAllSearch(tvSearch.getText().toString());
        call.enqueue(new Callback<List<JSonItemSubChapterLaw>>() {
            @Override
            public void onResponse(Call<List<JSonItemSubChapterLaw>> call, Response<List<JSonItemSubChapterLaw>> response) {
                listChapter.clear();
                List<JSonItemSubChapterLaw> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    listChapter.add(list.get(i));
                }
                searchAdapter.notifyDataSetChanged();
                srLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<JSonItemSubChapterLaw>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Get data fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inits() {
        ButterKnife.bind(this);
        listChapter = new ArrayList<>();
        rycResultSearch.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rycResultSearch.setLayoutManager(layoutManager);
        searchAdapter = new SubLawAdapter(this,listChapter);
        rycResultSearch.setAdapter(searchAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.img_back)
    public void onImgBackClicked() {
        finish();
    }

    @OnClick(R.id.img_close_search)
    public void onImgCloseSearchClicked() {
        tvSearch.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text = tvSearch.getText().toString();
        if(text.length() > 0){
            imgCloseSearch.setVisibility(View.VISIBLE);
        }else{
            imgCloseSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
