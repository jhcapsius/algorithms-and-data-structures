/**
 *implementation of a dynamic programming solution to solve the backpack-problem
 *@author Jan-Henrik Capsius
 *@version 1.0
 */


public class BackpackAlgorithmDynamicProgramming {
    int n, capacity, result, counter = 0;
    double start, end, duration;
    int[] weight;
    int[] values;
    int[][] backpack;


     /**
     * constructor for the Backpack algorithm
     * @param weight weigth of the items
     * @param values value of the items
     * @param n number of items
     * @param capacity capacity of the backpack
     */

    public BackpackAlgorithmDynamicProgramming(int[] weight, int[] values, int n, int capacity) {
        this.n = n;
        this.capacity = capacity;
        this.weight = weight;
        this.values = values;
        this.backpack = new int[n+1][capacity+1];
    }

    /**
     * Algorithm to find the maximum Part Sum of the Backpack
     */
    public void findMaxPartSum(){
        start = System.currentTimeMillis();
        for(int rc = 0; rc <= capacity; rc++){
            if(rc < weight[n-1]){
                backpack[n][rc] = 0;
            }else {
                backpack[n][rc] = values[n-1];
            }
        }
        printTable();
        for(int i = n-1; i >= 0; i--){
            for(int rc = 0; rc <= capacity; rc++){
                if (rc < weight[i]){
                    backpack[i][rc] = backpack[i+1][rc];
                    printTable();
                }else{
                    backpack[i][rc] = Math.max(backpack[i+1][rc], backpack[i+1][rc-weight[i]] + values[i]);
                    counter += 1;
                    printTable();
                }
            }
        }
        end = System.currentTimeMillis();
        printTable();
        duration = end - start;
        result = (backpack[0][capacity]);
        printResults();
    }

    /**
     * prints the backpack
     */
    public void printTable(){
        for(int[] x : backpack){
            for(int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the result
     */
    public void printResults(){
        System.out.println("n:" + n);
        System.out.println("backpack capacity: " + capacity);
        System.out.println("Gewicht");
        for(int i = 0; i < this.weight.length; i++){
            System.out.print(this.weight[i] + " ");
        }
        System.out.println("\nWert");
        for(int i = 0; i < this.weight.length; i++){
            System.out.print(this.values[i] + " ");
        }
        System.out.println("\nResult: " + result);
        System.out.println("Runtime: " + duration + "ms");
        System.out.println("Additionen: " + counter + "\n");
    }

    public static void main(String[] args){
        int n = 5;
        //int n2 = 20;
        int capacity = 6;
        //int capacity2 = 20;
        int[] weight = new int[]{2, 2, 6, 5, 4};
        int[] values = new int[]{6, 3, 5, 4, 6};
        //int[] weight2 = new int[]{2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4};
        //int[] values2 = new int[]{6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6};

        BackpackAlgorithmDynamicProgramming backpack = new BackpackAlgorithmDynamicProgramming(weight, values, n, capacity);
        backpack.findMaxPartSum();
        //BackpackAlgorithmDynamicProgramming backpack2 = new BackpackAlgorithmDynamicProgramming(weight2,values2, n2, capacity2);
        //backpack2.findMaxPartSum();

    }

}
