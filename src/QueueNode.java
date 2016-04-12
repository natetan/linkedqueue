/**
 * Created by Yulong on 4/8/2016.
 */

public class QueueNode<E> {
    public E data;
    public QueueNode next;
    public QueueNode prev;

    public QueueNode(E data) {
        this(data, null, null);
    }

    public QueueNode(E data, QueueNode next, QueueNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
