/**
 * UI class to interact with a avl-tree
 * @author Jan-Henrik Capsius
 * @version 1.0
 */

package src.main;

import java.util.Scanner;
import src.AVLTree.AVLTree;

public class UIAVL {
    private AVLTree<Integer> avlintTree;
    private AVLTree<String> avlStringTree;
    private String choiceMainUI, choiceSubUI, dataString;
    private Scanner keyboard = new Scanner(System.in);
    private int dataInt;
    private boolean whichTree;

    public void startUI(){
        mainUI();
    }

    /**
     * main UI with Int-Tree, String-Tree and end-programm option
     */

    private void mainUI(){
        do{
            System.out.print("[1]Int-Tree\n[2]String-Tree\n[3]End\n");
            System.out.print("Input: ");
            this.choiceMainUI = this.keyboard.next();
            switch(this.choiceMainUI){
                case "1":
                    this.avlintTree = new AVLTree<>();
                    this.whichTree = whichTree();
                    subUI();
                    break;
            
                case "2":
                    this.avlStringTree = new AVLTree<>();
                    this.whichTree = whichTree();
                    subUI();
                    break;
            
                case "3":
                    System.out.println("Program will be closed.");
                    break;
            
                default:
                    invalid();
                    break;
            }
        }while(!this.choiceMainUI.equals("3"));
    }

    private void subUI(){
        do{
            System.out.println();
            System.out.print("[1]Aa\n[2]Delete\n[3]Modif<\n[4]Print\n[5]Main menu\n");
            System.out.print("Input ");
            this.choiceSubUI = this.keyboard.next();
            switch (choiceSubUI) {
                case "1":
                    add();
                    break;
                case "2":
                    delete();
                    break;
                case "3":
                    modify();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    System.out.println("Back to the main menu...");
                    System.out.println();
                    break;
                default:
                    invalid();
                    break;

            }

        }while(!choiceSubUI.equals("5"));
    }

    /**
     * differentiates between the two differentent ypes
     * @return true if it is a integer binary tree and false if it is a string binary tree
     */
    private boolean whichTree(){
        if(this.choiceMainUI.equals("1")){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * method to add a new entry depending on the binary tree type
     */
    private void add(){
        if(this.whichTree){
            System.out.println("\nPlease enter a number: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" is not a valid input\n", input);
                System.out.print("Please enter a number: ");
            }
            this.avlintTree.add(this.keyboard.nextInt());
            System.out.println("Number added");
        }else{
            System.out.println("\nPlease enter a string: ");
            this.avlStringTree.add(this.keyboard.next());
            System.out.println("String added");
        }
    }

    /**
     * deletes an entry 
     * @return return true, when the entry is deleted, else false when entry not found
     */

    private boolean delete(){
        if(this.whichTree){
            System.out.println("\nPlease enter a number: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" is not a valid input\n", input);
                System.out.print("Please enter a number: ");
            }
            this.dataInt = this.keyboard.nextInt();
            return this.avlintTree.delete(dataInt); 
        }else{
            System.out.println("\nPlease enter a string: ");
            this.dataString = keyboard.next();
            return this.avlStringTree.delete(dataString);
        }
    }

    /**
     * modifies an entry
     */
    private void modify(){
        if(delete()){
            add();
        }
    }

    /**
     * prints the tree in inorder and preorder
     */

    private void print(){
        if(this.whichTree){
            System.out.println("\nInorder");
            this.avlintTree.traverseInOrder();
            System.out.println("\nInorder with details");
            this.avlintTree.traverseInOrderDetails();
            System.out.println("Preorder");
            this.avlintTree.traversePreOrder();
            System.out.println("Preorder with details");
            this.avlintTree.traversePreOrderDetails();
        }else{
            System.out.println("\nInorder");
            this.avlStringTree.traverseInOrder();
            System.out.println("Inorder with details");
            this.avlStringTree.traverseInOrderDetails();
            System.out.println("Preorder");
            this.avlStringTree.traversePreOrder();
            System.out.println("\nPreorder with details");
            this.avlStringTree.traversePreOrderDetails();
        }
    }

    private void invalid(){
        System.out.println("\nInavalid input\n");
    }
    
}
