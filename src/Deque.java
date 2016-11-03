import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Deque} class provides a generalization of a stack and a queue that supports removing
 *  items from either the front or the back of the data structure.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3/a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Jorge Frisancho
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Construct an empty deque
     */
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the number of items on the deque
     *
     * @return integer that represents the number of items on the deque
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the deque
     *
     * @return integer that represents the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds the item to the front
     *
     * @param item to be added
     */
    public void addFirst(Item item) {
        addElement(item, true);
    }

    /**
     * Adds the item to the end
     *
     * @param item to be added
     */
    public void addLast(Item item) {
        addElement(item, false);
    }

    /**
     * Removes and returns the item from the front
     *
     * @return item removed from the deque
     */
    public Item removeFirst() {
        checkRemove(head);
        Node node = head;

        return removeElement(node, true);
    }

    /**
     * Removes and returns the item from the end
     *
     * @return item removed from the deque
     */
    public Item removeLast() {
        checkRemove(tail);
        Node node = tail;

        return removeElement(node, false);
    }

    /**
     * Returns  an iterator over items in order from front to end
     *
     * @return independent iterator over solution of Boards
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void addElement(Item item, Boolean first) {
        checkAdd(item);
        Node node = new Node(item);

        if (size == 0) {
            head = node;
            tail = node;
        } else if (first) {
            head.prev = node;
            node.next = head;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
    }

    public Item removeElement(Node node, Boolean front) {
        Item item = node.item;

        if (size == 1) {
            head = null;
            tail = null;
        } else if (front) {
            head = head.next;
            head.prev = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;

        return item;
    }

    private void checkAdd(Item item) {
        if (item == null)
            throw new NullPointerException("add null");
    }

    private void checkRemove(Node node) {
        if (node == null)
            throw new NoSuchElementException("remove from null");
    }


    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        private Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException("not item");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("not support remove()");
        }
    }

    /**
     *  unit testing
     *
     *  @param args main function standard arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
