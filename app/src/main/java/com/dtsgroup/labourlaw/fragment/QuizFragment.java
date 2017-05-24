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
import com.dtsgroup.labourlaw.helper.SharePrefUtils;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemQuiz;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import io.realm.Realm;
import io.realm.RealmResults;

public class QuizFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "JSonItemQuiz";
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private ItemQuiz temp;

    private ItemQuiz question1, question2, question3, question4, question5, question6,
            question7, question8, question9, question10;

    private ArrayList<ItemQuiz> listAllQuestion = new ArrayList<ItemQuiz>();
    private ArrayList<ItemQuiz> listQuiz = new ArrayList<ItemQuiz>();

    private ImageView ivNext;
    private ImageView ivBack;
    private TextView tvPosItem;

    private int posItem = 0;
    private Realm realm = Realm.getDefaultInstance();
    private ShowQuizFragment frag1, frag2, frag3, frag4, frag5, frag6, frag7, frag8, frag9, frag10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ivNext = (ImageView) view.findViewById(R.id.iv_next);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        tvPosItem = (TextView) view.findViewById(R.id.tv_pos_fragment_quiz);
        viewPager = (ViewPager) view.findViewById(R.id.vp_show_quiz);

        ivBack.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivBack.setClickable(false);
        ivNext.setClickable(false);
        getAllQuestionQuiz();
        return view;
    }

    private ArrayList<ItemQuiz> createQuestion() {
        ArrayList<ItemQuiz> listQuizRandom = new ArrayList<ItemQuiz>();
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
        posItem = 0;
        RealmResults<ItemQuiz> realmResults = realm.where(ItemQuiz.class).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            listAllQuestion.add(realmResults.get(i));
        }
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
                tvPosItem.setText(String.valueOf(posItem+1).concat("/10"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        frag1 = (ShowQuizFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_show_quiz + ":" + 0);
        ivBack.setClickable(true);
        ivNext.setClickable(true);
    }

    private ItemQuiz randomQuestion(List<ItemQuiz> listQuestion) {
        Random rn = new Random();
        int rand = rn.nextInt(listQuestion.size());
        temp = listQuestion.get(rand);
        listQuestion.remove(rand);
        return temp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_next:
                if (posItem < 9) {
                    int curent_page = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(curent_page);
                    tvPosItem.setText(String.valueOf((curent_page+1)).concat("/10"));
                } else {
                    Intent mIntent = new Intent(getActivity(), ResultQuizActivity.class);
                    int result = SharePrefUtils.getScore(getActivity());
                    mIntent.putExtra(CommonVls.RESULT_QUIZ, result);
                    Bundle bundle = new Bundle();
                    bundle.putInt(CommonVls.ID_QUIZ_1, listQuiz.get(0).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_2, listQuiz.get(1).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_3, listQuiz.get(2).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_4, listQuiz.get(3).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_5, listQuiz.get(4).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_6, listQuiz.get(5).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_7, listQuiz.get(6).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_8, listQuiz.get(7).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_9, listQuiz.get(8).getId());
                    bundle.putInt(CommonVls.ID_QUIZ_10, listQuiz.get(9).getId());
                    mIntent.putExtras(bundle);
                    startActivity(mIntent);
                }
                break;
            case R.id.iv_back:
                int curent_page = viewPager.getCurrentItem() - 1;
                if(curent_page >= 0){
                    viewPager.setCurrentItem(curent_page);
                    tvPosItem.setText(String.valueOf(curent_page+1).concat("/10"));
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(QuizFragment.this)) {
            EventBus.getDefault().register(QuizFragment.this);
        }
        if (viewPager != null){
            viewPager.setCurrentItem(0);
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
        if (ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)) {
            pagerAdapter = new ShowQuizAdapter(getChildFragmentManager(), listQuiz);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(posItem);
        } else if (ev.getAction().equals(CommonVls.RELOAD_QUIZ)) {
            Log.e(TAG, "onMessageEvent: RELOAD_QUIZ");
            getAllQuestionQuiz();
            tvPosItem.setText((posItem + 1) + "/10");

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
        SharePrefUtils.updateScore(getActivity(),0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
