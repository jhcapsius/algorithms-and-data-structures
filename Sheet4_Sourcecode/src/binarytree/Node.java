/**
 * Class to create a node for the binery tree
 * @author Jan-Henrik Capsius
 * @version 1.1
 */

package src.binarytree;

public class Node <T> {
    private T data;
    private int depth;
    private Node<T> left_child, right_child;

    /**
     * creates a generic node
     * @param data data of the node
     */
    Node(T data){
        this.data = data;
        this.left_child = null;
        this.right_child = null;
    }

    /**
     * getter for the left child of this node
     * @return left child
     */
    public Node<T> getleft_child(){
        return this.left_child;
    }

    /**
     * setter for the left child of this node
     * @param node value for the left child
     */
    public void setleft_child(Node<T> node){
        this.left_child = node;
    }

     /**
     * getter for the right child of this node
     * @return left child
     */
    public Node<T> getright_child(){
        return this.right_child;
    }

     /**
     * setter for the right child of this node
     * @param node value for the right child
     */
    public void setright_child(Node<T> node){
        this.right_child = node;
    }


    /**
     * getter for the data this node
     * @return data of the node
     */
    public T getData(){
        return this.data;
    }

    /**
     * setter for data
     * @param data values for data
     */
    public void setData(T data){
        this.data = data;
    }

    /**
     * getter for the depth of the node
     * @return node depth
     */
    public int getDepth(){
        return this.depth;
    }
    
    /**
     * setter for the depth of the node
     * @param depth depth of the notde
     */
    public void setDepth(int depth){
        this.depth = depth;
    }
}
