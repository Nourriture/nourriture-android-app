package cn.edu.bjtu.nourriture.services;

import java.util.List;

import cn.edu.bjtu.nourriture.models.Consumer;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.models.Recipe;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Pavel Procházka on 31/12/14.
 */
public interface NourritureAPI {



    // Moment endpoints
    @POST("/moment")
    public boolean postMoment(Moment moment);

    //TODO: implement on the backend!
    @GET("/moment")
    public List<Moment> getAllMoments();

    //TODO: implement on the backend!
    /*@GET("/moment/{recipeID}")
    public List<Moment> getMomentsForRecipe(@Path("recipeID") String recipeID);*/



    // Recipe endpoints
    @GET("/recipe")
    public List<Recipe> getAllRecipes();

    @GET("/recipe/{id}")
    public Recipe getRecipe(@Path("id") String recipeID);



    // Consumer endpoints
    @POST("/consumer")
    public boolean postConsumer(Consumer consumer);

    @GET("/consumer")
    public List<Consumer> getAllConsumers();

    @GET("/consumer/{username}")
    public Consumer getConsumer(@Path("username") String consumerUsername);

    // Relationships
    @GET("/consumer/{username}/following")
    public Consumer getConsumerFollowing(@Path("username") String consumerUsername);

    @POST("/consumer/{username}/following")
    public Consumer getConsumerFollowing(@Path("username") Consumer consumer);
}