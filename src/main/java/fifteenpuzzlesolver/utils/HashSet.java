
package fifteenpuzzlesolver.utils;

public class HashSet<V> {
    
    private ArrayList<V>[] values;
    private int size;
    
    public HashSet() {
        this.values = new ArrayList[32];
        this.size = 0;
    }
    
    private ArrayList<V> getListForKey(V value) {
        int hash = Math.abs(value.hashCode() % this.values.length);
        
        if (this.values[hash] == null) {
            this.values[hash] = new ArrayList<>();
        }
        
        return this.values[hash];
    }
    
    private int getIndexOfValue(ArrayList<V> list, V value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
    
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
    
    private void increment() {
        ArrayList<V>[] newValues = new ArrayList[ this.values.length * 2 ];
        
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] != null) {
                copy(newValues, i);
            }
        }
        
        this.values = newValues;
    }
    
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
    
    public boolean contains(V value) {
        return getIndexOfValue(getListForKey(value), value) != -1;
    }
    
    public int size() {
        return this.size;
    }
}
