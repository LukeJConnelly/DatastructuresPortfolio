public class LinkedDeque<E> {
    // Instance Variables
    private DLLNode<E> header, trailer;
    private int size;
    //constructor takes no arguments
    public LinkedDeque()
    {
        header = new DLLNode(null, null, null);
        trailer = new DLLNode(null, null, header);
        header.setNext(trailer);
        size = 0;
    }

    public void insertFront(E e)
    {
        addBetween(e, header, header.getNext());
    }

    public void insertBack(E e)
    {
        addBetween(e, trailer.getPrev(), trailer);
    }

    private void addBetween(E e, DLLNode<E> predecessor, DLLNode<E> successor) {
        DLLNode<E> newest = new DLLNode<E>(e, successor, predecessor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    public E front()
    {
        return header.getNext().getData();
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public E back()
    {
        return trailer.getPrev().getData();
    }

    public E eraseFront()
    {
        if (isEmpty())       //cant remove element from empty list
        {
            System.out.println("List is already empty!");
            return null;
        }
        return remove(header.getNext());    //return what was the head of the list
    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        String s = "Linked Deque contains: ";
        if (isEmpty())       //list is empty cant print string
        {
            s+=("Nothing!");
            return s;
        }
        DLLNode<E> curr = header.getNext();
        for (int i=0; i<size; i++)
        {
            s+=curr.toString()+" ";
            curr=curr.getNext();
        }
        return s;
    }

    public E eraseBack()
    {
        if (isEmpty())       //cant remove element from empty list
        {
            System.out.println("List is already empty!");
            return null;
        }
        return remove(trailer.getPrev());    //return what was the head of the list
    }

    private E remove(DLLNode<E> node)
    {
        DLLNode<E> predecessor = node.getPrev();
        DLLNode<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getData();
    }

    public static void main (String[] args)
    {
        LinkedDeque<Integer> testDeque = new LinkedDeque<Integer>();
        testDeque.insertFront(2);
        testDeque.insertFront(3);
        testDeque.insertBack(1);
        System.out.println(testDeque.toString());
        System.out.println(testDeque.back().toString());
        System.out.println(testDeque.front().toString());
        testDeque.eraseBack();
        testDeque.eraseFront();
        testDeque.insertFront(1);
        testDeque.insertBack(3);
        System.out.println("Size is: "+testDeque.size());
        System.out.println(testDeque.toString());
        while(!testDeque.isEmpty())
        {
            System.out.println("Dequeued: "+testDeque.eraseFront());
        }
        System.out.println(testDeque.toString());
    }
}
