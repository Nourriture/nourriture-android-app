package cn.edu.bjtu.nourriture.activities.recipe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.fragments.moments.MomentsFragment;

public class MomentsOfRecipeActivity extends ActionBarActivity implements MomentsFragment.OnFragmentInteractionListener {



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_moments_of_recipe);

        String query = getIntent().getStringExtra(DetailRecipeActivity.INTENT_RECIPE_ID);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MomentsFragment.newInstance(101, MomentsFragment.MOMENTS_QUERY_TYPE.RECIPE, query))
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

    // will get overwritten by the MomentsFragment anyways
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
