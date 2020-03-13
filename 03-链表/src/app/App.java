package app;

public class App {

    public static void main(String[] args) throws Exception {
        // Solution.testHasCycle();
        test6();

        return;

    }

    public static void test1() {
        // ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.err.println(list);
        list.remove(19);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        for (int i = 0; i < 18; i++) {
            list.remove(0);
        }
        System.err.println(list);
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.err.println(list);
        list.add(19, 30);
        System.err.println(list);
        list.add(0, 111);
        System.err.println(list);
        list.remove(2);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        list.set(0, 10);
        System.err.println(list);

    }

    public static void test2() {
        ArrayList<Integer> list = new ArrayList<>();
        // LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        System.err.println(list);
        for (int i = 0; i < 50; i++) {
            list.remove(0);
        }

        System.err.println(list);
        // list.add(19, 30);
        // System.err.println(list);
        // list.add(0, 111);
        // System.err.println(list);
        // list.remove(2);
        // System.err.println(list);
        // list.remove(0);
        // System.err.println(list);
        // list.set(0, 10);
        // System.err.println(list);
    }

    public static void test3() {
        // ArrayList<Integer> list = new ArrayList<>();
        LinkedListB<Integer> list = new LinkedListB<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        System.err.println(list);
        list.add(19, 30);
        System.err.println(list);
        list.add(0, 111);
        System.err.println(list);
        list.remove(2);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        list.set(0, 10);
        System.err.println(list);
    }

    public static void test4() {
        // ArrayList<Integer> list = new ArrayList<>();
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.err.println(list);
        list.remove(19);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        for (int i = 0; i < 18; i++) {
            list.remove(0);
        }
        System.err.println(list);
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.err.println(list);

        System.err.println(list);
        list.add(19, 30);
        System.err.println(list);
        list.add(0, 111);
        System.err.println(list);
        list.remove(2);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        list.set(0, 10);
        System.err.println(list);

    }

    public static void test5() {
        // 约瑟夫问题，使用环形链表
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 0) {
            index +=2;
            index = index%list.size();
            // System.out.println(index);
            // list.remove(index);
            System.out.println(list.remove(index));
        }
        // System.out.println(list);
    }

    public static void test6() {
        // ArrayList<Integer> list = new ArrayList<>();
        CircleArrayList<Integer> list = new CircleArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.err.println(list);
        list.remove(19);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        for (int i = 0; i < 18; i++) {
            list.remove(0);
        }
        System.err.println(list);
        for (int i = 0; i < 20; i++) {
            list.add(i);
            // System.err.println(list);
        }
        System.err.println(list);
        list.add(20, 40);
        System.err.println(list);
        list.add(19, 30);
        System.err.println(list);
        list.add(0, 111);
        System.err.println(list);
        list.add(1, 222);
        System.err.println(list);
        list.remove(2);
        System.err.println(list);
        list.remove(0);
        System.err.println(list);
        list.set(0, 10);
        System.err.println(list);

    }
}