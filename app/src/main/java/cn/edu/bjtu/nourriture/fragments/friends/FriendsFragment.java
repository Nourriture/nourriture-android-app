package cn.edu.bjtu.nourriture.fragments.friends;

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
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bjtu.nourriture.activities.MainActivity;
import cn.edu.bjtu.nourriture.R;

import cn.edu.bjtu.nourriture.adapters.ConsumersAdapter;
import cn.edu.bjtu.nourriture.models.Consumer;
import cn.edu.bjtu.nourriture.services.Constants;
import cn.edu.bjtu.nourriture.services.NourritureAPI;
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
 public class FriendsFragment extends Fragment implements AbsListView.OnItemClickListener {



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
     * The text view for possible "empty text"
     */
    private TextView emptyTextView;

     /**
      * The Adapter which will be used to populate the ListView/GridView with
      * Views.
      */
     private ConsumersAdapter mAdapter;

     /**
      * For data loaded from API
      */
     private ArrayList<Consumer> myConsumers = new ArrayList<>();



     // --- CONSTRUCTOR ---
     public static FriendsFragment newInstance(int sectionNumber) {
         FriendsFragment fragment = new FriendsFragment();
         Bundle args = new Bundle();
         args.putInt(ARG_SECTION_NUMBER, sectionNumber);
         fragment.setArguments(args);
         return fragment;
     }

     /**
      * Mandatory empty constructor for the fragment manager to instantiate the
      * fragment (e.g. upon screen orientation changes).
      */
     public FriendsFragment() {
     }



    // --- FRAGMENT lifecycle methods ---
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setHasOptionsMenu(true);

         mAdapter = new ConsumersAdapter(getActivity().getBaseContext(), myConsumers);
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_friends, container, false);

         emptyTextView = (TextView) view.findViewById(android.R.id.empty);

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

        // Always fetch consumers when comes to the foreground
        fetchAllConsumers();
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



     // --- AdapterView.OnItemClickListener
     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //TODO: show consumer's profile?
         if (null != mListener) {
             // Notify the active callbacks interface (the activity, if the
             // fragment is attached to one) that an item has been selected.
             //mListener.onFriendSelected(DummyContent.FRIENDS.get(position).id);
         }
     }



    // --- ACTION BAR ---
    /**
     *  Inflate the menu resource into the given Menu to add each item to the action bar:
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.menu_friends, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add_friend:
                openSearchFriendFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // --- HELPERs ---
    private void openSearchFriendFragment() {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            // Calls the MainActivity, which then presents the NewMomentActivity
            mListener.onSearchFriendSelected();
        }

    }

    private void showEmptyView(boolean showEmptyView){

        if (showEmptyView){
            emptyTextView.setText(getString(R.string.no_friends));
        }
        else {
            emptyTextView.setText("");
        }
    }



    // --- API calls ---
    private void fetchAllConsumers() {

        // custom GSON parser http://stackoverflow.com/questions/18473011/retrofit-gson-serialize-date-from-json-string-into-java-util-date
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.NOURRITURE_PLATFORM_ANDROID_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        NourritureAPI api = restAdapter.create(NourritureAPI.class);
        api.getAllConsumers(new Callback<List<Consumer>>() {
            @Override
            public void success(List<Consumer> consumers, Response response) {

                myConsumers.clear();

                myConsumers.addAll(consumers);

                if (myConsumers.size() == 0) {
                    showEmptyView(true);
                } else {
                    showEmptyView(false);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.api_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
         public void onFriendSelected(String id);
         public void onSearchFriendSelected();
     }
 }