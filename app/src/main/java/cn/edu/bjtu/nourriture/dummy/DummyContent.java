package cn.edu.bjtu.nourriture.dummy;

import android.media.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bjtu.nourriture.R;
import cn.edu.bjtu.nourriture.models.Moment;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Moment> MOMENTS = new ArrayList<Moment>();

    public static List<DummyRecipe> RECIPES = new ArrayList<DummyRecipe>();

    public static List<DummyFriend> FRIENDS = new ArrayList<DummyFriend>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Moment> MOMENTS_MAP = new HashMap<String, Moment>();

    public static Map<String, DummyRecipe> RECIPES_MAP = new HashMap<String, DummyRecipe>();

    public static Map<String, DummyFriend> FRIENDS_MAP = new HashMap<String, DummyFriend>();

    static {
        // Add 3 sample items.
        Date d = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        Date d4 = new Date();
        Date d5 = new Date();
        Date d6 = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        try {
            //d = simpleDateFormat.parse("13/11/2014 20:35:55");
            d2 = simpleDateFormat.parse("26/12/2014 17:11:10");
            d3 = simpleDateFormat.parse("26/12/2014 17:30:10");
            d4 = simpleDateFormat.parse("13/12/2014 20:35:55");
            d5 = simpleDateFormat.parse("24/12/2014 11:30:10");
            d6 = simpleDateFormat.parse("25/12/2014 11:30:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }



        addRecipe(new DummyRecipe("sushi", "1", "Sushi", "Sushi makizushi", "Lorem Ipsum is simply dummy text of the printing and typesetting industry", "rice, salmon, sauce", "carbs: 100, fat: 50"));
        addRecipe(new DummyRecipe("goulash", "2", "Gulash", "Delicious gulash", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.", "Beef, vegetables, paprika", "carbs: 200, fat: 100"));
        addRecipe(new DummyRecipe("pizza4cheeses", "3", "Pizza", "Pizza quatro fromagi", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.", "4 cheeze, tomato sauce, pizza dough", "carbs: 230, fat: 130"));

        addFriend(new DummyFriend("1", "Martin Jensen"));
        addFriend(new DummyFriend("2", "John Applesed"));
        addFriend(new DummyFriend("3", "Rocky Balboa"));
    }

    private static void addMoment(Moment m) {
        MOMENTS.add(m);
        //MOMENTS_MAP.put(m.id, m);
    }

    private static void addRecipe(DummyRecipe r) {
        RECIPES.add(r);
        RECIPES_MAP.put(r.id, r);
    }

    private static void addFriend(DummyFriend f) {
        FRIENDS.add(f);
        FRIENDS_MAP.put(f.id, f);
    }

    /**
     * A dummy item representing a piece of content.
     */
    /*public static class DummyMoment {
        public String id;
        public String content;

        public DummyMoment(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }*/

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyRecipe {
        public String picture;
        public String id;
        public String content;
        public String description;
        public String instruction;
        public String ingredients;
        public String info;


        public DummyRecipe(String picture, String id, String name, String description,
                           String instruction, String ingredients, String info) {
            this.picture = picture;
            this.id = id;
            this.content = name;
            this.description = description;
            this.instruction = instruction;
            this.ingredients = ingredients;
            this.info = info;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyFriend {
        public String id;
        public String content;

        public DummyFriend(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
