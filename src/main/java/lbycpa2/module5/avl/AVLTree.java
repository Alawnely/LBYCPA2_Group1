package lbycpa2.module5.avl;

public class AVLTree {
    public static class Node {
        private final int item;
        private int height;
        private Node left, right;

        public Node(int d) {
            item = d;
            height = 1;
        }
    }

    private Node root;

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public Node rightRotate(Node rightNode) {
        // Perform rotation
        Node leftNode = rightNode.left;
        Node temp = leftNode.right;
        leftNode.right = rightNode;
        rightNode.left = temp;

        // Update height value
        leftNode.height = Math.max(getHeight(leftNode.left), getHeight(leftNode.right)) + 1;
        rightNode.height = Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;
        return leftNode;
    }

    public Node leftRotate(Node leftNode) {
        // Perform rotation
        Node rightNode = leftNode.right;
        Node temp = rightNode.left;
        rightNode.left = leftNode;
        leftNode.right = temp;

        // Update height value
        leftNode.height = Math.max(getHeight(leftNode.left), getHeight(leftNode.right)) + 1;
        rightNode.height = Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;
        return rightNode;
    }

    public void insertNode(int item) {
        root = insertNodeRec(root, item);
    }

    private Node insertNodeRec(Node node, int item) {
        // Create a new node if it doesn't exist
        if (node == null) {
            return new Node(item);
        }

        // Find where the node will be inserted
        if (item < node.item) {
            node.left = insertNodeRec(node.left, item);
        } else if (item > node.item) {
            node.right = insertNodeRec(node.right, item);
        } else {
            return node;
        }

        // Update the balance factor
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);

        // Perform balancing
        if (balanceFactor > 1) {
            // Tree is skewed to the left
            if (item > node.left.item) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            // Tree is skewed to the right
            if (item < node.right.item) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
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