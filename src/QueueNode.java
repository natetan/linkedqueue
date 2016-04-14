/**
 *  Yulong Tan
 *  4.8.16
 *
 *  Node class for LinkedQueue
 */

public class QueueNode<E> {
    public E data; // Generic data for storage
    public QueueNode next; // reference to the next node
    public QueueNode prev; // reference to the previous node

    // Constructs a QueueNode with given data, and null for
    // its next node, and prev node.
    public QueueNode(E data) {
        this(data, null, null);
    }

    // Constructs a QueueNode with given data, next node, and prev node
    public QueueNode(E data, QueueNode next, QueueNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
