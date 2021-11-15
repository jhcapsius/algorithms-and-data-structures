/**
 * example for the greedy algorithm
 * @author Jan-Henrik Capsius
 * @version 1.0
 */


import java.util.*;


public class GreedyAlgorithm {
    Scanner keyboard = new Scanner(System.in); 
    int zbB; 
    int gB; 
    int rest; 
    int muenzSum; 
    int muenzWert; 
    int muenzAnz; 
    String choice;
    int[] muenzWertArray; 
    int[] muenzAnzahlArray; 
    ArrayList<Integer> wechselgeld; 
    HashMap<Integer, Integer> muenzen; 
    TreeMap<Integer, Integer> muenzSort; 

    /**
     * default contructor
     */

    public GreedyAlgorithm() { 
        this.muenzen = new HashMap<>(); 
        this.muenzSort = new TreeMap<>(); 
        setzbB(); 
        setgB(); 
        setMuenzWertandAnz(); 
        this.muenzSort.putAll(muenzen); 
        print(); 
        calculateGreedy();
        menu();
    }

    /**
     * constructor for predefined test cases
     * @param zbB amount to be paid
     * @param gB given amount
     * @param muenzWertArray array, thats contains the value of the coins
     * @param muenzAnzahlArray array, that contains the amount of the coins
     */
    public GreedyAlgorithm(int zbB, int gB, int[] muenzWertArray, int[] muenzAnzahlArray) { 
        this.muenzen = new HashMap<>();
        this.zbB = zbB; 
        this.gB = gB;
        this.muenzWertArray = muenzWertArray; 
        this.muenzAnzahlArray = muenzAnzahlArray;
        
        //creates a hashmap with value of the coins as key and the amount of the coins as entry
        for (int i = 0; i < this.muenzAnzahlArray.length; i++) {
            this.muenzen.put(this.muenzWertArray[i], this.muenzAnzahlArray[i]);
        }
        this.muenzSort = new TreeMap<>();
        this.muenzSort.putAll(muenzen);
        print();
        calculateGreedy();
        menu();
    }

    /**
     * calculate the result by using the greedy algorithm
     */

    public void calculateGreedy() {
        this.wechselgeld = new ArrayList<>(); 
        ArrayList<Integer> keys = new ArrayList<>(muenzSort.keySet()); 
        ArrayList<Integer> values = new ArrayList<>(muenzSort.values());
        this.muenzSum = 0; 
        this.rest = this.gB - this.zbB; 
        //sets the available change
        for (int i = 0; i < keys.size(); i++) { //setzt das verfügbare wechselgeld
            this.muenzSum = muenzSum + (keys.get(i) * values.get(i));
        }
        //if there is not enough change
        if (muenzSum < this.rest) { //sollte das wechselgeld nicht ausreichen
            System.out.println("Es ist nicht genügend Wechselgeld vorhanden");
        } else {
            //calculates the change by using greedy
            for (int j = keys.size() - 1; j >= 0; j--) {
                int counter = values.get(j); 
                do {
                    if (this.rest <= 0) {
                        break;
                    }
                    if (this.rest < keys.get(j)) { 
                        counter = 0;
                    } else {
                        System.out.println("\nRest :" + rest);
                        this.rest = this.rest - keys.get(j); 
                        System.out.println("Rest nach Subtraktion :" + rest + "\nWert der verwendenten Muenze: " + keys.get(j));
                        values.set(j, values.get(j) - 1); 
                        System.out.println("Anzahl der " +keys.get(j) + "er Muenzen in der Kasse: " + values.get(j));
                        wechselgeld.add(keys.get(j)); 
                        System.out.println("Wechselgeld: " + wechselgeld.toString() + "\n");
                    }
                } while (counter != 0);
            }
            muenzen.clear(); 
            for (int i = 0; i < keys.size(); i++) { //füllt die hashmap mit den alten keys und der neuen anzahl der muenzen
                this.muenzen.put(keys.get(i), values.get(i));
            }
            this.muenzSort.putAll(muenzen); 
            //System.out.println("Wechselgeld: " + wechselgeld.toString()); // gibt die wechselgeldschritte aus
        }
    }

    /**
     * Method to set the amount to be paid with the keyboard
     */

    public void setzbB() { 
        do {
            System.out.print("Geben sie einen zu bezahlenden Betrag größer als 0 ein: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
                System.out.print("Geben sie einen zu bezahlenden Betrag größer als 0 ein: ");
            }
            this.zbB = this.keyboard.nextInt();
            if (this.zbB <= 0) {
                System.out.println("\"" + this.zbB + "\" ist eine ungültige Eingabe");
            }
        } while (this.zbB <= 0);
        System.out.println("Zahl wurde akzeptiert.");
    }

    /**
     * Method to set the given amount with the keyboard
     */

    public void setgB() { 
        do {
            System.out.print("Bitte geben sie einen Betrag ein, welcher mindestens um 1 größer ist wie der zu bezahlende Betrag sein muss ein: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
                System.out.print("Bitte geben sie einen Betrag ein, welcher mindestens um 1 größer ist wie der zu bezahlende Betrag sein muss ein: ");
            }
            this.gB = this.keyboard.nextInt();
            if (this.gB <= this.zbB) {
                System.out.println("\"" + this.gB + "\" ist zu klein um die zu zahlende Summe zu bezahlen");
            }
        } while (this.gB <= this.zbB);
        System.out.println("Zahl wurde akzeptiert.");
    }

