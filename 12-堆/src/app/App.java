package app;

import com.mj.printer.BinaryTrees;

import app.heap.BinaryHeap;

public class App {
    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(20);
        System.out.println(heap.get());
        heap.add(40);
        System.out.println(heap.get());
        heap.add(33);
        BinaryTrees.println(heap);
        System.out.println(heap.get());
        heap.add(50);
        System.out.println(heap.get());
        heap.add(55);
        System.out.println(heap.get());
        heap.add(31);
        System.out.println(heap.get());
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.replace(32);
        BinaryTrees.println(heap);
    }

    public static void test2() {
        Integer[] array = new Integer[]{1,2,80,40,99,12,15};
        BinaryHeap<Integer> heap = new BinaryHeap<>(null,array);
        BinaryTrees.println(heap);
    }
}