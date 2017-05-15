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
import com.dtsgroup.labourlaw.model.JSonItemBookmark;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder>{

    private List<JSonItemBookmark> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public BookmarkAdapter(Context context, List<JSonItemBookmark> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChapter;
        private TextView tvNameChapter;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter_bookmark);
            tvNameChapter = (TextView) itemView.findViewById(R.id.tv_name_chapter_bookmark);
        }
    }
}
