
package fifteenpuzzlesolver.utils;

/**
 * Class which mimics implementation of Java's Stack data structure. Implemented only those
 * methods that are needed for this app
 * 
 * @author miika
 */
public class Stack<T> {
    
    private T[] values;
    private int size;
    
    /**
     * Constructor class. Initializes arrays of values, size and a given comparator
     * for comparing elements
     * 
     */
    public Stack() {
        this.values = (T[]) new Object[10];
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
     * Adds value to the top of the stack.
     * 
     * @param value Value to be added to stack
     */
    public void push(T value) {
        if (this.values.length == this.size + 1) {
            incrementSize();
        }
        this.values[++this.size] = value;
    }
    
    /**
     * Checks whether stack is empty.
     * 
     * @return True if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    /**
     * Returns and "removes" top element (added last) from the stack.
     * 
     * @return Top element of the stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty - nothing to pop");
        }
        
        return this.values[this.size--];
    }
    
    /**
     * Returns but don't removetop element (added last) from the stack.
     * 
     * @return Top element of the stack
     */
    public T peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty - nothing to peek");
        }
        
        return this.values[this.size];
    }
    
    /**
     * Checks whether a given value is in the stack.
     * 
     * @param value Value to check
     * @return True if value is in the stack, false otherwise
     */
    public boolean contains(T value) {
        for (int i = 1; i <= this.size; i++) {
            if (this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns size of the stack. e.g. number of elements
     * 
     * @return Number of elements in the stack
     */
    public int size() {
        return this.size;
    }
}
