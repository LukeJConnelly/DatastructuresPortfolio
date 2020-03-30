import java.lang.reflect.Array;

public class ArrayStack<A> {
    private int t = -1;
    private A[] stack;
    public static final int CAPACITY=1000;

    public ArrayStack() {
        this(CAPACITY);
    }

    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity)
    {        // constructs stack with given capacity
        stack = (A[]) new Object[capacity];
    }

    public int size()
    {
        return t+1;
    }
    public boolean isEmpty()
    {
        return t==-1;
    }
    public void push(A a)
    {
        t++;
        stack[t]=a;
    }
    public A pop()
    {
        A temp = stack[t];
        stack[t]=null;
        t--;
        return temp;
    }
    public A top()
    {
        return stack[t];
    }

    public static void main (String[] args) {
        ArrayStack<Integer> testArray = new ArrayStack<Integer>();
        System.out.println(testArray.isEmpty());
        testArray.push(1);
        System.out.println(testArray.isEmpty());
        testArray.push(2);
        testArray.push(4);
        System.out.println(testArray.top().toString());
        testArray.pop();
        testArray.push(3);
        System.out.println("Size of the array: " + testArray.size());
        while(!testArray.isEmpty())
        {
            System.out.println(testArray.pop().toString());
        }
        System.out.println(testArray.isEmpty());
    }
}
