package cn.edu.bjtu.nourriture.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.services.NourritureAPI;
import cn.edu.bjtu.nourriture.services.NourritureBaseURL;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class DetailMomentActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {



    // --- PROPERTIES ---
    private String current_moment_id;

    private MomentAdapter adapter;

    private ArrayList<HashMap> currentMomentDataToShow;

    private Moment currentMoment;

    private ListView listView;



    // --- ACTIVITY lifecycle methods ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_moment);

        // Get the moment id from the intent
        Intent intent = getIntent();    //get the Intent that started your activity by calling getIntent() and retrieve the data contained within the intent
        current_moment_id = intent.getStringExtra(MainActivity.DETAILED_MOMENT_ID);

        currentMomentDataToShow = new ArrayList<>();

        currentMoment = null;

        adapter = new MomentAdapter();

        // can findViewById, because View already populated by setContentView
        listView = (ListView) findViewById(R.id.momentDetailsListView);
        listView.setAdapter(adapter);

        /**
         * Register a callback to be invoked when an item in this AdapterView has
         * been clicked.
         *
         * Set OnItemClickListener so we can be notified on item clicks
         */
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Always fetch moment when comes to the foreground
        fetchMomentDetails();
    }



    // --- AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        HashMap item = currentMomentDataToShow.get(position);

        if (item.containsKey(Moment.MOMENT_LIKES)){
            // User wants to see likes
        }
        else if (item.containsKey(Moment.MOMENT_COMMENTS)){
            // User wants to see comments
        }
    }



    // --- ACTION BAR ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_moment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_like_moment) {
            likeMoment();
            return true;
        }
        else if (id == R.id.action_comment_on_moment){
            commentOnMoment();
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

                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void likeMoment() {
        //FIXME: for now will be able to add as many likes as possible

        //TODO: POST "like" for this specific moment
    }

    private void commentOnMoment() {
        //TODO: implement
        Toast toast = Toast.makeText(getApplicationContext(), "Feature not implemented yet", Toast.LENGTH_SHORT);
        toast.show();
    }



    // --- CUSTOM INNER CLASS of ArrayAdapter ---
    private class MomentAdapter extends ArrayAdapter {

        // takes CONTEXT, LAYOUT and DATA
        public MomentAdapter() {
            super(getBaseContext(), R.layout.row_consumer_title_and_value, currentMomentDataToShow);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View rowView = null;

            HashMap momentInfo = currentMomentDataToShow.get(position);

            // row with MOMENT content
            if (momentInfo.containsKey(Moment.MOMENT_TEXT)){
                rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

                TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
                textView.setText(momentInfo.get(Moment.MOMENT_TEXT).toString());
                textView.setTextSize(20);
                //textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            }
            // row with TITLE and VALUE
            else {
                rowView = inflater.inflate(R.layout.row_consumer_title_and_value, parent, false);

                TextView titleTextView = (TextView) rowView.findViewById(R.id.titleTextView);
                TextView valueTextView = (TextView) rowView.findViewById(R.id.valueTextView);

                if (momentInfo.containsKey(Moment.MOMENT_AUTHOR)){
                    titleTextView.setText(R.string.momentDetailAuthor);
                    valueTextView.setText(momentInfo.get(Moment.MOMENT_AUTHOR).toString());
                }
                else if (momentInfo.containsKey(Moment.MOMENT_CREATED)){
                    titleTextView.setText(R.string.momentDetailCreated);
                    valueTextView.setText(momentInfo.get(Moment.MOMENT_CREATED).toString());
                }
                else if (momentInfo.containsKey(Moment.MOMENT_LIKES)){
                    titleTextView.setText(R.string.momentDetailLikes);
                    valueTextView.setText(momentInfo.get(Moment.MOMENT_LIKES).toString());
                }
                else if (momentInfo.containsKey(Moment.MOMENT_COMMENTS)){
                    titleTextView.setText(R.string.momentDetailComments);
                    valueTextView.setText(momentInfo.get(Moment.MOMENT_COMMENTS).toString());
                }
            }

            return rowView;
        }
    }
}
