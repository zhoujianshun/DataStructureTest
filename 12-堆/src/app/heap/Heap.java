package app.heap;

/**
 * heap
 */
public interface Heap<E> {

    public int size();
    public boolean isEmpty();
    public void clear();
    public void add(E element);
    public E get();
    public E remove();
    public E replace(E element);
    
}