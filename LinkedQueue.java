public class LinkedQueue<E> {
        // Instance Variables
        private DLLNode<E> header, trailer;
        private int size;
        //constructor takes no arguments
        public LinkedQueue()
        {
            header = new DLLNode(null, null, null);
            trailer = new DLLNode(null, null, header);
            header.setNext(trailer);
            size = 0;
        }

        public void enqueue(E e)
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

        public E dequeue()
        {
            if (isEmpty())       //cant remove element from empty list
            {
                System.out.println("Queue is already empty!");
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
            String s = "Linked Queue contains: ";
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
            LinkedQueue<Integer> testQueue = new LinkedQueue<Integer>();
            System.out.println(testQueue.toString());
            testQueue.enqueue(400);
            testQueue.enqueue(100);
            testQueue.enqueue(200);
            System.out.println(testQueue.isEmpty());
            testQueue.dequeue();
            testQueue.enqueue(300);
            System.out.println(testQueue.size());
            System.out.println(testQueue.toString());
            while(!testQueue.isEmpty())
            {
                System.out.println("Dequeued: "+testQueue.dequeue());
            }
            System.out.println(testQueue.isEmpty());
        }
}
