package lbycpa2.module3.stack;

public interface StackInterface<E> {
    public void push(E j) throws StackFullException;

    public void pop() throws StackEmptyException;

    public E top() throws StackEmptyException;

    public boolean isEmpty();

    public boolean isFull();

    public int size();
}
