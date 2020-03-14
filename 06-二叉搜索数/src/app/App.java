package app;

import java.util.Comparator;

import com.mj.printer.BinaryTrees;

import app.tree.AVLTree;
import app.tree.BST;
import app.tree.BinaryTree.Visitor;

public class App {
    public static void main(String[] args) throws Exception {
        test2();
        // test2();

    }

    public static void test1() {
        Integer data[] = new Integer[] { 85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56 };

        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        // bst.add(13);
        // BinaryTrees.println(bst);
        // bst.add(14);
        // BinaryTrees.println(bst);
        // bst.add(15);
        // BinaryTrees.println(bst);
    }

    public static void test2() {
        Integer data[] = new Integer[] { 85, 19, 69, 3, 7, 99, 95 };

        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(99);
        BinaryTrees.println(bst);
        bst.remove(85);
        BinaryTrees.println(bst);
        bst.remove(95);
        BinaryTrees.println(bst);
        // bst.add(14);
        // BinaryTrees.println(bst);
        // bst.add(15);
        // BinaryTrees.println(bst);
    }
}
