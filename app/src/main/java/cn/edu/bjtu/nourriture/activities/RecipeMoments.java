package cn.edu.bjtu.nourriture.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.dummy.DummyContent;
//import cn.edu.bjtu.nourriture.fragments.moments.MomentsAdapter;
import cn.edu.bjtu.nourriture.models.Moment;

/**
 * Created by jeremy on 04/01/15.
 */
public class RecipeMoments extends ActionBarActivity
{
    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moments_by_recipe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        if (b == null)
            System.out.println("Error");
        else {
            Id = b.getString("id");
            if (Id != null)
                System.out.println("Recipe " + Id);
        }

        TextView t = (TextView) findViewById(R.id.recipeMomentsNotFound);
        t.setText("");
        AbsListView lv = (AbsListView) findViewById(R.id.recipeMomentsList);
        ArrayList<Moment> searchResultMoment = new ArrayList<Moment>();
        /*List<DummyContent.DummyRecipe> searchResultRecipe = new ArrayList<DummyContent.DummyRecipe>();
        for (int i = 0; i < DummyContent.RECIPES.size();i++)
        {
            if (DummyContent.RECIPES.get(i).id.equals(Id))
            {
                searchResultRecipe.add(DummyContent.RECIPES.get(i));
            }
        }
        if (searchResultRecipe.size() == 0) {
            t.setText("Sorry, no moments found !");
        }
        else{
            for (int i = 0; i < searchResultRecipe.size();i++)
            {
                for (int a = 0; a < DummyContent.MOMENTS.size();a++)
                {
                    if (DummyContent.MOMENTS.get(a).getMomentSubjectID().equals(searchResultRecipe.get(i).id))
                    {
                        searchResultMoment.add(DummyContent.MOMENTS.get(a));
                    }
                }
            }
        }*/
        ListAdapter arrayAdapter = null;
        //arrayAdapter = new MomentsAdapter(this, searchResultMoment);

        lv.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_moment_recipe, menu);
        return true;
    }
}
