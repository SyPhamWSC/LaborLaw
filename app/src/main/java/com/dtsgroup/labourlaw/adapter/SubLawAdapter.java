package com.dtsgroup.labourlaw.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ActivityDetailLaw;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;
import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;

import java.util.List;

public class SubLawAdapter  extends RecyclerView.Adapter<SubLawAdapter.ViewHolder>{

    private List<JSonItemSubChapterLaw> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public SubLawAdapter(Context context, List<JSonItemSubChapterLaw> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_sub_law, parent, false);
        return new SubLawAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JSonItemSubChapterLaw item = list.get(position);

        String lang = LanguageHelper.getLanguage(context);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvTitle.setText(item.getChapterNameEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvTitle.setText(item.getChapterNameVi());
        }
        holder.tvCode.setText(item.getSubChapter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDetailLaw.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(CommonVls.KEY_DETAIL_LAW,item);
                intent.putExtra(CommonVls.BUNDLE_DETAIL_LAW,bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_chapter_sub_law);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_sub_law);
        }
    }
}
