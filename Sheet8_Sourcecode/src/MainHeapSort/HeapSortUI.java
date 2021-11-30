package src.MainHeapSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.HeapSort.HeapSort;

public class HeapSortUI {
    HeapSort hps;
    ArrayList<Integer> myList;
    int addInt;
    Scanner keyboard;
    String choiceUI, fileName;
    
    HeapSortUI() {
        initiateUtilities();
        mainUI();

    }
    /**
     * initializes the input option, the unsorted array and the heap sort array
     */
    private void initiateUtilities() {
        this.hps = new HeapSort();
        this.keyboard = new Scanner(System.in);
        this.fileName = "unsortedNumbers.txt";
        this.myList = new ArrayList<>();
        readData();

    }
    /**
     * menu
     */
    private void mainUI() {
        do {
            System.out.print("[e]Einfügen eines Elements\n[l]Löschen des kleinsten Elements aus dem Heap\n[s]Sortiertes Ausgeben des Heaps" +
                    "\n[a]Ausgeben des Arrays\n[n]Erneutes Einlesen der Datei\n[x]Programm beenden\n");
            System.out.print("Eingabe: ");
            choiceUI = keyboard.next().toLowerCase();
            System.out.println();
            switch (choiceUI) {
                case "e":
                    add();
                    break;
                case "l":
                    deleteFirst();
                    break;
                case "s":
                    System.out.println("Sortiertes Array");
                    this.myList = hps.heapSort(this.myList);
                    print();
                    break;
                case "a":
                    System.out.println("Eingelesenes Array: ");
                    print();
                    break;
                case "n":
                    readData();
                    System.out.println("Liste wurde neu eingelesen");
                    System.out.println();
                    break;
                case "x":
                    endProgram();
                    break;
                default:
                    invalid();
                    break;
            }
        } while (!choiceUI.equals("x"));
    }

    /**
     * adds a new element
     */
    private void add() {
        System.out.println("Bitte geben sie eine Zahl ein: ");
        while (!keyboard.hasNextInt()) { 
            String input = keyboard.next();
            System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
            System.out.print("Bitte geben sie eine neue Zahl ein: ");
        }
        this.myList.add(keyboard.nextInt());
        this.myList = hps.heapSort(this.myList);
        System.out.println(this.addInt + " wurde erfolgreich hinzugefügt");
        System.out.println();
    }
    /**
     *deletes the first entry
     */
    private void deleteFirst() {
        System.out.println(this.myList.get(0) + " wurde entfernt");
        System.out.println();
        this.myList.remove(0);
    }
    /**
     * reads a document
     */
    private void readData() {
        if (this.myList.size() > 0) {
            this.myList.clear();
        }
        try {
            Scanner scanner = new Scanner(new File(this.fileName));
            while (scanner.hasNextInt()) {
                this.myList.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println();
        }
    }

    /**
     * prints the document
     */
    private void print() {
        for (int i = 0; i < this.myList.size(); i++) {
            System.out.println("Index[" + i + "]: " + this.myList.get(i));
        }
        System.out.println();
    }

    /**
     * ends the program
     */
    private void endProgram() {
        choiceUI = "x";
        System.out.println("Programm wird beendet.");
    }

    /**
     * error message for invalid input
     */
    private void invalid() {
        System.out.print("\nUngültige Eingabe\n");
        System.out.println();
    }
    
}
