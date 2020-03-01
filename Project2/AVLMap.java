

import java.util.Vector;

public class AVLMap<K extends Comparable<K>, V> extends BSTMap<K,V> {

    boolean debug = false;

    // super calls the constructor for the BST map, which sets the root to null.
    public AVLMap() {
        super();
    }

    @Override
    public void put(K key, V value) throws Exception {
        super.put(key, value);
        if (debug) System.out.println("Size: " + this.root.getSize());
        // If it's unbalanced then balance it. 
        if (!isBalanced(this.root)) {
            rebalance(this.root);
        }
    }

    private boolean isBalanced(TreeNode<K,V> root) {
        if (root == null) return true;
        // Get the height of the left and right branches. 
        int lh = getHeight(root.getLeftChild());
        int rh = getHeight(root.getRightChild());
        if (debug) System.out.println("l: " + lh + ", r: " + rh);
        // Take the absolute value of the difference of the two heights.
        // If greater than 1, not balanced. 
        if (Math.abs(lh - rh) > 1) {
            return false;
        }
        // Is it balanced. 
        return true;
    }
    
    // Rebalancing by dumping tree to sorted list and reconstructing
    // Node Rotations method proved to be unfruitful ;(
    private void rebalance(TreeNode<K,V> root) {
        Vector<TreeNode<K,V>> list = new Vector<>();
        saveToList(root, list);
        if (debug) System.out.println("List: " + list.size());
        this.root = rebuild(list, 0, list.size() - 1);
        getHeight(this.root);

    }

    private TreeNode<K,V> rebuild(Vector<TreeNode<K,V>> list, int start, int end) {
        if (start > end) {
            return null;
        }
        // Look for middle node and set as root. 
        // Everything smaller than mid is considered left. 
        // Everything larger than mid is considered right. 
        // Recursively keep doing the same thing each time. 
        int mid = (start + end) / 2;
        TreeNode<K,V> node = list.get(mid);

        if (debug) System.out.println("Mid: " + mid + ", Node: " + node);

        node.setLeftChild(rebuild(list, start, mid - 1));
        node.setRightChild(rebuild(list, mid + 1, end));

        return node;
    }

    private void saveToList(TreeNode<K,V> root, Vector<TreeNode<K,V>> list) {
        if (root == null) {
            return;
        }
        // Add everything to list depending where it falls from middle. 
        // Create new TreeNode to start a fresh tree. 
        saveToList(root.getLeftChild(), list);
        list.add(new TreeNode<K,V>(root.getKey(), root.getValue()));
        saveToList(root.getRightChild(), list);
    }



    private int getHeight(TreeNode<K,V> node) {
        if (node == null) {
            return 0;
        }
        // Recursively add up everything on right and left plus itself. 
        node.setHeight(Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())) + 1);

        return node.getHeight();
    }

    @Override
    public String toString() {
        return "AVLMap{" +
                "root=" + root +
                '}';
    }

    public String getType() {
        return "AVLMap";
    }
}
