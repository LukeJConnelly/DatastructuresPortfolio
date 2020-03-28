import java.util.LinkedList;
import java.util.Random;

public class PQSort {
    public PQSort(){}
    public static void main(String[] args) {
        int n = 0;
        while (n < 5000) {
            LinkedList<Integer> myList = new LinkedList<Integer>();
            fillRandom(myList, n);
            //System.out.println(myList.toString());
            long startTime = System.nanoTime();
            LinkedList<Integer> pq = new LinkedList<Integer>();
            while (!myList.isEmpty()) {
                pq.addLast(myList.removeFirst());
            }
            while (!pq.isEmpty()) {
                myList.addFirst(removeMin(pq));
            }
            long endTime = System.nanoTime();
            long TimeTaken = endTime - startTime;
            //System.out.println(myList.toString());
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

    public static Integer removeMin(LinkedList<Integer> list)
    {
        int minIndex = 0;
        int minVal = list.get(minIndex);
        for(int i=1;i<list.size();i++)
        {
            int currentVal = list.get(i);
            if (currentVal<minVal)
            {
                minVal = currentVal;
                minIndex = i;
            }
        }
        list.remove(minIndex);
        return minVal;
    }
}
