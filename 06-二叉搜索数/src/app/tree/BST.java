package app.tree;

import java.util.Comparator;


/**
 * BinarySearchTree
 */
public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

   

    public void add(E element) {
        checkElementNotNull(element);
        if (this.root == null) {
            Node<E> newNode = new Node<>(element, null);
            this.root = newNode;
            this.size++;
            return;
        }

        Node<E> parent = null;
        Node<E> current = this.root;
        int compareResult = 0;
        while (current != null) {
            compareResult = compare(element, current.element);
            parent = current;
            if (compareResult > 0) {
                current = current.right;
            } else if (compareResult < 0) {
                current = current.left;
            } else {
                current.element = element;
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if (compareResult > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        this.size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    
    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {

        if (node == null) {
            return;
        }

        size--;
        if (node.hasTwoChildren()) {

            Node<E> s = this.successor2(node);
            node.element = s.element;
            node = s;

        }

        Node<E> child = node.left != null ? node.left : node.right;

        if (this.root == node) {
            this.root = child;
            child.parent = null;
        } else {
            if (child != null) {
                child.parent = node.parent;
            }
            if (node.parent.left == node) {
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        }
    }

    private Node<E> node(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


    private void checkElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null.");
        }
    }

    private int compare(E element1, E element2) {
        if (this.comparator != null) {
            return this.comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

}