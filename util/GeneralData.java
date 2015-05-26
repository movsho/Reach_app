package com.reach.reach_app_v10.util;

import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Mecmec on 5/11/15.
 */
public final class GeneralData {
    /**
     * This will define if the app should reset user settings to default (ie. a new user)
     */
    private static final boolean resetDefaults = false;
    public static boolean isResetDefaults() {
        return resetDefaults;
    }

    /**
     * The current parse user.
     * Null if empty.
     */
    private static ParseUser currentLoginUser = null;
    public static ParseUser getCurrentLoginUser() {
        return currentLoginUser;
    }

    public static void setCurrentLoginUser(ParseUser user) {
        currentLoginUser = user;
    }

    private static ParseObjectUserClass currentUserClass = null;
    public static ParseObjectUserClass getCurrentUserClass() {
        return currentUserClass;
    }

    public static void setCurrentUserClass(ParseObjectUserClass user) {
        currentUserClass = user;
    }

    private static List<String> myTags = null;
    public static List<String> getMyTags() {
        return myTags;
    }

    public static void setMyTags(List<String> myTags) {
        GeneralData.myTags = myTags;
        ParseObjectUserClass pUser;
    }

    private static List<String> topicTags = null;
    public static List<String> getTopicTags() {
        return topicTags;
    }

    public static void setTopicTags(List<String> topicTags) {
        GeneralData.topicTags = topicTags;
    }

    /**
     * The list of available universities.
     * This will populate the university tag, under the TopicList file.
     */
    private static List<String> universities = null;

    public static List<String> getUniversities() {

        return universities;
    }

    public static void setUniversities(List<String> universities) {
        GeneralData.universities = universities;
    }

    private static List<ParseObjectBroadcastItem> broadcastFeed = null;

    public static List<ParseObjectBroadcastItem> getBroadcastFeed() {

        return broadcastFeed;
    }

    public static void setbroadcastFeed(List<ParseObjectBroadcastItem> feed) {
        GeneralData.broadcastFeed = feed;
    }


}
