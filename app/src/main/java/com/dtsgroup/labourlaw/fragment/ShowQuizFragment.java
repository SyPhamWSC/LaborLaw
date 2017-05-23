package com.dtsgroup.labourlaw.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.helper.SharePrefUtils;
import com.dtsgroup.labourlaw.model.ItemQuiz;

import io.realm.Realm;

public class ShowQuizFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PAGE = "number";
    private static final String TAG = "ShowQuizFragment";
    private static final String ANSWER_A = "A";
    private static final String ANSWER_B = "B";
    private static final String ANSWER_C = "C";
    private static final String ANSWER_D = "D";

    private ViewGroup view;
    private int pageNumber;

    private RadioGroup rgContent;
    private RadioButton rbAnswerA;
    private RadioButton rbAnswerB;
    private RadioButton rbAnswerC;
    private RadioButton rbAnswerD;
    private TextView tvTitleQuestion;
    private Context context;
    private ItemQuiz itemQuiz;
    private String yourAnswer = "";
    private String trueAnswer;
    private Realm realm = Realm.getDefaultInstance();

    public static ShowQuizFragment create(int pageNumber, ItemQuiz itemQuiz){
        ShowQuizFragment fm = new ShowQuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt("quiz", itemQuiz.getId());
        fm.setArguments(args);
        return fm;
    }

    public ShowQuizFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARG_PAGE);
        int id = getArguments().getInt("quiz");
        itemQuiz = realm.where(ItemQuiz.class).equalTo("id",id).findFirst();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_show_quiz, container, false);

        rgContent = (RadioGroup) view.findViewById(R.id.rg_answer);
        rbAnswerA = (RadioButton) view.findViewById(R.id.rb_a);
        rbAnswerB = (RadioButton) view.findViewById(R.id.rb_b);
        rbAnswerC = (RadioButton) view.findViewById(R.id.rb_c);
        rbAnswerD = (RadioButton) view.findViewById(R.id.rb_d);
        tvTitleQuestion = (TextView) view.findViewById(R.id.tv_title_quiz);

        String lang = LanguageHelper.getLanguage(getParentFragment().getContext());

        if (lang.equals(CommonVls.ENGLISH)){
            tvTitleQuestion.setText(itemQuiz.getQuestionEn());
            rbAnswerA.setText(itemQuiz.getAnsaEn());
            rbAnswerB.setText(itemQuiz.getAnsbEn());
            rbAnswerC.setText(itemQuiz.getAnscEn());
            rbAnswerD.setText(itemQuiz.getAnsdEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)){
            tvTitleQuestion.setText(itemQuiz.getQuestionVi());
            rbAnswerA.setText(itemQuiz.getAnsaVi());
            rbAnswerB.setText(itemQuiz.getAnsbVi());
            rbAnswerC.setText(itemQuiz.getAnscVi());
            rbAnswerD.setText(itemQuiz.getAnsdVi());
        }
        trueAnswer = itemQuiz.getAnswer();

        rbAnswerA.setOnClickListener(this);
        rbAnswerB.setOnClickListener(this);
        rbAnswerC.setOnClickListener(this);
        rbAnswerD.setOnClickListener(this);

        return view;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public void onStart() {
        super.onStart();
        rbAnswerA.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_a :
                yourAnswer = ANSWER_A;
                break;
            case R.id.rb_b :
                yourAnswer = ANSWER_B;
                break;
            case R.id.rb_c :
                yourAnswer = ANSWER_C;
                break;
            case R.id.rb_d :
                yourAnswer = ANSWER_D;
                break;
        }
        resultAnswerQuiz();
    }

    public void resultAnswerQuiz(){
        if(yourAnswer.equals(trueAnswer)){
            int score = SharePrefUtils.getScore(getActivity());
            score++;
            SharePrefUtils.updateScore(getActivity(),score);
        }
    }
}
