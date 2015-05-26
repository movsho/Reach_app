package com.reach.reach_app_v10;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.parse.ParseFacebookUtils;

public class Tutorial extends FragmentActivity {



    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    private Button _btn1,_btn2,_btn3;
    private FragmentActivity _tutorial;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _tutorial = Tutorial.this;
        setContentView(R.layout.main);
        setUpView();
        setTab();


    }
    private void setUpView(){
        _mViewPager = (ViewPager) findViewById(R.id.viewPager);
        _adapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager(), Tutorial.this);
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton();
    }
    public FragmentActivity getActivity ()
    {
        if (_tutorial != null)
            return _tutorial;
        return null;
    }
    private void setTab(){
        _mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                btnAction(position);
            }

        });
    }
    private void btnAction(int action){
        switch(action){
            case 0: setButton(_btn1,"",20,20, Color.BLACK); setButton(_btn2,"",15,15, Color.WHITE); setButton(_btn3, "", 15, 15, Color.WHITE); break;

            case 1: setButton(_btn2,"",20,20, Color.BLACK); setButton(_btn1,"",15,15, Color.WHITE); setButton(_btn3, "", 15, 15, Color.WHITE); break;

            case 2: setButton(_btn3,"",20,20, Color.BLACK); setButton(_btn2,"",15,15, Color.WHITE); setButton(_btn1,"",15,15, Color.WHITE); break;
        }
    }
    private void initButton(){
        _btn1=(Button)findViewById(R.id.btn1);
        _btn2=(Button)findViewById(R.id.btn2);
        _btn3=(Button)findViewById(R.id.btn3);
        setButton(_btn1, "", 20, 20, Color.WHITE);
        setButton(_btn2, "", 15, 15, Color.WHITE);
        setButton(_btn3, "", 15, 15, Color.WHITE);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setButton(Button btn,String text,int h, int w, int color){
        ViewGroup.LayoutParams params = btn.getLayoutParams();
        params.height = h;
        params.width = w;
        btn.setLayoutParams(params);
        //((InsetDrawable)btn.getBackground()).setColorFilter(color, Color.BLUE);
        btn.setBackground(getDrawable(R.drawable.rounded_cell_black));
        btn.setText(text);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}