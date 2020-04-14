import java.util.LinkedList;

public class SinglyLinkedList<V> {
    // Instance Variables
    private SinglyLinkedListNode<V> head, tail;
    private int size;
    //constructor takes no arguments
    public SinglyLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(V v)
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

    public void addLast(V v)
    {
        if(isEmpty())
        {
            addFirst(v);
            return;
        }
        SinglyLinkedListNode SinglyLinkedListNode = new SinglyLinkedListNode(v, null);
        SinglyLinkedListNode.setNext(null); //last node points to nothing
        tail.setNext(SinglyLinkedListNode); // give current tail a next
        tail = SinglyLinkedListNode;    // new tail is the node we were passed
        size++; // size increases by one
    }

    public V first()
    {
        return head.getData();
    }

    public SinglyLinkedListNode<V> getLast()
    {
        return tail;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public V last()
    {
        return tail.getData();
    }

    public V removeFirst()
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
        String s = "Singly Linked List contains: ";
        if (head == null)       //list has no head -> is empty
        {
            s+=("Nothing!");
            return s;
        }
        SinglyLinkedListNode<V> curr = head;
        for (int i=0; i<size; i++)
        {
            s+=curr.getData().toString()+" ";
            curr=curr.getNext();
        }
        return s;
    }

    public V get(int n)
    {
        if (head == null)       //list has no head -> is empty
        {
            System.out.println("List is empty!");
            return null;
        }
        SinglyLinkedListNode<V> curr = head;
        for (int i=0; i<n; i++)
        {
            curr=curr.getNext();
        }
        return curr.getData();
    }

    public V removeLast()
    {
        SinglyLinkedListNode<V> nodeBefore;
        SinglyLinkedListNode<V> nodeToRemove;
        if (size == 0) // list is empty
        {
            System.out.println("List is already empty!");
            return null;
        }
        nodeBefore = head;
        for (int i=0; i<size-2; i++) //loop through to find second last node
        {
            nodeBefore = nodeBefore.getNext();
        }
        nodeToRemove = tail;
        nodeBefore.setNext(null);   //second last node now points to null
        tail = nodeBefore;  //tail is set to be second last node
        size--; //size reduced by 1
        return nodeToRemove.getData();    //return the original tail
    }

    public V remove(int index)    //precondition: index must be greater than 0 (use removeFirst otherwise)
    {
        if (index == 0) //just use removeFirst
        {
            removeFirst();
            return null;
        }
        if (index > size-1) // index out of bounds
        {
            System.out.println("Index is out of bounds!");
            return null;
        }
        SinglyLinkedListNode<V> nodeBefore;
        SinglyLinkedListNode<V> nodeToRemove;
        if (size == 0) // list is empty
        {
            System.out.println("List is already empty!");
            return null;
        }
        nodeBefore = head;
        for (int i=0; i<index-1; i++) //loop through to find node before the index
        {
            nodeBefore = nodeBefore.getNext();
        }
        nodeToRemove = nodeBefore.getNext();
        nodeBefore.setNext(nodeToRemove.getNext()); //node before's next becomes node removed's next
        size--; //size reduced by 1
        return nodeToRemove.getData();    //return the removed node
    }

    public void add(int index, V v)
    {
        SinglyLinkedListNode nodeToAdd = new SinglyLinkedListNode(v, null);
        if (index == 0) // use addFirst in this case
        {
            addFirst(v);
            return;
        }
        if (index >= size) // use addLast in this case or index out of bounds
        {
            System.out.println("Index out of bounds");
            return;
        }
        if(index == size-1)
        {
            addLast(v);
        }
        SinglyLinkedListNode<V> nodeBefore;
        nodeBefore = head;
        for (int i=0; i<index-1; i++) //loop through to find node before the index
        {
            nodeBefore = nodeBefore.getNext();
        }
        nodeToAdd.setNext(nodeBefore.getNext());
        nodeBefore.setNext(nodeToAdd);
        size++; //size reduced by 1
    }

    public static void main (String[] args)
    {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
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
        ll.remove(2);
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