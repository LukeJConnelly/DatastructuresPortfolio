public class DLLNode<E>
{
    private E data; // data we want to store - of variable type
    private DLLNode<E> next;   //pointer to next node
    private DLLNode<E> prev;   //pointer to next node

    public DLLNode()
    {
        this(null, null, null);   //constructor call for a node with no arguments
    }

    public DLLNode(E data, DLLNode<E> next, DLLNode<E> prev) //constructor call for a node with arguments
    {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    // now simple accessors/mutators
    public E getData()
    {
        return data;
    }

    public DLLNode<E> getNext()
    {
        return next;
    }

    public DLLNode<E> getPrev()
    {
        return prev;
    }

    public void setData(E data)
    {
        this.data = data;
    }

    public void setNext(DLLNode<E> next)
    {
        this.next = next;
    }

    public void setPrev(DLLNode<E> prev)
    {
        this.prev = prev;
    }

    public String toString()
    {
        if(data==null){return "null";}
        return data.toString();
    }

}
