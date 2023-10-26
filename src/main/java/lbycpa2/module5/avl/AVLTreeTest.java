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

        tree.insertNode("c");
        tree.insertNode("d");
        assertDoesNotThrow(tree::rightRotate);
        assertEquals("a b c d ", tree.preorderTraversal());
    }

    @Test
    void leftRotate() {
        AVLTree tree = new AVLTree();
        tree.insertNode("a");
        tree.insertNode("b");
        assertDoesNotThrow(tree::leftRotate);
        assertEquals("b a ", tree.preorderTraversal());

        tree.insertNode("c");
        tree.insertNode("d");
        assertDoesNotThrow(tree::leftRotate);
        assertEquals("c b a d ", tree.preorderTraversal());
    }

    @Test
    void insertNode() {
        char[] alphabet = new char[26];
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet[c - 'a'] = c;
        }

        AVLTree tree = new AVLTree();
        for (char c : alphabet) {
            assertDoesNotThrow(() -> tree.insertNode(""+c));
        }
        assertEquals("p h d b a c f e g l j i k n m o t r q s x v u w y z ", tree.preorderTraversal());

        tree.clear();
        for (int i = 25; i >= 0; i--) {
            int idx = i;
            assertDoesNotThrow(() -> tree.insertNode(""+alphabet[idx]));
        }
        assertEquals("k g c b a e d f i h j s o m l n q p r w u t v y x z ", tree.preorderTraversal());
    }

    @Test
    void deleteNode() {
        char[] alphabet = new char[26];
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet[c - 'a'] = c;
        }

        AVLTree tree = new AVLTree();
        for (char c : alphabet) {
            tree.insertNode(""+c);
        }
        for (int i = 1; i < 26; i += 3) {
            tree.deleteNode(""+alphabet[i]);
        }
        assertEquals("p i d c a f g l j o m u r s x v y ", tree.preorderTraversal());
    }
}