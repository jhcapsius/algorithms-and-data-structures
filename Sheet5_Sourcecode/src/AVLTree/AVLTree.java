package src.AVLTree;

public class AVLTree <T extends Comparable<T>>{
    private Node<T> root;

    private void updateNodeHeight(Node<T> current){
        current.setHeight(1 + Math.max(nodeHeight(current.getLeft()), nodeHeight(current.getRight())));
    }

    private int nodeHeight(Node<T> current){
        return current == null ? -1 : current.getHeight();
    }

    private int getBalance(Node<T> current){
        return current == null ? 0 : nodeHeight(current.getRight()) - nodeHeight(current.getLeft());
    }

    /**
     * Rotation from right to left to balance the tree
     *       b                    a
     *     /   \                /   \
     *    a          ->              b
     *   / \                        / \
     *      c                      c
     *
     * @param b start point
     * @return new srtpoint
     */
    private Node<T> rightRotation(Node<T> b){
        Node<T> a = b.getLeft();
        Node<T> c = a.getRight();
        a.setRight(b);
        b.setLeft(c);
        updateNodeHeight(b);
        updateNodeHeight(a);
        return a;
    }

    /**
     * Rotation from left to right to balance the tree
     *     b                     a
     *   /   \                 /   \
     *        a        ->     b
     *       / \             / \
     *      c                   c
     *
     * @param b start point
     * @return new start point
     */
    public Node<T> leftRotation(Node<T> b) {
        Node<T> a = b.getRight();
        Node<T> c = a.getLeft();
        a.setLeft(b);
        b.setRight(c);
        updateNodeHeight(b);
        updateNodeHeight(a);
        return a;
    }


     /**
     * balances the tree with the help of the node height and the leftrotation and rightrotation methods
     * @param current node from which to balance
     * @return  balanced tree from the given node
     */
    private Node<T> rebalance(Node<T> current){
        updateNodeHeight(current);
        if(getBalance(current) > 1){
            if(nodeHeight(current.getRight().getRight()) > nodeHeight(current.getRight().getLeft())){
                current = leftRotation(current);
            }else{
                current.setRight(rightRotation(current.getRight()));
                current = leftRotation(current);
            }
        } else if(getBalance(current) < -1){
            if(nodeHeight(current.getLeft().getLeft()) > nodeHeight(current.getLeft().getRight())){
                current = rightRotation(current);
            }else{
                current.setLeft(leftRotation(current.getLeft()));
                current = rightRotation(current);
            }
        }
        return current;
    }

    /**
     * Starts the insertion with the root as start point
     * @param data generic data for the new node
     */
    public void add(T data){
        this.root = add(this.root, data);
    }

    /**
     * Adds a new node to the binary tree
     * @param current starts with the root and follows with the left or right child
     * @param value generic data for the node
     */
    private Node<T> add(Node<T> current, T data){
        if(current == null){
            return new Node<T>(data);

        } else if(data.compareTo(current.getData()) < 0){
            current.setLeft(add(current.getLeft(), data));

        } else if(data.compareTo(current.getData()) > 0){
            current.setRight(add(current.getRight(), data));
        }
        
        return rebalance(current);
    }

    public boolean delete(T data){
        if(containsNode(data)){
            this.root = delete(this.root, data);
            return true;
        }else{
            System.out.println("Entry not found");
            return false;
        }
    }

    private Node<T> delete(Node<T> current, T data){
        if(current == null){
            return null;
        } else if(data.compareTo(current.getData()) < 0){
            current.setLeft(delete(current.getLeft(), data));
        
        } else if(data.compareTo(current.getData()) > 0){
            current.setRight(delete(current.getRight(), data));
        } else{
            if(current.getLeft() == null && current.getRight() == null){
                return null;
            } else if(current.getRight() == null){
                return current.getLeft();
            } else if(current.getLeft() == null){
                return current.getRight();
            } else{
                current.setData(findMax(current.getLeft()));
                current.setLeft(delete(current.getLeft(), current.getData()));
            }
        }

        if(current != null){
            current = rebalance(current);
        }
        
        return current;
    }

    private T findMax(Node<T> current){
        while(current.getRight() != null){
            current = current.getRight();
        }
        return current.getData();
    }

    public void depth(){
        depth(this.root, 0);
    }

    private void depth(Node<T> current, int depth){
        if(current != null){
            current.setDepth(depth);
            depth(current.getLeft(), depth + 1);
            depth(current.getRight(), depth + 1);
        }
    }

    private boolean containsNode(T data){
        return containsNode(this.root, data);
    }

