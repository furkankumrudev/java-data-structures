
package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchTree {
    Node root = null;

    class Node {
        int data;
        Node left, right;

        Node(int key) {
            this.data = key;
            left = right = null;
        }
    }

    public void insert(int key){
        if (search(key) != null) return;
        if (root == null) {
            root = new Node(key);
            return;
        }

        Node current = root, parent = null;
        while(current != null){
            parent = current;
            current = (key < current.data) ? current.left : current.right;
        }

        if (key < parent.data) parent.left = new Node(key);
        else parent.right = new Node(key);

        rebalance();
    }

    public Node search(int key){
        Node current = root;
        while(current != null){
            if (current.data == key) return current;
            current = (key < current.data) ? current.left : current.right;
        }
        return null;
    }

    public boolean delete(int key) {
        if (search(key) == null) return false;
        root = delete(root, key);
        rebalance();
        return true;
    }

    private Node delete(Node root, int key) {
        if (root == null) return null;
        if (key < root.data) root.left = delete(root.left, key);
        else if (key > root.data) root.right = delete(root.right, key);
        else {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node successor = inSucc(root.right);
            root.data = successor.data;
            root.right = delete(root.right, successor.data);
        }
        return root;
    }

    private Node inSucc(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorderTraversal() { inorderTraversal(root); }
    public void preorderTraversal() { preorderTraversal(root); }
    public void postorderTraversal() { postorderTraversal(root); }

    public void inorderTraversal(Node R){
        if (R != null) {
            inorderTraversal(R.left);
            System.out.print(R.data + " ");
            inorderTraversal(R.right);
        }
    }

    public void preorderTraversal(Node R){
        if (R != null) {
            System.out.print(R.data + " ");
            preorderTraversal(R.left);
            preorderTraversal(R.right);
        }
    }

    public void postorderTraversal(Node R){
        if (R != null) {
            postorderTraversal(R.left);
            postorderTraversal(R.right);
            System.out.print(R.data + " ");
        }
    }

    private int getHeight(Node node) {
        if (node == null) return -1;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int getImbalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public void findImbalancedNode() {
        findImbalancedNode(root);
    }

    private void findImbalancedNode(Node node) {
        if (node == null) return;
        findImbalancedNode(node.left);
        int imbalance = getImbalanceFactor(node);
        if (Math.abs(imbalance) >= 2) {
            System.out.println("Imbalanced Node: " + node.data + "    (Imbalance Factor: " + imbalance + ")");
        }
        findImbalancedNode(node.right);
    }

    public void rebalance() {
        List<Integer> sortedList = new ArrayList<>();
        storeInorder(root, sortedList);
        root = buildBalancedTree(sortedList, 0, sortedList.size() - 1);
    }

    private void storeInorder(Node node, List<Integer> list) {
        if (node == null) return;
        storeInorder(node.left, list);
        list.add(node.data);
        storeInorder(node.right, list);
    }

    private Node buildBalancedTree(List<Integer> list, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node midNode = new Node(list.get(mid));
        midNode.left = buildBalancedTree(list, start, mid - 1);
        midNode.right = buildBalancedTree(list, mid + 1, end);
        return midNode;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Random rand = new Random();
        System.out.println("=========== Insertion Phase ===========");

        int inserted = 0;
        List<Integer> insertedVals = new ArrayList<>();
        while (inserted < 20) {
            int val = rand.nextInt(100);
            if (bst.search(val) == null) {
                bst.insert(val);
                inserted++;
                insertedVals.add(val);
            }
        }

        System.out.println("Inserted: " + insertedVals + "\n");

        System.out.println("=========== Additional Insertions ===========");
        int insertedAdd = 0;
        List<Integer> additionalInserted = new ArrayList<>();
        while (insertedAdd < 10) {
            int val = rand.nextInt(100);
            if (bst.search(val) == null) {
                bst.insert(val);
                insertedAdd++;
                additionalInserted.add(val);
            }
        }

        System.out.println("Inserted: " + additionalInserted + "\n");

        System.out.println("=========== Deletion Phase ===========");
        for (int i = 0; i < 10; i++) {
            int val = rand.nextInt(100);
            boolean deleted = bst.delete(val);
            System.out.println((deleted ? "Deleted: " : "Not found: ") + val);
        }

        System.out.println("\n=========== Traversals ===========");
        System.out.print("Inorder:    ");
        bst.inorderTraversal();
        System.out.println();
        System.out.print("Preorder:   ");
        bst.preorderTraversal();
        System.out.println();
        System.out.print("Postorder:  ");
        bst.postorderTraversal();
        System.out.println();

        System.out.println("\nTree Height: " + bst.getHeight(bst.root));
        System.out.println("\n=========== Imbalanced Nodes ===========");
        bst.findImbalancedNode();
    }
}
