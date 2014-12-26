package cn.edu.bjtu.nourriture.models;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pavel Procházka on 26/12/14.
 */
public class Moment {
    private String              momentAuthor;
    private Date                momentCreated;
    //private ArrayList<Consumer> momentLikes;      // FIXME: use this instead of int
    private int                 momentLikes;
    private String              momentText;
    private String              momentSubjectID;    // NOTE: Referring to recipe, ingredient, gastronomist or company in Nourriture (3rd party)
    //private Comment             momentComment;    // TODO: nice to have


    public Moment(String momentAuthor, Date momentCreated, int momentLikes, String momentText, String momentSubjectID) {
        this.momentAuthor = momentAuthor;
        this.momentCreated = momentCreated;
        this.momentLikes = momentLikes;
        this.momentText = momentText;
        this.momentSubjectID = momentSubjectID;
    }

    public String getMomentAuthor() {
        return momentAuthor;
    }

    public void setMomentAuthor(String momentAuthor) {
        this.momentAuthor = momentAuthor;
    }

    public Date getMomentCreated() {
        return momentCreated;
    }

    public void setMomentCreated(Date momentCreated) {
        this.momentCreated = momentCreated;
    }

    public int getMomentLikes() {
        return momentLikes;
    }

    public void setMomentLikes(int momentLikes) {
        this.momentLikes = momentLikes;
    }

    public String getMomentText() {
        return momentText;
    }

    public void setMomentText(String momentText) {
        this.momentText = momentText;
    }

    public String getMomentSubjectID() {
        return momentSubjectID;
    }

    public void setMomentSubjectID(String momentSubjectID) {
        this.momentSubjectID = momentSubjectID;
    }
}
