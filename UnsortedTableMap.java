import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /** Underlying storage for the map of entries. */
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /** Constructs an initially empty map. */
    public UnsortedTableMap() {
    }

    // private utility
    /** Returns the index of an entry with equal key, or -1 if none found. */
    private int findIndex(K key) {
        EntryIterator EI = new EntryIterator();
        while(EI.hasNext())
        {
            if(EI.next().getKey()==key)
            {
                return EI.getCurrIndex();
            }
        }
        return -1;
    }

    // public methods
    /**
     * Returns the number of entries in the map.
     *
     * @return number of entries in the map
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry
     * exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) {
        EntryIterator EI = new EntryIterator();
        while(EI.hasNext())
        {
            Entry<K,V> curr = EI.next();
            if(curr.getKey()==key)
            {
                return curr.getValue();
            }
        }
        return null;
    }

    /**
     * Associates the given value with the given key. If an entry with the key was
     * already in the map, this replaced the previous value with the new one and
     * returns the old value. Otherwise, a new entry is added and null is returned.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such
     *         entry)
     */
    @Override
    public V put(K key, V value) {
        EntryIterator EI = new EntryIterator();
        while(EI.hasNext())
        {
            Entry<K,V> curr = EI.next();
            if(curr.getKey()==key)
            {
                V oldValue = curr.getValue();
                table.get(EI.getCurrIndex()).setValue(value);
                return oldValue;
            }
        }
        table.add(new MapEntry<K,V>(key, value));
        return null;
    }

    /**
     * Removes the entry with the specified key, if present, and returns its value.
     * Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     *         such entry exists
     */
    @Override
    public V remove(K key) {
        EntryIterator EI = new EntryIterator();
        while(EI.hasNext())
        {
            Entry<K,V> curr = EI.next();
            if(curr.getKey()==key)
            {
                V oldValue = curr.getValue();
                EI.remove();
                return oldValue;
            }
        }
        return null;
    }

    // ---------------- nested EntryIterator class ----------------
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException("No further entries");
            return table.get(j++);
        }

        public int getCurrIndex(){return j;}

        public void remove() {
            table.remove(getCurrIndex()-1);
        }
    } // ----------- end of nested EntryIterator class -----------

    // ---------------- nested EntryIterable class ----------------
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    } // ----------- end of nested EntryIterable class -----------

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    @Override
    public String toString()
    {
        EntryIterator EI = new EntryIterator();
        String s = "[";
        while(EI.hasNext())
        {
            s=s+" "+EI.next().getValue().toString();
            if(EI.hasNext()){s+=",";}
        }
        return s+" ]";
    }

    public static void main(String[] args) {
        //HashMap<Integer, String> m = new HashMap<Integer, String>();
        UnsortedTableMap<Integer, String> m = new UnsortedTableMap<Integer, String>();
        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");

        System.out.println("m: " + m.toString());

        m.remove(11);
        System.out.println("m: " + m.toString());
        System.out.println("m Element with Key 10: " + m.get(10).toString());
    }
}