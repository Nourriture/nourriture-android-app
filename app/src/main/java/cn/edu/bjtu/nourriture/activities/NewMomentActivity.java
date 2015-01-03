package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.models.Author;
import cn.edu.bjtu.nourriture.models.Consumer;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.services.NourritureAPI;
import cn.edu.bjtu.nourriture.services.NourritureBaseURL;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

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
        if (isMomentReady()){
            // 1) create a moment object
            EditText t = (EditText) findViewById(R.id.new_moment_edit_text);

            SharedPreferences pref = getSharedPreferences(MainActivity.MY_PROFILE_PREFERENCES, 0); // 0 - for private mode
            String consumerID = pref.getString(Consumer.CONSUMER_ID, "");
            String consumerName = pref.getString(Consumer.CONSUMER_NAME, "");

            Author a = new Author();
            a.setCId(consumerID);
            a.setName(consumerName);

            Moment m = new Moment();
            m.setAuthor(a);
            m.setText(t.getText().toString());

            // 2) POST request to API
            // custom GSON parser http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(NourritureBaseURL.LOCALHOST_PLATFORM_ANDROID_URL)
                    .setConverter(new GsonConverter(gson))
                    .build();

            NourritureAPI api = restAdapter.create(NourritureAPI.class);
            api.postMoment(m, new Callback<Moment>() {
                @Override
                public void success(Moment moment, Response response) {

                    Toast toast = Toast.makeText(getApplicationContext(), R.string.moment_posted, Toast.LENGTH_SHORT);
                    toast.show();

                    discardMoment();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.moment_text_missing, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void discardMoment() {

        // Present the "Main" activity modaly (slide down)
        Intent intent_home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_home);
        overridePendingTransition(R.anim.no_change_animation, R.anim.slide_down_animation);
    }

    private boolean isMomentReady(){
        EditText t = (EditText) findViewById(R.id.new_moment_edit_text);

        if (t.getText().toString().isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
