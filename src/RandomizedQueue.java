import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code RandomizedQueue} class provides a generalization of a stack or queue, except that the item
 *  removed is chosen uniformly at random from items in the data structure.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3/a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Jorge Frisancho
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
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
     * Removes and returns the item a random item
     *
     * @return item
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("enqueue null");
        if (size == a.length)
            resize(a.length * 2);
        a[size++] = item;
    }

    /**
     * Removes and returns the item a random item
     *
     * @return item
     */
    public Item dequeue() {
        checkElement();
        int rand = StdRandom.uniform(size);
        Item temp = a[rand];
        a[rand] = a[--size];
        a[size] = null;
        if (size > 0 && size == a.length / 4)
            resize(a.length / 2);
        return temp;
    }

    /**
     * Returns (but do not remove) a random item
     *
     * @return item
     */
    public Item sample() {
        checkElement();
        int rand = StdRandom.uniform(size);
        return a[rand];
    }

    /**
     * Returns  an an independent iterator over items in random order
     *
     * @return independent iterator over solution of Boards
     */
    public Iterator<Item> iterator() {
        return new RandQueueIterator();
    }

    private void checkElement() {
        if (size == 0)
            throw new NoSuchElementException("queue is empty");
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    private class RandQueueIterator implements Iterator<Item> {
        private int current;
        private int[] indexArray;

        public RandQueueIterator() {
            current = 0;
            indexArray = new int[size];
            for (int i = 0; i < size; i++)
                indexArray[i] = i;
            StdRandom.shuffle(indexArray);
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (current >= size)
                throw new NoSuchElementException("queue is empty");
            return a[indexArray[current++]];
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
        // TODO Auto-generated method stub

    }
}
