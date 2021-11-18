/**
 * UI class to interact with a binarytree
 * @author Jan-Henrik Capsius
 * @version 1.0
 */


package src.main;

import java.util.Scanner;

import src.binarytree.BinaryTree;

public class UI{
    private BinaryTree<Integer> binaryIntegerTree;
    private BinaryTree<String> binaryStringTree;
    private String choiceMainUI, choiceSubUI, dataString;
    private Scanner keyboard = new Scanner(System.in);
    private int dataInt;
    private boolean whichTree;

    public void startUI(){
        mainUI();
    }

    private void mainUI(){
        do{
            System.out.print("[1]Int-Tree\n[2]String-Tree\n[3]Beenden\n");
            System.out.print("Eingabe: ");
            this.choiceMainUI = this.keyboard.next();
            switch(this.choiceMainUI){
                case "1":
                    this.binaryIntegerTree = new BinaryTree<>();
                    this.whichTree = whichTree();
                    subUI();
                    break;
            
                case "2":
                    this.binaryStringTree = new BinaryTree<>();
                    this.whichTree = whichTree();
                    subUI();
                    break;
            
                case "3":
                    System.out.println("Programm wird beendet.");
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
            System.out.print("[1]Einfügen\n[2]Löschen\n[3]Modifizieren\n[4]Baumausgabe\n[5]Zurück ins Hauptmenü\n");
            System.out.print("Eingabe: ");
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
                    System.out.println("Zurück ins Hauptmenü...");
                    System.out.println();
                    break;
                default:
                    invalid();
                    break;

            }

        }while(!choiceSubUI.equals("5"));
    }

    private boolean whichTree(){
        if(this.choiceMainUI.equals("1")){
            return true;
        }else{
            return false;
        }
    }
    
    private void add(){
        if(this.whichTree){
            System.out.println("\nBitte geben sie eine Zahl ein: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
                System.out.print("Bitte geben sie eine neue Zahl ein: ");
            }
            this.binaryIntegerTree.add(this.keyboard.nextInt());
            System.out.println("Zahl wurde hinzugefügt");
        }else{
            System.out.println("\nBitte geben sie einen String ein ein: ");
            this.binaryStringTree.add(this.keyboard.next());
            System.out.println("Der String wurde hinzugefügt");
        }
    }

    private boolean delete(){
        if(this.whichTree){
            System.out.println("\nBitte geben sie eine Zahl ein, die sie löschen wollen: ");
            while (!this.keyboard.hasNextInt()) {
            String input = this.keyboard.next();
            System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
            System.out.print("Bitte geben sie eine neue Zahl ein: ");
            }
            this.dataInt = this.keyboard.nextInt();
            if(this.binaryIntegerTree.containsNode(this.dataInt)){
                this.binaryIntegerTree.deleteNode(this.dataInt);
                System.out.println("Eintrag wurde gelöscht");
                return true;
            }else{
                System.out.println("Eintrag wurde nicht gefunden");
                return false;
            }
        }else{
            System.out.println("\nBitte geben sie einen String ein, die sie löschen wollen: ");
            this.dataString = keyboard.next();
            if(this.binaryStringTree.containsNode(this.dataString)){
                binaryStringTree.deleteNode(this.dataString);
                System.out.println("Eintrag wurde gelöscht");
                return true;
            }else{
                System.out.println("Eintrag wurde nicht gefunden");
                return false;
            }

        }
    }

    private void modify(){
        if(delete()){
            add();
        }
    }

    private void print(){
        if(this.whichTree){
            System.out.println("\nInorder");
            this.binaryIntegerTree.traverseInOrder();
            System.out.println("\nInorder with details");
            this.binaryIntegerTree.traverseInOrderDetails();
            System.out.println("Preorder");
            this.binaryIntegerTree.traversePreOrder();
            System.out.println("Preorder with details");
            this.binaryIntegerTree.traversePreOrderDetails();
        }else{
            System.out.println("\nInorder");
            this.binaryStringTree.traverseInOrder();
            System.out.println("Inorder with details");
            this.binaryStringTree.traverseInOrderDetails();
            System.out.println("Preorder");
            this.binaryStringTree.traversePreOrder();
            System.out.println("\nPreorder with details");
            this.binaryStringTree.traversePreOrderDetails();
        }
    }

    private void invalid(){
        System.out.println("\nUngültige Eingabe\n");
    }
}
