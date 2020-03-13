package app;

/**
 * CircleArrayList
 */
public class CircleArrayList<E> extends AbstractList<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int start;

    public CircleArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
        this.start = 0;
    }

    public CircleArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void clear() {

        for (int i = 0; i < size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
        this.start = 0;
    }

    public void add(E element) {
        add(size, element);
    }

    public E get(int index) {
        checkRange(index);
        int trueIndex = getTrueIndex(index);
        return this.elements[trueIndex];
    }

    public E set(int index, E element) {
        checkRange(index);
        int trueIndex = getTrueIndex(index);
        E old = this.elements[trueIndex];
        this.elements[trueIndex] = element;
        return old;
    }

    public void add(int index, E element) {
        checkRangeForAdd(index);

        ensureCapacity();
        // int trueIndex = getTrueIndex(index);
        if (index < size / 2) {
            // 前半部分移动
            if (this.start == 0) {
                this.start = this.elements.length - 1;
            } else {
                this.start--;
            }
            for (int i = 0; i <= index; i++) {
                this.elements[getTrueIndex(i)] = this.elements[getTrueIndex(i + 1)];
            }
        } else {
            // 后半部分移动
            for (int i = this.size(); i > index; i--) {
                this.elements[getTrueIndex(i)] = this.elements[getTrueIndex(i - 1)];
            }
        }

        this.elements[getTrueIndex(index)] = element;
        this.size++;

    }

    public E remove(int index) {

        checkRange(index);
        int trueIndex = getTrueIndex(index);
        E old = this.elements[trueIndex];

        if (trueIndex - start < size / 2) {
            // 前半部分移动

            for (int i = index; i > 0; i--) {
                this.elements[getTrueIndex(i)] = this.elements[getTrueIndex(i - 1)];
            }

            if (this.start == this.elements.length - 1) {
                this.start = 0;
            } else {
                this.start++;
            }
        } else {
            // 后半部分移动
            for (int i = index + 1; i > this.size; i--) {
                this.elements[getTrueIndex(i-1)] = this.elements[getTrueIndex(i)];
            }
        }
        // for (int i = index + 1; i < this.size; i++) {
        // this.elements[i - 1] = this.elements[i];
        // }
        // 清空最后一个用不到的
        // this.elements[this.size - 1] = null;
        this.size--;
        trim();
        return old;
    }

    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                int trueIndex = getTrueIndex(i);
                if (this.elements[trueIndex] == null) {
                    return i;
                }
            }
        } else {

            for (int i = 0; i < this.size; i++) {
                int trueIndex = getTrueIndex(i);
                if (this.elements[trueIndex].equals(element)) {
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
                int trueIndex = getTrueIndex(i);
                newArray[i] = this.elements[trueIndex];
            }
            this.elements = newArray;
            this.start = 0;
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
            int trueIndex = getTrueIndex(i);
            newArray[i] = this.elements[trueIndex];
        }
        this.start = 0;
        this.elements = newArray;
        System.out.println(capacity + "缩容为：" + newCapacity);
    }

    private int getTrueIndex(int index) {
        int trueIndex = (index + start) % (this.elements.length);
        return trueIndex;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(", [");
        for (int i = 0; i < this.size; i++) {
            int trueIndex = getTrueIndex(i);
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(this.elements[trueIndex] == null ? "null" : this.elements[trueIndex].toString());
        }
        builder.append("]");

        return builder.toString();
    }

}