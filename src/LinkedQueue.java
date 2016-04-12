import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Yulong Tan
 * 4.8.16
 *
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

    public void addAll(LinkedQueue other) {
        this.back.next = other.front;
        this.back = other.back;
    }

    public void clear() {
        this.size = 0;
        this.front = null;
    }

    public boolean contains(E e) {
        if (this.front == null) {
            return false;
        } else if (this.front.data.equals(e)) {
            return true;
        } else {
            QueueNode current = this.front.next;
            while (current != null) {
                if (current.data.equals(e)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    // Returns index of first occurence of e.
    // Returns -1 if not found
    public int indexOf(E e) {
        if (!this.contains(e)) {
            return -1;
        } else {
            int index = 0;
            if (this.front.data.equals(e)) {
                return 0;
            } else {
                QueueNode current = this.front.next;
                while (current != null) {
                    index++;
                    if (current.data.equals(e)) {
                        current = null;
                    } else {
                        current = current.next;
                    }
                }
                return index;
            }
        }
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public QueueNode nodeAt(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return this.front;
        } else {
            QueueNode current = this.front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.remove(0);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        E data;
        this.size--;
        if (index == 0) {
            data = (E) this.front.data;
            this.front = this.front.next;
        } else {
            QueueNode current = this.front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = (E) current.next.data;
            current.next = current.next.next;
        }
        return data;
    }

    // Removes all occurrences of e
    public void removeAll(E e) {
        if (this.contains(e)) {
            QueueNode current = this.front;
            while (current.next != null) {
                if (current.next.data.equals(e)) {
                    current.next = current.next.next;
                    this.size--;
                } else {
                    current = current.next;
                }
            }
            if (this.front.data.equals(e)) {
                this.front = this.front.next;
                this.size--;
            }
        }
        // Without access to the previous node, this way is used
        QueueNode current = this.front;
        while (current.next != null) {
            current = current.next;
        }
        this.back = current;
    }

    // Should rearrange all the links randomly
    public void shuffle() {
        LinkedQueue<E> storage = new LinkedQueue<>();
        Random r = new Random();
        storage.addAll(this);
        this.clear();
        int rand = r.nextInt(storage.size());
        this.front = storage.nodeAt(rand);
        storage.remove(rand);
        QueueNode current = this.front;
        while (!storage.isEmpty()) {
            int random = r.nextInt(storage.size());
            current.next = storage.nodeAt(random);
            storage.remove(random);
            current = current.next;
        }
    }

    public int size() {
        return this.size;
    }

    public List<E> toArray() {
        List<E> newList = new ArrayList<>();
        for (QueueNode current = this.front; current != null; current = current.next) {
            newList.add((E) current.data);
        }
        return newList;
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
