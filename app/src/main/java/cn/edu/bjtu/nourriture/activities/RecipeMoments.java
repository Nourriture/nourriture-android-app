package cn.edu.bjtu.nourriture.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import cn.edu.bjtu.nourriture.R;

/**
 * Created by jeremy on 04/01/15.
 */
public class RecipeMoments extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moment_by_recipe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_moment_recipe, menu);
        return true;
    }
}
