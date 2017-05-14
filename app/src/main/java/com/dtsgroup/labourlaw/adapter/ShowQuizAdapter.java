package com.dtsgroup.labourlaw.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtsgroup.labourlaw.common.CommonVls;
import com.dtsgroup.labourlaw.fragment.ShowQuizFragment;
import com.dtsgroup.labourlaw.model.JSonItemQuiz;

import java.util.ArrayList;

public class ShowQuizAdapter extends FragmentPagerAdapter{

   private ArrayList<JSonItemQuiz> list;
    private int result1 = 0;
    private int result2 = 0;
    private int result3 = 0;
    private int result4 = 0;
    private int result5 = 0;
    private int result6 = 0;
    private int result7 = 0;
    private int result8 = 0;
    private int result9 = 0;
    private int result10 = 0;
    private ShowQuizFragment f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    public ShowQuizAdapter(FragmentManager fm) {
        super(fm);
    }

    public ShowQuizAdapter(FragmentManager fm,ArrayList<JSonItemQuiz> list){
        super(fm);
       this.list = list;
        f1 = ShowQuizFragment.create(0,list.get(0));
        f2 = ShowQuizFragment.create(1,list.get(1));
        f3 = ShowQuizFragment.create(2,list.get(2));
        f4 = ShowQuizFragment.create(3,list.get(3));
        f5 = ShowQuizFragment.create(4,list.get(4));
        f6 = ShowQuizFragment.create(5,list.get(5));
        f7 = ShowQuizFragment.create(6,list.get(6));
        f8 = ShowQuizFragment.create(7,list.get(7));
        f9 = ShowQuizFragment.create(8,list.get(8));
        f10 = ShowQuizFragment.create(9,list.get(9));
    }

    @Override
    public Fragment getItem(int position) {
        return callFragment(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public Fragment callFragment(int pos){
        ShowQuizFragment f = null;
        switch (pos){
            case 0:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f = f1;
                break;
            case 1:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f2;
                break;
            case 2:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f3;
                break;
            case 3:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f4;
                break;
            case 4:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f5;
                break;
            case 5:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f6;
                break;
            case 6:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f7;
                break;
            case 7:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f8;
                break;
            case 8:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f9;
                break;
            case 9:
//                f = ShowQuizFragment.create(pos,list.get(pos));
                f=f10;
                break;
        }
        return f;
    }
}
