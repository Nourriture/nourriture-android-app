package cn.edu.bjtu.nourriture.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pavel Procházka on 29/12/14.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Recipe {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String title;
    @Expose
    private String picture;
    @Expose
    private String description;
    @Expose
    private String author;
    @Expose
    private Date created;
    @Expose
    private Integer difficulty;
    @Expose
    private Integer calories;
    @Expose
    private Integer carbs;
    @Expose
    private Integer proteins;
    @Expose
    private Integer fat;
    @SerializedName("__v")
    @Expose
    private Integer V;
    @Expose
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    @Expose
    private List<Instruction> instructions = new ArrayList<Instruction>();

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
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * The created
     */
    public String getCreated() {
        // Create our date "formatter" (the date format we want)
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");

        // Create a new String using the date format we want
        String dateString = formatterDate.format(created);
        String timeString = formatterTime.format(created);

        return dateString + " at " +timeString;
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
     * The difficulty
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @param difficulty
     * The difficulty
     */
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @return
     * The calories
     */
    public Integer getCalories() {
        return calories;
    }

    /**
     *
     * @param calories
     * The calories
     */
    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    /**
     *
     * @return
     * The carbs
     */
    public Integer getCarbs() {
        return carbs;
    }

    /**
     *
     * @param carbs
     * The carbs
     */
    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    /**
     *
     * @return
     * The proteins
     */
    public Integer getProteins() {
        return proteins;
    }

    /**
     *
     * @param proteins
     * The proteins
     */
    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    /**
     *
     * @return
     * The fat
     */
    public Integer getFat() {
        return fat;
    }

    /**
     *
     * @param fat
     * The fat
     */
    public void setFat(Integer fat) {
        this.fat = fat;
    }

    /**
     *
     * @return
     * The V
     */
    public Integer getV() {
        return V;
    }

    /**
     *
     * @param V
     * The __v
     */
    public void setV(Integer V) {
        this.V = V;
    }

    /**
     *
     * @return
     * The ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     * The ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return
     * The instructions
     */
    public List<Instruction> getInstructions() {
        return instructions;
    }

    /**
     *
     * @param instructions
     * The instructions
     */
    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

}