package app.circle;

/**
 * CircleDeque
 */
public class CircleDeque<E> {

    private int size;
    private E[] elements;
    private int front;

    private final static int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
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

    public void enQueueRear(E element) {
        ensureCapacity();
        int trueIndex = getTrueIndex(this.size);
        this.elements[trueIndex] = element;
        this.size++;
    }

    public E deQueueRear() {
        if (isEmpty()) {
            return null;
        }

        int trueIndex = getTrueIndex(this.size - 1);
        E old = this.elements[trueIndex];
        this.elements[trueIndex] = null;
        this.size--;
        trim();
        return old;
    }

    public void enQueueFront(E element) {
        ensureCapacity();
        // if (this.front == 0) {
        //     this.front = this.elements.length - 1;
        // } else {
        //     this.front--;
        // }
        this.front = this.getTrueIndex(-1);
        this.elements[this.front] = element;
        this.size++;
    }

    public E deQueueFront() {
        if (isEmpty()) {
            return null;
        }
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

        E old = this.elements[this.front];
        return old;
    }

    public void clear() {
     
        for (int i = 0; i < this.size; i++) {
            elements[getTrueIndex(i)] = null;
        }
        this.size = 0;
        this.front = 0;
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
        // 取模效率低，转换为加减法
        // int trueIndex = (index + front) % (this.elements.length);
        // if((index + front)>=this.elements.length){
        // return (index + front)- this.elements.length;
        // }else{
        // return (index + front);
        // }
        if (index < 0) {
            index = index + this.elements.length;
        }
        index = (index + front);
        return index - (index >= this.elements.length ? this.elements.length : 0);
    }

}