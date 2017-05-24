package com.dtsgroup.labourlaw.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.ItemQuiz;

import java.util.List;

public class ReviewQuizAdapter extends RecyclerView.Adapter<ReviewQuizAdapter.ViewHolder>{

    private List<ItemQuiz> listQuiz;
    private LayoutInflater layoutInflater;
    private Context context;

    public ReviewQuizAdapter(Context context, List<ItemQuiz> listQuiz){
        this.listQuiz = listQuiz;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_review_quiz,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemQuiz item = listQuiz.get(position);

        String answer = item.getAnswer();

        String lang = LanguageHelper.getLanguage(context);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvTitle.setText(item.getQuestionEn());
            switch (answer){
                case "A":
                    holder.tvAnswer.setText(item.getAnsaEn());
                    break;
                case "B":
                    holder.tvAnswer.setText(item.getAnsbEn());
                    break;
                case "C":
                    holder.tvAnswer.setText(item.getAnscEn());
                    break;
                case "D":
                    holder.tvAnswer.setText(item.getAnsdEn());
                    break;
            }
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvTitle.setText(item.getQuestionVi());
            switch (answer){
                case "A":
                    holder.tvAnswer.setText(item.getAnsaVi());
                    break;
                case "B":
                    holder.tvAnswer.setText(item.getAnsbVi());
                    break;
                case "C":
                    holder.tvAnswer.setText(item.getAnscVi());
                    break;
                case "D":
                    holder.tvAnswer.setText(item.getAnsdVi());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return listQuiz.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAnswer;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_ques_rv_quiz);
            tvAnswer = (TextView) itemView.findViewById(R.id.tv_answer_rv_quiz);
        }
    }
}
