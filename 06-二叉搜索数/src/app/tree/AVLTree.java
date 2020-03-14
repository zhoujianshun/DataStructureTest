package app.tree;

import java.util.Comparator;

/**
 * AVLTree
 */
public class AVLTree<E> extends BST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {

        while ((node = node.parent) != null) {
            // node是否平衡
            if (isBalanced(node)) {
                // 平衡
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            // node是否平衡
            if (isBalanced(node)) {
                // 平衡
                updateHeight(node);
            } else {
                rebalance(node);
                // break;
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<E>(element, parent);
    }

    // 恢复平衡
    private void rebalance(Node<E> grand) {
        // grand 高度最低的失衡节点
        AVLNode<E> parent = (AVLNode<E>) ((AVLNode<E>) grand).tallerChild();
        AVLNode<E> node = (AVLNode<E>) ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL
                // 一次右转
                rotateRight(grand);
            } else {
                // LR
                // 先左再右
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                // RL
                // 先右再左
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR
                // 一次左转
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> node) {
        Node<E> grand = node;
        Node<E> parent = node.right;
        Node<E> child = parent.left;

        Node<E> gg = grand.parent;
        if (grand.isLeftChild()) {
            gg.left = parent;
        } else if (grand.isRightChild()) {
            gg.right = parent;
        } else {
            // 没有父节点
            root = parent;
        }
        parent.parent = gg;

        parent.left = grand;
        grand.parent = parent;

        grand.right = child;
        if (child != null) {
            child.parent = parent;
        }

        updateHeight(grand);
        updateHeight(parent);
    }

    private void rotateRight(Node<E> node) {
        Node<E> grand = node;
        Node<E> parent = node.left;
        Node<E> child = parent.right;

        Node<E> gg = grand.parent;
        if (grand.isLeftChild()) {
            gg.left = parent;
        } else if (grand.isRightChild()) {
            gg.right = parent;
        } else {
            // 没有父节点
            root = parent;
        }
        parent.parent = gg;

        parent.right = grand;
        grand.parent = parent;

        grand.left = child;
        if (child != null) {
            child.parent = grand;
        }

        updateHeight(grand);
        updateHeight(parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        AVLNode<E> avlNode = (AVLNode<E>) node;
        avlNode.updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);

        }

        public int balanceFactor() {
            int leftHeight = this.left != null ? ((AVLNode<E>) this.left).height : 0;
            int rightHeight = this.right != null ? ((AVLNode<E>) this.right).height : 0;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = this.left != null ? ((AVLNode<E>) this.left).height : 0;
            int rightHeight = this.right != null ? ((AVLNode<E>) this.right).height : 0;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = this.left != null ? ((AVLNode<E>) this.left).height : 0;
            int rightHeight = this.right != null ? ((AVLNode<E>) this.right).height : 0;

            if (leftHeight > rightHeight) {
                return left;
            } else if (rightHeight > leftHeight) {
                return right;
            } else {
                return isLeftChild() ? left : right;
            }
        }

        @Override
        public String toString() {
            String parent = "null";
            if (this.parent != null) {
                parent = this.parent.element.toString();
            }
            return element.toString() + "p(" + parent + ")_h(" + this.height + ")";
        }

    }
}