/**
 * Algorithm to find the largest partial sum from the lecture
 *
 * @author Jan-Henrik Capsius
 * @Version1 1.0
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Partialsumme {
    double start, end, duration;
    int[] myValues;
    int n, von = 0, bis = 0, maxsumme, additionen;

    public void wrapper_Part_Sum_Algorithm(int[] values){
        part_Sum_Algorithm(values);
    }

    private void part_Sum_Algorithm(int[] values){
        this.n = values.length;
        this.myValues = values;
        Arrays.sort(this.myValues);
        this.maxsumme = this.myValues[0];
        this.additionen = 0;
        System.out.println("Meine Werte:");
        for (int i = 0; i < this.n; i++) System.out.print(this.myValues[i] + " ");
        System.out.println();
        start = System.nanoTime();
        for (int i = 0; i < this.n; i++) {
            for (int j = i; j < this.n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += this.myValues[k];
                    this.additionen += 1;
                }
                if (sum > maxsumme) {
                    this.maxsumme = sum;
                    this.von = i;
                    this.bis = j + 1;
                }
            }
        }
        this.end = System.nanoTime();
        this.duration = (this.end - this.start);
        printResult();
    }

    private void printResult(){
        System.out.println("max teilsumme: " + maxsumme);
        System.out.println("von: " + von);
        System.out.println("bis: " + bis);
        System.out.println("Anzahl Additionen: " + additionen);
        System.out.println("Laufzeit: " + duration + "ns");
    }



    public static void main(String[] args) throws IOException {

        //Initiates a scanner to enter the filename
        Scanner scan = new Scanner(System.in);
        System.out.print("Dataname: ");
        String path = scan.nextLine();
        System.out.println(path);

        //reads the file and adds its content to the arraylist lines
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        //Adds the contents of Lines to the array myValuesInString
        String[] myValuesInString = lines.toArray(new String[lines.size()]);

        //Creates a new int array with the length of the string array. Then converts the strings to ints and adds them to the int array
        int[] myValues = new int[myValuesInString.length];
        for (int l = 0; l < myValuesInString.length; l++) {
            myValues[l] = Integer.parseInt(myValuesInString[l]);}

        new Partialsumme().wrapper_Part_Sum_Algorithm(myValues);
    }

}
