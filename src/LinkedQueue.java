import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 4.8.16
 * <p>
 * LinkedList implementation of a Queue, with First In, First Out structure.
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

    public void addAll(LinkedQueue other) {
        this.back.next = other.front;
        this.back = other.back;
    }

    public List<E> toArray() {
        List<E> newList = new ArrayList<>();
        for (QueueNode current = this.front; current != null; current = current.next) {
            newList.add((E) current.data);
        }
        return newList;
    }

    public void clear() {
        this.size = 0;
        this.front = null;
    }

    public boolean contains(E e) {
        if (this.front == null) {
            return false;
        } else if (this.front == e) {
            return true;
        } else {
            QueueNode current = this.front.next;
            while (current != null) {
                if (current == e) {
                    return true;
                }
            }
            return false;
        }
    }

    // Removes all occurrences of e
    public void removeAll(E e) {
        if (this.contains(e)) {
            QueueNode current = this.front;
            while (current.next != null) {
                if (current.next == e) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
            if (this.front == e) {
                this.front = this.front.next;
            }
        }
    }
}
