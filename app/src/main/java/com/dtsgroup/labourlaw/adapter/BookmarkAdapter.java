package com.dtsgroup.labourlaw.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.activity.DetailBookmarkActivity;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.interaction.IClickListener;
import com.dtsgroup.labourlaw.model.ItemBookmark;

import io.realm.Realm;
import io.realm.RealmResults;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder>{

    private RealmResults<ItemBookmark> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private IClickListener clickListener;
    private Realm realm = Realm.getDefaultInstance();

    public BookmarkAdapter(Context context, RealmResults<ItemBookmark> list){
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
        final ItemBookmark itemBookmark = list.get(position);
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
                    mIntent.putExtra(CommonVls.BUNDLE_DETAIL_BOOKMARK,list.get(getAdapterPosition()).getId());
                    context.startActivity(mIntent);
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage(context.getResources().getString(R.string.delete_item));
                    alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.dismiss();
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                   RealmResults<ItemBookmark> itemBookmarks = realm.where(ItemBookmark.class).equalTo("id",getAdapterPosition()).findAll();
                                    itemBookmarks.deleteAllFromRealm();
                                    list = realm.where(ItemBookmark.class).findAll();
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    });

                    alertDialogBuilder.setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }
    }
}
