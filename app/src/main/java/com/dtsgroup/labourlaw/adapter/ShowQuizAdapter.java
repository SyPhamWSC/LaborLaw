package com.dtsgroup.labourlaw.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dtsgroup.labourlaw.common.CommonVls;

public class ShowQuizAdapter extends FragmentPagerAdapter{

    private Fragment f1;
    private Fragment f2;
    private Fragment f3;
    private Fragment f4;
    private Fragment f5;
    private Fragment f6;
    private Fragment f7;
    private Fragment f8;
    private Fragment f9;
    private Fragment f10;
    public ShowQuizAdapter(FragmentManager fm) {
        super(fm);
    }

    public ShowQuizAdapter(FragmentManager fm, Fragment f1, Fragment f2,
                           Fragment f3, Fragment f4, Fragment f5,
                           Fragment f6, Fragment f7, Fragment f8,
                           Fragment f9, Fragment f10){
        super(fm);
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.f5 = f5;
        this.f6 = f6;
        this.f7 = f7;
        this.f8 = f8;
        this.f9 = f9;
        this.f10 = f10;
    }

    @Override
    public Fragment getItem(int position) {
        return callFragment(position);
    }

    @Override
    public int getCount() {
        return CommonVls.NUMBER_QUIZ;
    }

    public Fragment callFragment(int pos){
        Fragment f = null;
        switch (pos){
            case 0:
                f = f1;
                break;
            case 1:
                f = f2;
                break;
            case 2:
                f = f3;
                break;
            case 3:
                f = f4;
                break;
            case 4:
                f = f5;
                break;
            case 5:
                f = f6;
                break;
            case 6:
                f = f7;
                break;
            case 7:
                f = f8;
                break;
            case 8:
                f = f9;
                break;
            case 9:
                f = f10;
                break;
        }
        return f;
    }
}
