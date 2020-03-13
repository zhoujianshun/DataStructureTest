package app;

/**
 * CircleLinkedListB 单项循环链表
 */
public class CircleLinkedListB<E> extends AbstractList<E> {

    /**
     * InnerCircleLinkedListB
     */
    public static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> first;

    public CircleLinkedListB() {
        this.size = 0;
        this.first = null;
    }

    @Override
    public void clear() {
        if (this.first != null) {
            this.first.next = null;
        }
        this.first = null;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        this.add(this.size, element);
    }

    @Override
    public E get(int index) {
        checkRange(index);
        Node<E> current = nodeAtIndex(index);
        return current.element;

    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        Node<E> current = nodeAtIndex(index);
        E old = current.element;
        current.element = element;
        return old;

    }

    @Override
    public void add(int index, E element) {
        checkRangeForAdd(index);

        if (isEmpty()) {
            this.first = new Node<E>(element, this.first);
            this.first.next = this.first;
        } else {

            if (index == 0) {
                Node<E> last = nodeAtIndex(size - 1);
                this.first = new Node<E>(element, this.first);
                last.next = this.first;
            } else {

                Node<E> pre = nodeAtIndex(index - 1);
                Node<E> current = pre.next;
                pre.next = new Node<E>(element, current);
            }

        }
        this.size++;

    }

    @Override
    public E remove(int index) {
        checkRange(index);

        Node<E> current;
        if (index == 0) {
            Node<E> last = nodeAtIndex(size - 1);
            current = this.first;

            if (current == last) {
                // 只有一个元素
                this.first = null;
            } else {
                this.first = current.next;
                last.next = this.first;
            }

        } else {
            Node<E> pre = nodeAtIndex(index - 1);
            current = pre.next;
            pre.next = current.next;
            // if(index==size-1){
            // //最后一个
            // }
        }
        this.size--;
        return current.element;
    }

    public E remove(E element) {
        if (isEmpty()) {
            return null;
        }

        Node<E> prev = null;
        Node<E> current = this.first;
        Node<E> target = null;
        for (int i = 0; i < size; i++) {
            if (current.element == element) {
                target = current;
                break;
            }
            prev = current;
            current = current.next;
        }

        if (target == null) {
            return null;
        }

        
        if (prev == null) {
            Node<E> last = nodeAtIndex(size - 1);
            
            if (last == target) {
                // 只有一个node
                this.first = null;
            } else {
                this.first = target.next;
                last.next = this.first;
            }

        } else {
            prev.next = target.next;
        }

        return current.element;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> current = this.first;
        int i = 0;
        do {
            if (current.element.equals(element)) {
                return i;
            }
            i++;
            current = current.next;
        } while (current != this.first);

        return -1;
    }

    private Node<E> nodeAtIndex(int index) {

        Node<E> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(this.size()).append(", [");

        if (!isEmpty()) {
            Node<E> current = this.first;
            do {
                builder.append(current.element.toString());
                current = current.next;
                if (current != this.first) {
                    builder.append(", ");
                } else {
                    break;
                }
            } while (true);
        }

        builder.append("]");
        return builder.toString();
    }

}