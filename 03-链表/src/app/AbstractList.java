package app;

/**
 * AbstractList
 */
public abstract class AbstractList<E> implements List<E>{
    protected int size;
    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public Boolean contains(E element) {

        return indexOf(element) >= 0;
    }
    


    protected void outOfBoundsException(int index) {
        throw new IndexOutOfBoundsException("index:" + index + ", size:" + this.size);
    }

    protected void checkRange(int index) {
        if (index < 0 || index >= this.size()) {
            outOfBoundsException(index);
        }
    }

    protected void checkRangeForAdd(int index) {
        if (index < 0 || index > this.size()) {
            outOfBoundsException(index);
        }
    }
}