package app.tree;

import java.util.LinkedList;
import java.util.Stack;

import com.mj.printer.BinaryTreeInfo;

/**
 * BinaryTree
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    // 左序遍历
    public void preorderTraversal() {
        preorder2(this.root, new Visitor<E>() {

            @Override
            public boolean visit(E element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public void preorder(Visitor<E> vistor) {
        if (vistor == null) {
            return;
        }
        preorder(this.root, vistor);
    }

    public void preorder(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor.stop) {
            return;
        }

        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    // 前序遍历非递归
    public void preorder2(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (visitor.visit(node.element)) {
                return;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.left;
            if (node == null) {
                if (stack.empty()) {
                    return;
                }
                node = stack.pop();
            }
        }

    }

    // 中序遍历
    public void inorderTraversal() {
        inorder2(this.root, new Visitor<E>() {
            @Override
            public boolean visit(E element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public void inorder(Visitor<E> vistor) {
        if (vistor == null) {
            return;
        }
        inorder(this.root, vistor);
    }

    // 中序遍历递归
    public void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        inorder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    // 中序遍历父递归
    public void inorder2(Node<E> node, Visitor<E> visitor) {
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;

            } else if (stack.empty()) {
                return;
            } else {
                node = stack.pop();
                if (visitor.visit(node.element)) {
                    return;
                }
                node = node.right;
            }
        }
    }

    // 后序遍历
    public void posorderTraversal() {
        posorder2(this.root, new Visitor<E>() {

            @Override
            public boolean visit(E element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public void posorder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        posorder(this.root, visitor);
    }

    public void posorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        posorder(node.left, visitor);
        posorder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    // 后序遍历非递归
    public void posorder2(Node<E> root, Visitor<E> visitor) {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        Node<E> prev= null;
        while (!stack.isEmpty()) {
            Node<E> top = stack.peek();
            if (top.isLeaf()||(prev!=null&&prev.parent==top)) {
                prev = stack.pop();
                if (visitor.visit(prev.element)){
                    return;
                }

            }else{
                if(top.right!=null){
                    stack.push(top.right);
                }

                if(top.left!=null){
                    stack.push(top.left);
                }
            }
        }
    }

    // 层序遍历
    public void levelOrderTraversal() {
        this.levelOrder(new Visitor<E>() {

            @Override
            public boolean visit(E element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public void levelOrder(Visitor<E> visitor) {
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) {
                return;
            }
            ;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 树的高度，使用层序遍历实现
    public int height2() {
        if (this.root == null) {
            return 0;
        }

        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        int height = 1;
        int count = 1;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            count--;
            if (count == 0) {
                count = queue.size();
                height++;
            }
        }
        return --height;
    }

    // 获取树的高度，使用递归
    public int height() {
        return height(this.root);
    }

    public int height(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        return (right > left ? right : left) + 1;
    }

    // 是否是完全二叉树, 使用层序遍历
    // public boolean isComleteTree() {
    // if (this.root == null) {
    // return false;
    // }
    // LinkedList<Node<E>> queue = new LinkedList<>();
    // queue.offer(this.root);
    // boolean needLeaf = false;

    // while (!queue.isEmpty()) {
    // Node<E> current = queue.poll();

    // if (needLeaf && !current.isLeaf()) {
    // return false;
    // }
    // if (current.left != null) {
    // queue.offer(current.left);
    // }
    // if (current.right != null) {
    // queue.offer(current.right);
    // }

    // if (current.hasTwoChildren()) {

    // } else if (current.left == null && current.right != null) {
    // // 左节点不存在，右节点存在，不是完全二叉树
    // return false;
    // } else {
    // // 此后所有的节点不需要是页子节点
    // needLeaf = true;
    // }
    // }
    // return true;
    // }

    public boolean isComleteTree() {
        if (this.root == null) {
            return false;
        }
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        boolean needLeaf = false;

        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();

            if (needLeaf && !current.isLeaf()) {
                return false;
            }
            if (current.left != null) {
                queue.offer(current.left);
            } else {
                // 左边为空

                // 但是右不为空，则必定不是完全二叉树
                if (current.right != null) {
                    return false;
                }
            }

            if (current.right != null) {
                queue.offer(current.right);
            } else {
                // 右边为空

                // 1.current.right==null&&current.left != null
                // 2.current.right==null&&current.left == null
                needLeaf = true;
            }

        }
        return true;
    }

    // 二叉树反转
    public void invert() {
        invert(this.root);
    }

    public void invert(Node<E> node) {
        if (node == null) {
            return;
        }
        Node<E> temp = node.left;
        node.left = node.right;
        node.right = temp;
        invert(node.left);
        invert(node.right);
    }

    public void test() {
        Node<E> node = this.root.left.left.left;
        Node<E> pre = this.successor2(node);
        System.out.println((pre == null ? "null" : pre.element) + ": " + node.element);
    }

    // 前驱节点
    public Node<E> predesessor(Node<E> node) {
        // return predesessor(node, 0);

        if (node.left != null) {
            // 左子树存在，向下检索
            return predesessor(node.left, 1);
        } else {
            // 左子树不存在，父节点存在，向上检索
            if (node.parent != null) {
                // 向上检索
                return predesessor(node, -1);
            }
        }
        return null;
    }

    public Node<E> predesessor(Node<E> node, int flag) {
        if (node == null) {
            return null;
        }

        if (flag > 0) {
            if (node.right != null) {
                return predesessor(node.right, 1);
            }
            return node;
        } else {
            if (node.parent == null) {
                return null;
            } else {
                if (node.parent.right == node) {
                    return node.parent;
                } else {
                    return predesessor(node.parent, -1);
                }
            }
        }
        // return null;
    }

    public Node<E> predesessor2(Node<E> node) {
        // return predesessor(node, 0);
        if (node == null) {
            return node;
        }

        if (node.left != null) {
            Node<E> current = node.left;
            // 左子树存在，向下检索
            while (current.right != null) {
                current = current.right;
            }
            return current;
        } else {
            // 左子树不存在，父节点存在，向上检索

            // 向上检索
            // Node<E> current = node;
            // while (current.parent != null) {
            // if (current.parent.right == current) {
            // return current.parent;
            // } else {
            // current = current.parent;
            // }
            // }

            Node<E> current = node;
            while (current.parent != null && current.parent.left == current) {
                current = current.parent;
            }

            return current.parent;

        }
        // return null;
    }

    // 后置节点
    public Node<E> successor2(Node<E> node) {
        // return predesessor(node, 0);
        if (node == null) {
            return node;
        }

        if (node.right != null) {
            Node<E> current = node.right;
            // 左子树存在，向下检索
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            // 左子树不存在，父节点存在，向上检索
            // if (node.parent != null) {
            // 向上检索
            Node<E> current = node;
            while (current.parent != null) {
                if (current.parent.left == current) {
                    return current.parent;
                } else {
                    current = current.parent;
                }
            }

            // }

        }
        return null;
    }

    @Override
    public String toString() {
        // 使用前序遍历打印
        StringBuilder sb = new StringBuilder();
        toString(this.root, sb, "");
        return sb.toString();
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }

        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "--");
        toString(node.right, sb, prefix + "--");
    }

    /**
     * InnerBinarySearchTree
     */
    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);

    }

    /**
     * InnerBinarySearchTree
     */
    protected static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        public boolean isLeftChild() {
            if (this.parent != null && this == this.parent.left) {
                return true;
            }
            return false;
        }

        public boolean isRightChild() {
            return this.parent != null && this == this.parent.right;
        }

        public Node<E> sibling() {
            if (this.isLeftChild()) {
                return this.parent.right;
            } else if (this.isRightChild()) {
                return this.parent.left;
            } else {
                return null;
            }
        }

    }

    @Override
    public Object root() {

        return this.root;
    }

    @Override
    public Object left(Object node) {

        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {

        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element.toString();
        // Node<E> p = ((Node<E>) node).parent;
        // return "p(" + (p != null ? p.element : "null") + ")_" + ((Node<E>)
        // node).element;
    }
}