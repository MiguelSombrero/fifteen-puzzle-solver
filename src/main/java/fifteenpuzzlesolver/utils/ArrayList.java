
package fifteenpuzzlesolver.utils;

/**
 * Class which mimics implementation of Java's ArrayList data structure.
 * @author miika
 */
public class ArrayList<T> {
    
    private T[] values;
    private int index;
    
    /**
     * Constructor class.
     */
    public ArrayList() {
        this.values = (T[]) new Object[10];
        this.index = 0;
    }
    
    /**
     * Method which doubles current values array's size. Doubling the size of an
     * array would be very inefficient with BIG arrays. Since we are dealing with 
     * only very SMALL arrays in this assignment, it's OK.
     */
    private void increment() {
        T[] newValues = (T[]) new Object[ this.values.length * 2 ];
        
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = this.values[i];
        }
        
        this.values = newValues;
    }
    
    /**
     * Method for adding element to an ArrayList.
     * 
     * @param value Element to be added
     */
    public void add(T value) {
        if (this.values.length == this.index) {
            increment();
        }
        this.values[this.index++] = value;
    }
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param i index of the element to replace
     * @param value value to be saved in index i
     */
    public void set(int i, T value) {
        this.values[i] = value;
    }
    
    /**
     * Method for getting an element in given index. Method doesn't remove element from the list
     * @param i Index where element is searched from
     * @return Element to be returned
     */
    public T get(int i) {
        if (i < 0 || i >= this.index) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " is out of range [0, " + this.index + "]");
        }
        return this.values[i];
    }
    
    /**
     * Method which returns size of an ArrayList e.g. number of elements in the list. 
     * 
     * @return Size of an ArraList
     */
    public int size() {
        return this.index;
    }
}
