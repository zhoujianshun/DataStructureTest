package app;

/**
 * CircleLinkedList 双向循环链表
 */
public class CircleLinkedList<E> extends AbstractList<E> {

    /**
     * InnerCircleLinkedList
     */
    public static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> first;
    private Node<E> end;

    public CircleLinkedList() {
        this.size = 0;
        this.first = null;
        this.end = null;
    }

    @Override
    public void clear() {
        this.first = null;
        this.end = null;
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

        if (index == size) {
            // 添加到最后
            Node<E> oldEnd = this.end;
            this.end = new Node<>(oldEnd, element, this.first);
            if (isEmpty()) {
                // 第一个node
                this.first = this.end;
                this.first.next = this.end;
                this.first.prev = this.end;

            } else {
                oldEnd.next = this.end;
                this.first.prev = this.end;

            }
        } else {

            Node<E> next = nodeAtIndex(index);
            Node<E> pre = next.prev;
            Node<E> newNode = new Node<E>(pre, element, next);
            next.prev = newNode;
            pre.next = newNode;
            if (index == 0) {
                this.first = newNode;
            } else {
               
            }
        }
        this.size++;

    }

    @Override
    public E remove(int index) {
        checkRange(index);

        Node<E> current = nodeAtIndex(index);
        Node<E> prev = current.prev;
        Node<E> next = current.next;
        if (current == this.first) {
            // 表示删除第一个
            if (this.first == this.end) {
                this.first = null;
                this.end = null;
            } else {
                this.first = next;
                next.prev = prev;
                prev.next = next;
            }
        } else {
            prev.next = next;
            next.prev = prev;
            if (current == this.end) {
                this.end = prev;
            }

        }
        this.size--;
        return current.element;
    }

    public E remove(E element) {

        Node<E> current = this.first;
        Node<E> target = null;
        for (int i = 0; i < size; i++) {
            if (current.element == element) {
                target = current;
                break;
            }
            current = current.next;
        }

        if (target == null) {
            return null;
        } else {

            if (size == 1) {
                this.first = null;
                this.end = null;
            } else {
                Node<E> prev = target.prev;
                Node<E> next = target.next;
                next.prev = prev;
                prev.next = next;
                if (this.first == target) {
                    this.first = next;

                } else {

                }
            }
            this.size--;
        }

        return target.element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> current = this.first;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (current.element == element) {
                index = i;
                break;
            }
            current = current.next;
        }
        return index;
    }

    private Node<E> nodeAtIndex(int index) {
        Node<E> current = null;
        if (index > size / 2) {
            // 后半部分，从后面开始检索
            current = this.end;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        } else {
            current = this.first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }

        return current;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(this.size()).append(", [");

        Node<E> current = this.first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }

            builder.append(current.prev.element.toString());
            builder.append("_");
            builder.append(current.element.toString());

            builder.append("_");
            builder.append(current.next.element.toString());

            current = current.next;
        }

        builder.append("]");
        return builder.toString();
    }

}