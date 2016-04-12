import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Yulong on 4/8/2016.
 */

public class Test {
    public static void main(String[] stuff) {
        LinkedQueue<String> q = new LinkedQueue();
        q.add("Apples");
        q.add("Bananas");
        q.add("Carrots");
        System.out.println("Initial queue: " + q.toString()); // Apples, Bananas, Carrots
        System.out.println("Size: " + q.size()); // 3
        String x = q.remove(); // x = Apples
        System.out.println("After removal of " + x + ": " + q.toString()); // Bananas, Carrots
        System.out.println("Size: " + q.size()); // 2
        String y = q.peek(); // Bananas
        System.out.println("After peeking of " + y + ": " + q.toString()); // Bananas, Carrots
        System.out.println("Size: " + q.size()); // 2

        q.add("Durian");
        q.add("Eggplant");
        System.out.println("Added Durian and Eggplant: " + q.toString());
        System.out.println("Size: " + q.size()); // 4
        q.add(q.remove());
        System.out.println("Added the removal: " + q.toString());
        System.out.println("Size: " + q.size()); // 4

        List<String> list = q.toArray();
        System.out.println("List: " + list);

        for (int i = 0; i < 5; i++) {
            q.add("hello");
        }
        System.out.println("Added hello 5 times: " + q.toString());
        q.removeAll("hello");
        System.out.println("Called removeAll(hello): " + q.toString());
        System.out.println("Index of Carrots: " + q.indexOf("Carrots")); // 0
        System.out.println("Index of Bananas: " + q.indexOf("Bananas")); // 3
        q.remove(2);
        System.out.println("Removed index 2: " + q.toString());
        System.out.println("Size: " + q.size()); // 3
        q.add("Swag");
        q.add("Evil");
        q.add("Spider-Man");
        System.out.println("Added stuff: " + q.toString());
        System.out.println("Size: " + q.size());
    }

    public static int removeMin(LinkedQueue q) {
        int size = q.size();
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            int next = (Integer) q.remove();
            min = Math.min(min, next);
            q.add(next);
        }
        for (int i = 0; i < size; i++) {
            int next = (Integer) q.remove();
            if (next != min) {
                q.add(next);
            }
        }
        return min;
    }
}
