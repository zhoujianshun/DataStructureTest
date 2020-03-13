package app.circle;

/**
 * CircleQuque
 */
public class CircleQuque<E> {

    private int size;
    private E[] elements;
    private int front;

    private final static int DEFAULT_CAPACITY = 10;

    public CircleQuque() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity();
        int trueIndex = getTrueIndex(this.size);
        this.elements[trueIndex] = element;
        this.size++;
    }

    public E deQueue() {
        if (isEmpty()) {
            return null;
        }

        // int trueIndex = getTrueIndex(0);
        E old = this.elements[this.front];
        this.elements[this.front] = null;
        this.front = (this.front + 1) % this.elements.length;
        this.size--;
        trim();
        return old;
    }

    public E front() {
        if (isEmpty()) {
            return null;
        }
        // int trueIndex = getTrueIndex(0);
        E old = this.elements[this.front];
        return old;
    }

    public E rear() {
        if (isEmpty()) {
            return null;
        }
        int trueIndex = getTrueIndex(this.size - 1);
        E old = this.elements[trueIndex];
        return old;
    }

    public void clear() {
        this.size = 0;
        this.front = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[getTrueIndex(i)] = null;
        }
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
            this.front = 0;
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
        this.front = 0;
        this.elements = newArray;
        System.out.println(capacity + "缩容为：" + newCapacity);
    }

    private int getTrueIndex(int index) {
        index = index + front;
        return index - (index >= this.elements.length ? this.elements.length : 0);
    }

}