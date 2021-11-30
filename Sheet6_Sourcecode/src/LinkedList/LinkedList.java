/**
 * Linked list implementation from scratch
 * 
 * @author Jan-Henrik Capsius
 * @version 1.0
 */

package src.LinkedList;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private int elements;
    private int index = 0;

    /**
     * prints the linked list
     */
    public void print(){
        if(this.elements == 0){
            System.out.println("List is empty\n");
        }else{
            System.out.println("Amount of elements: " + this.elements);
            Node<T> temp = this.head;
            while(temp.getNext() != null){
                System.out.print(temp.getData() + ", ");
                temp = temp.getNext();
            }
            System.out.print(temp.getData() + "\n\n");
        }
    }

    /**
     * returns value at the given index
     * @param index index of the item we want to return
     * @return value
     */
    public T getElem(int index){
        //return null if there are no elements or the index is out of bounce
        if(this.elements == 0 || index < 0 || index >= this.elements){
            return null;
        }else{
            //iterates through the list to the given index and return the value
            Node<T> current = this.head;
            while(current.getIndex() != index){
                current = current.getNext();
            }
            return current.getData();
        }
    }

    /**
     * appends item at the end of the linked list
     * @param data item
     * @return true, if item successfully added to the end of the list
     */

    public boolean addNextElem(T data){
        //if the head is null, the new data will be head node
        if(this.head == null){
            this.head = new Node<>(data, 0);
            this.elements++;
            return true;
        }

        //goes to the end of the list and add the new data as the new last node 
        Node<T> current = this.head;
        while(current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(new Node<>(data, this.elements));
        this.elements++;
        return true;
    }

    /**
     * adds an item to the given index in the list
     * @param index index where to place the new item
     * @param data value of the new item
     * @return true, if successfully added or false if not
     */

    public boolean addNextElem(int index, T data){
        //list is empty    
        if(index == 0 && this.elements == 0){
            return addNextElem(data);
        }

        //index out of bounce
        if(index >= this.elements || index < 0){
            return false;
        
        //if the index the last entry     
        }else if(index - 1 == this.elements){
            return addNextElem(data);
        
        //if the index somewhere in the list
        }else{
            Node<T> current = this.head;
            while(current.getIndex() != index){
                current = current.getNext();
            }
            Node<T> temp = current.getNext();
            current.setNext(new Node<>(data));
            current.getNext().setNext(temp);
            this.elements++;
            updateIndex();
            return true;
        }
    }

    /**
     * updates indices after adding or deleting items
     */
    private void updateIndex(){
        Node<T> current = this.head;
        while(current.getNext() != null){
            current.setIndex(this.index);
            current = current.getNext();
            this.index++;
        }
        current.setIndex(this.index);
        this.index = 0;
    }

    /**
     * get the value after the given index
     * @param index Index of the item before the returned item
     * @return value of the next item
     */
    public T getNextElem(int index){
        //if there are not enough items in the list or the index is out of bounce
        if(this.elements <=1 || index >= this.elements - 1 || index < 0 ){
            return null;
        }else{
            //iterates through the list to the given index and return the next value
            Node<T> current = this.head;
            while(current.getIndex() != index){
                current = current.getNext();
            }
            return current.getNext().getData();
        }
    }

    /**
     * removes the last item
     * @return true, if successfully removes or false when the list is empty
     */
    public boolean removeNextElem(){
        //error if no elements in the list
        if(this.elements == 0){
            return false;

        //sets the head node null, if there is only 1 element    
        }else if(this.elements == 1){
            this.head = null;
            this.elements--;;
            return true;
        }else{

            //iterates to the penultimate node and sets the next null
            Node<T> current = this.head;
            while(current.getIndex() != this.elements - 2){
                current = current.getNext();
            }
            current.setNext(null);
            this.elements--;
            return true;
        }
    }

    /**
     * removes the item after the given index
     * @param index index of the node before the node to be deleted
     * @return true, if successfully removed and false if not
     */
    public boolean removeNextElem(int index){
        //error if the index is out of bounce or not enough elements in the list
        if(this.elements -1 <= index || index < 0 || elements == 0){
            return false;

        //sets the head node null, if there is only 1 element        
        }else if(this.elements == 1 && index == 0){
            return removeNextElem();

        //remove the last item if the index is the second to last     
        }else if(index == this.elements-2) {
            return removeNextElem();
        
        //removes the next item somewhere in the list
        }else{
            Node<T> current = this.head;
            while(current.getIndex() != index){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            this.elements--;
            updateIndex();
            return true;
        }
    }

    /**
     * sets new head
     * @param data value of the new head
     * @return true, if succesfully added, false if the list is empty
     */
    public boolean addPrevElem(T data){
        if(this.elements == 0){
            return false;
        }else{
            Node<T> temp = this.head;
            this.head = new Node<>(data);
            this.head.setNext(temp);
            this.elements++;
            updateIndex();
            return true;
        }
    }

    /**
     * adds an item before the given index
     * @param index index after the position we want to insert the item
     * @param data value of item
     * @return true, if succesfully added, false if the list is empty or index out of bounce
     */
    public boolean addPrevElem(int index, T data){
        if(this.elements == 0|| index >= this.elements || index < 0){
            return false;
        }else if((this.elements >= 1 && index == 0)){
            return addPrevElem(data);
        }else{
            Node<T> current = this.head;
            while(current.getIndex() != index-1){
                current = current.getNext();
            } 
            Node<T> temp = current.getNext();
            current.setNext(new Node<>(data));
            current.getNext().setNext(temp);
            this.elements++;
            updateIndex();
            return true;
        }
    }

    /**
     * gets the value of the item before the given index
     * @param index index after the position of the item we want to get
     * @return value of the item we want to get
     */

    public T getPrevElem(int index){
        //error if index is out of bounce or not enough elements in the list
        if(this.elements <= 1 || index >= this.elements || index <= 0){
            return null;
        
        //gets the value of the item
        }else{
            Node<T> current = this.head;
            while(current.getIndex() != index -1){
                current = current.getNext();
            }
            return current.getData();
        }
    }


    /**
     * sets a new head
     * @return true, if remove was successfully, false if there are not enough elements in the list
     */
    public boolean removePrevElem(){
        if(this.elements <= 1){
            return false;
        }else{
            this.head = this.head.getNext();
            this.elements--;
            updateIndex();
            return true;
        }
    }

    /**
     * removes the item before the item of the given index
     * @param index position of the item after the item we want to remove
     * @return true, if remove was successfully, false if there are not enough elemnts or index out of bounce
     */
    public boolean removePrevElem(int index){
        if(this.elements <= 1 || index >= this.elements || index <= 0){
            return false;
        }else if(index == 1){
            return removeNextElem();
        }else{
            Node<T> current = this.head;
            while(current.getIndex() != index - 2){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            this.elements--;
            updateIndex();
            return true;
        }
    }

   
}
