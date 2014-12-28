package cn.edu.bjtu.nourriture.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pavel Procházka on 29/12/14.
 */
public class Recipe {
    private String      recipeTitle;
    private Date        recipeCreated;
    private String      recipeDescription;
    private String      recipeAuthor;
    private String      recipePicture;
    private int         recipeDifficulty;

    private int         recipeCarbs;
    private int         recipeCalories;
    private int         recipeProteins;
    private int         recipeFats;

    //private ArrayList   recipeIngredients;
    //private ArrayList   recipeInstructions;
}
