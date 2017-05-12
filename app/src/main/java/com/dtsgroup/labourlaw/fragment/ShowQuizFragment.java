package com.dtsgroup.labourlaw.fragment;


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
import com.dtsgroup.labourlaw.model.JSonItemQuiz;

public class ShowQuizFragment extends Fragment {

    private static final String ARG_PAGE = "number";

    private ViewGroup view;
    private int pageNumber;

    private RadioGroup rgContent;
    private RadioButton rbAnswerA;
    private RadioButton rbAnswerB;
    private RadioButton rbAnswerC;
    private RadioButton rbAnswerD;
    private TextView tvTitleQuestion;

    public static ShowQuizFragment create(int pageNumber){
        ShowQuizFragment fm = new ShowQuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fm.setArguments(args);
        return fm;
    }

    public ShowQuizFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARG_PAGE);
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

        return view;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setTextToAnswer(JSonItemQuiz questionQuiz){
//        String lang = LanguageHelper.getLanguage(getContext());

//        if (lang.equals(CommonVls.ENGLISH)){
            tvTitleQuestion.setText(questionQuiz.getQuestionEn());
            rbAnswerA.setText(questionQuiz.getAnsaEn());
            rbAnswerB.setText(questionQuiz.getAnsbEn());
            rbAnswerC.setText(questionQuiz.getAnscEn());
            rbAnswerD.setText(questionQuiz.getAnsdEn());
//        } else if (lang.equals(CommonVls.VIETNAMESE)){
//            tvTitleQuestion.setText(questionQuiz.getQuestionVi());
//            rbAnswerA.setText(questionQuiz.getAnsaVi());
//            rbAnswerB.setText(questionQuiz.getAnsbVi());
//            rbAnswerC.setText(questionQuiz.getAnscVi());
//            rbAnswerD.setText(questionQuiz.getAnsdVi());
//        }

    }
}
