/**
 *implementation of a backtrack-algorithm to solve the backpack-problem
 * @author Jan-Henrik Capsius
 * @version 1.0
 */


public class BackpackAlgorithmBacktracking {
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

    public BackpackAlgorithmBacktracking(int[] weight, int[] values, int n, int capacity) {
        this.n = n; 
        this.capacity = capacity;
        this.weight = weight;
        this.values = values;
        this.backpack = new int[n+1][capacity+1];
        this.start = System.currentTimeMillis();
        this.result = backtrack(0, this.capacity);
        this.end = System.currentTimeMillis();
        this.duration = this.end - this.start;
    }

    /**
     *  if n equals the last index of the array and if the capacity smaller than the weight of the last object,
     *  this methode will return a 0, otherwise it will return the weight of the last object.
     *
     *  Else, it will check the weight of the object on position n and if the capacity is smaller than the weight of the object,
     *  it will call the backtrack method with the next position of the array. Otherwise it will increase the counter with
     *  +1 and will call the backtrack method with math.max
     * @param n amount of objects
     * @param capacity weight-capacity of the bagpack
     * @return weight of the object
     */
    public int backtrack(int n, int capacity){
        if(n == this.n-1){
            if(capacity < this.weight[n]){
                return 0;
            }else {
                return this.values[n];
            }
        //
        }else{
            if(capacity < weight[n]){
                return backtrack(n+1, capacity);
            }else{
                counter +=1;
                return Math.max(backtrack(n+1, capacity), backtrack(n+1, capacity-this.weight[n]) + values[n]);
            }
        }
    }

    //prints the result, runtime and the amount of additions
    public void print(){
        System.out.println("n:" + n);
        System.out.println("backpack capacity: " + capacity);
        System.out.println("Result: " + result);
        System.out.println("Runtime: " + duration + "ms");
        System.out.println("Aufrufe: " + counter + "\n");

    }

    public static void main(String[] args) {
        int n = 5;
        int n2 = 20;
        int capacity = 6;
        int capacity2 = 20;
        int[] weight = new int[]{2, 2, 6, 5, 4};
        int[] values = new int[]{6, 3, 5, 4, 6};
        int[] weight2 = new int[]{2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4, 2, 2, 6, 5, 4};
        int[] values2 = new int[]{6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6, 6, 3, 5, 4, 6};

        BackpackAlgorithmBacktracking backpack = new BackpackAlgorithmBacktracking(weight, values, n, capacity);
        backpack.print();
        BackpackAlgorithmBacktracking backpack2 = new BackpackAlgorithmBacktracking(weight2,values2, n2, capacity2);
        backpack2.print();
    }
}
