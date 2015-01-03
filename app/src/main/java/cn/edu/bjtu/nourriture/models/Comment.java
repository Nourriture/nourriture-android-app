package cn.edu.bjtu.nourriture.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pavel Procházka on 03/01/15.
 */

@Generated("org.jsonschema2pojo")
public class Comment {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String text;
    @Expose
    private String created;
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
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
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

}