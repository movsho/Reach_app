package com.reach.reach_app_v10;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.reach.reach_app_v10.util.GeneralData;
import com.reach.reach_app_v10.util.ParseObjectUserClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;



public class Walkthrough3 extends Fragment {

    private static FragmentActivity _fragActivity;

    public static Fragment newInstance(Context context, FragmentActivity fragActiv) {
        Walkthrough3 f = new Walkthrough3();
        _fragActivity = fragActiv;
        return f;
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_walkthrough3, null);
        return root;
    }*/
    private Dialog progressDialog;
    // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
    // (https://developers.facebook.com/docs/facebook-login/permissions/)
    List<String> permissions = Arrays.asList("public_profile");
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {

            return null;
        }

        RelativeLayout mLinearLayout;
        mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_walkthrough3,
                container, false);

        // note that we're looking for a button with id="@+id/myButton" in your inflated layout
        // Naturally, this can be any View; it doesn't have to be a button
        Button mButton = (Button) mLinearLayout.findViewById(R.id.button_start);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button,
                // e.g. launch a new activity
                progressDialog = ProgressDialog.show(v.getContext(), "", "Logging in...", true);

                ParseFacebookUtils.logInWithReadPermissionsInBackground(_fragActivity, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        progressDialog.dismiss();
                        if (user == null) {
//                        Log.d(TAG, "Uh oh. The user cancelled the Facebook login.");
                        } else {
                            GeneralData.setCurrentLoginUser(user);
                            Intent intent = new Intent(getActivity().getApplicationContext(), TopicList.class);
                            startActivityForResult(intent, 4);

                            if (GeneralData.getCurrentUserClass() == null) {
                                ParseQuery<ParseObjectUserClass> u = new ParseQuery<ParseObjectUserClass>("UserClass");
                                u.whereEqualTo("user", user);
                                u.findInBackground(new FindCallback<ParseObjectUserClass>() {
                                    @Override
                                    public void done(List<ParseObjectUserClass> list, ParseException e) {
                                        if (e != null) {
                                            Log.w("ParseObjectUserClass Query", e.getMessage());
                                            return;
                                        }
                                        if (list.size() > 1) {
                                            Log.w("ParseObjectUserClass duplicate", "More than 1 user with id!");
                                            return;
                                        }
                                        if (list.size() < 1) {
                                            Log.w("ParseObjectUserClass duplicate", "No users with with id!");
                                            ParseObjectUserClass u = new ParseObjectUserClass();
                                            u.put("user", GeneralData.getCurrentLoginUser());
                                            u.saveInBackground();
                                            GeneralData.setCurrentUserClass(u);
                                        }
                                        GeneralData.setCurrentUserClass(list.get(0));
                                    }
                                });
                            }
                        }
//                        if (user.isNew()) {
//                            GeneralData.setCurrentUser(user);
////                        Log.d(TAG, "User signed up and logged in through Facebook!");
//                            Log.d("HEY", "FB!!");
////                                    showUserDetailsActivity();
//                        } else {
////                        Log.d(TAG, "User logged in through Facebook!");
//                            GeneralData.setCurrentUser(user);
//                            Log.d("HEY", "FB!!");
////                                    showUserDetailsActivity();
//                            Intent intent = new Intent(getActivity().getApplicationContext(), TopicList.class);
//                            startActivityForResult(intent, 4);
//                        }
                    }
                });
            }
        });

        // after you've done all your manipulation, return your layout to be shown
        return mLinearLayout;
    }
}