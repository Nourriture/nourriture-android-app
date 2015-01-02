package cn.edu.bjtu.nourriture.services;

import java.util.List;

import cn.edu.bjtu.nourriture.models.Consumer;
import cn.edu.bjtu.nourriture.models.Moment;
import cn.edu.bjtu.nourriture.models.Recipe;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Pavel Procházka on 31/12/14.
 */
public interface NourritureAPI {



    // Moment endpoints
    @Headers("Content-Type:application/json")   //FIXME: put in the main activity as RequestInterceptor
    @POST("/moment")
    public boolean postMoment(Moment moment);

    @Headers("Content-Type:application/json")
    @GET("/moment")
    public void getAllMoments(Callback<List<Moment>> callback);

    //TODO: implement on the backend!
    /*@GET("/moment/{recipeID}")
    public List<Moment> getMomentsForRecipe(@Path("recipeID") String recipeID);*/



    // Recipe endpoints
    @Headers("Content-Type:application/json")
    @GET("/recipe")
    public void getAllRecipes(Callback<List<Recipe>> callback);

    @Headers("Content-Type:application/json")
    @GET("/recipe/{id}")
    public Recipe getRecipe(@Path("id") String recipeID);



    // Consumer endpoints
    @Headers("Content-Type:application/json")
    @POST("/consumer")
    public boolean postConsumer(Consumer consumer);

    @Headers("Content-Type:application/json")
    @GET("/consumer")
    public List<Consumer> getAllConsumers();

    @Headers("Content-Type:application/json")
    @GET("/consumer/{username}")
    public Consumer getConsumer(@Path("username") String consumerUsername);

    // Relationships
    @Headers("Content-Type:application/json")
    @GET("/consumer/{username}/following")
    public Consumer getConsumerFollowing(@Path("username") String consumerUsername);

    @Headers("Content-Type:application/json")
    @POST("/consumer/{username}/following")
    public Consumer getConsumerFollowing(@Path("username") Consumer consumer);
}