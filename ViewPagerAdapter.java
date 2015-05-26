package com.reach.reach_app_v10;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    private FragmentActivity _fragActivity;
    public static int totalPage=3;
    public ViewPagerAdapter(Context context, FragmentManager fm, FragmentActivity fa) {
        super(fm);
        _fragActivity = fa;
        _context=context;

    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch(position){
            case 0:
                f=Walkthrough1.newInstance(_context);
                break;
            case 1:
                f=Walkthrough2.newInstance(_context);
                break;
            case 2:
                f=Walkthrough3.newInstance(_context, _fragActivity);
                break;
        }
        return f;
    }
    @Override
    public int getCount() {
        return totalPage;
    }

}