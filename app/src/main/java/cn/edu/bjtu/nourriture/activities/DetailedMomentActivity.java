package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.fragments.moments.MomentOverviewFragment;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.services.NourritureAPI;
import cn.edu.bjtu.nourriture.services.NourritureBaseURL;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class DetailedMomentActivity extends ActionBarActivity implements MomentOverviewFragment.OnFragmentInteractionListener {



    // --- PROPERTIES ---
    private String              current_moment_id;   //used for API call
    private Moment              currentMoment;   //data source used for social list of moments
    private ArrayList<HashMap>  currentMomentDataToShow; //data source for our adapter



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_moment);

        // Get the moment id from the intent
        Intent intent = getIntent();    //get the Intent that started your activity by calling getIntent() and retrieve the data contained within the intent
        current_moment_id = intent.getStringExtra(MainActivity.DETAILED_MOMENT_ID);

        // Objects init
        currentMoment = null;
        currentMomentDataToShow = new ArrayList<>();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MomentOverviewFragment.newInstance("",""))
                    .commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Always fetch moment when comes to the foreground
        fetchMomentDetails();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // --- API calls ---
    private void fetchMomentDetails() {
        // custom GSON parser http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NourritureBaseURL.LOCALHOST_PLATFORM_ANDROID_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        NourritureAPI api = restAdapter.create(NourritureAPI.class);
        api.getMoment(current_moment_id, new Callback<Moment>() {
            @Override
            public void success(Moment moment, Response response) {
                currentMomentDataToShow.clear();

                currentMomentDataToShow.addAll(moment.getMomentInfoToDisplay());

                currentMoment = moment;

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }



    // --- OnFragmentInteractionListener methods ---
    @Override
    public void onShowLikesInteraction(String id) {
        //TODO: present the LIKEs fragment
    }

    @Override
    public void onShowCommentsInteraction(String id) {
        //TODO: present the COMMENTs fragment
    }


    /**
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detailed_moment, container, false);
            return rootView;
        }
    }*/
}
