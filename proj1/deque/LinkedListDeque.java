package deque;

public class LinkedListDeque <T> {
    /*
    - add a private class like IntNode
    - learn Generic type
     */
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.size = 0;
    }

    public LinkedListDeque(T item) {
        this.sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, null, sentinel);
        this.size = 1;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

}
