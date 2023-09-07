public class LinkedListDeque<T> {
    private Node sentinel; // sentinel node for dummy
    private int size; // size counter

    private class Node { // invariants
        private Node prev; // dummy node for last
        private T item; // initialize Generic type T item
        private Node next; // dummy node for first

        // create one last dummy node and one first dummy node referencing the prev and next
        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev; // store the last reference node
            this.item = item; // store the element of the node
            this.next = next; // store the first reference node
        }
    }

    public LinkedListDeque() { // empty LL
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel; // set the first point to the sent
        sentinel.next = sentinel; // set the last point to the sent
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) { // add first function
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) { // add an item to the last of the list
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() { // true/false for empty
        return size == 0;
    }

    public int getSize() { // return the size of the deque
        return size;
    }

    public void printDeque() { // print out the LL
        // sentinel.next is the next item
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            if (i.next == sentinel) { // if the next item of the index is the sentinel which is the head
                System.out.println(i.item);// print out the last item that the index was on
                break; // code will stop
            }
            System.out.print(i.item + " ");
        }
    }

    public T removeFirst() { // if the list is empty return null
        if (isEmpty()) {
            return null;
        }
        T remove = sentinel.next.item; // traverse through the dummy node first
        sentinel.next = sentinel.next.next; // set the node pointing to the node after the removed node
        sentinel.next.prev = sentinel; // set the sent node to be the head
        size--;
        return remove;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T remove = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return remove;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
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

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(LinkedListDeque<T>.Node node, int i) { // helper recursive
        if (i == 0) {
            return node.item;
        }
        return getRecursive(node.next, i - 1);
    }
}
