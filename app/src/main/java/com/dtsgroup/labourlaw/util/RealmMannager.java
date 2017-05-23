package com.dtsgroup.labourlaw.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.SharePrefUtils;
import com.dtsgroup.labourlaw.model.ChapterLaw;
import com.dtsgroup.labourlaw.model.ItemQA;
import com.dtsgroup.labourlaw.model.ItemQuiz;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemQA;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;
import com.dtsgroup.labourlaw.parser.JChapterLaw;
import com.dtsgroup.labourlaw.parser.JItemQA;
import com.dtsgroup.labourlaw.parser.JQuiz;
import com.dtsgroup.labourlaw.service.APIService;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealmMannager {
    private static final String TAG = "RealmMannager";
    private static final int LOAD_DATA_DONE = 10;

    private Realm realm = Realm.getDefaultInstance();
    private boolean isHasChapterLaw = false;
    private boolean isHasRealm = false;
    private boolean isHasItemQA = false;
    private Handler mHandler;
    private Context context;

    public RealmMannager(Context context) {
        this.context = context;
    }

    //    private static RealmMannager instance;
//
//    private static Realm realm = Realm.getDefaultInstance();
//    private static Context context;
//    public RealmMannager(Application app) {
//        this.realm = Realm.getDefaultInstance();
//    }
//
//    public static RealmMannager with(Fragment fragment){
//        if(instance == null){
//            instance = new RealmMannager(fragment.getActivity().getApplication());
//        }
//        return instance;
//    }
//
//    public static RealmMannager with(AppCompatActivity activity){
//        if (instance == null){
//            instance = new RealmMannager(activity.getApplication());
//        }
//        return instance;
//    }
//
//    public static RealmMannager getInstance(){
//        return instance;
//    }
//
//    public Realm getRealm(){
//        return realm;
//


    public void setDataRealm(){
        setChapterLaw();
        setSubChapter();
        setItemQA();
        setQuiz();
        SharePrefUtils.updateFirstApp(context,true);
    }

    private void setQuiz() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemQuiz>> call = apiService.getAllQiz();
        call.enqueue(new Callback<List<JSonItemQuiz>>() {
            @Override
            public void onResponse(Call<List<JSonItemQuiz>> call, Response<List<JSonItemQuiz>> response) {
                List<JSonItemQuiz> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    final ItemQuiz itemQuiz = JQuiz.getItemQuiz(list.get(i));
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(itemQuiz);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<JSonItemQuiz>> call, Throwable t) {

            }
        });
    }

    private void setItemQA() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemQA>> call = apiService.getALlQA();
        call.enqueue(new Callback<List<JSonItemQA>>() {
            @Override
            public void onResponse(Call<List<JSonItemQA>> call, Response<List<JSonItemQA>> response) {
                List<JSonItemQA> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    final ItemQA itemQA = JItemQA.getItemQA(list.get(i));
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(itemQA);
                        }
                    });
                }

                Log.e(TAG, "load itemQA: ok" );
            }

            @Override
            public void onFailure(Call<List<JSonItemQA>> call, Throwable t) {
                Log.e(TAG, "load itemQA: fail" );
            }
        });
    }

    private void setSubChapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL+ "getchapters/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
//        Call<List<JSonItemSubChapterLaw>> call = apiService.getAllSubChapterLaw(parentChapter);
//        call.enqueue(new Callback<List<JSonItemSubChapterLaw>>() {
//            @Override
//            public void onResponse(Call<List<JSonItemSubChapterLaw>> call, Response<List<JSonItemSubChapterLaw>> response) {
//
//                listSubChapter.clear();
//                List<JSonItemSubChapterLaw> listTemp = response.body();
//                for (int i = 0; i < listTemp.size(); i++) {
//                    listSubChapter.add(listTemp.get(i));
//                }
//                subLawAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<List<JSonItemSubChapterLaw>> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
//                Toast.makeText(SubChapterLawActivity.this,"Load fail", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void setChapterLaw() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonChapterLaw>> call = apiService.getAllChapterLaw();
        call.enqueue(new Callback<List<JSonChapterLaw>>() {
            @Override
            public void onResponse(Call<List<JSonChapterLaw>> call, Response<List<JSonChapterLaw>> response) {
                List<JSonChapterLaw> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    final ChapterLaw chapterLaw = JChapterLaw.getChapterLaw(list.get(i));
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(chapterLaw);
                        }
                    });
                }
                Log.e(TAG, "load chapter law : ok" );
            }

            @Override
            public void onFailure(Call<List<JSonChapterLaw>> call, Throwable t) {
                Log.e(TAG, "load chapter law : fail" );
            }
        });
    }


}
