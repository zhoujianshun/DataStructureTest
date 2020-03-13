package app;

import java.util.Comparator;

import com.mj.printer.BinaryTrees;

import app.tree.BST;

/**
 * BSTTest
 */
public class BSTTest {

    public static void test1() {
        BST<Person> tree = new BST<>(new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() - o2.getAge() > 0) {
                    return 1;
                } else if (o1.getAge() - o2.getAge() < 0) {
                    return -1;
                }
                return 0;
            }
        });
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3 };
        for (int i = 0; i < data.length; i++) {
            tree.add(new Person(i + "", data[i]));
        }

        BinaryTrees.println(tree);
        tree.levelOrderTraversal();
    }

    public static void test2() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        // tree.posorderTraversal();
        // tree.posorder(new Visitor<Integer>() {

        // @Override
        // public boolean visit(Integer element) {
        // System.out.println(element + "_");
        // return false;
        // }
        // });

        // System.out.println(tree);
    }

    public static void test3() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 1, 22, 33, 55, 44 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        System.out.println("height:" + tree.height2());
    }

    public static void test4() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 1, 22, 33, 55, 44 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        System.out.println("isComplete:" + (tree.isComleteTree() ? "true" : "false"));
    }

    public static void test5() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 1, 22, 33, 55, 44 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        tree.invert();
        BinaryTrees.println(tree);
    }

    public static void test6() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 1, 22, 33, 55, 44 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        tree.test();
    }

    public static void test7() {
        BST<Integer> tree = new BST<>();
        Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 1, 22, 33, 55, 44 };

        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }

        BinaryTrees.println(tree);
        tree.remove(44);
        BinaryTrees.println(tree);
    }
}