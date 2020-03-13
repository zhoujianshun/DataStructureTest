package app;

/**
 * List
 */
public interface List<E> {

    public void clear();

    public int size();

    public boolean isEmpty();

    public Boolean contains(E element);

    public void add(E element);

    public E get(int index);

    public E set(int index, E element);

    public void add(int index, E element);

    public E remove(int index);

    public int indexOf(E element);

}