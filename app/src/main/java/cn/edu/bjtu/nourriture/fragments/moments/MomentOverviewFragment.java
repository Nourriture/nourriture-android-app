package cn.edu.bjtu.nourriture.fragments.moments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.bjtu.nourriture.R;

import cn.edu.bjtu.nourriture.models.Comment;
import cn.edu.bjtu.nourriture.models.Like;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.services.NourritureAPI;
import cn.edu.bjtu.nourriture.services.NourritureBaseURL;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class MomentOverviewFragment extends Fragment implements AbsListView.OnItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MOMENT_ID = "momentID";

    // --- PROPERTIES ---
    private String              current_moment_id;
    private Moment              currentMoment;   //data source used for social list of moments
    private ArrayList<HashMap>  currentMomentDataToShow; //data source for our adapter

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MomentAdapter mAdapter;



    // --- CONSTRUCTORS ---
    public static MomentOverviewFragment newInstance(String momentID) {
        MomentOverviewFragment fragment = new MomentOverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOMENT_ID, momentID);
        fragment.setArguments(args);

        return fragment;
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MomentOverviewFragment() {
    }



    // --- FRAGMENT lifecycle methods ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            current_moment_id = getArguments().getString(ARG_MOMENT_ID);
        }

        currentMomentDataToShow = new ArrayList<>();

        currentMoment = null;

        mAdapter = new MomentAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_momentoverview, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Always fetch moment when comes to the foreground
        fetchMomentDetails();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    // --- AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            HashMap item = currentMomentDataToShow.get(position);

            // User wants to see likes
            if (item.containsKey(Moment.MOMENT_LIKES) && !currentMoment.getLikes().isEmpty()){

                mListener.onShowLikesInteraction(currentMoment.getLikes());
            }
            // User wants to see comments
            else if (item.containsKey(Moment.MOMENT_COMMENTS) && !currentMoment.getComments().isEmpty()){

                mListener.onShowCommentsInteraction(currentMoment.getComments());
            }
        }
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

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(getActivity(), R.string.api_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onShowLikesInteraction(List<Like> likes);

        public void onShowCommentsInteraction(List<Comment> comments);
    }



    // --- CUSTOM INNER CLASS of ArrayAdapter ---
    private class MomentAdapter extends ArrayAdapter {

        // takes CONTEXT, LAYOUT and DATA
        public MomentAdapter() {
            super(getActivity(), R.layout.row_consumer_title_and_value, currentMomentDataToShow);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getActivity().getLayoutInflater();

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
