package src.binarytree;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    /**
     * Starts the insertion with the root as start point
     * @param data data for the new node
     */
    public void add(T data){
        add(this.root, data);
    }

    /**
     * Adds a new node to the binary tree
     * @param current starts with the root and follows with the left or right child
     * @param value generic data for the node
     */
    private void add(Node<T> current, T data){
        //sets root if is not already set
        if(current == null){
            current = new Node<T>(data);
        }

        //if the left side is null, it create a new node with data as data. Otherwise it will call this method with the left child and the data
        else if(data.compareTo(current.getData()) < 0){
            if(current.left_child == null){
                current.left_child = new Node<T>(data);
            }else{
                add(current.left_child, data);
            }
        }

        //if the right side is null, it create a new node with data as data. Otherwise it will call this method with the right child and the data
        else if(data.compareTo(current.getData()) > 0){
            if(current.right_child == null){
                current.right_child = new Node<T>(data);
            }else{
                add(current.right_child, data);
            }
        }
    }

     /**
     * Starts the check with the root as start point
     * @param value generic value that we want to check 
     */
    public boolean containsNode(T data){
        return containsNode(this.root, data);
    }

     /**
     * Checks if the node is in the tree
     * @param current starts with the root and follows with the left or right child
     * @param value generic value that we want to check 
     * @return true if the tree contains that value or false if it is not in the tree
     */
    private boolean containsNode(Node<T> current, T data){
        if(current.getData() == null){
            return false;
        }

        if(data.compareTo(current.getData()) == 0){
            return true;
        }

        //checks left side when the data is smaller than the data of the current node and checks right side when the data is bigger than the data of the current node
        return data.compareTo(current.getData()) < 0 ? containsNode(current.left_child, data) : containsNode(current.right_child, data);
    }

    /**
     * Finds the smallest generic value
     * @param current current node as startpoint
     * @return smallest generic value
     */
    private T findSmallestData(Node<T> current){
        return current.left_child.getData() == null ? current.getData() : findSmallestData(current.left_child); 
    }

     /**
     * Starts the deletion process
     * @param value integer value that we want to delete
     */
    public void deleteNode(T data) {
        this.root = deleteNode(this.root, data);
    }

    /**
     * deletes an existing node with a specific generic value
     * @param current starts with the root and follows with the left or right child
     * @param value generic value that we want to delete
     * @return a node that is on the place of that node we deleted
     */
    private Node<T> deleteNode(Node<T> current, T data) {
        if(current.getData() == null){
            return null;
        }

        if(data.compareTo(current.getData()) == 0){
            if(current.left_child.getData() == null && current.right_child.getData() == null){
                return null;
            }

            else if(current.right_child.getData() == null){
                return current.left_child;
            }

            else if(current.left_child.getData() == null){
                return current.right_child;
            }

            T smallestData = findSmallestData(current.right_child);
            current.setData(smallestData);
            current.right_child = deleteNode(current.right_child, smallestData);
            return current;
        }

        if(data.compareTo(current.getData()) < 0){
            current.left_child = deleteNode(current.left_child, data);
            return current;
        }else{
            current.right_child = deleteNode(current.right_child, data);
            return current;
        }
    }

    /**
     * Sets the depth of a node
     * @param node starts with the root node
     * @param depth starts with 0
     */
    public void depth(Node<T> node, int depth) {
        if (node.getData() != null) {
            node.setDepth(depth);
            depth(node.left_child, depth + 1);
            depth(node.right_child, depth + 1);
        }
    }

     /**
     * checks if the tree is empty
     * @return true if the tree is empty and false if it is not empty
     */
    public boolean is_empty() {
        if(this.root.getData() == null){
            return true;
        }else {
            return false;
        }
     }
 
     /**
      * get root
      * @return root
      */
     public Node<T> getRoot() {
         return this.root;
     }

    /**
     * Prints a generic binary tree in preorder
     * @param node startnode is root
     */
    public void traversePreOrderInt(Node<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            traversePreOrderInt(node.left_child);
            traversePreOrderInt(node.right_child);
        }
    }

    /**
     * Prints a generic binary tree in preorder with more details
     * @param node startnode is root
     */
    public void traversePreOrderInt2(Node<T> node) {
        if (node != null && node.left_child != null && node.right_child != null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.left_child.getData() + "(d:" + node.left_child.getDepth() + ")," 
            + node.right_child.getData() + "(d:" + node.right_child.getDepth() + ")\n");
            traversePreOrderInt2(node.left_child);
            traversePreOrderInt2(node.right_child);
        } else if (node != null && node.left_child == null && node.right_child == null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(d:" + (node.getDepth() + 1) + ")," + null +
                    "(d:" + (node.getDepth() + 1) + ")\n");
        } else if (node != null && node.left_child != null && node.right_child == null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.left_child.getData() + "(d:" + (node.left_child.getDepth()) + ")," + null +
                    "(d:" + (node.getDepth() + 1) + ")\n");
            traversePreOrderInt2(node.left_child);
        } else if (node != null && node.left_child == null && node.right_child != null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(d:" + (node.getDepth() + 1) + ")," + node.right_child.getData() +
                    "(d:" + (node.right_child.getDepth()) + ")\n");
            traversePreOrderInt2(node.right_child);
        }
    }

    /**
     * Prints an generic binary tree in inorder
     * @param node startnode is root
     */
    public void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.left_child);
            System.out.print(node.getData() + " ");
            traverseInOrder(node.right_child);
        }
    }
    /**
     * Prints an generic binary tree in inorder with more details
     * @param node startnode is root
     */
    public void traverseInOrder2(Node<T> node) {
        if (node != null && node.left_child != null && node.right_child != null) {
            traverseInOrder(node.left_child);
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.left_child.getData() + "(d:" + node.left_child.getDepth() + ")," 
                + node.right_child.getData() + "(d:" + node.right_child.getDepth() + ")\n");
            traverseInOrder(node.right_child);
        }
        if (node != null && node.left_child == null && node.right_child == null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(d:" + (node.getDepth() + 1) + ")," + null + "(d:" + (node.getDepth() + 1) + ")\n");
        }
        if (node != null && node.left_child != null && node.right_child == null) {
            traverseInOrder(node.left_child);
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.left_child.getData() + "(d:" + node.left_child.getDepth() + ")," + null + 
            "(d:" + (node.getDepth() + 1) + ")\n");
        }
        if (node != null && node.left_child == null && node.right_child != null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(h:" + (node.getDepth() + 1) + ")," + node.right_child.getData()+ 
            "(d:" + (node.right_child.getDepth()) + ")\n");
            traverseInOrder(node.right_child);
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(1);
        binaryTree.add(0);
        binaryTree.add(2);

        binaryTree.traversePreOrderInt2(binaryTree.root);
    }
}
