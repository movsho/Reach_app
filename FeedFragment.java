package com.reach.reach_app_v10;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.reach.reach_app_v10.util.GeneralData;
import com.reach.reach_app_v10.util.ParseObjectBroadcastItem;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends ListFragment {

    String data[];
//    = new String[] {"Android", "iPhone", "WindowsMobile",
//            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
//            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
//            "Android", "iPhone", "WindowsMobile"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (GeneralData.getBroadcastFeed() == null) {
            ParseQuery<ParseObjectBroadcastItem> bFeed = new ParseQuery<ParseObjectBroadcastItem>("Broadcast");
            bFeed.whereContainedIn("keyword", GeneralData.getCurrentUserClass().getProfile());
            bFeed.findInBackground(new FindCallback<ParseObjectBroadcastItem>() {
                @Override
                public void done(List<ParseObjectBroadcastItem> list, ParseException e) {
                    if (e != null) {
                        Log.w("ParseObjectBroadcastItem Query", e.getMessage());
                        return;
                    }
                    GeneralData.setbroadcastFeed(list);
                    List<String> newList = new ArrayList<String>();
                    for (ParseObjectBroadcastItem item : list) {
                        newList.add(item.getKeyword());
                    }

                    data = new String[newList.size()];
                    data = newList.toArray(data);

//                    Object[] o = newList.;
//                    data = (String[]) o;
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1, data);
                    setListAdapter(adapter);
                }
            });
        } else {
            List<String> newList = new ArrayList<String>();
            for (ParseObjectBroadcastItem item : GeneralData.getBroadcastFeed()) {
                newList.add(item.getKeyword());
            }
            data = (String[]) newList.toArray();;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, data);
            setListAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, null);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = l.getItemAtPosition(position);
        String pen = o.toString();
        Toast.makeText(getActivity().getApplicationContext(), pen, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity().getApplicationContext(), Temp.class);
        intent.putExtra("titleBar", pen);
        startActivityForResult(intent, 1);
    }
}
