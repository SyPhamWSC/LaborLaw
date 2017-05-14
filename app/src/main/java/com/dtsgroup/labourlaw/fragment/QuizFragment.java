package com.dtsgroup.labourlaw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ResultQuizActivity;
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


public class QuizFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "JSonItemQuiz";
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private JSonItemQuiz temp;

    private JSonItemQuiz question1, question2, question3, question4, question5, question6,
            question7, question8, question9, question10;

    private ArrayList<JSonItemQuiz> listAllQuestion = new ArrayList<JSonItemQuiz>();
    private ArrayList<JSonItemQuiz> listQuiz = new ArrayList<JSonItemQuiz>();

    private ImageView ivNext;
    private ImageView ivBack;
    private TextView tvPosItem;
    private int posItem = 0;
    private ShowQuizFragment f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        getAllQuestionQuiz();

        ivNext = (ImageView) view.findViewById(R.id.iv_next);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        tvPosItem = (TextView) view.findViewById(R.id.tv_pos_fragment_quiz);

        ivBack.setOnClickListener(this);
        ivNext.setOnClickListener(this);

        return view;
    }

    private ArrayList<JSonItemQuiz> createQuestion() {
        ArrayList<JSonItemQuiz> listQuizRandom = new ArrayList<JSonItemQuiz>();
        question1 = randomQuestion(listAllQuestion);
        question2 = randomQuestion(listAllQuestion);
        question3 = randomQuestion(listAllQuestion);
        question4 = randomQuestion(listAllQuestion);
        question5 = randomQuestion(listAllQuestion);
        question6 = randomQuestion(listAllQuestion);
        question7 = randomQuestion(listAllQuestion);
        question8 = randomQuestion(listAllQuestion);
        question9 = randomQuestion(listAllQuestion);
        question10 = randomQuestion(listAllQuestion);
        listQuizRandom.add(question1);
        listQuizRandom.add(question2);
        listQuizRandom.add(question3);
        listQuizRandom.add(question4);
        listQuizRandom.add(question5);
        listQuizRandom.add(question6);
        listQuizRandom.add(question7);
        listQuizRandom.add(question8);
        listQuizRandom.add(question9);
        listQuizRandom.add(question10);

        return listQuizRandom;
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
                listAllQuestion.clear();
                for (int i = 0; i < listTemp.size(); i++) {
                    listAllQuestion.add(listTemp.get(i));
                }
                Log.i(TAG, listAllQuestion.get(0).getAnsaEn());
                viewPager = (ViewPager) getActivity().findViewById(R.id.vp_show_quiz);
                listQuiz = createQuestion();
                pagerAdapter = new ShowQuizAdapter(getChildFragmentManager(), listQuiz);
                viewPager.setAdapter(pagerAdapter);
                viewPager.setCurrentItem(0);
                f1 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 0);
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
        listQuestion.remove(rand);
        return temp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_next:
                if (posItem < 9) {
                    switch (posItem){
                        case 0:
                            f1 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 0);
                            break;
                        case 1:
                            f2 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 1);
                            break;
                        case 2:
                            f3 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 2);
                            break;

                        case 3:
                            f4 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 3);
                            break;
                        case 4:
                            f5 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 4);
                            break;
                        case 5:
                            f6 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 5);
                            break;
                        case 6:
                            f7 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 6);
                            break;
                        case 7:
                            f8 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 7);
                            break;
                        case 8:
                            f9 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 8);
                            break;


                    }
                    posItem = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(posItem);
                    tvPosItem.setText(posItem + 1 + "/10");
                } else {
                    f10 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 9);
                    Intent mIntent = new Intent(getActivity(), ResultQuizActivity.class);
                    mIntent.putExtra(CommonVls.RESULT_QUIZ,result());
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_1,listQuiz.get(0));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_2,listQuiz.get(1));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_3,listQuiz.get(2));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_4,listQuiz.get(3));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_5,listQuiz.get(4));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_6,listQuiz.get(5));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_7,listQuiz.get(6));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_8,listQuiz.get(7));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_9,listQuiz.get(8));
                    mIntent.putExtra(CommonVls.QUESTION_QUIZ_10,listQuiz.get(9));
                    startActivity(mIntent);
                }
                break;
            case R.id.iv_back:
                if (posItem > 0) {
                    posItem = viewPager.getCurrentItem() - 1;
                    viewPager.setCurrentItem(posItem);
                    tvPosItem.setText(posItem + 1 + "/10");
                }
                break;
        }
    }
    private int result(){
        int resultQuiz = 0;
            resultQuiz = f1.resultAnswerQuiz() + f2.resultAnswerQuiz() + f3.resultAnswerQuiz()
                    + f4.resultAnswerQuiz()+ f5.resultAnswerQuiz()+ f6.resultAnswerQuiz()
                    + f7.resultAnswerQuiz()+ f8.resultAnswerQuiz()+ f9.resultAnswerQuiz()+ f10.resultAnswerQuiz();

        return resultQuiz;
    }
}
