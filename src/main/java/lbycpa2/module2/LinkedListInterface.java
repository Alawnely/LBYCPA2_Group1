package lbycpa2.module2;

public interface LinkedListInterface<E> {

    public void add (E e);
    public void clear();
    public E get (int index) throws ListEmptyException;
    public void remove(int index);
    public int size();
    public boolean isEmpty();

}
