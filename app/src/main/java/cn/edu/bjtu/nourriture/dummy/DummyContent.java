package cn.edu.bjtu.nourriture.dummy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        try {
            d = simpleDateFormat.parse("13/11/2014 20:35:55");
            d2 = simpleDateFormat.parse("10/10/2013 11:30:10");
            d3 = simpleDateFormat.parse("26/12/2014 11:30:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        addMoment(new Moment("ID1", "Paja Prochazka", d, 5, "This is my first moment", "Reference object ID"));
        addMoment(new Moment("ID2", "Martin Jensen", d2, 10, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "Reference object ID"));
        addMoment(new Moment("ID3", "Victoria Secret", d3, 3, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Reference object ID"));
        addMoment(new Moment("ID4", "Rocky Balboa", d, 2, "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.", "Reference object ID"));
        addMoment(new Moment("ID5", "Barack Obama", d2, 8, "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.", "Reference object ID"));
        addMoment(new Moment("ID6", "Tony Hawk", d3, 13, "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.", "Reference object ID"));

        addRecipe(new DummyRecipe("1", "Sushi"));
        addRecipe(new DummyRecipe("2", "Gulash"));
        addRecipe(new DummyRecipe("3", "Pizza"));

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
        public String id;
        public String content;

        public DummyRecipe(String id, String content) {
            this.id = id;
            this.content = content;
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
