package cn.edu.bjtu.nourriture.models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pavel Procházka on 26/12/14.
 */

@Generated("org.jsonschema2pojo")
public class Moment {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private Date created;
    @Expose
    private String author;
    @Expose
    private String text;
    @Expose
    private List<Object> comments = new ArrayList<Object>();
    @Expose
    private List<Object> likes = new ArrayList<Object>();


    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return convertDateToTimeElapsedString(created, new Date());
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The comments
     */
    public List<Object> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The likes
     */
    public List<Object> getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     * The likes
     */
    public void setLikes(List<Object> likes) {
        this.likes = likes;
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