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

    public void addFirst(SinglyLinkedListNode<V> SinglyLinkedListNode)
    {
        if (tail == null)   // if this is the very first node added it also has to become the tail
        {
            tail = SinglyLinkedListNode;
        }
        SinglyLinkedListNode.setNext(head); // set next to be the current head
        head = SinglyLinkedListNode; // make head the node we were passed
        size++; // size increases by one
    }

    public void addLast(SinglyLinkedListNode<V> SinglyLinkedListNode)
    {
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

    public SinglyLinkedListNode<V> removeFirst()
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
        return temp;    //return what was the head of the list
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

    public SinglyLinkedListNode<V> get(int n)
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
        return curr;
    }

    public SinglyLinkedListNode<V> removeLast()
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
        return nodeToRemove;    //return the original tail
    }

    public SinglyLinkedListNode<V> remove(int index)    //precondition: index must be greater than 0 (use removeFirst otherwise)
    {
        if (index == 0) //just use removeFirst
        {
            System.out.println("Use removeFirst please, I'm a struggling CompSci student and you're making this unnecessarily hard!");
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
        return nodeToRemove;    //return the removed node
    }

    public void add(int index, SinglyLinkedListNode<V> nodeToAdd)    //precondition: index must be greater than 0 (use addFirst otherwise)
    {
        if (index == 0) // use addFirst in this case
        {
            System.out.println("Index is 0, you can use addFirst!");
            return;
        }
        if (index >= size-1) // use addLast in this case or index out of bounds
        {
            System.out.println("Index is size or greater, you can use addLast or change your index respectively!");
            return;
        }
        SinglyLinkedListNode<V> nodeBefore;
        if (size == 0) // list is empty
        {
            System.out.println("List is empty, you can use addFirst!");
            return;
        }
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
        // I realize the declarations here are clunky, but I've done so because my code can take variable types easier: simply
        // comment out lines 188 and 191-201 and comment back in lines 189 and 202-212 to see it use chars instead of ints
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
//        SinglyLinkedList<Character> ll = new SinglyLinkedList<Character>();
        System.out.println(ll.toString());
        SinglyLinkedListNode<Integer> element0 = new SinglyLinkedListNode<Integer>(0,null);
        SinglyLinkedListNode<Integer> element1 = new SinglyLinkedListNode<Integer>(1,null);
        SinglyLinkedListNode<Integer> element2 = new SinglyLinkedListNode<Integer>(3,null);
        SinglyLinkedListNode<Integer> element3 = new SinglyLinkedListNode<Integer>(4,null);
        SinglyLinkedListNode<Integer> element4 = new SinglyLinkedListNode<Integer>(5,null);
        SinglyLinkedListNode<Integer> element5 = new SinglyLinkedListNode<Integer>(2,null);
        SinglyLinkedListNode<Integer> element6 = new SinglyLinkedListNode<Integer>(-100,null);
        SinglyLinkedListNode<Integer> element7 = new SinglyLinkedListNode<Integer>(100,null);
        SinglyLinkedListNode<Integer> element8 = new SinglyLinkedListNode<Integer>(9999,null);
        SinglyLinkedListNode<Integer> element9 = new SinglyLinkedListNode<Integer>(8888,null);
        SinglyLinkedListNode<Integer> element10 = new SinglyLinkedListNode<Integer>(7777,null);
//        SinglyLinkedListNode<Character> element0 = new SinglyLinkedListNode<Character>('a',null);
//        SinglyLinkedListNode<Character> element1 = new SinglyLinkedListNode<Character>('b',null);
//        SinglyLinkedListNode<Character> element2 = new SinglyLinkedListNode<Character>('c',null);
//        SinglyLinkedListNode<Character> element3 = new SinglyLinkedListNode<Character>('d',null);
//        SinglyLinkedListNode<Character> element4 = new SinglyLinkedListNode<Character>('0',null);
//        SinglyLinkedListNode<Character> element5 = new SinglyLinkedListNode<Character>('&',null);
//        SinglyLinkedListNode<Character> element6 = new SinglyLinkedListNode<Character>('e',null);
//        SinglyLinkedListNode<Character> element7 = new SinglyLinkedListNode<Character>('f',null);
//        SinglyLinkedListNode<Character> element8 = new SinglyLinkedListNode<Character>('g',null);
//        SinglyLinkedListNode<Character> element9 = new SinglyLinkedListNode<Character>('h',null);
//        SinglyLinkedListNode<Character> element10 = new SinglyLinkedListNode<Character>('+',null);
        ll.addFirst(element0);
        ll.addFirst(element1);
        ll.addFirst(element2);
        ll.addFirst(element3);
        ll.addFirst(element4);
        ll.add(3, element5);
        System.out.println(ll.toString());
        ll.addFirst(element6);
        ll.addLast(element7);
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
        ll.addFirst(element8);
        ll.addFirst(element9);
        ll.addFirst(element10);
        System.out.println(ll.toString());
        System.out.println(ll.get(0).toString());
        System.out.println(ll.get(1).toString());
        System.out.println(ll.get(2).toString());
        System.out.println(ll.toString());
    }
}