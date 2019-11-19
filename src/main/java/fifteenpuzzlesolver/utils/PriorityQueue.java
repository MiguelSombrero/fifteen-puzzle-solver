
package fifteenpuzzlesolver.utils;

import java.util.Comparator;

/**
 *
 * @author miika
 */
public class PriorityQueue<T> {
    
    private T[] values;
    private int size;
    private Comparator comparator;
    
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
    
    private int leftChild(int i) {
        return 2 * i;
    }
    
    private int rightChild(int i) {
        return 2 * i + 1;
    }
    
    private int parent(int i) {
        return i / 2;
    }
    
    private void increaseKey(int i, T value) {
        this.values[i] = value;
        
        while (i > 1 && comparator.compare(this.values[parent(i)], this.values[i]) > 0) {
            exchange(i, parent(i));
            i = parent(i);
        }
    }
    
    public void add(T value) {
        if (this.values.length == this.size+1) {
            incrementSize();
        }
        this.size++;
        increaseKey(this.size, value);
    }
    
    private void exchange(int i, int j) {
        T temp = this.values[i];
        this.values[i] = this.values[j];
        this.values[j] = temp;
    }
    
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
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
}
