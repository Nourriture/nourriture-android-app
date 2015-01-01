package cn.edu.bjtu.nourriture.fragments.moments;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.dummy.DummyContent;
import cn.edu.bjtu.nourriture.models.Moment;

/**
 * Created by Vincent on 02/01/2015.
 */
public class MomentsAdapter extends ArrayAdapter {

    // takes CONTEXT, LAYOUT and DATA
    Activity act;
    ArrayList<Moment> moment;
    public MomentsAdapter(Activity main, ArrayList<Moment> myDummyMoments){
        super(main, R.layout.row_moment, myDummyMoments);
        act = main;
        moment = myDummyMoments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View momentView = convertView;

        // Make sure we have a view to work with
        if (momentView == null){
            momentView = act.getLayoutInflater().inflate(R.layout.row_moment, parent, false);
        }

        // Set author name
        TextView author = (TextView) momentView.findViewById(R.id.momentAuthorTextView);
        author.setText(moment.get(position).getMomentAuthor());

        // Set content text
        TextView content = (TextView) momentView.findViewById(R.id.momentContentTextView);
        content.setText(moment.get(position).getMomentText());

        // Set time elapsed
        TextView timeElapsed = (TextView) momentView.findViewById(R.id.momentTimeTextView);
        timeElapsed.setText(moment.get(position).getMomentCreated());

        // Set likes
        TextView likes = (TextView) momentView.findViewById(R.id.momentLikesTextView);
        likes.setText(moment.get(position).getMomentLikes() + " likes");

        return momentView;
    }
}

