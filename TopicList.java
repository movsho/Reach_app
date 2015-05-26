package com.reach.reach_app_v10;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.reach.reach_app_v10.util.GeneralData;
import com.reach.reach_app_v10.util.ParseObjectHeaderTags;

import java.util.ArrayList;
import java.util.List;


public class TopicList extends ListActivity {

    private ListView mainListView;
    Button button;
    ArrayAdapter<Model> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> tempTopics = GeneralData.getTopicTags();
        if (tempTopics == null) {
            ParseQuery<ParseObjectHeaderTags> headTagsQuery = new ParseQuery<ParseObjectHeaderTags>("HeaderTags");
            headTagsQuery.orderByAscending("order");
            headTagsQuery.findInBackground(new FindCallback<ParseObjectHeaderTags>() {
                @Override
                public void done(List<ParseObjectHeaderTags> list, ParseException e) {
                    if (e != null) {
                        Log.w("ParseObjectHeaderTags Query", e.getMessage());
                        return;
                    }
                    List<Model> l = new ArrayList<Model>();
                    for (ParseObjectHeaderTags k : list) {
                        l.add(get(k.getKeyword()));
                    }
                    adapter = new TopicViewHolder(TopicList.this, l);
                    setListAdapter(adapter);
                }
            });
        } else {
            List<Model> tempList = new ArrayList<Model>();
            for (String str : tempTopics) {
                tempList.add(get(str));
            }
            adapter = new TopicViewHolder(TopicList.this, tempList);
            setListAdapter(adapter);
        }
        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.activity_topic_list, lv, false);
        lv.addHeaderView(header, null, false);
        button = new Button(this);

    }

    private Model get(String s) {
        return new Model(s);
    }

    public void follow (View v){
        /**
         * Save selected profile to the Parse DB
         */
        List<Model> followList = ((TopicViewHolder)adapter).getList();
        List<String> newProf = new ArrayList<String>();

        for (Model m : followList) {
            if (m.isSelected())
                newProf.add(m.getName());
        }
        GeneralData.getCurrentUserClass().setProfile(newProf);


        Intent intent = new Intent(getApplicationContext(), Feed.class);
        startActivityForResult(intent,1);
    }
}
