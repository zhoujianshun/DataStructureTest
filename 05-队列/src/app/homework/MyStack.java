package app.homework;

import java.util.LinkedList;

/**
 * MyStack
 */
public class MyStack {

    LinkedList<Integer> in;
    LinkedList<Integer> out;

    /** Initialize your data structure here. */
    public MyStack() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        in.offerLast(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (in.size()>1) {
            out.offerLast(in.pollFirst());
        }

        int result = in.pollFirst();
        LinkedList<Integer> temp = in;
        in = out;
        out = temp;
        return result;
    }

    /** Get the top element. */
    public int top() {
        while (in.size()>1) {
            out.offerLast(in.pollFirst());
        }

        int result = in.pollFirst();
        LinkedList<Integer> temp = in;
        in = out;
        out = temp;
        in.offerLast(result);
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}