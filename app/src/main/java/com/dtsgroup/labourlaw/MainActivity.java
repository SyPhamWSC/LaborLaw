package com.dtsgroup.labourlaw;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dtsgroup.labourlaw.adapter.DrawerLvAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.fragment.EnterGuideFragment;
import com.dtsgroup.labourlaw.fragment.QuizFragment;
import com.dtsgroup.labourlaw.helper.FullDrawerLayout;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.ItemLvDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.drawer_layout)
    FullDrawerLayout drawerLayout;
    @BindView(R.id.lv_drawer)
    ListView lvDrawer;

    private EnterGuideFragment enterGuideFragment;
    private Fragment quizFragment;
    private ActionBarDrawerToggle drawertoggle;

    private List<ItemLvDrawer> listItemDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inits();

        final DrawerLvAdapter drawerLvAdapter = new DrawerLvAdapter(this, listItemDrawer);
        lvDrawer.setAdapter(drawerLvAdapter);
        lvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLvAdapter.setItemSelected(position);
                drawerLvAdapter.notifyDataSetChanged();
                setFragmentToView(position);
            }
        });


    }

    private void inits() {
        ButterKnife.bind(this);
        drawertoggle = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(drawertoggle);
        drawerLayout.openDrawer(GravityCompat.START);

        enterGuideFragment = new EnterGuideFragment();
        quizFragment = new QuizFragment();

        listItemDrawer = getListItemDrawerView();
        callFragment(enterGuideFragment);
    }

    private List<ItemLvDrawer> getListItemDrawerView() {
        List<ItemLvDrawer> lv = new ArrayList<ItemLvDrawer>();

        String temp = getString(R.string.enter_guide);
        ItemLvDrawer enterGuide = new ItemLvDrawer(temp, R.mipmap.ic_guide_unselected, R.mipmap.ic_guide_selected);

        temp = getString(R.string.quiz);
        ItemLvDrawer quiz = new ItemLvDrawer(temp, R.mipmap.ic_quiz_unselect, R.mipmap.ic_quiz_selected);
        ItemLvDrawer bookmark = new ItemLvDrawer(getString(R.string.book_mark), R.mipmap.ic_bookmark_unselected, R.mipmap.ic_bookmark_selected);
        ItemLvDrawer askedQuestion = new ItemLvDrawer(getString(R.string.ask), R.mipmap.ic_question_unselected, R.mipmap.ic_question_selected);
        ItemLvDrawer updates = new ItemLvDrawer(getString(R.string.check_update), R.mipmap.ic_update_unselected, R.mipmap.ic_update_selected);
        ItemLvDrawer settings = new ItemLvDrawer(getString(R.string.settings), R.mipmap.ic_settings_unselected, R.mipmap.ic_settings_selected);

        lv.add(enterGuide);
        lv.add(quiz);
        lv.add(bookmark);
        lv.add(askedQuestion);
        lv.add(updates);
        lv.add(settings);

        return lv;
    }

    private void callFragment(Fragment fm){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fm_content, fm);
        transaction.commit();
    }

    private void setFragmentToView(int pos){
        switch (pos){
            case CommonVls.ENTER_GUIDE_POS:
                callFragment(enterGuideFragment);
                break;
            case CommonVls.QUIZ_POS:
                callFragment(quizFragment);
                break;
        }
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void onBackPressed() {
        FullDrawerLayout drawer = (FullDrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageHelper.onAttach(newBase));
    }
}
