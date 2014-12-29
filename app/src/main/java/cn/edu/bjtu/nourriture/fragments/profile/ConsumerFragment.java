package cn.edu.bjtu.nourriture.fragments.profile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.activities.MainActivity;
import cn.edu.bjtu.nourriture.models.Consumer;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ConsumerFragment extends Fragment implements AbsListView.OnItemClickListener {



    // --- PROPERTIES ---
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private final String MY_PROFILE_PREFERENCES = "myProfile";

    private Consumer currentConstumer = null;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;



    // --- CONSTRUCTOR ---
    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public static ConsumerFragment newInstance(int sectionNumber) {
        ConsumerFragment fragment = new ConsumerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConsumerFragment() {
    }



    // --- FRAGMENT lifecycle methods ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        writeProfileToSharedPreferences();

        readProfileFromSharedPreferences();

        mAdapter = new ConsumerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consumer, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
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
            HashMap hm = currentConstumer.getConsumerInfoToDisplay().get(position);
            mListener.onConsumerInteraction(hm.values().toString());
        }
    }



    // --- CUSTOM METHODS ---
    private void writeProfileToSharedPreferences() {
        SharedPreferences pref = getActivity().getSharedPreferences(MY_PROFILE_PREFERENCES, 0);

        SharedPreferences.Editor editor = pref.edit(); // used for save data
        editor.putString(Consumer.CONSUMER_PICTURE, "R.drawable.rockybalboaimage"); // Storing string value
        editor.putString(Consumer.CONSUMER_USERNAME, "rockyUS"); // Storing string value
        editor.putString(Consumer.CONSUMER_NAME, "Rocky Brambora"); // Storing string value
        editor.putString(Consumer.CONSUMER_OCCUPATION, "Boxer"); // Storing string value
        editor.putLong(Consumer.CONSUMER_BIRTHDATE, new Date().getTime()); // Storing Date value
        editor.putString(Consumer.CONSUMER_WEBSITE, "http://www.me.com"); // Storing string value
        editor.putString(Consumer.CONSUMER_BIO, "Miloš Zeman si nechal od Putinova blízkého spolupracovníka zaplatit dovolenou na Rhodosu, navzdory informacím od českých i aliančních rozvědek popíral přítomnost ruských vojáků na Ukrajině, rozhodnutí Ukrajinců v prezidentských i parlamentních volbách směřovat západním směrem označil za umělý pokus o změnu vlastního geopolitického směřování, v čínské státní televizi uváděl nepravdivé informace o ukrajinském jazykovém zákoně přesně podle linie Kremlu."); // Storing string value
        editor.putString(Consumer.CONSUMER_EMAIL, "bla@foo.com");
        editor.putInt(Consumer.CONSUMER_GENDER, 1);

        editor.commit(); // commit changes into sharedpreferences file.
    }

    private void readProfileFromSharedPreferences() {
        SharedPreferences pref = getActivity().getSharedPreferences(MY_PROFILE_PREFERENCES, 0); // 0 - for private mode

        currentConstumer = new Consumer();
        currentConstumer.setConsumerUsername(pref.getString(Consumer.CONSUMER_USERNAME, ""));
        currentConstumer.setConsumerName(pref.getString(Consumer.CONSUMER_NAME, ""));
        currentConstumer.setConsumerPicture(pref.getString(Consumer.CONSUMER_PICTURE, ""));
        currentConstumer.setConsumerOccupation(pref.getString(Consumer.CONSUMER_OCCUPATION, ""));
        currentConstumer.setConsumerBirthdate(new Date(pref.getLong(Consumer.CONSUMER_BIRTHDATE, 0)));
        currentConstumer.setConsumerWebsite(pref.getString(Consumer.CONSUMER_WEBSITE, ""));
        currentConstumer.setConsumerBio(pref.getString(Consumer.CONSUMER_BIO, ""));
        currentConstumer.setConsumerEmail(pref.getString(Consumer.CONSUMER_EMAIL, ""));
        currentConstumer.setConsumerGender(pref.getInt(Consumer.CONSUMER_GENDER, 0));
    }



    // --- CUSTOM INNER CLASS of ArrayAdapter ---
    private class ConsumerAdapter extends ArrayAdapter {

        // takes CONTEXT, LAYOUT and DATA
        public ConsumerAdapter(){
            super(getActivity(), R.layout.row_moment, currentConstumer.getConsumerInfoToDisplay()); //will pass an array of Consumer available info to display
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getActivity().getLayoutInflater();

            View rowView = null;

            ArrayList<HashMap> consumerArrayOfInfo = currentConstumer.getConsumerInfoToDisplay();
            HashMap consumerInfo = consumerArrayOfInfo.get(position);

            // row with IMAGE
            if (consumerInfo.containsKey(Consumer.CONSUMER_PICTURE)){
                rowView = inflater.inflate(R.layout.row_consumer_image, parent, false);

                ImageView imgView = (ImageView) rowView.findViewById(R.id.consumerImageView);
                imgView.setImageResource(R.drawable.rockybalboaimage);
            }
            // row with TITLE and VALUE
            else {
                rowView = inflater.inflate(R.layout.row_consumer_title_and_value, parent, false);

                TextView titleTextView = (TextView) rowView.findViewById(R.id.titleTextView);
                TextView valueTextView = (TextView) rowView.findViewById(R.id.valueTextView);

                if (consumerInfo.containsKey(Consumer.CONSUMER_USERNAME)){
                    titleTextView.setText(R.string.consumerUsername);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_USERNAME).toString());
                }
                else if (consumerInfo.containsKey(Consumer.CONSUMER_NAME)){
                    titleTextView.setText(R.string.consumerName);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_NAME).toString());
                }
                else if (consumerInfo.containsKey(Consumer.CONSUMER_OCCUPATION)){
                    titleTextView.setText(R.string.consumerOccupation);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_OCCUPATION).toString());
                }
                else if (consumerInfo.containsKey(Consumer.CONSUMER_BIRTHDATE)){
                    titleTextView.setText(R.string.consumerBirthdate);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_BIRTHDATE).toString());
                }
                else if (consumerInfo.containsKey(Consumer.CONSUMER_WEBSITE)){
                    titleTextView.setText(R.string.consumerWebsite);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_WEBSITE).toString());
                }
                else if (consumerInfo.containsKey(Consumer.CONSUMER_BIO)){
                    titleTextView.setText(R.string.consumerBio);
                    valueTextView.setText(consumerInfo.get(Consumer.CONSUMER_BIO).toString());
                }
            }

            return rowView;
        }
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
        public void onConsumerInteraction(String id);
    }
}