    private boolean containsNode(Node<T> current, T data){
        if(current == null){
            return false;
        }else if(data.compareTo(current.getData()) == 0){
            return true;
        }else{
            return data.compareTo(current.getData()) < 0 ? containsNode(current.getLeft(), data) : containsNode(current.getRight(), data); 
        }
    }

    public boolean isEmpty(){
        return this.root == null ? true : false;
    }

    public Node<T> getRoot(){
        return this.root;
    }

    public void traversePreOrder(){
        traversePreOrder(this.root);
        System.out.println();
    }

    /**
     * Prints a generic binary tree in preorder
     * @param node startnode is root
     */
    private void traversePreOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    public void traversePreOrderDetails(){
        depth();
        traversePreOrderDetails(this.root);
        System.out.println();
    }

    /**
     * Prints a generic binary tree in preorder with more details
     * @param node startnode is root
     */
    private void traversePreOrderDetails(Node<T> node) {
        if (node != null && node.getLeft() != null && node.getRight() != null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.getLeft().getData() + "(d:" + node.getLeft().getDepth() + ")," 
            + node.getRight().getData() + "(d:" + node.getRight().getDepth() + ")\n");
            traversePreOrderDetails(node.getLeft());
            traversePreOrderDetails(node.getRight());
        } else if (node != null && node.getLeft() == null && node.getRight() == null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(d:" + (node.getDepth() + 1) + ")," + null +
                    "(d:" + (node.getDepth() + 1) + ")\n");
        } else if (node != null && node.getLeft() != null && node.getRight() == null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.getLeft().getData() + "(d:" + (node.getLeft().getDepth()) + ")," + null +
                    "(d:" + (node.getDepth() + 1) + ")\n");
            traversePreOrderDetails(node.getLeft());
        } else if (node != null && node.getLeft() == null && node.getRight() != null) {
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + null + "(d:" + (node.getDepth() + 1) + ")," + node.getRight().getData() +
                    "(d:" + (node.getRight().getDepth()) + ")\n");
            traversePreOrderDetails(node.getRight());
        }
    }

    public void traverseInOrder(){
        traverseInOrder(this.root);
        System.out.println();
    }

    /**
     * Prints an generic binary tree in inorder
     * @param node startnode is root
     */
    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            traverseInOrder(node.getRight());
        }
    }

    public void traverseInOrderDetails(){
        depth();
        traverseInOrderDetails(this.root);
        System.out.println();
    }

    /**
     * Prints an generic binary tree in inorder with more details
     * @param node startnode is root
     */
    private void traverseInOrderDetails(Node<T> node) {
        
        if (node != null && node.getLeft() != null && node.getRight() != null) {
            traverseInOrderDetails(node.getLeft());
            System.out.print(node.getData() + "(d:" + node.getDepth() + "):" + node.getLeft().getData() + "(d:" + node.getLeft().getDepth() + ")," 
            + node.getRight().getData() + "(d:" + node.getRight().getDepth() + ")\n");
            traverseInOrderDetails(node.getRight());

        }else if(node != null && node.getLeft() == null && node.getRight() == null){
            System.out.print(node.getData() + "(h:" + node.getDepth() + "):" + null + "(h:" + (node.getDepth() + 1) + ")," + null + "(h:" + (node.getDepth() + 1) + ")\n");
        
        }else if (node != null && node.getLeft() != null && node.getRight() == null) {
            traverseInOrderDetails(node.getLeft());
            System.out.print(node.getData() + "(h:" + node.getDepth() + "):" + node.getLeft().getData() + "(h:" + node.getLeft().getDepth() + ")," 
            + null + "(h:" + (node.getDepth() + 1) + ")\n");

        }else if(node != null && node.getLeft() == null && node.getRight() != null){
            System.out.print(node.getData() + "(h:" + node.getDepth() + "):" + null + "(h:" + (node.getDepth() + 1) + ")," + node.getRight().getData()+ 
            "(h:" + (node.getRight().getDepth()) + ")\n");
            traverseInOrderDetails(node.getRight());
        }
    }

    public void printLeftSubtree(){
        printLeftSubtree(this.root.getLeft());
    }

    private void printLeftSubtree(Node<T> node){
        if(node != null){
            System.out.print(node.getData() + " ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }

    }

    public void printRightSubtree(){
        printRightSubtree(this.root.getRight());
    }

    private void printRightSubtree(Node<T> node){
        if (node != null){
            System.out.print(node.getData() + " ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }



    /**
     * test integer tree
     * @return test integer tree
     */
    public AVLTree<Integer> testTreeInteger(){
        AVLTree<Integer> test = new AVLTree<>();
        test.add(9);
        test.add(66);
        test.add(6);
        test.add(3);
        test.add(7);
        test.add(1);
        test.add(55);
        test.add(56);

        return test;
    }
    
}
