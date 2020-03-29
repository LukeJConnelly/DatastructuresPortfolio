public class SinglyLinkedListNode<V>
{
    private V data; // data we want to store - of variable type
    private SinglyLinkedListNode<V> next;   //pointer to next node

    public SinglyLinkedListNode()
    {
        this(null, null);   //constructor call for a node with no arguments
    }

    public SinglyLinkedListNode(V data, SinglyLinkedListNode<V> next) //constructor call for a node with arguments
    {
        this.data = data;
        this.next = next;
    }

    // now simple accessors/mutators
    public V getData()
    {
        return data;
    }

    public SinglyLinkedListNode<V> getNext()
    {
        return next;
    }

    public void setData(V data)
    {
        this.data = data;
    }

    public void setNext(SinglyLinkedListNode<V> next)
    {
        this.next = next;
    }

    public String toString()
    {
        return "Data: "+data.toString();
    }

}