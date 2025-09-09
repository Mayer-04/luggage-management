package datastructures;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void append(T element) {
        var node = new Node<T>(element);

        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }

        tail.next = node;
        tail = node;
        size++;
    }

    public void prepend(T element) {
        var node = new Node<T>(element);

        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }

        node.next = head;
        head = node;
        size++;
    }

    public void insert(int index, T element) {

    }

    public void remove(T element) {

        Node<T> current = head;

        while (current != null) {

            if (current.value == element) {
                current.value = null;
                current = current.next;
            }

            current = current.next;
        }

    }

    public void removeFirst() {

    }

    public void removeLast() {

    }

    public T get(int index) {
        return null;
    }

    public T getFirst() {
        return null;
    }

    public T getLast() {
        return null;
    }

    public boolean contains(T element) {
        return false;
    }

    public T indexOf(T element) {
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        sb.append("head -> ");
        while (current != null) {
            sb.append("[").append(current.value).append("]");
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append(" <- tail");
        return sb.toString();
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
}
