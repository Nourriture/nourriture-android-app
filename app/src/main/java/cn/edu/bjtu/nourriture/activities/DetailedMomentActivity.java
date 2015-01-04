package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.fragments.moments.MomentOverviewFragment;
import cn.edu.bjtu.nourriture.models.Comment;
import cn.edu.bjtu.nourriture.models.Like;

public class DetailedMomentActivity extends ActionBarActivity implements MomentOverviewFragment.OnFragmentInteractionListener {



    // --- PROPERTIES ---
    private String              current_moment_id;   //used for API call



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_moment);

        // Get the moment id from the intent
        Intent intent = getIntent();    //get the Intent that started your activity by calling getIntent() and retrieve the data contained within the intent
        current_moment_id = intent.getStringExtra(MainActivity.DETAILED_MOMENT_ID);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MomentOverviewFragment.newInstance(current_moment_id))
                    .commit();
        }
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_moment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_like_moment) {
            //likeMoment();
            return true;
        }
        else if (id == R.id.action_comment_on_moment){
            //commentOnMoment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // --- API calls ---
    //TODO: POST like from here or from Fragment?



    // --- OnFragmentInteractionListener methods ---
    @Override
    public void onShowLikesInteraction(List<Like> likes) {
        //TODO: present the LIKEs fragment
    }

    @Override
    public void onShowCommentsInteraction(List<Comment> comments) {
        //TODO: present the COMMENTs fragment
    }
}
