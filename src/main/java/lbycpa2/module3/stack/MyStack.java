package lbycpa2.module3.stack;

public class MyStack<E> implements StackInterface<E> {

    private final Object[] array;       // Or use https://www.baeldung.com/java-generic-array
    private final int size;
    private int tos;

    public MyStack(int size) {
        this.size = size;
        tos = 0;
        array = new Object[size];
    }

    @Override
    public void push(E j) throws StackFullException {
        if (isFull()) {
            throw new StackFullException("Attempted to push to a full stack.");
        }
        array[tos] = j;
        tos++;
    }

    @Override
    public void pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Attempted to pop from an empty stack.");
        }
        tos--;
    }

    @Override
    public E top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Attempted to peek on an empty stack.");
        }
        return (E) array[tos-1];
    }

    @Override
    public boolean isEmpty() {
        return tos == 0;
    }

    @Override
    public boolean isFull() {
        return tos == size;
    }

    @Override
    public int size() {
        return size;
    }
}
