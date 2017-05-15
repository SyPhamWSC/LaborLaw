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
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage(context.getResources().getString(R.string.delete_item));
                    alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl(CommonVls.GET_URL)
//                                    .addConverterFactory(GsonConverterFactory.create())
//                                    .build();
//                            APIService apiService = retrofit.create(APIService.class);
//                            Call<JSonItemBookmark> call = apiService.deleteitem(list.get(getAdapterPosition()).getId());
//                            call.enqueue(new Callback<JSonItemBookmark>() {
//                                @Override
//                                public void onResponse(Call<JSonItemBookmark> call, Response<JSonItemBookmark> response) {
//                                    EventBus.getDefault().post(new EventMessage(CommonVls.UPDATE_ADAPTER));
//                                }
//
//                                @Override
//                                public void onFailure(Call<JSonItemBookmark> call, Throwable t) {
//
//                                }
//                            });

                            Toast.makeText(context,"Needed API",Toast.LENGTH_SHORT).show();
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
