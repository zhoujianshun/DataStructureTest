package app;

import app.list.ArrayList;

/**
 * StackB
 */
public class StackB<E> extends ArrayList<E>{

    // public int size() {
    //     return 0;
    // }

    // public boolean isEmpty() {
    //     return false;
    // }

    public void push(E element) {
        this.add(element);
    }

    public E pop() {
        return this.remove(this.size() - 1);
    }

    public E top() {
        return this.get(this.size() - 1);
    }
    
}