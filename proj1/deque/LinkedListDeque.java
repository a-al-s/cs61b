package deque;

public class LinkedListDeque <T> {
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
        // the next and prev can be null but I think for circular list it should be the same sentinel
        // this.sentinel
        this.sentinel = new Node(null, null, null);
        this.size = 0;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            this.size++;
        } else {
            Node n = sentinel.next;
            sentinel.next = new Node(item, n, sentinel);
            this.size++;
        }
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            this.size++;
        } else {
            Node p = sentinel.prev;
            sentinel.prev = new Node(item, sentinel, p);
            this.size++;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (this.size > 0) {
            Node first = sentinel.next;
            while (first.item != null) {
                System.out.print(first.item + " ");
                first = first.next;
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node firstItem = sentinel.next;
            sentinel.next = firstItem.next;
            this.size--;
            return firstItem.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node lastItem = sentinel.prev;
            sentinel.prev = lastItem.prev;
            this.size--;
            return lastItem.item;
        }
    }

}
