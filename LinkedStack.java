public class LinkedStack<V> {
    // Instance Variables
    private SinglyLinkedListNode<V> head, tail;
    private int size;
    //constructor takes no arguments
    public LinkedStack()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void push(V v)
    {
        SinglyLinkedListNode SinglyLinkedListNode = new SinglyLinkedListNode(v, null);
        if (tail == null)   // if this is the very first node added it also has to become the tail
        {
            tail = SinglyLinkedListNode;
        }
        SinglyLinkedListNode.setNext(head); // set next to be the current head
        head = SinglyLinkedListNode; // make head the node we were passed
        size++; // size increases by one
    }

    public V top()
    {
        return head.getData();
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public V pop()
    {
        if (head == null)       //list has no head -> is empty
        {
            System.out.println("List is already empty!");
            return null;
        }
        SinglyLinkedListNode<V> temp = head;   //save current head
        head = head.getNext();      // head becomes 2nd in the list
        temp.setNext(null);
        size--; //reduce size by one
        return temp.getData();    //return what was the head of the list
    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        String s = "[";
        if (head == null)       //list has no head -> is empty
        {
            s+=("");
            return s+"]";
        }
        SinglyLinkedListNode<V> curr = head;
        for (int i=0; i<size-1; i++)
        {
            s+=curr.getData().toString()+", ";
            curr=curr.getNext();
        }
        s+=curr.getData().toString();
        return s+"]";
    }

    public static void main (String[] args) {
        LinkedStack<Integer> testArray = new LinkedStack<Integer>();
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
