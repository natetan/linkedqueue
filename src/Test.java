import java.util.*;

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

        // shuffle
        q.shuffle();
        System.out.println("Shuffled queue: " + q.toString());
        System.out.println("Sorted: " + q.isSorted());

        // sort
        q.sort();
        System.out.println("Merge Sort: " + q.toString());
        System.out.println("Sorted: " + q.isSorted());
        System.out.println("Size: " + q.size());

        // Palindrome
        LinkedQueue<String> pal = new LinkedQueue<>();
        pal.add("hello");
        pal.add("this");
        pal.add("is");
        pal.add("is");
        pal.add("this");
        pal.add("hello");
        System.out.println("Pal queue: " + pal.toString());
        System.out.println("isPalindrome: " + isPalindrome(pal));

        // Reverse
        q.reverse();
        System.out.println("Reversed queue: " + q.toString());

        System.out.println("q = pal: " + q.equals(pal)); // false
        System.out.println("q = q: " + q.equals(q)); // true

        // Testing for uniqueness and removing duplicates
        System.out.println(pal.toString());
        System.out.println("Is it unique?: " + pal.isUnique());
        Set dupes = pal.removeDuplicates();
        System.out.println("Removed dupes: " + pal.toString());
        System.out.println("Is it unique?: " + pal.isUnique());
        System.out.println("Dupes: " + dupes.toString());

        // Get counts
        q.add("Spider-Man");
        Map map = q.getCounts();
        System.out.println("Counts: " + map.toString());

        System.out.println("Before rotate: " + q.toString());
        q.rotate(5);
        System.out.println("After rotating 5 times: " + q.toString());

        Set occurences = q.getMaxOccurrences();
        System.out.println("Most frequent: " + occurences.toString());

        // Retain all
        LinkedQueue items = new LinkedQueue();
        for (int i = 0; i < 5; i++) {
            items.add(i);
        }
        LinkedQueue oldItems = new LinkedQueue();
        for (int i = 0; i < 5; i++) {
            oldItems.add(i + 1);
        }
        oldItems.add(1);
        oldItems.add(2);
        System.out.println("Items: " + items.toString());
        System.out.println("oldItems: " + oldItems.toString());
        oldItems.retainAll(items);
        System.out.println("old items: " + oldItems.toString());
    }

    public static boolean isPalindrome(LinkedQueue q) {
        Stack s = new Stack();
        boolean ok = true;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Object data = q.remove();
            s.push(data);
            q.add(data);
        }
        while (!s.isEmpty()) {
            Object o1 = s.pop();
            Object o2 = q.remove();
            q.add(o2);
            if (!o1.equals(o2)) {
                ok = false;
            }
        }
        return ok;
    }
}
