public class ArrayDeque<T> {
    private T[] items; // Array to store deque items
    private int nextFirst; // Index for the next first item
    private int nextLast; // Index for the next last item
    private int size; // Current number of items in the deque

    private static final int startSize = 8; // Initial capacity of the array
    private static final int reFact = 2; // Resizing factor for the array

    public ArrayDeque() {
        items = (T[]) new Object[startSize]; // Initialize array with initial capacity
        nextFirst = 0; // Index for the first item (empty)
        nextLast = 1; // Index for the second item (empty)
        size = 0; // Initialize size to zero (empty deque)
    }

    // Calculate the index for the previous position in the array (circular)
    private void getFront() {
        nextFirst = minusOne(nextFirst);
    }

    // Calculate the index for the next position in the array (circular)
    private void getLast() {
        nextLast = plusOne(nextLast);
    }

    // Calculate the next index in circular fashion
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    // Calculate the previous index in circular fashion
    private int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    // Check if the deque is full
    private boolean isFull() {
        return size == items.length;
    }

    // Resize the array to a new capacity
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity]; // Create a new array with the new capacity
        int start = plusOne(nextFirst); // Find the index of the first item in the new array
        for (int i = 0; i < size; i++) {
            newArray[i] = items[start]; // Copy items from the old array to the new array
            start = plusOne(start); // Move to the next item in the old array
        }
        items = newArray; // Update the array reference
        nextFirst = newArray.length - 1; // Update the index for the first item
        nextLast = size; // Update the index for the next item
    }

    // Check if resizing is needed based on usage
    private void checkSize() {
        if (isSparse()) {
            reSmall(); // Shrink the array if usage is low
        } else if (isFull()) {
            reLarge(); // Extend the array if it is full
        }
    }

    // Add an item to the front of the deque
    public void addFirst(T item) {
        items[nextFirst] = item; // Add the item to the nextFirst position
        getFront(); // Update nextFirst index
        size++; // Increase the size
        checkUsage(); // Check if resizing is needed
    }

    // Add an item to the end of the deque
    public void addLast(T item) {
        items[nextLast] = item; // Add the item to the nextLast position
        getLast(); // Update nextLast index
        checkUsage(); // Check if resizing is needed
        size++; // Increase the size

    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the deque
    public int size() {
        return size;
    }

    // Check if the array is sparsely used
    private boolean isSparse() {
        return items.length >= 16 && size < items.length / 4;
    }

    // Resize the array to a smaller capacity
    private void reSmall() {
        resize(items.length / reFact);
    }

    // Resize the array to a larger capacity
    private void reLarge() {
        resize(reFact * items.length);
    }

    // Remove the first item from the deque
    public T removeFirst() {
        T returnItem = items[plusOne(nextFirst)]; // Get the item to be removed
        items[plusOne(nextFirst)] = null; // Clear the item
        nextFirst = plusOne(nextFirst); // Update nextFirst index

        size--; // Decrease the size
        checkSize(); // see if resizing is rneeded
        return returnItem; // Return the removed item
    }

    // Remove the last item from the deque
    public T removeLast() {
        T returnItem = items[minusOne(nextLast)]; // Get the item to be removed
        items[minusOne(nextLast)] = null; // Clear the item
        nextLast = minusOne(nextLast); // Update nextLast index
        checkSize(); // Check if resizing is needed
        size--; // Decrease the size

        return returnItem; // Return the removed item
    }

    // Print the contents of the deque
    public void printDeque() {
        for (int i = plusOne(nextFirst), count = 0; count < size; i = plusOne(i), count++) {
            System.out.print(items[i] + " "); // Print each item in the deque
        }
        System.out.println(); // Print a new line at the end
    }

    // Get an item at the specified index
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null; // Return null if index is out of bounds
        }
        return items[(plusOne(nextFirst) + index) % items.length]; // Get the item using circular indexing
    }
}
