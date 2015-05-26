package com.reach.reach_app_v10.util;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mecmec on 5/12/15.
 */
@ParseClassName("UserClass")
public class ParseObjectUserClass extends ParseObject {
    public ArrayList<String> getProfile()
    {
        return (ArrayList<String>) this.get("profile");
    }
    public void setProfile (List<String> newP)
    {
        this.addAllUnique("oldProfiles", this.getProfile());
        this.removeAll("profile", this.getProfile());
        this.saveInBackground();
        newP.add("TAU");
        this.addAll("profile", newP);
        this.saveInBackground();
    }
}
