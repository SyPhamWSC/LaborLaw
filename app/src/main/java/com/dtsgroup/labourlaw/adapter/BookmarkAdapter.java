package com.dtsgroup.labourlaw.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.DetailBookmarkActivity;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.interaction.IClickListener;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.JSonItemBookmark;
import com.dtsgroup.labourlaw.service.APIService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder>{

    private List<JSonItemBookmark> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private IClickListener clickListener;

    public BookmarkAdapter(Context context, List<JSonItemBookmark> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setClickListener(IClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_bookmark, parent, false);
        return new BookmarkAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JSonItemBookmark itemBookmark = list.get(position);
        String lang = LanguageHelper.getLanguage(context);
        if (lang.equals(CommonVls.ENGLISH)) {
            holder.tvNameChapter.setText(itemBookmark.getNameEn());
        } else if (lang.equals(CommonVls.VIETNAMESE)) {
            holder.tvNameChapter.setText(itemBookmark.getNameVi());
        }
        holder.tvChapter.setText(itemBookmark.getChapter());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onRemoveBookmark(itemBookmark.getChapter());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        final JSonItemBookmark itemBookmark = list.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChapter;
        private TextView tvNameChapter;
        private ImageView ivDelete;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter_bookmark);
            tvNameChapter = (TextView) itemView.findViewById(R.id.tv_name_chapter_bookmark);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delte_item_bookmark);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(itemView.getContext(), DetailBookmarkActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(CommonVls.KEY_DETAIL_BOOKMARK,list.get(getAdapterPosition()));
                    mIntent.putExtra(CommonVls.BUNDLE_DETAIL_BOOKMARK,bundle);
                    context.startActivity(mIntent);
                }
            });
        }
    }
}
