package app.set;

import app.tree.BinaryTree;
import app.tree.RBTree;
// import app.tree.BinaryTree.Visitor;

/**
 * TreeSet
 */
public class TreeSet<E> implements Set<E> {
    RBTree<E> tree = new RBTree<>();

    @Override
    public int size() {

        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();

    }

    @Override
    public boolean contains(E element) {

        return tree.contains(element);
    }

    @Override
    public void add(E element) {

        tree.add(element);
    }

    @Override
    public void remove(E element) {

        tree.remove(element);
    }

    @Override
    public void traversal(Set.Visitor<E> visitor) {
        tree.inorder(new BinaryTree.Visitor<E>() {

            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }

}