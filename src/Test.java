/**
 * Created by Yulong on 4/8/2016.
 */

public class Test {
    public static void main(String[] stuff) {
        LinkedQueue<Integer> q = new LinkedQueue();
        q.add(5);
        q.add(3);
        q.add(2);
        System.out.println("Initial queue: " + q.toString()); // 5 3 2
        System.out.println("Size: " + q.size()); // 3
        int x = q.remove(); // x = 5
        System.out.println("After removal of " + x + ": " + q.toString()); // 3 2
        System.out.println("Size: " + q.size()); // 2
        int y = q.peek();
        System.out.println("After peeking of " + y + ": " + q.toString()); // 3 2
        System.out.println("Size: " + q.size()); // 2

        q.add(5);
        q.add(7);
        System.out.println("Added 7 and 5: " + q.toString()); // 3 2 5 7
        System.out.println("Size: " + q.size()); // 4
        q.add(q.remove());
        System.out.println("Added the removal: " + q.toString()); // 2 5 7 3
        System.out.println("Size: " + q.size()); // 4
    }
}
