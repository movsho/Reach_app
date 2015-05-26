package com.reach.reach_app_v10.util;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Mecmec on 5/17/15.
 */

@ParseClassName("Broadcast")
public class ParseObjectBroadcastItem extends ParseObject {
    public String getKeyword()
    {
        return getString ("keyword");
    }
    public String getContent()
    {
        return getString ("content");
    }
    public int getLikes()
    {
        return getInt ("likes");
    }
    public int getCommentsCount()
    {
        return getInt("comments");
    }
}
