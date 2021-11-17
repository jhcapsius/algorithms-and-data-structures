/**
 * Class to create a node
 * @author Jan-Henrik Capsius
 * @version 1.1
 */

package src.binarytree;

public class Node <T> {
    private T data;
    private int depth;
    Node<T> left_child, right_child;

    /**
     * creates a generic node
     * @param data data of the node
     */
    Node(T data){
        this.data = data;
        this.left_child = null;
        this.right_child = null;
    }

    public Node<T> getleft_child(){
        return this.left_child;
    }

    public void setleft_child(Node<T> node){
        this.left_child = node;
    }
    
    public Node<T> getright_child(){
        return this.right_child;
    }

    public void setright_child(Node<T> node){
        this.right_child = node;
    }


    /**
     * getter for data
     * @return
     */
    public T getData(){
        return this.data;
    }

    /**
     * setter for data
     * @param data
     */
    public void setData(T data){
        this.data = data;
    }

    public int getDepth(){
        return this.depth;
    }
    
    public void setDepth(int depth){
        this.depth = depth;
    }
}
