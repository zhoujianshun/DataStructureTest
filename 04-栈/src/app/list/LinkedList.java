package app.list;



/**
 * LinkedList 双向链表
 */
public class LinkedList<E> extends AbstractList<E> {

    /**
     * InnerLinkedList
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

    public LinkedList() {
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
            this.end = new Node<>(this.end, element, null);
            if (this.end.prev == null) {
                // 第一个node
                this.first = this.end;
            } else {
                this.end.prev.next = this.end;
            }
        } else {
            
            Node<E> next = nodeAtIndex(index);
            Node<E> pre = next.prev;
            Node<E> newNode = new Node<E>(pre, element, next);
            next.prev = newNode;
            
            if (pre == null) {
                // index = 0
                this.first = newNode;

            } else {
                pre.next = newNode;
                // next.prev = newNode;
            }
        }
        this.size++;
        // if (index == 0) {
        // // 表示加到第一个
        // this.first = new Node<E>(null, element, this.first);
        // if (this.first.next == null) {
        // // 表示只有一个，头和尾相同
        // this.end = this.first;
        // } else {
        // this.first.next.prev = this.first;
        // }

        // } else {
        // Node<E> pre = nodeAtIndex(index - 1);
        // Node<E> next = pre.next;
        // Node<E> newNode = new Node<E>(pre, element, next);
        // pre.next = newNode;

        // if (next == null) {
        // // 表示加到最后一个
        // this.end = newNode;
        // } else {
        // next.prev = newNode;
        // }

        // }
        // this.size++;

    }

    @Override
    public E remove(int index) {
        checkRange(index);

        Node<E> current = nodeAtIndex(index);
        Node<E> prev = current.prev;
        Node<E> next = current.next;
        if (prev == null) {
            // 表示删除第一个
            this.first = next;
            // if (next == null) {
            //     // 只有一个全删完了
            //     this.end = prev;
            // } else {
            //     next.prev = prev;
            // }

        } else {
            prev.next = next;
            // if (next == null) {
            //     // 表示删除最后一个
            //     this.end = prev;
            // } else {
            //     next.prev = prev;
            // }
        }

        if (next == null) {
            this.end = prev;
        } else {
            next.prev = prev;
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
            if (this.first == null) {
                this.end = null;
            } else {
                this.first.prev = null;
            }
        } else {
            prev.next = current.next;
            if (prev.next == null) {
                this.end = prev;
            } else {
                current.next.prev = prev;
            }
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
        if (current != null) {
            builder.append(current.element.toString());
            while (current.next != null) {
                current = current.next;
                builder.append(", ");
                if (current.prev == null) {
                    builder.append("null");
                } else {
                    builder.append(current.prev.element.toString());
                }
                builder.append("_");
                builder.append(current.element.toString());

                builder.append("_");
                if (current.next == null) {
                    builder.append("null");
                } else {
                    builder.append(current.next.element.toString());
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

}