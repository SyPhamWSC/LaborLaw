package com.dtsgroup.labourlaw.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ActivityAppendix;
import com.dtsgroup.labourlaw.activity.IntroduceActivity;
import com.dtsgroup.labourlaw.activity.SubChapterLawActivity;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.ChapterLaw;

import io.realm.RealmResults;

public class EnterGuideLvAdapter extends RecyclerView.Adapter<EnterGuideLvAdapter.ViewHolder> {
    private RealmResults<ChapterLaw> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public EnterGuideLvAdapter(Context context,RealmResults<ChapterLaw> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_enter_guide, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChapterLaw item = list.get(position);

        String lang = LanguageHelper.getLanguage(context);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvTitle.setText(item.getSname_chapter_en());
            holder.tvShorDescreption.setText(item.getDescription_en());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvTitle.setText(item.getSname_chapter_vi());
            holder.tvShorDescreption.setText(item.getDescription_vi());
        }
        holder.tvCode.setText(String.valueOf(item.getId() - 1));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvShorDescreption;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_postion);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvShorDescreption = (TextView) itemView.findViewById(R.id.tv_short_descreption);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChapterLaw item = list.get(getAdapterPosition());
                    if(item.getType_chapter().equals("content")){
                        Intent mIntent = new Intent(itemView.getContext(),SubChapterLawActivity.class);
                        int chapter = item.getId() - 1;
                        mIntent.putExtra(CommonVls.SUB_CHAPTER_LAW,chapter);
                        context.startActivity(mIntent);
                    }else if(item.getType_chapter().equals("addendum")){
                        Intent intent = new Intent(context, ActivityAppendix.class);
                        context.startActivity(intent);
                    }
                    else if(item.getType_chapter().equals("introduce")){
                        Intent mIntent = new Intent(itemView.getContext(), IntroduceActivity.class);
                        mIntent.putExtra(CommonVls.INTRODUCE_VI,item.getDescription_vi());
                        mIntent.putExtra(CommonVls.INTRODUCE_EN,item.getDescription_en());
                        context.startActivity(mIntent);
                    }
                }
            });
        }
    }
}
