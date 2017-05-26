package com.dtsgroup.labourlaw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.ActivityDetailAppendix;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.ItemAppendix;

import io.realm.RealmResults;


public class AppendixAdpater extends RecyclerView.Adapter<AppendixAdpater.MyViewHolder> {
    private Context mContext;
    private RealmResults<ItemAppendix> list;

    public AppendixAdpater(Context mContext, RealmResults<ItemAppendix> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_appendix,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemAppendix appendix = list.get(position);
        holder.tvNumAppendix.setText(String.valueOf(appendix.getId()));
        String lang = LanguageHelper.getLanguage(mContext);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvTitleAppendix.setText(appendix.getTitleEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvTitleAppendix.setText(appendix.getTitleVi());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityDetailAppendix.class);
                intent.putExtra(CommonVls.BUNDLE_APPENDIX,appendix.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumAppendix, tvTitleAppendix;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNumAppendix = (TextView) itemView.findViewById(R.id.tv_number_appendix);
            tvTitleAppendix = (TextView) itemView.findViewById(R.id.tv_title_appendix);
        }
    }
}
