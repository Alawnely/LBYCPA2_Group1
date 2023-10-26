package lbycpa2.module5.avl;

public class AVLTree {
    public static class Node {
        private int item, height;
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
        root = insertNode(root, item);
    }

    private Node insertNode(Node node, int item) {
        // Create a new node if it doesn't exist
        if (node == null) {
            return new Node(item);
        }

        // Find where the node will be inserted
        if (item < node.item) {
            node.left = insertNode(node.left, item);
        } else if (item > node.item) {
            node.right = insertNode(node.right, item);
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
        root = deleteNode(root, item);
    }

    private Node deleteNode(Node node, int item) {
        // There's nothing to delete
        if (root == null) {
            return null;
        }

        // Find the item to be deleted
        if (item < node.item) {
            node.left = deleteNode(node.left, item);
        } else if (item > node.item) {
            node.right = deleteNode(node.right, item);
        } else {
            // Perform deletion
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node temp = node.right;
                while (temp != null) {
                    node.item = temp.item;
                    temp = temp.left;
                }
                node.right = deleteNode(node.right, node.item);
            }
        }

        // Update the balance factor
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);

        // Perform balancing
        if (balanceFactor > 1) {
            // Tree is skewed to the left
            if (getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            // Tree is skewed to the right
            if (getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node node) {
        System.out.print(node.item);
        if (node.left != null) {
            preorderTraversal(node.left);
        }
        if (node.right != null) {
            preorderTraversal(node.right);
        }
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node node) {
        if (node.left != null) {
            postorderTraversal(node.left);
        }
        if (node.right != null) {
            postorderTraversal(node.right);
        }
        System.out.print(node.item);
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node.left != null) {
            inorderTraversal(node.left);
        }
        System.out.print(node.item);
        if (node.right != null) {
            inorderTraversal(node.right);
        }
    }

    public void print() {
        print(root, "", true);
    }

    private void print(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(node.item);
            print(node.left, indent, false);
            print(node.right, indent, true);
        }
    }

    // TODO: insert more necessary methods

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        // TODO
    }
}