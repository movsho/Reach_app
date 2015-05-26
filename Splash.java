package com.reach.reach_app_v10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.reach.reach_app_v10.util.GeneralData;
import com.reach.reach_app_v10.util.ParseObjectBroadcastItem;
import com.reach.reach_app_v10.util.ParseObjectHeaderTags;
import com.reach.reach_app_v10.util.ParseObjectNodeTags;
import com.reach.reach_app_v10.util.ParseObjectUserClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (GeneralData.isResetDefaults())
            GeneralData.setCurrentLoginUser(null);
        else
            GeneralData.setCurrentLoginUser(ParseUser.getCurrentUser());

        ParseQuery<ParseObjectHeaderTags> headTagsQuery = new ParseQuery<ParseObjectHeaderTags>("HeaderTags");
        headTagsQuery.orderByAscending("order");
        headTagsQuery.findInBackground(new FindCallback<ParseObjectHeaderTags>() {
            @Override
            public void done(List<ParseObjectHeaderTags> list, ParseException e) {
                if (e != null) {
                    Log.w("ParseObjectHeaderTags Query", e.getMessage());
                    return;
                }

                List<String> l = new ArrayList<String>();
                for (ParseObjectHeaderTags k : list) {
                    l.add(k.getKeyword());
                }
                GeneralData.setTopicTags(l);
            }
        });

        ParseQuery<ParseObjectNodeTags> nodeTagsQuery = new ParseQuery<ParseObjectNodeTags>("NodeTags");
        nodeTagsQuery.orderByAscending("order");
        nodeTagsQuery.findInBackground(new FindCallback<ParseObjectNodeTags>() {
            @Override
            public void done(List<ParseObjectNodeTags> list, ParseException e) {
                if (e != null) {
                    Log.w("ParseObjectNodeTags Query", e.getMessage());
                    return;
                }
                List<String> l = new ArrayList<String>();
                for (ParseObjectNodeTags k : list) {
                    l.add(k.getKeyword());
                }
                GeneralData.setUniversities(l);
            }
        });
        if (GeneralData.getCurrentLoginUser() != null) {
            if (GeneralData.getCurrentUserClass() == null) {
                ParseQuery<ParseObjectUserClass> u = new ParseQuery<ParseObjectUserClass>("UserClass");
                u.whereEqualTo("user", GeneralData.getCurrentLoginUser());
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

        if (GeneralData.getCurrentUserClass() != null) {
            ParseQuery<ParseObjectBroadcastItem> bFeed = new ParseQuery<ParseObjectBroadcastItem>("Broadcast");
            bFeed.whereContainedIn("keywords", GeneralData.getCurrentUserClass().getProfile());
            bFeed.findInBackground(new FindCallback<ParseObjectBroadcastItem>() {
                @Override
                public void done(List<ParseObjectBroadcastItem> list, ParseException e) {
                    if (e != null) {
                        Log.w("ParseObjectBroadcastItem Query", e.getMessage());
                        return;
                    }
                    GeneralData.setbroadcastFeed(list);
                }
            });
        }


            new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (GeneralData.getCurrentLoginUser() == null) {
                      Intent i = new Intent(Splash.this, Tutorial.class);
                      startActivity(i);
                      finish();
                  } else {
                      Intent intent = new Intent(getApplicationContext(), TopicList.class);
                      startActivityForResult(intent, 1);
                  }
              }
          }, SPLASH_TIME_OUT);
    }


}
