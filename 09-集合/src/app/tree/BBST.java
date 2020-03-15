package app.tree;

import java.util.Comparator;

/**
 * BBST
 */
public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(Node<E> node) {
        Node<E> grand = node;
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
       

        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> node) {
        Node<E> grand = node;
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);

    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
      

        Node<E> gg = grand.parent;
        parent.parent = gg;
        if (grand.isLeftChild()) {
            gg.left = parent;
        } else if (grand.isRightChild()) {
            gg.right = parent;
        } else {
            // 没有父节点
            root = parent;
        }
        if(child!=null){
            child.parent = grand;
        }
        grand.parent = parent;

      
    }
}