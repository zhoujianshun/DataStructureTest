package app.set;

/**
 * set
 */
public interface Set<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    /**
     * Innerset
     */
    public static abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }
}