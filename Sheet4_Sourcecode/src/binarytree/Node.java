/**
 * Classe to create a node
 * @author Jan-Henrik Capsius
 * @version 1.0
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
