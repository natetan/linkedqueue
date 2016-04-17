import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *  Yulong Tan
 *  4.8.16
 *
 *  LinkedList implementation of a Queue, with First In, First Out structure.
 *
 *
 */

public class LinkedQueue<E> {
    private QueueNode front; // reference to the front
    private QueueNode back; // reference to the back
    private int size; // reference to the size of the queue

    // Constructs an empty LinkedQueue
    public LinkedQueue() {
        this.front = null;
        this.back = this.front;
        this.size = 0;
    }

    // Adds the given data to the queue, increases the size
    public void add(E data) {
        if (this.isEmpty()) {
            this.front = new QueueNode(data);
            this.back = this.front;
            this.front.prev = null;
        } else {
            QueueNode next = new QueueNode(data);
            this.back.next = next;
            QueueNode prev = this.back;
            this.back = this.back.next;
            next.prev = prev;
        }
        this.size++;
    }

    // Adds all the contents from another LinkedQueue to this one
    public void addAll(LinkedQueue other) {
        int size = other.size();
        for (int i = 0; i < size; i++) {
            E next = (E) other.remove();
            this.add(next);
            other.add(next);
        }
    }

    // Removes all elements from the LinkedQueue
    // Size becomes 0
    public void clear() {
        this.size = 0;
        this.front = null;
    }

    // Returns true if LinkedQueue contains given data and
    // false otherwise
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

    // Returns true if the size is = 0, and false otherwise
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns the node at the given index. If the index is >= than the size,
    // it throws an IndexOutOfBoundsException
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

    // Returns the data at the front of the queue. If the queue is empty,
    // throws a NoSuchElementException
    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    // Removes the first element and returns its data.
    // If the LinkedQueue is empty, throws a NoSuchElementException.
    // Decreases the size
    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.remove(0);
    }

    // Removes element and returns the data at a given index.
    // If index >= size, throws an IndexOutOfBoundsException.
    // Decreases the size.
    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size);
        }
        E data;
        this.size--;
        if (index == 0) {
            data = (E) this.front.data;
            this.front = this.front.next;
            if (this.front != null) {
                this.front.prev = null;
            }
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

    // Removes all occurrences of e.
    // Decreases the size.
    public void removeAll(E e) {
        if (this.contains(e)) {
            while (this.back.data.equals(e)) {
                this.back = this.back.prev;
                this.back.next = null;
                this.size--;
            }
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
    }

    // Sorts the queue
    public void sort() {
        if (this.size() > 1) {
            int size1 = this.size() / 2;
            int size2 = this.size() - size1;
            LinkedQueue half1 = new LinkedQueue();
            LinkedQueue half2 = new LinkedQueue();
            for (int i = 0; i < size1; i++) {
                half1.add(this.remove());
            }
            for (int i = 0; i < size2; i++) {
                half2.add(this.remove());
            }
            half1.sort();
            half2.sort();
            this.mergeSort(this, half1, half2);
        }
    }

    // Performs the merge sort algorithm to sort the queue, given
    // the actual queue, and its two halves.
    private void mergeSort(LinkedQueue result, LinkedQueue half1, LinkedQueue half2) {
        while (!half1.isEmpty() && !half2.isEmpty()) {
            // Casting Object to Comparable fixes the problem
            if ((((Comparable)half1.peek()).compareTo((half2.peek()))) <= 0) {
                result.add(half1.remove());
            } else {
                result.add(half2.remove());
            }
        }
        while (!half1.isEmpty()) {
            result.add(half1.remove());
        }
        while (!half2.isEmpty()) {
            result.add(half2.remove());
        }
    }

    // Randomly rearranges all the links in the LinkedQueue
    // Only shuffles the queue if the size > 1
    public void shuffle() {
        if (this.size() > 1) {
            LinkedQueue<E> storage = new LinkedQueue<>();
            storage.addAll(this);
            Random r = new Random();
            this.clear();
            int rand = r.nextInt(storage.size() - 1);
            this.add((E) storage.nodeAt(rand).data);
            storage.remove(rand);
            // Going until the size is 1 fixes the issue of r.nextInt(0)
            while (storage.size() > 1) {
                int random = r.nextInt(storage.size() - 1);
                this.add((E) storage.nodeAt(random).data);
                storage.remove(random);
            }
            // Adds in the last one. Large if check guarantees
            // that there will always be one at the end.
            this.add((E) storage.remove());
        }
    }

    // Returns the size of the queue
    public int size() {
        return this.size;
    }

    // Copies all the data of the LinkedQueue, adds them to a new list, and
    // returns it. Order and LinkedQueue is unchanged.
    public List<E> toArray() {
        List<E> newList = new ArrayList<>();
        for (QueueNode current = this.front; current != null; current = current.next) {
            newList.add((E) current.data);
        }
        return newList;
    }

    // Returns a string representation of the contents of the
    // LinkedQueue. Data is printed in square brackets, with
    // each element separated by a comma.
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
