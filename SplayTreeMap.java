import java.util.Comparator;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


    /** Constructs an empty map using the natural ordering of keys. */
    public SplayTreeMap() { super(); }

    /**
     * Constructs an empty map using the given comparator to order keys.
     * @param comp comparator defining the order of keys in the map
     */
    public SplayTreeMap(Comparator<K> comp) { super(comp); }

    /** Utility used to rebalance after a map operation. */
    private void splay(Position<Entry<K,V>> p) {
        while (!isRoot(p)) {
            Position<Entry<K,V>> parent = parent(p);
            Position<Entry<K,V>> gp = parent(parent);
            if (gp == null)                                          // zig case
                swap(p);
            else if ((parent == left(gp)) == (p == left(parent))) {  // zig-zig case
                swap(parent);      // move PARENT upward
                swap(p);           // then move p upward
            } else {                                                    // zig-zag case
                swap(p);           // move p upward
                swap(p);           // move p upward again
            }
        }
    }

    public void swap(Position<Entry<K,V>> p) {
        LinkedBinaryTree.Node<Entry<K, V>> first = (LinkedBinaryTree.Node<Entry<K, V>>) p;
        LinkedBinaryTree.Node<Entry<K, V>> second = first.getParent();
        LinkedBinaryTree.Node<Entry<K, V>> third = second.getParent();
        if (third == null) {
            tree.root = first;
            first.setParent(null);
        } else
            connect(third, first, second == third.getLeft());
        if (first == second.getLeft()) {
            connect(second, first.getRight(), true);
            connect(first, second, false);
        } else {
            connect(second, first.getLeft(), false);
            connect(first, second, true);
        }
    }

    private void connect(LinkedBinaryTree.Node<Entry<K,V>> p, LinkedBinaryTree.Node<Entry<K,V>> c, boolean left) {
        c.setParent(p);
        if (left) {
            p.setLeft(c);
        }
        else {
            p.setRight(c);
        }
    }

    protected void rebalanceAccess(Position<Entry<K,V>> p) {
        if (isExternal(p))
        {
            p = parent(p);
        }
        if (p != null)
        {
            splay(p);
        }
    }

    protected void rebalanceInsert(Position<Entry<K,V>> p) {
        splay(p);
    }

    protected void rebalanceDelete(Position<Entry<K,V>> p) {
        if (!isRoot(p))
        {
            splay(parent(p));
        }
    }
}