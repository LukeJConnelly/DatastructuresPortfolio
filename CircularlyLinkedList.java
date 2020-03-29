public class CircularlyLinkedList<C> {
    // Instance Variables
    private CircularlyLinkedNode<C> head, tail;
    private int size;
    //constructor takes no arguments
    public void CircularlyLinkedNode()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(C c)
    {
        CircularlyLinkedNode CircularlyLinkedNode = new CircularlyLinkedNode(c, null);
        if (tail == null)   // if this is the very first node added it also has to become the tail
        {
            tail = CircularlyLinkedNode;
        }
        CircularlyLinkedNode.setNext(head); // set next to be the current head
        tail.setNext(CircularlyLinkedNode);
        head = CircularlyLinkedNode; // make head the node we were passed
        size++; // size increases by one
    }

    public void addLast(C c)
    {
        CircularlyLinkedNode CircularlyLinkedNode = new CircularlyLinkedNode(c, null);
        CircularlyLinkedNode.setNext(head); //last node points to nothing
        tail.setNext(CircularlyLinkedNode); // give current tail a next
        tail = CircularlyLinkedNode;    // new tail is the node we were passed
        size++; // size increases by one
    }

    public C first()
    {
        return head.getData();
    }

    public CircularlyLinkedNode<C> getLast()
    {
        return tail;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public C last()
    {
        return tail.getData();
    }

    public C removeFirst()
    {
        if (isEmpty())  //cant remove first
        {
            System.out.println("List is already empty!");
            return null;
        }
        CircularlyLinkedNode<C> temp = head;   //save current head
        if (head == tail)
        {
            head=null;
            tail=null;
        }
        head = head.getNext();      // head becomes 2nd in the list
        tail.setNext(head);
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
        String s = "Circularly Linked List contains: ";
        if (isEmpty())       // size=0
        {
            s+=("Nothing!");
            return s;
        }
        CircularlyLinkedNode<C> curr = head;
        for (int i=0; i<size; i++)
        {
            s+=curr.getData().toString()+" ";
            curr=curr.getNext();
        }
        return s;
    }

    public C get(int n)
    {
        if (isEmpty())       //cant get from empty list
        {
            System.out.println("List is empty!");
            return null;
        }
        CircularlyLinkedNode<C> curr = head;
        for (int i=0; i<n; i++)
        {
            curr=curr.getNext();
        }
        return curr.getData();
    }

    public C removeLast()
    {
        CircularlyLinkedNode<C> nodeBefore;
        CircularlyLinkedNode<C> nodeToRemove;
        if (isEmpty()) // list is empty, cant remove last
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
        if (head == tail)
        {
            head=null;
            tail=null;
        }
        nodeBefore.setNext(head);   //second last node now points to null
        tail = nodeBefore;  //tail is set to be second last node
        size--; //size reduced by 1
        return nodeToRemove.getData();    //return the original tail
    }

    public C remove(int index)    //precondition: index must be greater than 0 (use removeFirst otherwise)
    {
        if (index == 0||index<0||index>size-1) //just use removeFirst
        {
            System.out.println("Use removeFirst, removeLast, or your index is out of bounds!");
            return null;
        }
        CircularlyLinkedNode<C> nodeBefore;
        CircularlyLinkedNode<C> nodeToRemove;
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

    public void add(int index, C c)    //precondition: index must be greater than 0 (use addFirst otherwise)
    {
        CircularlyLinkedNode nodeToAdd = new CircularlyLinkedNode(c, null);
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
        CircularlyLinkedNode<C> nodeBefore;
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
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
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
