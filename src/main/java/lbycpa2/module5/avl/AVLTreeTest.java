package lbycpa2.module5.avl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    @Test
    void rightRotate() {
        AVLTree tree = new AVLTree();
        tree.insertNode("b");
        tree.insertNode("a");

        assertDoesNotThrow(tree::rightRotate);
        assertEquals("a b ", tree.preorderTraversal());
    }

    @Test
    void leftRotate() {
    }

    @Test
    void insertNode() {
    }

    @Test
    void deleteNode() {
    }
}