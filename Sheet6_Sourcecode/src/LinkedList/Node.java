/**
 * Node-class that contains the data of a node and the reference of the following node
 * 
 * @Author Jan-Henrik Capsius
 */

package src.LinkedList;

public class Node<T> {
    private T data;
    private int index;
    private Node<T> next;

    Node(T data){
        this.data = data;
    }
    Node(T data, int index){
        this.data = data;
        this.index = index;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
