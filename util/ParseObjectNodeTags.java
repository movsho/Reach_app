package com.reach.reach_app_v10.util;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Mecmec on 5/12/15.
 */
@ParseClassName("NodeTags")
public class ParseObjectNodeTags extends ParseObject {
    public String getKeyword()
    {
        return getString ("keyword");
    }
    public String getType()
    {
        return getString ("type");
    }
    public int getCount()
    {
        return getInt("count");
    }
    public int getOrder()
    {
        return getInt("order");
    }
}
