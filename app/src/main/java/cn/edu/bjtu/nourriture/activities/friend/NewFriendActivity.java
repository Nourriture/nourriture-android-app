package cn.edu.bjtu.nourriture.activities.friend;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.activities.MainActivity;
import cn.edu.bjtu.nourriture.adapters.ConsumersAdapter;
import cn.edu.bjtu.nourriture.models.Consumer;

public class NewFriendActivity extends ActionBarActivity {



    // --- PROPERTIES ---
    private ConsumersAdapter adapter;

    private ListView listView;

    private ArrayList<Consumer> myConsumers = new ArrayList<>();    //For data loaded from API



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        adapter = new ConsumersAdapter(getBaseContext(), myConsumers);

        // can findViewById, because View already populated by setContentView
        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cancel_friend) {
            discardFriend();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    // --- HANDLERS ---
    private void discardFriend() {

        // Present the "Main" activity modaly (slide down)
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.no_change_animation, R.anim.slide_down_animation);
    }
}