    /**
     * Method to set the value of a coin with the keyboard
     */
    public void setMuenzWert() { 
        do {
            System.out.print("Bitte geben sie einen Muenzwert, der mindestens 1 sein muss, an: ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
                System.out.print("Bitte geben sie einen Muenzwert, der mindestens 1 sein muss, an: ");
            }
            this.muenzWert = this.keyboard.nextInt();
            if (this.muenzen.containsKey(muenzWert)) {
                System.out.println("Muenzwert bereits vorhanden");
                this.muenzWert = 0;
            }
        } while (this.muenzWert <= 0);
        System.out.println("Zahl wurde akzeptiert.");
    }

    /**
     * Method to set the amount of the coins with the keyboard
     */

    public void setMuenzAnz() { 
        do {
            System.out.print("Bitte geben sie an wie viele Muenzen sie vom Wert " + this.muenzWert + " haben/hinzufügen wollen (mindestens 0): ");
            while (!this.keyboard.hasNextInt()) {
                String input = this.keyboard.next();
                System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
                System.out.print("Bitte geben sie wie viele Muenzen sie vom Wert " + this.muenzWert + " haben/hinzufügen wollen: ");
            }
            this.muenzAnz = this.keyboard.nextInt();
            if (this.muenzAnz < 0) {
                System.out.println("\"" + this.muenzAnz + "\" ist eine ungültige Eingabe");
            }
        } while (this.muenzAnz < 0);
        System.out.println("Zahl wurde akzeptiert.");
    }

    /**
     * Fills a hashmap with the coin value as key and the amount of coins as value
     */
    public void setMuenzWertandAnz() { 
        boolean endCondition;  
        do {
            setMuenzWert();
            setMuenzAnz();
            this.muenzen.put(muenzWert, muenzAnz);
            this.muenzSort.putAll(muenzen);
            endCondition = repeatInput();
        } while (endCondition);
    }

    /**
     * ask if you want to enter more inputs
     * @return true or fals
     */
    public boolean repeatInput() { 
        String endString;
        boolean endCondition = true, endCondition2 = true;
        do {
            System.out.println("Wollen sie eine weitere Eingabe tätigen? j/n");
            endString = this.keyboard.next();
            switch (endString) {
                case "J":
                case "j":
                    endCondition = true;
                    endCondition2 = false;
                    break;
                case "N":
                case "n":
                    endCondition = false;
                    endCondition2 = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe");
                    break;

            }
        } while (endCondition2);

        return endCondition;

    }
    /**
     * checks if a coin value already exist and protects it from being overwritten
     * @param muenzWert
     * @return
     */
    public boolean checkMuenz(int muenzWert) { 
        return this.muenzen.containsKey(muenzWert);
    }

    /**
     * let you at more coins with a already set value
     */
    public void addMuenz() { 
        System.out.print("Bitte geben sie einen vorhadenen Münzwert ein: ");
        while (!this.keyboard.hasNextInt()) {
            String input = this.keyboard.next();
            System.out.printf("\"%s\" ist eine ungültige Eingabe\n", input);
            System.out.print("Bitte geben sie einen vorhadenen Münzwert ein: ");
        }
        this.muenzWert = keyboard.nextInt();
        if (checkMuenz(this.muenzWert)) {
            setMuenzAnz();
            this.muenzen.put(this.muenzWert, this.muenzAnz + this.muenzen.get(this.muenzWert));
            this.muenzSort.putAll(muenzen);
        } else {
            System.out.println("Muenzwert nicht vorhanden");
        }

    }
    /**
     * menu
     */
    public void menu() { 
        do {
            System.out.print("\n\nWas möchten sie tun?\n");
            System.out.print("[1]Neue zu zahlende Summe eingeben\n[2]Neuen gegebenen Betrag eingeben\n" +
                    "[3]Weitere Muenzen vorhandener Werte hinzufügen\n[4]Neuen Muenzwert und neue Muenzanzahl hinzufügen\n" +
                    "[5]Wechselgeld berechnen\n[6]Status\n[7]Beenden\n");
            this.choice = keyboard.next();
            switch (this.choice) {
                case "1":
                    setzbB();
                    if (this.zbB > gB) {
                        System.out.println("Der gegebene Betrag wurde automatisch angepasst, da die zu bezahlende Summe größer ist.");
                        this.gB = this.zbB + 1;
                    }
                    break;
                case "2":
                    setgB();
                    break;
                case "3":
                    addMuenz();
                    break;
                case "4":
                    setMuenzWertandAnz();
                    break;
                case "5":
                    calculateGreedy();
                    break;
                case "6":
                    print();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Ungültige Eingabe");
                    break;
            }
        } while (!choice.equals("7"));
    }

    /**
     * prints the sum, that has to be paid, the given sum, the coin values and the amount of coins
     */
    public void print() { //gibt die zu bezahlende Summe, die gegebene Summe, die muenzwerte und deren anzahl aus
        System.out.println("Zu bezahlende Summe: " + this.zbB);
        System.out.println("Gegebene Summe: " + this.gB);
        for (Map.Entry<Integer, Integer> entry : this.muenzSort.entrySet())
            System.out.print("Muenzwert: " + entry.getKey() + "\nAnzahl der Muenzen: " + entry.getValue() + "\n");

    } 
}
