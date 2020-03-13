package app;

/**
 * LinkedListB 单项链表
 */
public class LinkedListB<E> extends AbstractList<E> {

    /**
     * InnerLinkedListB
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

    public LinkedListB() {
        this.size = 0;
        this.first = null;
    }

    @Override
    public void clear() {

        this.first = null;
        this.size = 0;

    }

    @Override
    public void add(E element) {
        this.add(this.size, element);
        // Node<E> newNode = new Node<E>(element, null);
        // if (isEmpty()) {
        // this.first = newNode;
        // this.size++;
        // } else {
        // Node<E> last = nodeAtIndex(this.size() - 1);
        // last.next = newNode;
        // this.size++;
        // }

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

        if (index == 0) {
            this.first = new Node<E>(element, this.first);

        } else {

            Node<E> pre = nodeAtIndex(index - 1);
            Node<E> current = pre.next;
            pre.next = new Node<E>(element, current);

        }
        this.size++;

    }

    @Override
    public E remove(int index) {
        checkRange(index);

        Node<E> current;
        if (index == 0) {
            current = this.first;
            this.first = current.next;
        } else {
            Node<E> pre = nodeAtIndex(index - 1);
            current = pre.next;
            pre.next = current.next;
        }
        this.size--;
        return current.element;
    }

    public E remove(E element) {
       
        Node<E> prev = null;
        Node<E> current = this.first;
        while (current != null) {
            if (current.element == element) {
                break;
            }
            prev = current;
            current = current.next;

        }

        if (current == null) {
            // throw new Exception("");
            return null;
        }

        if (prev == null) {
            this.first = current.next;
        } else {
            prev.next = current.next;
        }

        return current.element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> current = this.first;
        int i = 0;
        while (current.next != null) {
            if (current.element.equals(element)) {
                return i;
            }
            i++;
            current = current.next;
        }
        return -1;
    }

    private Node<E> nodeAtIndex(int index) {

        Node<E> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // int i = 0;
        // while (current != null) {
        // if (index == i) {
        // return current;
        // }
        // i++;
        // current = current.next;
        // }
        return current;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(this.size()).append(", [");
        Node<E> current = this.first;
        builder.append(current.element.toString());
        while (current.next != null) {
            current = current.next;
            builder.append(", ");
            builder.append(current.element.toString());
        }
        builder.append("]");
        return builder.toString();
    }

}