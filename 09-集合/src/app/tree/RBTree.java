package app.tree;

import java.util.Comparator;

/**
 * RBTree
 */
public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent == null) {
            // 添加的是根节点，或者上溢到根节点
            // 染色
            black(node);
            return;
        }

        if (isBlack(parent)) {
            // 如果父节点为黑色不用做处理
            return;
        }

        Node<E> grand = parent.parent;
        Node<E> uncle = parent.sibling();
        if (isRed(uncle)) {
            // uncel是红色,上溢
            black(parent);
            black(uncle);

            red(grand);
            afterAdd(grand);
            return;
        } else {
            // uncel不是红色

            if (parent.isLeftChild()) {
                if (node.isLeftChild()) {
                    // LL
                    black(parent);
                    red(grand);
                    rotateRight(grand);
                } else {
                    // LR
                    black(node);
                    red(grand);
                    rotateLeft(parent);
                    rotateRight(grand);

                }
            } else {
                if (node.isLeftChild()) {
                    // RL
                    black(node);
                    red(grand);
                    rotateRight(parent);
                    rotateLeft(grand);

                } else {
                    // RR
                    black(parent);
                    red(grand);
                    rotateLeft(grand);
                }
            }

        }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 如果删除的节点是红色
        if (isRed(node)) {
            return;
        }

        // 用以取代node的节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点
        // 判断被删除的node
        boolean left = parent.left == null||node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            // 被删除节点在左边，兄弟节点在右边

            // 兄弟节点是红色
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点为黑色
            if(isBlack(sibling.left)&&isBlack(sibling.right)){
                //兄弟节点没有一个是红色节点，父节点要向下根兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent, null);
                }

            }else{
                // 兄弟节点至少有一个红色节点，向兄弟节点借元素

                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateRight(parent );
            }
        } else {
            // 被删除节点在右边，兄弟节点在左边

            // 兄弟节点是红色
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点为黑色
            if(isBlack(sibling.left)&&isBlack(sibling.right)){
                //兄弟节点没有一个是红色节点，父节点要向下根兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent, null);
                }

            }else{
                // 兄弟节点至少有一个红色节点，向兄弟节点借元素

                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent );
            }
        }

    }

    private Node<E> color(Node<E> node, Boolean color) {
        if (node == null) {
            return node;
        }

        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private Boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private Boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * InnerRBTree
     */
    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }

            return str + element.toString();
        }
    }

}