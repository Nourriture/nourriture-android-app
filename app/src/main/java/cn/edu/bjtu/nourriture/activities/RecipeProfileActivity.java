package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.dummy.DummyContent;
import cn.edu.bjtu.nourriture.fragments.NavigationDrawerFragment;


public class RecipeProfileActivity extends ActionBarActivity
{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DummyContent.DummyRecipe r;
    public String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String id = "";
        Bundle b = getIntent().getExtras();
        if (b == null)
            System.out.println("Error");
        else {
            Id = b.getString("id");
            if (Id != null)
                System.out.println("Recipe " + id);
        }

        for (int i = 0; i < DummyContent.RECIPES.size();i++)
        {
            if (DummyContent.RECIPES.get(i).id.equals(Id)) {
                ImageView pic = (ImageView) findViewById(R.id.picture);
                pic.setImageResource(getResources().getIdentifier(DummyContent.RECIPES.get(i).picture, "drawable", "cn.edu.bjtu.nourriture"));
                TextView cont = (TextView) findViewById(R.id.content);
                cont.setText(DummyContent.RECIPES.get(i).content);
                TextView desc = (TextView) findViewById(R.id.description);
                desc.setText(DummyContent.RECIPES.get(i).description);
                TextView inst = (TextView) findViewById(R.id.instruction);
                inst.setText(DummyContent.RECIPES.get(i).instruction);
                TextView ing = (TextView) findViewById(R.id.ingredients);
                ing.setText(DummyContent.RECIPES.get(i).ingredients);
                TextView info = (TextView) findViewById(R.id.info);
                info.setText(DummyContent.RECIPES.get(i).info);

                /*final Button ShowMoment = (Button) findViewById(R.id.ShowMoment);
                ShowMoment.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        for (int a = 0; a < DummyContent.MOMENTS.size();a++)
                        {
                            if (DummyContent.MOMENTS.get(a).getMomentSubjectID().equals(Id))
                            {
                                Intent intent = new Intent(RecipeProfileActivity.this, RecipeMoments.class);
                                intent.putExtra("id", Id);
                                startActivity(intent);
                            }
                        }
                    }
                });*/

            }

        }
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
        getMenuInflater().inflate(R.menu.menu_recipe_profile, menu);
        return true;
    }
}
