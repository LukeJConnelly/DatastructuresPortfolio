public class CircularlyLinkedNode<C> {
    private C data; // data we want to store - of variable type
    private CircularlyLinkedNode<C> next;   //pointer to next node

    public CircularlyLinkedNode()
    {
        this(null, null);   //constructor call for a node with no arguments
    }

    public CircularlyLinkedNode(C data, CircularlyLinkedNode<C> next) //constructor call for a node with arguments
    {
        this.data = data;
        this.next = next;
    }

    // now simple accessors/mutators
    public C getData()
    {
        return data;
    }

    public CircularlyLinkedNode<C> getNext()
    {
        return next;
    }

    public void setData(C data)
    {
        this.data = data;
    }

    public void setNext(CircularlyLinkedNode<C> next)
    {
        this.next = next;
    }

    public String toString()
    {
        return "Data: "+data.toString();
    }
}
