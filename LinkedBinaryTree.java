/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {


    /** Nested static class for a binary tree node. */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public E getElement() throws IllegalStateException {
            return this.element;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public String toString(){
            if(element == null){ return "null";}
            return element.toString();
        }
    }

    /** Factory function to create a new node storing element e. */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    /** The root of the binary tree */
    protected Node<E> root = null;     // root of the tree

    /** The number of nodes in the binary tree */
    private int size = 0;              // number of nodes in the tree

    // constructor
    /** Construts an empty binary tree. */
    public LinkedBinaryTree() { }      // constructs an empty binary tree

    // nonpublic utility
    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p   a Position (that should belong to this tree)
     * @return    the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)
    /**
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p    A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).parent;
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).left;
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).right;
    }

    // update methods supported by this class
    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(root!=null){
            throw new IllegalStateException("root already exists");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e){
        //recursively add from root
        if(root==null)
        {
            addRoot(e);
            ++size;
            return;
        }
        addRecursive(root, e);
        ++size;
    }

    //recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e){
        if(p.getLeft()==null) {
            Node<E> toAdd = new Node<E>(e, p, null, null);
            p.setLeft(toAdd);
            return p;
        }
        else if(p.getRight()==null) {
            Node<E> toAdd = new Node<E>(e, p, null, null);
            p.setRight(toAdd);
            return p;
        }
        else
        {
            return addRecursive(p.getLeft(), e);
        }
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p   the Position to the left of which the new element is inserted
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e)
            throws IllegalArgumentException {
        Node<E> parent = (Node<E>) p;
        Node<E> node = createNode(e, parent, null, null);
        parent.setLeft(node);
        ++size;
        return node;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p   the Position to the right of which the new element is inserted
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e)
            throws IllegalArgumentException {
        Node<E> parent = (Node<E>) p;
        Node<E> node = createNode(e, parent, null, null);
        parent.setRight(node);
        ++size;
        return node;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p   the relevant Position
     * @param e   the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        E replaced = p.getElement();
        ((Node<E>) p).setElement(e);
        return replaced;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p   a leaf of the tree
     * @param t1  an independent tree whose structure becomes the left child of p
     * @param t2  an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                       LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        root=createNode(p.getElement(), null, (Node<E>) t1.root(), (Node<E>) t2.root());
        size=t1.size()+t2.size()+1;
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p   the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> n = (Node<E>) p;
        if (numChildren(n) == 2) {
            throw new IllegalArgumentException("Node has two children");
        }
        Node<E> child = n.getLeft() != null ? n.getLeft() : n.getRight();
        if(child!=null){
            child.setParent(n.getParent());
        }
        if(n==root)
        {
            root = child;
        }
        else{
            Node<E> parent = n.getParent();
            if(n == parent.getLeft()){
                parent.setLeft(child);
            }
            else{
                parent.setRight(child);
            }
        }
        size--;
        E old = n.getElement();
        return old;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Position<E> p : positions()) {
            sb.append(p.getElement());
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String [] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        int [] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
        for(int i : arr) {
            bt.insert(i);
        }
        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt postorder: " + bt.size() + " " + bt.postorder());
        System.out.println("bt root: "+bt.root()+"\nleft child: "+bt.left(bt.root())+"\nright child: "+bt.right(bt.root()));
        System.out.println("bt height: " + bt.height(bt.left(bt.root())) + "\nbt depth: " + bt.depth(bt.left(bt.root())));

        LinkedBinaryTree<Character> cbt = new LinkedBinaryTree<Character>();
        cbt.addRoot('h');
        cbt.addLeft(cbt.root(), 'i');
        cbt.addRight(cbt.root(), 'y');
        cbt.addLeft(cbt.left(cbt.root()), 'a');
        cbt.insert('!');
        System.out.println("cbt inorder: " + cbt.size() + " " + cbt.inorder());

        LinkedBinaryTree<Character> cbt2 = new LinkedBinaryTree<Character>();
        cbt2.addRoot('b');
        cbt2.addLeft(cbt2.root(), 'e');
        cbt2.addRight(cbt2.root(), 't');
        System.out.println("cbt2 inorder: " + cbt2.size() + " " + cbt2.inorder());

        LinkedBinaryTree<Character> cbtcombo = new LinkedBinaryTree<Character>();
        Node<Character> comboRoot = new Node<Character>('0', null, null, null);
        cbtcombo.attach(comboRoot, cbt, cbt2);
        System.out.println("cbtcombo inorder: " + cbtcombo.size() + " " + cbtcombo.inorder());
    }
} 