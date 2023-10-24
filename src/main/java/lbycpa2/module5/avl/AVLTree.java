package lbycpa2.module5.avl;

public class AVLTree {
    public static class Node {
        public int item, height;
        public Node left, right;

        public Node(int d) {
            item = d;
            height = 1;
        }
    }

    private Node root;

    public int getHeight(Node node) {
        // TODO
        return 0;
    }

    public int getBalanceFactor(Node node) {
        // TODO
        return 0;
    }

    public Node rightRotate(Node rightNode) {
        // TODO
        return null;
    }

    public Node leftRotate(Node leftNode) {
        // TODO
        return null;
    }

    public void insertNode(int item) {
        if (root == null) {
            root = new Node(item);
        }
        insertNodeRec(root, item);
    }

    private Node insertNodeRec(Node node, int item) {
        // TODO
        return null;
    }

    public void deleteNode(int item) {
        if (root == null) {
            root = new Node(item);
        }
        deleteNodeRec(root, item);
    }

    private Node deleteNodeRec(Node node, int item) {
        // TODO
        return null;
    }

    // TODO: insert more necessary methods

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        // TODO
    }
}
