package com.dtsgroup.labourlaw;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dtsgroup.labourlaw.activity.SearchActivity;
import com.dtsgroup.labourlaw.adapter.DrawerLvAdapter;
import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.fragment.AskedFragment;
import com.dtsgroup.labourlaw.fragment.BookmarkFragment;
import com.dtsgroup.labourlaw.fragment.EnterGuideFragment;
import com.dtsgroup.labourlaw.fragment.QuizFragment;
import com.dtsgroup.labourlaw.fragment.SettingsFragment;
import com.dtsgroup.labourlaw.fragment.UpdatesFragment;
import com.dtsgroup.labourlaw.helper.FullDrawerLayout;
import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.model.EventMessage;
import com.dtsgroup.labourlaw.model.ItemLvDrawer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.drawer_layout)
    FullDrawerLayout drawerLayout;
    @BindView(R.id.lv_drawer)
    ListView lvDrawer;
    @BindView(R.id.tb_main)
    Toolbar tbMain;

    private EnterGuideFragment enterGuideFragment;
    private QuizFragment quizFragment;
    private BookmarkFragment bookmarkFragment;
    private UpdatesFragment updatesFragment;
    private SettingsFragment settingsFragment;
    private AskedFragment askedFragment;

    private int pos = 0; // get position of Fragment is showing;

    private ActionBarDrawerToggle drawertoggle;
    private DrawerLvAdapter drawerLvAdapter;

    private List<ItemLvDrawer> listItemDrawer;

    private FragmentManager manager;
    FragmentTransaction transaction;

    private Realm realm = Realm.getDefaultInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);

        this.realm = Realm.getDefaultInstance();


        inits();

        drawerLvAdapter = new DrawerLvAdapter(this, listItemDrawer);
        lvDrawer.setAdapter(drawerLvAdapter);
        lvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLvAdapter.setItemSelected(position);
                drawerLvAdapter.notifyDataSetChanged();
                pos = position;
                setFragmentToView(position);
                setTitleToolBar(position);
            }
        });


    }

    private void setTitleToolBar(int position) {
        switch (position){
            case CommonVls.ENTER_GUIDE_POS:
                tbMain.setTitle(getResources().getString(R.string.enter_guide));
                break;
            case CommonVls.QUIZ_POS:
                String temp = getResources().getString(R.string.quiz);
                tbMain.setTitle(getResources().getString(R.string.quiz));
                Log.i(TAG,temp);
                break;
            case CommonVls.BOOKMARKS_POS:
                tbMain.setTitle(getResources().getString(R.string.book_mark));
                break;
            case CommonVls.ASKED_POS:
                tbMain.setTitle(getResources().getString(R.string.ask));
                break;
            case CommonVls.UPDATES_POS:
                tbMain.setTitle(getResources().getString(R.string.check_update));
                break;
            case CommonVls.SETTINGS_POS:
                tbMain.setTitle(getString(R.string.settings));
                break;
        }
    }

    private void initFragment() {
        enterGuideFragment = new EnterGuideFragment();
        quizFragment = new QuizFragment();
        bookmarkFragment = new BookmarkFragment();
        askedFragment = new AskedFragment();
        updatesFragment = new UpdatesFragment();
        settingsFragment = new SettingsFragment();
    }

    private void inits() {
        ButterKnife.bind(this);
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawertoggle = new ActionBarDrawerToggle(this, drawerLayout, tbMain, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(drawertoggle);
        drawerLayout.openDrawer(GravityCompat.START);


        tbMain.setTitle(getResources().getString(R.string.enter_guide));

        initFragment();

        listItemDrawer = getListItemDrawerView();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        callFragment(enterGuideFragment);
    }

    private List<ItemLvDrawer> getListItemDrawerView() {
        List<ItemLvDrawer> lv = new ArrayList<ItemLvDrawer>();

        ItemLvDrawer enterGuide = new ItemLvDrawer(getResources().getString(R.string.enter_guide), R.mipmap.ic_guide_unselected, R.mipmap.ic_guide_selected);
        ItemLvDrawer quiz = new ItemLvDrawer(getResources().getString(R.string.quiz), R.mipmap.ic_quiz_unselect, R.mipmap.ic_quiz_selected);
        ItemLvDrawer bookmark = new ItemLvDrawer(getResources().getString(R.string.book_mark), R.mipmap.ic_bookmark_unselected, R.mipmap.ic_bookmark_selected);
        ItemLvDrawer askedQuestion = new ItemLvDrawer(getResources().getString(R.string.ask), R.mipmap.ic_question_unselected, R.mipmap.ic_question_selected);
        ItemLvDrawer updates = new ItemLvDrawer(getResources().getString(R.string.check_update), R.mipmap.ic_update_unselected, R.mipmap.ic_update_selected);
        ItemLvDrawer settings = new ItemLvDrawer(getResources().getString(R.string.settings), R.mipmap.ic_settings_unselected, R.mipmap.ic_settings_selected);

        lv.add(enterGuide);
        lv.add(quiz);
        lv.add(bookmark);
        lv.add(askedQuestion);
        lv.add(updates);
        lv.add(settings);

        return lv;
    }

    private void callFragment(Fragment fm) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fm_fragment, fm);
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
            case CommonVls.BOOKMARKS_POS:
                callFragment(bookmarkFragment);
                break;
            case CommonVls.ASKED_POS:
                callFragment(askedFragment);
                break;
            case CommonVls.UPDATES_POS:
                callFragment(updatesFragment);
                break;
            case CommonVls.SETTINGS_POS:
                callFragment(settingsFragment);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.menu_translate:
                String lang = LanguageHelper.getLanguage(MainActivity.this);
                if (lang.equals(CommonVls.ENGLISH)){
                    updateViews(CommonVls.VIETNAMESE);
                }else {
                    updateViews(CommonVls.ENGLISH);
                }
                EventBus.getDefault().post(new EventMessage(CommonVls.ACTION_UPDATE_LANGUAGE));
                break;
            case R.id.menu_search:
                Intent mIntent = new Intent(this, SearchActivity.class);
                startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateViews(String languageCode) {
        Context context = LanguageHelper.setLocale(this,languageCode);
        setTitleToolBar(pos);
        List<ItemLvDrawer> list = getListItemDrawerView();
        listItemDrawer.clear();
        for (int i = 0; i < list.size(); i++) {
            listItemDrawer.add(list.get(i));
        }
        drawerLvAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(MainActivity.this)){
            EventBus.getDefault().register(MainActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
        EventBus.getDefault().unregister(MainActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage ev) {
        Log.e(TAG, "onMessageEvent from MainActivity: " );
        if(ev.getAction().equals(CommonVls.ACTION_UPDATE_LANGUAGE)){
            setTitleToolBar(pos);
            List<ItemLvDrawer> list = getListItemDrawerView();
            listItemDrawer.clear();
            for (int i = 0; i < list.size(); i++) {
                listItemDrawer.add(list.get(i));
            }
           drawerLvAdapter.notifyDataSetChanged();
        }else if(ev.getAction().equals(CommonVls.OPEN_DRAWER_LAYOUT)){
            drawerLayout.openDrawer(Gravity.START);
        }
    }
}
