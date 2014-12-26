package cn.edu.bjtu.nourriture.models;

import java.util.Date;

/**
 * Created by Pavel Procházka on 26/12/14.
 */
public class Moment {

    private String              momentID;
    private String              momentAuthor;
    private Date                momentCreated;
    //private ArrayList<Consumer> momentLikes;      // FIXME: use this instead of int
    private int                 momentLikes;
    private String              momentText;
    private String              momentSubjectID;    // NOTE: Referring to recipe, ingredient, gastronomist or company in Nourriture (3rd party)
    //private Comment             momentComment;    // TODO: nice to have


    public Moment(String momentID, String momentAuthor, Date momentCreated, int momentLikes, String momentText, String momentSubjectID) {
        this.momentID = momentID;
        this.momentAuthor = momentAuthor;
        this.momentCreated = momentCreated;
        this.momentLikes = momentLikes;
        this.momentText = momentText;
        this.momentSubjectID = momentSubjectID;
    }

    public String getMomentID() {
        return momentID;
    }

    public void setMomentID(String momentID) {
        this.momentID = momentID;
    }

    public String getMomentAuthor() {
        return momentAuthor;
    }

    public void setMomentAuthor(String momentAuthor) {
        this.momentAuthor = momentAuthor;
    }

    public String getMomentCreated() {
        return convertDateToTimeElapsedString(this.momentCreated, new Date());
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


    /**
     *  Count the elapsed time between now and moment created
     */
    public String convertDateToTimeElapsedString(Date startDate, Date endDate){

        String timeElapsed = "";

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        /*System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);*/

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if(elapsedDays != 0){
            if (elapsedDays == 1)
                timeElapsed += elapsedDays + " day ago";
            else
                timeElapsed += elapsedDays + " days ago";
        }
        else if (elapsedDays == 0 && elapsedHours != 0 ){
            if (elapsedHours == 1)
                timeElapsed += elapsedHours + " hour ago";
            else
                timeElapsed += elapsedHours + " hours ago";
        }
        else if (elapsedHours == 0 && elapsedMinutes != 0 ){
            if (elapsedMinutes == 1)
                timeElapsed += elapsedMinutes + " minute ago";
            else
                timeElapsed += elapsedMinutes + " minutes ago";
        }
        else if (elapsedMinutes == 0){
            timeElapsed += "A few seconds ago";
        }

        return timeElapsed;
    }
}