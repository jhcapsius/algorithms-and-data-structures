# Sheet 8

## Heap
Insertion into a heap was not properly covered in the lecture. This is because the heap sort was the primary focus, so it was about sorting data.
Develop an algorithm that inserts an element into an existing heap that is in an array, so that the heap property is not violated. Pay attention to to the runtime, which must not overwrite O(n - log(n)).
Now implement a program that reads an unsorted sequence of numbers from a file and inserts it into an array. Then establish the heap property (smallest element in front) and output the array. 
In a menu, the user should then have the following have the following choices in a menu:

e : Inserting an element into the heap<br>
l : delete the smallest element from the heap<br>
s : sorted output of the heap (= perform HeapSort)<br>
a : Output of the array<br>
n : re-reading of the data<br>