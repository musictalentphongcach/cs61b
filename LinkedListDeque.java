public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() { //emty LL
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) { // add first function
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }


    public void addLast(T item) { // add an item to last of list
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }


    public boolean isEmpty() { // true false for empty
        return size == 0;
    }


    public int getSize() { //return the size of the dq
        return size;
    }

    public void printDeque() { // print out the LL
        // sentinel.next is the next item
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            if (i.next == sentinel) {
                System.out.println(i.item);
                break;
            }
            System.out.print(i.item + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
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
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
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
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (size < index) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (size < index) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(LinkedListDeque<T>.Node node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursive(node.next, i - 1);
    }

}