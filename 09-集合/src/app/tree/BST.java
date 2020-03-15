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
            Node<E> newNode = createNode(element, null);
            this.root = newNode;
            this.size++;
            afterAdd(newNode);
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
        Node<E> newNode = createNode(element, parent);
        if (compareResult > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        this.size++;
        afterAdd(newNode);
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    protected void afterAdd(Node<E> node) {

    }

    protected void afterRemove(Node<E> node, Node<E> replacement) {

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

        // 删除node节点（node的度必为1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            // node的度为1
            replacement.parent = node.parent;
            if (node.parent == null) {
                // node的度为1，且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                // node == node.parent.right
                node.parent.right = replacement;
            }
            afterRemove(node, replacement);
        } else {
            if (node.parent == null) {
                // 删除的是根节点
                root = null;
                afterRemove(node, null);
            } else {
                // node 是叶子节点，但是不是root节点
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    // node.parent.right == node
                    node.parent.right = null;
                }

                afterRemove(node, null);
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