public class LinkedListDeque<Gen> {
    public IntNode first;
    public int size;

    public LinkedListDeque() {
        first = null;
        size = 0;
    }

    public int size(){
        return size;
    }
    public LinkedListDeque(Gen x) {
        first = new IntNode(x, null);
        first.next = first;
        size++;
    }

    public void addFirst(Gen x) {
        size++;
        first = new IntNode(x, first);
    }

    public void addLast(Gen x) {
        size++;
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        first = new IntNode(x, first);
    }

    public int sizeList() {
        return size;
    }

    public void printDeque() {
        IntNode p = first;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public Gen removeFirst() {
        if (first == null) {
            return null;
        }
        Gen removedItem = first.item;
        first = null;
        size--;
        return removedItem;
    }

    public Gen removeLast() { //remove last item of the LL
        if (first == null) { //check if the list exist
            return null;
        }
        if (first.next == null) { // if the list only has 2 item remove the item and decrease by 1
            Gen removedItem = first.item;
            first = null;
            size--;
            return removedItem;
        }
        IntNode p = first; // use the pointer p to traverse thru the LL
        while (p.next.next != null) { // check if the second to last node is valid
            p = p.next;
        }
        Gen removedItem = p.next.item; // initialize Gen object to store the remove item
        p.next = null;
        size--;
        return removedItem;
    }

    public Gen get(int x) { // get the item at the specified index
        if (x < 0 || x >= size) { // check the range of the index if invalid return null
            return null;
        }
        IntNode p = first; // use pointer p to traverse thru the array
        for (int i = 0; i < x; i++) {
            p = p.next;
        }
        return p.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public class IntNode {
        public Gen item;
        IntNode next;

        public IntNode(Gen i, IntNode n) {
            this.item = i;
            this.next = n;
        }
    }
}
