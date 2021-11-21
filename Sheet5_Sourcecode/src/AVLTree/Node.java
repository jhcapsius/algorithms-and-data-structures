package src.AVLTree;

public class Node<T> {
    private T data;
    private int height, depth;
    private Node<T> left_child, right_child;
    
    public Node(T data){
        this.data = data;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public int getHeight(){
        return this.height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }

    public Node<T> getLeft(){
        return this.left_child;
    }

    public void setLeft(Node<T> node){
        this.left_child = node;
    }

    public Node<T> getRight(){
        return this.right_child;
    }

    public void setRight(Node<T> node){
        this.right_child = node;
    }
}
