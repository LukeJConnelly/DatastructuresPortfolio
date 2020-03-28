import java.util.LinkedList;
import java.util.Random;

public class HeapSort {
    public HeapSort(){}
    public static void main(String[] args) {
        int n = 0;
        while (n < 5000) {
            LinkedList<Integer> myList = new LinkedList<Integer>();
            fillRandom(myList, n);
//            System.out.println(myList.toString());
            long startTime = System.nanoTime();
            for (int i = n / 2 - 1; i >= 0; i--) {  //build heap
                HeapSortRecursively(myList, n, i);
            }
            // and take the elements one at a time from heap
            for (int i=n-1; i>=0; i--)
            {
                // move first element to end
                int temp = myList.get(0);
                myList.set(0, myList.get(i));
                myList.set(i, temp);

                // recursively call to heap sort reduced list
                HeapSortRecursively(myList, i, 0);
            }
            long endTime = System.nanoTime();
            long TimeTaken = endTime - startTime;
//            System.out.println(myList.toString());
            System.out.println(n + ", " + TimeTaken);
            n+=100;
        }
    }
    public static void fillRandom(LinkedList list, int n)
    {
        Random rnd = new Random();
        for (int i=0; i<n; i++)
        {
            list.add(rnd.nextInt()%10000);
        }
    }

    public static void HeapSortRecursively(LinkedList<Integer> myList, int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;  // left
        int r = 2*i + 2;  // right
        if (l < n && myList.get(l) > myList.get(largest)) {
            largest = l;
        }
        if (r < n && myList.get(r) > myList.get(largest)) {
            largest = r;
        }
        if (largest != i)
        {
            int swap = myList.get(i);
            myList.set(i, myList.get(largest));
            myList.set(largest, swap);
            // Recursively heapsort the sub tree
            HeapSortRecursively(myList, n, largest);
        }
    }
}