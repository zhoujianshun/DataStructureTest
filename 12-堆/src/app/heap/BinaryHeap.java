package app.heap;

import java.util.Comparator;

import com.mj.printer.BinaryTreeInfo;

/**
 * BinaryHeap
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] inputElements) {
        super(comparator);
        if (inputElements != null && inputElements.length > 0) {
            int length = Math.max(inputElements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[length];
            for (int i = 0; i < inputElements.length; i++) {
                this.elements[i] = inputElements[i];
            }
            size = inputElements.length;
            heapify();
        }

    }

    public BinaryHeap(Comparator<E> comparator) {
        this(comparator, null);
    }

    public BinaryHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        siftup(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        int last = size - 1;
        E root = elements[0];
        elements[0] = elements[last];
        elements[last] = null;
        size--;

        siftDown(0);
        return root;
    }

    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;// 非叶子节点数量，二叉堆是完全二叉树

        while (index < half) {
            int childIndex = (index << 1) + 1; // 非叶子节点左节点一定存在
            E child = elements[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < size && (compare(child, elements[rightIndex]) < 0)) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) {
                break;
            } else {
                elements[index] = child;
                index = childIndex;
            }
        }

        elements[index] = element;

    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (isEmpty()) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void heapify() {
        // 两只方式
        // 1.自上而下上滤
        // 2.自下而上下滤（复杂度较低）

        // 自下而上下滤
        int half = size >> 1 - 1;
        for (int i = half; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftup(int currentIndex) {

        // int cmp = 0;
        // int parentIndex = 0;
        // while (currentIndex > 0) {
        // // parentIndex = (int) Math.floor((currentIndex - 1) / 2.0);
        // parentIndex = (currentIndex - 1) >> 1;
        // cmp = compare(elements[currentIndex], elements[parentIndex]);
        // if (cmp > 0) {
        // E temp = elements[currentIndex];
        // elements[currentIndex] = elements[parentIndex];
        // elements[parentIndex] = temp;
        // } else {
        // break;
        // }

        // currentIndex = parentIndex;

        // }

        int cmp = 0;
        int parentIndex = 0;
        E e = elements[currentIndex];

        while (currentIndex > 0) {
            // parentIndex = (int) Math.floor((currentIndex - 1) / 2.0);
            parentIndex = (currentIndex - 1) >> 1;
            cmp = compare(e, elements[parentIndex]);
            if (cmp > 0) {
                elements[currentIndex] = elements[parentIndex];
            } else {

                break;
            }

            currentIndex = parentIndex;
        }

        elements[currentIndex] = e;
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must not be null.");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) {
            return;
        }

        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为：" + newCapacity);
    }

    @Override
    public Object root() {
        return 0;

    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        int left = (index << 1) + 1;
        if (left > size - 1) {
            return null;
        } else {
            return left;
        }

    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        int right = (index << 1) + 2;
        if (right > size - 1) {
            return null;
        } else {
            return right;
        }
    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }

}