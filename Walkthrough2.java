package com.reach.reach_app_v10;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Walkthrough2 extends Fragment {

    public static Fragment newInstance(Context context) {
        Walkthrough2 f = new Walkthrough2();

        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_walkthrough2, null);
       //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_walkthrough2, container, false);
       // ImageView imageview = (ImageView)root.findViewById(R.id.imageView);
       /// View root = inflater.inflate(R.layout.fragment_walkthrough2, container, false);
        View v = inflater.inflate(R.layout.fragment_walkthrough2, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView2);

        return v;
    }

}