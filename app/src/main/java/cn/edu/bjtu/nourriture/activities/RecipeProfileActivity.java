package cn.edu.bjtu.nourriture.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.dummy.DummyContent;


public class RecipeProfileActivity extends Activity
{
    private DummyContent.DummyRecipe r;
    private ListView ListViewPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_profile);
        String id = "";
        Bundle b = getIntent().getExtras();
        if (b == null)
            System.out.println("Error");
        else {
            id = b.getString("id");
            if (id != null)
                System.out.println("Recipe " + id);
        }
        ListViewPerso = (ListView) findViewById(R.id.listviewperso);

        for (int i = 0; i < DummyContent.RECIPES.size();i++)
        {
            if (DummyContent.RECIPES.get(i).id.equals(id)) {

                ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map;

                r = DummyContent.RECIPES.get(i);
                map = new HashMap<String, String>();
                map.put("picture", r.picture);
                map.put("content", r.content);
                map.put("description", r.description);
                map.put("instruction", r.instruction);
                map.put("ingredients", r.ingredients);
                map.put("info", r.info);
                listItem.add(map);


                System.out.println("Recipe " + r.content);
                SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.recipe_detail,
                        new String[] {"picture", "content", "description", "instruction", "ingredients", "info"},
                        new int[] {R.id.picture, R.id.content, R.id.description, R.id.description, R.id.instruction, R.id.ingredients, R.id.info});

                ListViewPerso.setAdapter(mSchedule);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_profile, menu);
        return true;
    }

}
