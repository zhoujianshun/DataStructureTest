package app;

import app.list.ArrayList;

/**
 * Stack
 */
public class Stack<E>{

    private ArrayList<E> list = new ArrayList<>();


    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(this.size() - 1);
    }

    public E top() {
        return list.get(this.size() - 1);
    }
    
}