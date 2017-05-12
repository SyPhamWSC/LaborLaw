package com.dtsgroup.labourlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.adapter.ShowQuizAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;
import com.dtsgroup.labourlaw.service.APIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QuizFragment extends Fragment {

    private static final String TAG = "JSonItemQuiz";
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private ShowQuizFragment fShowQuiz1;
    private ShowQuizFragment fShowQuiz2;
    private ShowQuizFragment fShowQuiz3;
    private ShowQuizFragment fShowQuiz4;
    private ShowQuizFragment fShowQuiz5;
    private ShowQuizFragment fShowQuiz6;
    private ShowQuizFragment fShowQuiz7;
    private ShowQuizFragment fShowQuiz8;
    private ShowQuizFragment fShowQuiz9;
    private ShowQuizFragment fShowQuiz10;

    private JSonItemQuiz temp;

    private JSonItemQuiz question1, question2, question3, question4, question5, question6,
            question7, question8, question9, question10;

    private List<JSonItemQuiz> listQuestion = new ArrayList<JSonItemQuiz>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        createChildFragment();
        viewPager = (ViewPager) view.findViewById(R.id.vp_show_quiz);
        pagerAdapter = new ShowQuizAdapter(getChildFragmentManager(), fShowQuiz1, fShowQuiz2, fShowQuiz3, fShowQuiz4
                , fShowQuiz5, fShowQuiz6, fShowQuiz7, fShowQuiz8, fShowQuiz9, fShowQuiz10);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        getAllQuestionQuiz();
        return view;
    }

    private void createChildFragment() {
        fShowQuiz1 = ShowQuizFragment.create(0);
        fShowQuiz2 = ShowQuizFragment.create(1);
        fShowQuiz3 = ShowQuizFragment.create(2);
        fShowQuiz4 = ShowQuizFragment.create(3);
        fShowQuiz5 = ShowQuizFragment.create(4);
        fShowQuiz6 = ShowQuizFragment.create(5);
        fShowQuiz7 = ShowQuizFragment.create(6);
        fShowQuiz8 = ShowQuizFragment.create(7);
        fShowQuiz9 = ShowQuizFragment.create(8);
        fShowQuiz10 = ShowQuizFragment.create(9);
    }

    private void createQuestion(){
        question1 = randomQuestion(listQuestion);
        question2 = randomQuestion(listQuestion);
        question3 = randomQuestion(listQuestion);
        question4 = randomQuestion(listQuestion);
        question5 = randomQuestion(listQuestion);
        question6 = randomQuestion(listQuestion);
        question7 = randomQuestion(listQuestion);
        question8 = randomQuestion(listQuestion);
        question9 = randomQuestion(listQuestion);
        question10 = randomQuestion(listQuestion);
    }
    private void setViewForQuiz(){
        fShowQuiz1.setTextToAnswer(question1);
        fShowQuiz2.setTextToAnswer(question2);
        fShowQuiz3.setTextToAnswer(question3);
        fShowQuiz4.setTextToAnswer(question4);
        fShowQuiz5.setTextToAnswer(question5);
        fShowQuiz6.setTextToAnswer(question6);
        fShowQuiz7.setTextToAnswer(question7);
        fShowQuiz8.setTextToAnswer(question8);
        fShowQuiz9.setTextToAnswer(question9);
        fShowQuiz10.setTextToAnswer(question10);
    }

    private void getAllQuestionQuiz() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVls.GET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<JSonItemQuiz>> call = apiService.getAllQiz();
        call.enqueue(new Callback<List<JSonItemQuiz>>() {
            @Override
            public void onResponse(Call<List<JSonItemQuiz>> call, Response<List<JSonItemQuiz>> response) {
                List<JSonItemQuiz> listTemp = response.body();
                listQuestion.clear();
                for (int i = 0; i < listTemp.size(); i++) {
                    listQuestion.add(listTemp.get(i));
                }
                Log.i(TAG,listQuestion.get(0).getAnsaEn());
//                createQuestion();
//                setViewForQuiz();
                question1 = listQuestion.get(1);
                fShowQuiz1.setTextToAnswer(question1);
                question2 = listQuestion.get(1);
                fShowQuiz2.setTextToAnswer(question1);
                question3 = listQuestion.get(1);
                fShowQuiz3.setTextToAnswer(question1);
                question3 = listQuestion.get(1);
                fShowQuiz3.setTextToAnswer(question1);
                pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JSonItemQuiz>> call, Throwable t) {

            }
        });
    }

    private JSonItemQuiz randomQuestion(List<JSonItemQuiz> listQuestion) {
        Random rn = new Random();

        int rand = rn.nextInt(listQuestion.size());
        temp =listQuestion.get(rand);
       // temp.setAnsaVi(listQuestion.get(rand).getAnsaVi());
        Log.e(TAG, "randomQuestion: " + temp.getAnsaVi());
        listQuestion.remove(rand);
        pagerAdapter.notifyDataSetChanged();
        return temp;
    }

    private int rand(int min, int max) {
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum = min + rn.nextInt(range);
        return randomNum;
    }

}
