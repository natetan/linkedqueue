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
    }
}
