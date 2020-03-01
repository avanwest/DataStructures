
import java.util.ArrayList;
import java.util.List;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {
    protected TreeNode<K,V> root;

    public BSTMap() {
        this.root = null;
    }

    @Override
    public void put(K key, V value) throws Exception {
        TreeNode<K,V> node = new TreeNode<>(key, value);
        this.root = insertInTree(this.root, node);
    }
    
    //protected methods for use in AVL Tree. 

    protected TreeNode<K,V> insertInTree(TreeNode<K,V> root, TreeNode node) throws Exception {
    		// Make node the root node if empty.
        if (root == null) {
            root = node;
            return root;
        }
        // If node is less than the root and/or is null, insert it at left side
        // or keep looking down left side (recursively).
        else {
            if (node.getKey().compareTo(root.getKey()) < 0) {
                if ( root.getLeftChild() == null ) {
                    root.setLeftChild(node);
                }
                else {
                    insertInTree(root.getLeftChild(), node);
                }
            }
            // If node is greater than root and/or null, insert at right side 
            // or keep looking down right side (recursively). 
            else if (node.getKey().compareTo(root.getKey()) > 0) {
                if ( root.getRightChild() == null) {
                    root.setRightChild(node);
                }
                else {
                    insertInTree(root.getRightChild(), node);
                }
            }
            // Otherwise it's a duplicate.
            else {
                throw new Exception("Duplicate keys not allowed, key: " + node.getKey());
            }
        }
        return root;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        // If we find node, return value otherwise return null.
        TreeNode<K,V> node = findInTree(this.root, key);
        return (node != null) ? node.getValue() : null;
    }

    // AVL uses this method.
    protected TreeNode<K,V> findInTree(TreeNode<K,V> root, K key) {
        if (root == null) {
            return null;
        }
        // If on leftSide than go left, if right go right.
        // Otherwise we found the node. 
        if (key.compareTo(root.getKey()) < 0) {
            return findInTree(root.getLeftChild(), key);
        }
        else if (key.compareTo(root.getKey()) > 0) {
            return findInTree(root.getRightChild(), key);
        }
        else {
            return root;
        }
    }

    // Had a lot of difficulty with this
    // Inspired directly from https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
    private TreeNode<K,V> removal(TreeNode<K,V> root, K key) {
        if (root == null)  
        	return root;
        // Search down left from our current root node or search down right. 
        if (key.compareTo(root.getKey()) < 0)
            root.setLeftChild(removal(root.getLeftChild(), key));
        else if (key.compareTo(root.getKey()) > 0)
            root.setRightChild(removal(root.getRightChild(), key));
        else
        {
        		// Is this a node with only one child? 
            if (root.getLeftChild() == null)
                return root.getRightChild();
            else if (root.getRightChild() == null)
                return root.getLeftChild();
            // Look for the min key value on right branch 
            root = findMinNode(root.getRightChild());
            // Recursivley remove it from wherever we found it. 
            root.setRightChild(removal(root.getRightChild(), root.getKey()));
        }
        return root;
    }

    @Override
    public V remove(K key) {
    		// calling the recursive function. If it has a value then return it. 
        TreeNode<K,V> node = removal(this.root, key);
        return (node != null) ? node.getValue() : null;
    }


    protected TreeNode<K,V> findMinNode(TreeNode<K,V> root) {
        K minKey = root.getKey();
        // Make a copy of the node as root. 
        TreeNode<K,V> node = root;
        // Go down leftSide until we find the smallest. 
        while (root.getLeftChild() != null) {
            node = root.getLeftChild();
            minKey = node.getKey();
            root = root.getLeftChild();
        }
        return node;
    }


    protected TreeNode<K,V> findMaxNode(TreeNode<K,V> root) {
        K maxKey = root.getKey();
        // Make copy of the node, set as root. 
        TreeNode<K,V> node = root;
        // Go down rightSide until you find max. 
        while (root.getRightChild() != null) {
            node = root.getRightChild();
            maxKey = node.getKey();
            root = root.getRightChild();
        }
        return node;
    }


    protected boolean isOnLeftBranch(TreeNode<K,V> node) {
        if (node == null || node.getParent() == null) return false;
        // Is it on left or right branch?
        return (node.getKey().compareTo(node.getParent().getKey()) < 0) ? true : false;
    }

    @Override
    public int size() {
        return (root == null) ? 0 : root.getSize();

    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public String toString() {
        return "BSTMap{" +
                "root=" + root +
                '}';
    }

    public String getType() {
        return "BSTMap";
    }


}
