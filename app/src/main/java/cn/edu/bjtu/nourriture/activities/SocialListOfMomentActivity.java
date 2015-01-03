package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.models.Moment;

enum LIST_TYPE{
    LIST_TYPE_LIKES,
    LIST_TYPE_COMMENTS
}

public class SocialListOfMomentActivity extends ActionBarActivity {



    // --- PROPERTIES ---
    private LIST_TYPE   listViewType;
    private Moment      momentToShowDetails;



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
        }
        else if (title.equals(getString(R.string.listTypeComments))){
            listViewType = LIST_TYPE.LIST_TYPE_COMMENTS;
        }
    }


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
}
