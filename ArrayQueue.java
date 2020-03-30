public class ArrayQueue<A> {
    private int f = 0, b = 0;
    private A[] queueArray;
    public static final int CAPACITY=1000;

    public ArrayQueue() {
        this(CAPACITY);
    }

    @SuppressWarnings({"unchecked"})
    public ArrayQueue(int capacity)
    {        // constructs stack with given capacity
        queueArray = (A[]) new Object[capacity];
    }

    public void enqueue(A data)
    {
        // check if queue is full
        if (CAPACITY == b) {
            System.out.printf("Queue is already full!");
            return;
        }

        // add to back
        else {
            queueArray[b] = data;
            b++;
        }
        return;
    }

    public void dequeue()
    {
        // if stack empty cant delete
        if (f == b) {
            System.out.printf("Queue is already empty!");
            return;
        }
        // move the queue
        else {
            for (int i = 0; i < b - 1; i++) {
                queueArray[i] = queueArray[i + 1];
            }

            // clear the back
            if (b < CAPACITY) {
                queueArray[b] = null;
            }
            // back decreases
            b--;
        }
        return;
    }

    public String toString()
    {
        int i;
        String s="Queue Contains";
        if (f == b) {
            s+="Nothing!";
            return s;
        }
        // add each queue element
        for (i = f; i < b; i++) {
            s+=" "+ queueArray[i];
        }
        return s;
    }

    public A front()
    {
        if (f == b) {
            System.out.println("Queue is empty!");
            return null;
        }
        return queueArray[f];
    }

    public static void main (String[] args)
    {
        LinkedQueue<Integer> testQueue = new LinkedQueue<Integer>();
        System.out.println(testQueue.toString());
        testQueue.enqueue(400);
        testQueue.enqueue(100);
        testQueue.enqueue(200);
        System.out.println(testQueue.isEmpty());
        testQueue.dequeue();
        testQueue.enqueue(300);
        System.out.println(testQueue.size());
        System.out.println(testQueue.toString());
        while(!testQueue.isEmpty())
        {
            System.out.println("Dequeued: "+testQueue.dequeue());
        }
        System.out.println(testQueue.isEmpty());
    }
}