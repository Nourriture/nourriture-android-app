package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.edu.bjtu.nourriture.R;

public class NewMomentActivity extends ActionBarActivity {



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_moment);
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_moment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_moment) {
            postMoment();
            return true;
        }
        else if (id == R.id.action_cancel_moment){
            discardMoment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // --- Moment handlers ---
    private void postMoment() {
        //TODO: POST to API

        //TODO 2: discard moment
    }

    private void discardMoment() {

        // Present the "Main" activity modaly (slide down)
        Intent intent_home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_home);
        overridePendingTransition(R.anim.no_change_animation, R.anim.slide_down_animation);
    }
}
