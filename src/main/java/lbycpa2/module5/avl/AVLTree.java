package lbycpa2.module5.avl;

public class AVLTree {
    public static class Node {
        private String item;
        private int height;
        private Node left, right;

        public Node(String word) {
            item = word;
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
        rightNode.height = Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;
        leftNode.height = Math.max(getHeight(leftNode.left), getHeight(leftNode.right)) + 1;
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

    public void insertNode(String item) {
        root = insertNode(root, item);
    }

    private Node insertNode(Node node, String item) {
        // Create a new node if it doesn't exist
        if (node == null) {
            return new Node(item);
        }

        // Find where the node will be inserted
        int compareResult = item.compareTo(node.item);
        if (compareResult < 0) {
            node.left = insertNode(node.left, item);
        } else if (compareResult > 0) {
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
            if (item.compareTo(node.left.item) > 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            // Tree is skewed to the right
            if (item.compareTo(node.right.item) < 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    public void deleteNode(String item) {
        root = deleteNode(root, item);
    }

    private Node deleteNode(Node node, String item) {
        // There's nothing to delete
        if (root == null) {
            return null;
        }

        // Find the item to be deleted
        int compareResult = item.compareTo(node.item);
        if (compareResult < 0) {
            node.left = deleteNode(node.left, item);
        } else if (compareResult > 0) {
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
        System.out.println();
    }

    private void preorderTraversal(Node node) {
        System.out.print(node.item+" ");
        if (node.left != null) {
            preorderTraversal(node.left);
        }
        if (node.right != null) {
            preorderTraversal(node.right);
        }
    }

    public void postorderTraversal() {
        postorderTraversal(root);
        System.out.println();
    }

    private void postorderTraversal(Node node) {
        if (node.left != null) {
            postorderTraversal(node.left);
        }
        if (node.right != null) {
            postorderTraversal(node.right);
        }
        System.out.print(node.item+" ");
    }

    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if (node.left != null) {
            inorderTraversal(node.left);
        }
        System.out.print(node.item+" ");
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
                System.out.print("R");
                indent += "   ";
            } else {
                System.out.print("L");
                indent += "\u2502  ";
            }
            System.out.print((node.left != null || node.right != null) ? "\u2500\u2500\u252c\u2500" : "\u2500\u2500\u2500\u2500");
            System.out.println(node.item);

            print(node.left, indent, false);
            print(node.right, indent, true);
        }
    }

    private static final String sentence = "The quick brown fox jumps over the lazy dog.";

    public static void main(String[] args) {
        String[] words = sentence.split("\\s+");
        AVLTree tree = new AVLTree();

        // Insert words
        for (String word : words) {
            // Remove punctuation
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!word.isEmpty()) {
                tree.insertNode(word);
            }
        }
        tree.print();

        // Delete words
        System.out.println("\nAfter Deletion: ");
        tree.deleteNode("brown");
        tree.deleteNode("quick");
        tree.print();

        // Print infix
        System.out.print("\nIn-order: ");
        tree.inorderTraversal();

        // Print postfix
        System.out.print("Post-order: ");
        tree.postorderTraversal();

        // Print prefix
        System.out.print("Pre-order: ");
        tree.preorderTraversal();
    }
}