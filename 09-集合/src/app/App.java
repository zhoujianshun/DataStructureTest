package app;

import app.set.ListSet;
import app.set.Set;
import app.set.TreeSet;
import app.set.Set.Visitor;

public class App {
    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test1() {
        Set<Integer> set = new ListSet<>();

        set.add(10);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(11);
        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void test2() {
        Set<Integer> set = new TreeSet<>();

        set.add(10);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(11);
        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}