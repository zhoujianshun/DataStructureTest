package app;

import app.circle.CircleDeque;
import app.circle.CircleQuque;

public class App {
    public static void main(String[] args) throws Exception {

        test5();
        System.out.println("------");
        test4();
    }

    public static void test1() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.front());
            System.out.println(queue.deQueue());
        }
    }

    public static void test2() {
        QueueB<Integer> queue = new QueueB<>();
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.front());
            System.out.println(queue.deQueue());
        }
    }

    public static void test3() {
        Deque<Integer> queue = new Deque<>();
        queue.enQueueFront(11);
        queue.enQueueFront(22);

        queue.enQueueRear(33);
        queue.enQueueRear(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.front());
            System.out.println(queue.deQueueFront());
        }

    }

    public static void test4() {
        CircleQuque<Integer> queue = new CircleQuque<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueue());
        }

        for (int i = 15; i < 30; i++) {
            queue.enQueue(i);
        }
        // queue.enQueue(11);
        // queue.enQueue(22);
        // System.out.println(queue.deQueue());
        // queue.enQueue(33);
        // System.out.println(queue.front());
        // queue.enQueue(44);
        // System.out.println(queue.deQueue());

        // queue.enQueue(1);
        // queue.enQueue(2);
        // queue.enQueue(3);

        while (!queue.isEmpty()) {
            System.out.println(queue.front());
            System.out.println(queue.deQueue());
        }

    }

    public static void test5() {
        CircleDeque<Integer> queue = new CircleDeque<>();
        queue.enQueueFront(11);
        queue.enQueueFront(22);
     
        queue.enQueueRear(33);
        queue.enQueueRear(44);
        System.out.println(queue.deQueueRear());


        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i);
            queue.enQueueRear(100+i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.front());
            System.out.println(queue.deQueueFront());
        }

    }
}