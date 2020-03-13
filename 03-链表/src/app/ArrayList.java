package app;

/**
 * ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void clear() {

        for (int i = 0; i < size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    public void add(E element) {
        add(size, element);
    }

    public E get(int index) {
        checkRange(index);
        return this.elements[index];
    }

    public E set(int index, E element) {
        checkRange(index);
        E old = this.elements[index];
        this.elements[index] = element;
        return old;
    }

    public void add(int index, E element) {
        checkRangeForAdd(index);

        ensureCapacity();

        for (int i = this.size; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        this.size++;

    }

    public E remove(int index) {

        checkRange(index);
        E old = this.elements[index];
        for (int i = index + 1; i < this.size; i++) {
            this.elements[i - 1] = this.elements[i];
        }
        // 清空最后一个用不到的
        this.elements[this.size - 1] = null;
        this.size--;
        trim();
        return old;
    }

    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {

            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i].equals(element)) {
                    return i;
                }
            }

        }
        return -1;
    }

    private boolean isFull() {
        return this.elements.length == this.size;
    }

    private void ensureCapacity() {
        if (this.isFull()) {
            // oldCapacity + oldCapacity >> 1 相当于乘以1.5
            int oldCapacity = this.elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newArray = (E[]) (new Object[newCapacity]);
            for (int i = 0; i < this.elements.length; i++) {
                newArray[i] = this.elements[i];
            }
            this.elements = newArray;

            System.out.println("oldCapacity:" + oldCapacity + ", 扩容为：" + newCapacity);
        }

    }

    private void trim() {
        int capacity = this.elements.length;
        int newCapacity = capacity >> 1;
        if (size > newCapacity || newCapacity < DEFAULT_CAPACITY) {
            return;
        }
        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = this.elements[i];
        }
        this.elements = newArray;
        System.out.println(capacity + "缩容为：" + newCapacity);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(", [");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(this.elements[i] == null ? "null" : this.elements[i].toString());
        }
        builder.append("]");

        return builder.toString();
    }

}