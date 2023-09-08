/**
 * Represents a generic doubly-linked list deque (double-ended queue).
 *
 * @param <T> the type of elements held in this deque
 */
public class LinkedListDeque<T> {

    private Node sentinel;  // Sentinel node to facilitate operations on the deque
    private int size;       // Number of items in the deque
    /**
     * Constructs an empty LinkedListDeque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item to the front of the deque.
     *
     * @param item the item to be added
     */
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Adds an item to the back of the deque.
     *
     * @param item the item to be added
     */
    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Checks if the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return the size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            if (i.next == sentinel) {
                System.out.println(i.item);
                break;
            }
            System.out.print(i.item + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     *
     * @return the item at the front, or null if the deque is empty
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque.
     *
     * @return the item at the back, or null if the deque is empty
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return res;
    }

    /**
     * Retrieves the item at a given index.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds
     */
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /**
     * Retrieves the item at a given index using recursion.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if the index is out of bounds
     */
    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /**
     * Helper method to recursively retrieve the item at a given index.
     *
     * @param node the current node being examined
     * @param i    the remaining index to traverse
     * @return the item at the specified index
     */
    private T getRecursive(LinkedListDeque<T>.Node node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursive(node.next, i - 1);
    }

    /**
     * Represents a node in the LinkedListDeque.
     */
    private class Node {
        private Node prev;  // Reference to the previous node
        private T item;     // Data item stored in the node
        private Node next;  // Reference to the next node

        /**
         * Constructs a new Node.
         *
         * @param prev reference to the previous node
         * @param item data item to be stored in the node
         * @param next reference to the next node
         */
        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
