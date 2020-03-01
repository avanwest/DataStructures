

import java.util.Objects;

public class TreeNode<K extends Comparable<K>,V>  {

    private K key;
    private V value;
    private int size;
    private int height;
    private TreeNode<K,V> parent;
    private TreeNode<K,V> leftChild;
    private TreeNode<K,V> rightChild;


    public TreeNode(K key, V value) {

        this.key = key;
        this.value = value;
        this.size = 1;
        this.height = 1;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;

    }

    public K getKey() {
        return key;
    }


    public V getValue() {
        return value;
    }


    public TreeNode<K,V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<K,V> leftChild) {
    		// Set leftchild and update child's parent pointer.
        this.leftChild = leftChild;
        if ( leftChild != null) {
            leftChild.setParent(this);
        }
    }

    public TreeNode<K,V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<K,V> rightChild) {
    		// Set rightChild and update child's parent pointer. 
        this.rightChild = rightChild;
        if ( rightChild != null ) {
            rightChild.setParent(this);
        }
    }

    public TreeNode<K, V> getParent() {
        return parent;
    }

    public void setChild(TreeNode<K,V> node) {
    		// Set if leftChild. 
        if (node.getKey().compareTo(key) < 0) {
            setLeftChild(node);
        }
        // Set as rightChild. 
        else if (node.getKey().compareTo(key) > 0) {
            setRightChild(node);
        }
        // Update the child's parent to this node. 
        node.setParent(this);
    }

    public void setParent(TreeNode<K, V> parent) {
        this.parent = parent;
    }

    public int getSize() {
    		// If it's null then there are no left or right children. 
    		// Otherwise recursively get the size of all nodes on the respective branch.
        int lcnt = (getLeftChild() == null) ? 0 : getLeftChild().getSize();
        int rcnt = (getRightChild() == null) ? 0 : getRightChild().getSize();
        this.size = lcnt + rcnt + 1;
        return this.size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

//    @Override 
// // For Debugging. 
//    public String toString() {
//        K pkey = (parent == null) ? null : parent.getKey();
//        return "TreeNode{" +
//                "size=" + getSize() +
//                ", height=" + getHeight() +
//                ", key=" + key +
//                ", value=" + value +
//                ", parent=" +  pkey +
//                ", leftChild=" + leftChild +
//                ", rightChild=" + rightChild +
//                '}';
//    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "key='" + key + "'" +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }

    @Override
    // Auto-generated methods by IDE to compare. 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?, ?> treeNode = (TreeNode<?, ?>) o;
        return Objects.equals(getKey(), treeNode.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}

