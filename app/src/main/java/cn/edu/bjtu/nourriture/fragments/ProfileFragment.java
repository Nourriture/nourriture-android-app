package cn.edu.bjtu.nourriture.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.edu.bjtu.nourriture.MainActivity;
import cn.edu.bjtu.nourriture.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {



    // --- PROPERTIES ---
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private final String MY_PROFILE_PREFERENCES = "myProfile";
    private final String PROFILE_NAME = "name";
    private final String PROFILE_OCCUPATION = "occupation";
    private final String PROFILE_BIRTHDATE = "birthdate";
    private final String PROFILE_WEBSITE = "website";
    private final String PROFILE_BIO = "bio";

    private OnFragmentInteractionListener mListener;



    // --- CONSTRUCTOR ---
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(int sectionNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }



    // --- FRAGMENT lifecycle methods ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getActivity().getSharedPreferences(MY_PROFILE_PREFERENCES, 0);

        SharedPreferences.Editor editor = pref.edit(); // used for save data
        editor.putString(PROFILE_NAME, "Rocky Brambora"); // Storing string value
        editor.putString(PROFILE_OCCUPATION, "Boxer"); // Storing string value
        editor.putString(PROFILE_BIRTHDATE, "02/05/1958"); // Storing string value
        editor.putString(PROFILE_WEBSITE, "http://www.me.com"); // Storing string value
        editor.putString(PROFILE_BIO, "You all know me well!"); // Storing string value

        editor.commit(); // commit changes into sharedpreferences file.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences(MY_PROFILE_PREFERENCES, 0); // 0 - for private mode

        TextView name = (TextView) v.findViewById(R.id.profileNameValueTextView);
        name.setText(pref.getString(PROFILE_NAME, ""));

        TextView occupation = (TextView) v.findViewById(R.id.profileOccupationValueTextView);
        occupation.setText(pref.getString(PROFILE_OCCUPATION, ""));

        TextView birthdate = (TextView) v.findViewById(R.id.profileBirthdateValueTextView);
        birthdate.setText(pref.getString(PROFILE_BIRTHDATE, ""));

        TextView website = (TextView) v.findViewById(R.id.profileWebsiteValueTextView);
        website.setText(pref.getString(PROFILE_WEBSITE, ""));

        TextView bio = (TextView) v.findViewById(R.id.profileBioValueTextView);
        bio.setText(pref.getString(PROFILE_BIO, ""));

        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onProfileInteraction(uri);
        }
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
        public void onProfileInteraction(Uri uri);
    }
}