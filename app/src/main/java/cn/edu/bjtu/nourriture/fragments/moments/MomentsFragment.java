package cn.edu.bjtu.nourriture.fragments.moments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.List;

import cn.edu.bjtu.nourriture.activities.MainActivity;
import cn.edu.bjtu.nourriture.R;
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
public class MomentsFragment extends Fragment implements AbsListView.OnItemClickListener {



    // --- PROPERTIES ---
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MomentsAdapter mAdapter;

    /**
     * Load some dummy moments
     */
    private ArrayList<Moment> myMoments = new ArrayList<>();



    // --- CONSTRUCTOR ---
    public static MomentsFragment newInstance(int sectionNumber) {
        MomentsFragment fragment = new MomentsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MomentsFragment() {
    }



    // --- FRAGMENT lifecycle methods ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        // TODO: Change Adapter to display your content
        mAdapter = new MomentsAdapter();//new ArrayAdapter<Moment>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.MOMENTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

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

        // Always fetch moments when comes to the foreground
        fetchAllMoments();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;

            // Tell the main activity that fragment has been attached (will change the title)
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onMomentSelected(myMoments.get(position).getId());
        }
    }



    // --- ACTION BAR ---
    /**
     *  Inflate the menu resource into the given Menu to add each item to the action bar:
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.moments_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add_moment:
                openAddNewMomentFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // --- HELPERs ---
    private void openAddNewMomentFragment() {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            // Calls the MainActivity, which then presents the NewMomentActivity
            mListener.onNewMomentSelected();
        }

    }

    private void showEmptyView(boolean showEmptyView){

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_item, ((ViewGroup) getView().getParent()), false);

        TextView t = (TextView) view.findViewById(android.R.id.empty);

        if (showEmptyView){
            t.setText(getString(R.string.no_moments));
        }
        else {
            t.setText("");
        }
    }



    // --- API calls ---
    private void fetchAllMoments() {

        // custom GSON parser http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NourritureBaseURL.LOCALHOST_PLATFORM_ANDROID_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        NourritureAPI api = restAdapter.create(NourritureAPI.class);

        //once response is received, in case of JSON api your data will be transformed to your model using Gson library
        api.getAllMoments(new Callback<List<Moment>>() {
            @Override
            public void success(List<Moment> moments, Response response) {

                myMoments.clear();

                myMoments.addAll(moments);

                if (myMoments.size() == 0) {
                    showEmptyView(true);
                }
                else {
                    showEmptyView(false);
                }

                mAdapter.notifyDataSetChanged();    // Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.api_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }



    // --- CUSTOM INNER CLASS of ArrayAdapter ---
    private class MomentsAdapter extends ArrayAdapter {

        // takes CONTEXT, LAYOUT and DATA
        public MomentsAdapter(){
            super(getActivity(), R.layout.row_moment, myMoments);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View momentView = convertView;

            // Make sure we have a view to work with
            if (momentView == null){
                momentView = getActivity().getLayoutInflater().inflate(R.layout.row_moment, parent, false);
            }

            Moment m = myMoments.get(position);

            // Set author name
            TextView author = (TextView) momentView.findViewById(R.id.momentAuthorTextView);
            author.setText(m.getAuthor());

            // Set content text
            TextView content = (TextView) momentView.findViewById(R.id.momentContentTextView);
            content.setText(m.getText());

            // Set time elapsed
            TextView timeElapsed = (TextView) momentView.findViewById(R.id.momentTimeTextView);
            timeElapsed.setText(m.getCreated());

            // Set comments count
            TextView comments = (TextView) momentView.findViewById(R.id.momentCommentTextView);
            if (m.getCommentCount() == 1){
                comments.setText(m.getCommentCount() + " " + getString(R.string.momentComment));
            }
            else {
                comments.setText(m.getCommentCount() + " " + getString(R.string.momentComments));
            }

            // Set likes count
            TextView likes = (TextView) momentView.findViewById(R.id.momentLikesTextView);
            if (m.getLikeCount() == 1){
                likes.setText(m.getCommentCount() + " " + getString(R.string.momentLike));
            }
            else {
                likes.setText(m.getCommentCount() + " " + getString(R.string.momentLikes));
            }

            return momentView;
        }
    }



    // --- INTERFACE methods decleration ---
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
        public void onMomentSelected(String id);

        public void onNewMomentSelected();
    }

}
