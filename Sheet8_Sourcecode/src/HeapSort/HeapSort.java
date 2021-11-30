package src.HeapSort;

import java.util.ArrayList;

public class HeapSort {
    /**
     * starts the heap sort
     * @param unsortedList unsorted list we want to sort
     * @return sorted list
     */
    public ArrayList<Integer> heapSort(ArrayList<Integer> unsortedList) {
        if (unsortedList.size() <= 1) {
            return unsortedList;
        }
        //creates the heap after size/2 passes
        for (int i = (int) Math.floor((Math.floor(unsortedList.size() / 2))); i >= 0; i--) {
            unsortedList = heapify(unsortedList, unsortedList.size(), i);
        }
        for (int i = unsortedList.size() - 1; i > 0; i--){
            int temp = unsortedList.get(0);
            unsortedList.set(0, unsortedList.get(i));
            unsortedList.set(i, temp);
            unsortedList = heapify(unsortedList, i, 0);
        }
        return unsortedList;
    }
    /**
     * @param heap given list
     * @param n    amount of elements
     * @param idx  highest index of the list
     * @return heapified list
     */
    private ArrayList<Integer> heapify(ArrayList<Integer> heap, int n, int idx) {
        int max_idx = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if (left < n && heap.get(max_idx) < heap.get(left)) {
            max_idx = left;
        }
        if (right < n && heap.get(max_idx) < heap.get(right)) {
            max_idx = right;
        }
        if(max_idx != idx){
            int temp = heap.get(idx);
            heap.set(idx, heap.get(max_idx));
            heap.set(max_idx, temp);
            heap = heapify(heap, n, max_idx);
        }
        return heap;
    }
    
}
