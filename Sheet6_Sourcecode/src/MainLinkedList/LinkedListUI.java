/**
 * UI class to interact with a linked list
 * @author Jan-Henrik Capsius
 * @version 1.0
 */

package src.MainLinkedList;

import java.util.Scanner;
import src.LinkedList.LinkedList;

public class LinkedListUI {
    private LinkedList<String> linkedList;
    private String choiceMainUI;
    private String data;
    private Scanner keyboard = new Scanner(System.in);

    LinkedListUI(){
        this.linkedList = new LinkedList<>();

    }

    public void startUI(){
        mainUI();
    }

    private void mainUI(){
        do{
            System.out.print("[1]addNextElem\n[2]getNextElem\n[3]removeNextElem\n[4]addPrevElem\n[5]getPrevElem[6]removePrevElem\n[7]print\n[8]end programm\n");
            System.out.print("Input: ");
            this.choiceMainUI = this.keyboard.next();
            switch(this.choiceMainUI){
                case "1":
                    addNextElem();
                    break;
            
                case "2":
                    getNextElem();
                    break;
            
                case "3":
                    removeNextElem();
                    break;
                
                case "4":
                    addPrevElem();
                    break;
                case "5":
                    getPrevElem();
                    break;
                case "6":
                    removePrevElem();
                    break;
                case "7":
                    this.linkedList.print();
                case "8":
                    System.out.println("Program will be closed.");
                    break;
                default:
                    invalid();
                    break;
            }
        }while(!this.choiceMainUI.equals("8"));

    }
    
    private String setData(){
        System.out.println("\nPlease enter a string: ");
        return this.keyboard.next();
    }

    private int setIndex(){
        System.out.println("\nPlease enter a index: ");
        while (!this.keyboard.hasNextInt()) {
            String input = this.keyboard.next();
            System.out.printf("\"%s\" is not a valid input\n", input);
            System.out.print("Please enter a number: ");
            }
        return this.keyboard.nextInt();
    }

    private void addNextElem(){
        if(this.linkedList.addNextElem(setIndex(), setData())){
            System.out.println("File was added");
        }else{
            System.out.println("Error, file was not added");
        }
    }

    private void getNextElem(){
        this.data = linkedList.getNextElem(setIndex());
        if(this.data != null){
            System.out.println("Next elem:" + this.data);
        }else{
            System.out.println("Error");
        }
    }

    private void removeNextElem(){
        if(this.linkedList.removeNextElem(setIndex())){
            System.out.println("Elem was successfully removed");
        }else{
            System.out.println("Elem was not succesfully removed");
        }
    }

    private void addPrevElem(){
        if(this.linkedList.addPrevElem(setIndex(), setData())){
            System.out.println("File was added");
        }else{
            System.out.println("Error, file was not added");
        }
    }

    private void getPrevElem(){
        this.data = linkedList.getPrevElem(setIndex());
        if(this.data != null){
            System.out.println("Next elem:" + this.data);
        }else{
            System.out.println("Error");
        }
    }

    private void removePrevElem(){
        if(this.linkedList.removePrevElem(setIndex())){
            System.out.println("Elem was successfully removed");
        }else{
            System.out.println("Elem was not succesfully removed");
        }
    }

    private void invalid(){
        System.out.println("\nInavalid input\n");
    }
}
