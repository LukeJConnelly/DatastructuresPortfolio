import java.util.Comparator;

/**
 * An implementation of a sorted map using an AVL tree.
 */

public class AVLTreeMap<K, V> extends TreeMap<K, V> {


    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


    /** Constructs an empty map using the natural ordering of keys. */
    public AVLTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    /** Returns the height of the given tree position. */
    protected int height(Position<Entry<K, V>> p) {
        return tree.getAux(p);
    }

    /**
     * Recomputes the height of the given position based on its children's heights.
     */
    protected void recomputeHeight(Position<Entry<K, V>> p) {
        int auxleft=height(left(p)), auxright=height(right(p));
        tree.setAux(p, auxleft>auxright? auxleft+1 : auxright+1);
    }

    /** Returns whether a position has balance factor between -1 and 1 inclusive. */
    protected boolean isBalanced(Position<Entry<K, V>> p) {
        return height(left(p)) - height(right(p)) <= 1;
    }

    /** Returns a child of p with height no smaller than that of the other child. */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        if (height(left(p)) > height(right(p)))
            return left(p);
        if (height(left(p)) < height(right(p)))
            return right(p);
        if (isRoot(p))
            return left(p);
        if (p == left(parent(p)))
            return left(p);
        return right(p);
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This
     * traverses the path upward from p, performing a trinode restructuring when
     * imbalance is found, continuing until balance is restored.
     */
    protected void rebalance(Position<Entry<K, V>> p) {
        int prev=1, curr=0;
        while (!(prev == curr) && (p != null))
        {
            prev = height(p);
            if (!isBalanced(p)) {
                p = tallerChild(tallerChild(p));
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            recomputeHeight(p);
            curr = height(p);
            p = parent(p);
        }
    }

    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            rebalance(parent(p));
        }
    }

    /** Ensure that current tree structure is valid AVL (for debug use only). */
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }

	/*
	public String toBinaryTreeString() {
		BinaryTreePrinter< Entry<K, V> > btp = new BinaryTreePrinter<>( (LinkedBinaryTree<Entry<K, V>>) this.tree);
		return btp.print();
	}
	*/

    public static void main(String [] args) {
        AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
        Integer[] arr = new Integer[] { 44, 17, 88, 8, 32, 65, 97, 28, 54, 82, 93, 21, 29, 76, 80 };
        for (Integer i : arr) {
            avl.put(i, i);
        }

        System.out.println("avl: " + avl);

        avl.remove(arr[0]);

        System.out.println("avl: " + avl);
    }
}