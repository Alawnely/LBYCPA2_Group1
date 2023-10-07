package lbycpa2.module4;

// MyQueue.java
public class MyQueue<T> implements QueueInterface<T> {

    private int front, back;
    private T[] arr;

    private int MAX_SIZE;
    public MyQueue(int size) {
        front = 0;
        back = 0;
        arr = (T[] )new Object[size];
        this.MAX_SIZE = size;
    }

    @Override
    public void push(Object item) {
        /* Insert */
        arr[back] = (T) item;
        back = (back + 1) % MAX_SIZE; /* wraps around */
    }

    @Override
    public T pop() {
        /* remove and pop */
        /* front ++ */
        T item = arr[front];
        front = (front + 1) % MAX_SIZE;
        return item;
    }

    @Override
    public T front() {
        return arr[front];
    }

    @Override
    public T back() {
        int index;
        if (back == 0) {
            index = MAX_SIZE - 1;
        } else {
            index = back - 1;
        }
        return arr[index];
    }
    @Override
    public int size() {
        return (back - front + MAX_SIZE) % MAX_SIZE;
    }

    @Override
    public boolean empty() {
        return front==back;
    }
}
