package lbycpa2.module3.stack;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @org.junit.jupiter.api.Test
    void push() {
        // INITIALIZE STACK
        Random random = new Random();
        int size = random.nextInt(5, 11);
        MyStack<Integer> stack = new MyStack<>(size);

        // ENSURE ALL ELEMENTS ARE PUSHED
        assertDoesNotThrow(() -> {
            for (int i = 0; i < size; i++) {
                stack.push(i);
            }
        });

        // ENSURE PROPER EXCEPTION IS THROWN
        assertThrowsExactly(StackFullException.class, () -> stack.push(100));
    }

    @org.junit.jupiter.api.Test
    void pop() {
        // INITIALIZE STACK
        Random random = new Random();
        int size = random.nextInt(5, 11);
        MyStack<Integer> stack = new MyStack<>(size);
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }

        // ENSURE ALL ELEMENTS ARE POPPED
        assertDoesNotThrow(() -> {
            for (int i = 0; i < size; i++) {
                stack.pop();
            }
        });

        // ENSURE PROPER EXCEPTION IS THROWN
        assertThrowsExactly(StackEmptyException.class, stack::pop);
    }

    @org.junit.jupiter.api.Test
    void top() {
        // INITIALIZE STACK
        Random random = new Random();
        int size = random.nextInt(5, 11);
        MyStack<Integer> stack = new MyStack<>(size);

        // ENSURE PROPER EXCEPTION IS THROWN
        assertThrowsExactly(StackEmptyException.class, stack::top);

        // ENSURE CORRECT ELEMENT IS OBTAINED
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }
        assertEquals(size-1, stack.top());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        MyStack<Integer> stack = new MyStack<>(0);
        assertTrue(stack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        // INITIALIZE STACK
        int size = 10;
        MyStack<Integer> stack = new MyStack<>(size);
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }

        // ENSURE STACK IS FULL
        assertTrue(stack.isFull());
    }

    @org.junit.jupiter.api.Test
    void size() {
        // INITIALIZE STACK
        Random random = new Random();
        int size = random.nextInt(11);
        MyStack<Integer> stack = new MyStack<>(size);

        // ENSURE CORRECT SIZE IS RETURNED
        assertEquals(size, stack.size());
    }
}