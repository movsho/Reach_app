package com.reach.reach_app_v10;

/**
 * Created by Mecmec on 5/11/15.
 */

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.facebook.FacebookSdk;
import com.parse.ParseObject;
import com.reach.reach_app_v10.util.ParseObjectBroadcastItem;
import com.reach.reach_app_v10.util.ParseObjectHeaderTags;
import com.reach.reach_app_v10.util.ParseObjectLeafTags;
import com.reach.reach_app_v10.util.ParseObjectNodeTags;
import com.reach.reach_app_v10.util.ParseObjectUserClass;
import com.reach.reach_app_v10.util.ParseObjectBroadcastItem;

public class ReachApp extends Application {

    @Override public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());

        ParseObject.registerSubclass(ParseObjectHeaderTags.class);
        ParseObject.registerSubclass(ParseObjectNodeTags.class);
        ParseObject.registerSubclass(ParseObjectLeafTags.class);
        ParseObject.registerSubclass(ParseObjectUserClass.class);
        ParseObject.registerSubclass(ParseObjectBroadcastItem.class);

        Parse.initialize(this,
                "dDOeranObAPUVwaiHKT5JZadu9ssGoXW42mtuC99",
                "WK84Dt1eserR5wSKnkFmygxJEDPYDj2S8UUJcCJt"
        );
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        ParseFacebookUtils.initialize(getApplicationContext());
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}