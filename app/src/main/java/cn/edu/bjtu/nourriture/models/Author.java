package cn.edu.bjtu.nourriture.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

/**
 * Created by Pavel Procházka on 03/01/15.
 */

@Generated("org.jsonschema2pojo")
public class Author {

    @Expose
    private String cId;
    @Expose
    private String name;

    /**
     *
     * @return
     * The cId
     */
    public String getCId() {
        return cId;
    }

    /**
     *
     * @param cId
     * The cId
     */
    public void setCId(String cId) {
        this.cId = cId;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}