package com.dtsgroup.labourlaw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.QADetailsActivity;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.JSonItemQA;

import java.io.Serializable;
import java.util.List;

public class QAAdapter extends RecyclerView.Adapter<QAAdapter.ViewHolder> {
    private List<JSonItemQA> itemQAList;
    private LayoutInflater layoutInflater;
    private Context context;

    public QAAdapter(Context context, List<JSonItemQA> list) {
        this.context = context;
        this.itemQAList = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_qa, parent, false);
        return new QAAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JSonItemQA itemQA = itemQAList.get(position);

        String lang = LanguageHelper.getLanguage(context);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvTitle.setText(itemQA.getQuestionEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvTitle.setText(itemQA.getQuestionVi());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, QADetailsActivity.class);
                mIntent.putExtra(CommonVls.ITEM_QA, itemQA);
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemQAList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_qa);
        }
    }
}
