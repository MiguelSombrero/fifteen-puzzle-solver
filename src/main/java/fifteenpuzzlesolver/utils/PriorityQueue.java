
package fifteenpuzzlesolver.utils;

import java.util.Comparator;

/**
 * Class which mimics implementation of Java's PriorityQueue data structure. Implemented only those
 * methods that are needed for this app
 * 
 * @author miika
 * @param <T> Generic value type of elements. Is specified when PriorityQueue is created.
 */
public class PriorityQueue<T> {
    
    private T[] values;
    private int size;
    private Comparator comparator;
    
    /**
     * Constructor class. Initializes arrays of values, size and a given comparator
     * for comparing elements
     * 
     * @param comparator Comparator for comparing elements in the queue
     */
    public PriorityQueue(Comparator comparator) {
        this.values = (T[]) new Object[10];
        this.comparator = comparator;
        this.size = 0;
    }
    
    /**
     * Method which multiplies current values array's size by 2.
     */
    private void incrementSize() {
        T[] newValues = (T[]) new Object[this.values.length * 2];
        
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = this.values[i];
        }
        
        this.values = newValues;
    }
    
    /**
     * Returns index of left child of the given position.
     * 
     * @param i Index of element which left child's index is returned
     * @return Index of left child
     */
    private int leftChild(int i) {
        return 2 * i;
    }
    
    /**
     * Returns index of right child of the given position.
     * 
     * @param i Index of element which right child's index is returned
     * @return Index of right child
     */
    private int rightChild(int i) {
        return 2 * i + 1;
    }
    
    /**
     * Returns index of parent of the given position.
     * 
     * @param i Index of element which parent's index is returned
     * @return Index of parent
     */
    private int parent(int i) {
        return i / 2;
    }
    
    /**
     * Increases added value from leaf to root node as long as parent
     * values are larger than it.
     * 
     * @param i Index of the current node
     * @param value Value that is added to the queue
     */
    private void increaseKey(int i, T value) {
        this.values[i] = value;
        
        while (i > 1 && comparator.compare(this.values[parent(i)], this.values[i]) > 0) {
            exchange(i, parent(i));
            i = parent(i);
        }
    }
    
    /**
     * Adds element to the PriorityQueue.
     * 
     * @param value Element to be added
     */
    public void add(T value) {
        if (this.values.length == this.size + 1) {
            incrementSize();
        }
        this.size++;
        increaseKey(this.size, value);
    }
    
    /**
     * Changes values from indexes i and j.
     * 
     * @param i Index of element no 1
     * @param j Index of element no 2
     */
    private void exchange(int i, int j) {
        T temp = this.values[i];
        this.values[i] = this.values[j];
        this.values[j] = temp;
    }
    
    /**
     * Decrements value from top to bottom as long as the value is larger
     * than it's left or right child.
     * 
     * @param i Index which value is decremented
     */
    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int min = i;
        
        if (left <= this.size && comparator.compare(this.values[left], this.values[i]) < 0) {
            min = left;
        }
        if (right <= this.size && comparator.compare(this.values[right], this.values[min]) < 0) {
            min = right;
        }
        if (min != i) {
            exchange(i, min);
            heapify(min);
        }
    }
    
    /**
     * Returns and removes minimum value from a queue. After extracting minimum value
     * from the queue, heapify() method makes sure that heap-condition
     * is still valid
     * 
     * @return Minimum value
     */
    public T poll() {
        if (this.size < 1) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty - nothing to poll");
        }
        
        T min = this.values[1];
        this.values[1] = this.values[this.size];
        this.size--;
        heapify(1);
        return min;
    }
    
    /**
     * Returns size of the queue. e.g. number of elements
     * 
     * @return Number of elements in the queue
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Checks whether queue is empty.
     * 
     * @return True if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
}
