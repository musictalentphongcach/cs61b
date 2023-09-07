public class ArrayDeque<T> {
    private T[] items; // Array to store deque items
    private int left; // Index for the front of the deque
    private int right; // Index for the back of the deque
    private int capacity = 8; // Initial capacity of the array

    public ArrayDeque() {
        items = (T[]) new Object[capacity]; // Initialize array with initial capacity
        left = 0; // Index for the front of the deque
        right = 0; // Index for the back of the deque
    }

    // Adds an item to the front of the deque
    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (capacity * 2)); // If full, resize the array to increase capacity
        }
        left = (left - 1 + capacity) % capacity; // Calculate the new front index
        items[left] = item; // Add the item at the new front index
    }

    // Adds an item to the back of the deque
    public void addLast(T item) {
        if (isFull()) {
            resize((int) (capacity * 2)); // If full, resize the array to increase capacity
        }
        items[right] = item; // Add the item at the current back index
        right = (right + 1) % capacity; // Calculate the new back index in a circular method
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return left == right; // If the front and back indices are the same, the deque is empty
    }

    // Returns the number of items in the deque
    public int size() {
        return (right - left + capacity) % capacity; // Calculate the size of the deque in a circular manner
    }

    // Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        int size = size(); // Get the size of the deque
        for (int i = 0; i < size; i++) {
            int index = (left + i) % capacity; // Calculate the index for the current item
            if (i == size - 1) {
                System.out.println(items[index]); // Print the last item with a newline
            } else {
                System.out.print(items[index] + " "); // Print the item with a space separator
            }
        }
    }

    // Removes and returns the item at the front of the deque
    public T removeFirst() {
        if (isEmpty()) {
            return null; // If empty, return null
        }
        T res = items[left]; // Get the item at the front
        left = (left + 1) % capacity; // Calculate the new front index in a circular manner
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5)); // Resize the array to reduce capacity
        }
        return res; // Return the removed item
    }

    // Removes and returns the item at the back of the deque
    public T removeLast() {
        if (isEmpty()) {
            return null; // If empty, return null
        }
        right = (right - 1 + capacity) % capacity; // Calculate the new back index in a circular manner
        T res = items[right]; // Get the item at the back
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5)); // Resize the array to reduce capacity
        }
        return res; // Return the removed item
    }

    // Gets the item at the given index
    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) {
            return null; // If invalid index or empty deque, return null
        }
        int itemIndex = (left + index) % capacity; // Calculate the index for the requested item
        return items[itemIndex]; // Return the item at the calculated index
    }

    // Checks if the deque is full
    private boolean isFull() {
        return size() == capacity - 1; // Check if the size is equal to one less than capacity
    }

    // Checks if resizing is needed based on usage
    private boolean isLowUsageRate() {
        return capacity >= 16 && size() / (double) capacity < 0.25; // Check if the usage rate is low
    }

    // Resizes the array to a new capacity
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize]; // Create a new array with the specified capacity
        int size = size(); // Get the current size of the deque
        for (int i = 0; i < size; i++) {
            int index = (left + i) % capacity; // Calculate the index for the current item
            newArray[i] = items[index]; // Copy items to the new array
        }
        left = 0; // Reset the front index
        right = size; // Set the back index to the new size
        items = newArray; // Update the deque to use the new array
        capacity = newSize; // Update the capacity
    }
}
