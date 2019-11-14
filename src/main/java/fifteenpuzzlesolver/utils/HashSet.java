
package fifteenpuzzlesolver.utils;

/**
 * Class which mimics implementation of Java's HashSet data structure. Implemented only those
 * methods that are needed for this app
 * 
 * @author miika
 * @param <V> 
 */
public class HashSet<V> {
    
    private ArrayList<V>[] values;
    private int size;
    
    /**
     * Constructor class. Creates new HashSet with 32 slots for values
     */
    public HashSet() {
        this.values = new ArrayList[32];
        this.size = 0;
    }
    
    /**
     * Search and returns a list of values from given position of HashSet.
     * 
     * @param value Key from where list is searched from
     * @return List of value found from given index
     */
    private ArrayList<V> getListForKey(V value) {
        int hash = Math.abs(value.hashCode() % this.values.length);
        
        if (this.values[hash] == null) {
            this.values[hash] = new ArrayList<>();
        }
        
        return this.values[hash];
    }
    
    /**
     * Searches and returns index of given value from the list.
     * 
     * @param list List from which the value is searched from
     * @param value Searched value
     * @return Index of the value 
     */
    private int getIndexOfValue(ArrayList<V> list, V value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Copies values of list found in given index to new list.
     * 
     * @param newList List where values is copied to
     * @param index Index of the list found from values array
     */
    private void copy(ArrayList<V>[] newList, int index) {
        for (int i = 0; i < this.values[index].size(); i++) {
            V value = this.values[index].get(i);
            int hash = Math.abs(value.hashCode() % newList.length);
        
            if (newList[hash] == null) {
                newList[hash] = new ArrayList<>();
            }
        
            newList[hash].add(value);
        }
    }
    
    /**
     * Doubles the size of the HashSet.
     */
    private void increment() {
        ArrayList<V>[] newValues = new ArrayList[ this.values.length * 2 ];
        
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] != null) {
                copy(newValues, i);
            }
        }
        
        this.values = newValues;
    }
    
    /**
     * Adds value to HashSet.
     * 
     * @param value Value to add
     */
    public void add(V value) {
        ArrayList<V> list = getListForKey(value);
        int index = getIndexOfValue(list, value);
        
        if (index < 0) {
            list.add(value);
            this.size++;
        } else {
            list.set(index, value);
        }
        
        if (1.0 * this.size / this.values.length > 0.75) {
            increment();
        }
    }
    
    /**
     * Checks if HashSet contains given value.
     * 
     * @param value to search for
     * @return True if value is found, false otherwise
     */
    public boolean contains(V value) {
        return getIndexOfValue(getListForKey(value), value) != -1;
    }
    
    /**
     * Returns size of HashSet e.g. number of elements in set.
     * 
     * @return Numbers of elements in HashSet
     */
    public int size() {
        return this.size;
    }
}
