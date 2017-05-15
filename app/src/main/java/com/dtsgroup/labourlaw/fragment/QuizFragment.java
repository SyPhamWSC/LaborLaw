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

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ResultQuizActivity;
import com.dtsgroup.labourlaw.adapter.ShowQuizAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;
import com.dtsgroup.labourlaw.service.APIService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ShowQuizFragment frag1, frag2, frag3, frag4, frag5, frag6, frag7, frag8, frag9, frag10;

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
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        posItem = position;
                        tvPosItem.setText((posItem + 1) + "/10");
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                frag1 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 0);
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
                            frag1 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 0);
                            break;
                        case 1:
                            frag2 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 1);
                            break;
                        case 2:
                            frag3 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 2);
                            break;

                        case 3:
                            frag4 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 3);
                            break;
                        case 4:
                            frag5 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 4);
                            break;
                        case 5:
                            frag6 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 5);
                            break;
                        case 6:
                            frag7 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 6);
                            break;
                        case 7:
                            frag8 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 7);
                            break;
                        case 8:
                            frag9 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 8);
                            break;


                    }
                    posItem = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(posItem);
                    tvPosItem.setText(posItem + 1 + "/10");
                } else {
                    frag10 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz +":" + 9);
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
            resultQuiz = frag1.resultAnswerQuiz() + frag2.resultAnswerQuiz() + frag3.resultAnswerQuiz()
                    + frag4.resultAnswerQuiz()+ frag5.resultAnswerQuiz()+ frag6.resultAnswerQuiz()
                    + frag7.resultAnswerQuiz()+ frag8.resultAnswerQuiz()+ frag9.resultAnswerQuiz()+ frag10.resultAnswerQuiz();

        return resultQuiz;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(QuizFragment.this)) {
            EventBus.getDefault().register(QuizFragment.this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(QuizFragment.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        Log.e(TAG, "from quiz fragment");
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            pagerAdapter = new ShowQuizAdapter(getChildFragmentManager(), listQuiz);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(posItem);
        }

    }
}
