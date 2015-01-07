package cn.edu.bjtu.nourriture.activities.friend;

/**
 * Created by Kevin on 31/12/14.
 * TO BE DELETED
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsStatus;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.dummy.DummyContent;
import cn.edu.bjtu.nourriture.activities.MainActivity;


public class SearchFriendActivity extends ActionBarActivity {

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;


    // --- ACTIVITY lifecycle methods ---

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfriend);

        mAdapter = new ArrayAdapter<DummyContent.DummyFriend>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.FRIENDS);



    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchfriend, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        if (DummyContent.FRIENDS.size() == 0) {
            TextView t = (TextView) view.findViewById(android.R.id.empty);
            t.setText(getString(R.string.no_friends));
        }

        return view;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != view) {
            addFriend();

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_friend_menu, menu);
        return true;
    }

    private void addFriend()
    {

    }

}