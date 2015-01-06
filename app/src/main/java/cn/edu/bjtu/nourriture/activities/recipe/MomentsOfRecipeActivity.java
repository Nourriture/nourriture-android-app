package cn.edu.bjtu.nourriture.activities.recipe;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.fragments.moments.MomentsFragment;

public class MomentsOfRecipeActivity extends ActionBarActivity implements MomentsFragment.OnFragmentInteractionListener {



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments_of_recipe);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MomentsFragment.newInstance(1))
                    .commit();
        }
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_moments_of_recipe, menu);
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



    // --- Fragments INTERFACE methods implementation ---
    @Override
    public void onMomentSelected(String id) {
        System.out.println(id);
    }

    @Override
    public void onNewMomentSelected() {
        System.out.println("foo");
    }
}
