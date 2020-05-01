import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E>{
    // Instance Variables
    private DLLNode<E> header, trailer;
    private int size;
    //constructor takes no arguments
    public DoublyLinkedList()
    {
        header = new DLLNode(null, null, null);
        trailer = new DLLNode(null, null, header);
        header.setNext(trailer);
        size = 0;
    }

    public void addFirst(E e)
    {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e)
    {
        addBetween(e, trailer.getPrev(), trailer);
    }

    private void addBetween(E e, DLLNode<E> predecessor, DLLNode<E> successor) {
        DLLNode<E> newest = new DLLNode<E>(e, successor, predecessor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    public E first()
    {
        return header.getNext().getData();
    }

    public DLLNode<E> getLast()
    {
        return trailer.getPrev();
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public E last()
    {
        return trailer.getPrev().getData();
    }

    public E removeFirst()
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
        String s = "[";
        if (isEmpty())       //list is empty cant print string
        {
            s+=("");
            return s+"]";
        }
        DLLNode<E> curr = header.getNext();
        for (int i=0; i<size-1; i++)
        {
            s+=curr.toString()+", ";
            curr=curr.getNext();
        }
        s+=curr.toString();
        return s+"]";
    }

    public E get(int n)
    {
        if (isEmpty())       //is empty has no indexes
        {
            System.out.println("List is empty!");
            return null;
        }
        DLLNode<E> curr = header.getNext();
        for (int i=0; i<n; i++)
        {
            curr=curr.getNext();
        }
        return curr.getData();
    }

    public E removeLast()
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

    public void add(int index, E toAdd)    //precondition: index must be greater than 0 (use addFirst otherwise)
    {
        if (index<0||index>=size) // use addFirst in this case
        {
            System.out.println("Index is out of bounds");
            return;
        }
        DLLNode<E> nodeBefore;
        nodeBefore = header.getNext();
        for (int i=0; i<index-1; i++) //loop through to find node before the index
        {
            nodeBefore = nodeBefore.getNext();
        }
        addBetween(toAdd, nodeBefore, nodeBefore.getNext());
    }

    public E remove(int index)
    {
        if (index<0||index>=size)
        {
            System.out.println("Index is out of bounds");
            return null;
        }
        DLLNode<E> nodeBefore;
        nodeBefore = header.getNext();
        for (int i=0; i<index-1; i++) //loop through to find node before the index
        {
            nodeBefore = nodeBefore.getNext();
        }
        return remove(nodeBefore.getNext());
    }

    public Iterator<E> iterator(){ return new DoublyLinkedListIterator(header.getNext());}

    public class DoublyLinkedListIterator implements Iterator<E> {
        private DLLNode current;
        public DoublyLinkedListIterator(DLLNode e){this.current=e;}
        public boolean hasNext() {
            return current.getNext() != null;
        }
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E tmp = (E) current.getData();
            current = current.getNext();
            return tmp;
        }
    }

    public static void main (String[] args)
    {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        System.out.println(ll.toString());
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.add(3, 2);
        System.out.println(ll.toString());
        ll.addFirst(-100);
        ll.addLast(100);
        System.out.println(ll.toString());
        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll.toString());
        //Removes the item in the specified index
        System.out.println(ll.toString());
        ll.removeFirst();
        System.out.println(ll.toString());
        ll.removeLast();
        System.out.println(ll.toString());
        ll.removeFirst();
        System.out.println(ll.toString());
        ll.addFirst(9999);
        ll.addFirst(8888);
        ll.addFirst(7777);
        System.out.println(ll.toString());
        System.out.println(ll.get(0).toString());
        System.out.println(ll.get(1).toString());
        System.out.println(ll.get(2).toString());
        System.out.println(ll.toString());
    }
}