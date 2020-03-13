package app.homework;

import java.util.LinkedList;

/**
 * MyStack2
 */
public class MyStack2 {

    
    LinkedList<Integer> queue1;
    LinkedList<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offerLast(x);

        while (!queue2.isEmpty()) {
            queue1.offerLast(queue2.pollFirst());
        }
        LinkedList<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int result = queue2.pollFirst();
        return result;
    }

    /** Get the top element. */
    public int top() {
     
        return queue2.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}