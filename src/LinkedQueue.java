import java.util.NoSuchElementException;

/**
 *  Yulong Tan
 *  4.8.16
 *
 *  LinkedList implementation of a Queue, with First In, First Out structure.
 */

public class LinkedQueue<E> {
    private QueueNode front; // reference to the front
    private QueueNode back; // reference to the back
    private int size; // reference to the size of the queue

    public LinkedQueue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    public void add(E data) {
        if (this.isEmpty()) {
            this.front = new QueueNode(data);
            this.back = this.front;
        } else {
            this.back.next = new QueueNode(data);
            this.back = this.back.next;
        }
        this.size++;
    }

    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = (E) this.front.data;
        this.front = this.front.next;
        this.size--;
        return data;
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            String result = "[" + this.front.data;
            QueueNode current = this.front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            return result + "]";
        }
    }
}
