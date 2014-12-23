package cn.edu.bjtu.nourriture.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static List<DummyMoment> MOMENTS = new ArrayList<DummyMoment>();

    public static List<DummyRecipe> RECIPES = new ArrayList<DummyRecipe>();

    public static List<DummyFriend> FRIENDS = new ArrayList<DummyFriend>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyMoment> MOMENTS_MAP = new HashMap<String, DummyMoment>();

    public static Map<String, DummyRecipe> RECIPES_MAP = new HashMap<String, DummyRecipe>();

    public static Map<String, DummyFriend> FRIENDS_MAP = new HashMap<String, DummyFriend>();

    static {
        // Add 3 sample items.
        addMoment(new DummyMoment("1", "My first moment"));
        addMoment(new DummyMoment("2", "My second moment"));
        addMoment(new DummyMoment("3", "My third moment"));

        addRecipe(new DummyRecipe("1", "Sushi"));
        addRecipe(new DummyRecipe("2", "Gulash"));
        addRecipe(new DummyRecipe("3", "Pizza"));

        addFriend(new DummyFriend("1", "Martin Jensen"));
        addFriend(new DummyFriend("2", "John Applesed"));
        addFriend(new DummyFriend("3", "Rocky Balboa"));
    }

    private static void addMoment(DummyMoment m) {
        MOMENTS.add(m);
        MOMENTS_MAP.put(m.id, m);
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
    public static class DummyMoment {
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
    }

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
