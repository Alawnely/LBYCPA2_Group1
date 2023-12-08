package lbycpa2.module2;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @org.junit.jupiter.api.Test
    void add() {
        // INITIALIZE LIST
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Random random = new Random();
        int size = 10+random.nextInt(91);

        // ENSURE ALL NODES ARE ADDED
        assertDoesNotThrow(() -> {
            for (int i = 0; i < size; i++) {
                list.add(random.nextInt(1000));
            }
        });
        assertEquals(size, list.size());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        // INITIALIZE LIST
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        // ENSURE LIST IS CLEARED
        list.clear();
        assertEquals(0, list.size());
    }

    @org.junit.jupiter.api.Test
    void get() {
        // INITIALIZE LIST
        MyLinkedList<Integer> list = new MyLinkedList<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // ENSURE CORRECT NODE IS OBTAINED
        for (int i = 0; i < size; i++) {
            assertEquals(i, list.get(i));
        }

        // ENSURE PROPER EXCEPTION IS THROWN
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(110));
        list.clear();
        assertThrows(ListEmptyException.class, () -> list.get(0));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        // INITIALIZE LIST
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Random random = new Random();
        int size = 10+random.nextInt(41);
        int idx = 2+random.nextInt(size-3);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // ENSURE CORRECT NODE IS REMOVED
        assertDoesNotThrow(() -> list.remove(size-1));
        assertEquals(size-2, list.get(size-2));
        assertDoesNotThrow(() -> list.remove(idx));
        assertEquals(idx+1, list.get(idx));
        assertDoesNotThrow(() -> list.remove(0));
        assertEquals(1, list.get(0));

        // ENSURE PROPER EXCEPTION IS THROWN
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(110));
        list.clear();
        assertThrows(ListEmptyException.class, () -> list.remove(0));
    }

    @org.junit.jupiter.api.Test
    void size() {
        // INITIALIZE LIST
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Random random = new Random();
        int size = 10+random.nextInt(101);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000));
        }

        // ENSURE CORRECT SIZE IS RETURNED
        assertEquals(size, list.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());
    }
}