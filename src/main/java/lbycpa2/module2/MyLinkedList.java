package lbycpa2.module2;

public class MyLinkedList<E> implements LinkedListInterface<E> {

    public class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    private Node<E> head;
    private int size;

    public MyLinkedList() {
        head = null;
    }

    public void add(Object e){
        if (isEmpty()) {
            head = new Node(e);
        } else {
            Node<E> currNode = head;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = new Node(e);
        }
        size++;
    }

    public void clear(){
        head = null;
        size = 0;
    }

    public E get (int index){
        if (isEmpty()) {
            throw new ListEmptyException("Attempted to get from an empty linked list.");
        } else if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Specified index " + index + " outside of range 0-" + (size-1));
        }

        Node<E> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.item;
    }

    public void remove (int index){
        if (isEmpty()) {
            throw new ListEmptyException("Attempted to get from an empty linked list.");
        } else if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Specified index " + index + " outside of range 0-" + (size-1));
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node<E> currNode = head;
            for (int i = 0; i < index - 1; i++) {
                currNode = currNode.next;
            }
            currNode.next = currNode.next.next;
        }
        size--;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}