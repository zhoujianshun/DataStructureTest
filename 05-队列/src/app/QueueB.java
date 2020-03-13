package app;

import java.util.Stack;


/**
 * QueueB
 */
public class QueueB<E> {

    Stack<E> in = new Stack<>();
    Stack<E> out = new Stack<>();

    public int size() {
        return in.size();
    }

    public boolean isEmpty() {
        return in.isEmpty()&&out.isEmpty();
    }

    public void enQueue(E element) {
        in.push(element);
    }

    public E deQueue() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        } else {

        }
        return out.pop();
    }

    public E front() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        } else {

        }
        return out.peek();
    }

    public void clear() {
        in.clear();
        out.clear();
    }

}