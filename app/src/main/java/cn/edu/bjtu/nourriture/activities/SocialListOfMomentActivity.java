package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.models.Moment;

enum LIST_TYPE{
    LIST_TYPE_LIKES,
    LIST_TYPE_COMMENTS
}

public class SocialListOfMomentActivity extends ActionBarActivity {



    // --- PROPERTIES ---
    private LIST_TYPE       listViewType;   //to distingush when to show "Likes" and "Comments"

    private Moment          momentToShowDetails;

    private SocialAdapter   adapter;

    private ListView        listView;



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_list_of_moment);

        // Get the moment and activity title from the intent
        Intent intent = getIntent();    //get the Intent that started your activity by calling getIntent() and retrieve the data contained within the intent
        momentToShowDetails = (Moment) intent.getSerializableExtra(DetailMomentActivity.DETAILED_MOMENT);

        //TODO: set title for this Activity
        String title = intent.getStringExtra(DetailMomentActivity.DETAILED_MOMENT_ACTIVITY_TITLE);
        if (title.equals(getString(R.string.listTypeLikes))){
            listViewType = LIST_TYPE.LIST_TYPE_LIKES;

            adapter = new SocialAdapter(momentToShowDetails.getLikes());
        }
        else if (title.equals(getString(R.string.listTypeComments))){
            listViewType = LIST_TYPE.LIST_TYPE_COMMENTS;

            adapter = new SocialAdapter(momentToShowDetails.getComments());
        }

        // can findViewById, because View already populated by setContentView
        listView = (ListView) findViewById(R.id.momentSocialDetailsListView);
        listView.setAdapter(adapter);
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_social_list_of_moment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // --- CUSTOM INNER CLASS of ArrayAdapter ---
    private class SocialAdapter extends ArrayAdapter {

        // takes CONTEXT, LAYOUT and DATA
        public SocialAdapter(List<Object> content) {
            super(getBaseContext(), android.R.layout.simple_list_item_1, content);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View rowView = null;

            // row with LIKE author
            if (listViewType == LIST_TYPE.LIST_TYPE_LIKES){
                rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

                String likeAuthor = (String) momentToShowDetails.getLikes().get(position);

                TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
                textView.setText(likeAuthor);
            }
            // row with COMMENT author and comment text
            else {
                rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

                String commentAuthor = (String) momentToShowDetails.getLikes().get(position);
                String commentText = "BLAALALALD";

                TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
                textView.setText(commentAuthor);

                TextView textView2 = (TextView) rowView.findViewById(android.R.id.text2);
                textView2.setText(commentText);
            }

            return rowView;
        }
    }
}
