package app.map;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * TreeMap
 */
public class TreeMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    private Node<K, V> root;

    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        checkKeyNotNull(key);
        if (this.root == null) {
            Node<K, V> newNode = new Node<K, V>(key, value, null);
            this.root = newNode;
            this.size++;
            afterPut(newNode);
            return null;
        }

        Node<K, V> parent = null;
        Node<K, V> current = this.root;
        int compareResult = 0;
        while (current != null) {
            compareResult = compare(key, current.key);
            parent = current;
            if (compareResult > 0) {
                current = current.right;
            } else if (compareResult < 0) {
                current = current.left;
            } else {
                V old = current.value;
                current.value = value;
                current.key = key;
                return old;
            }
        }
        Node<K, V> newNode = new Node<K, V>(key, value, parent);
        if (compareResult > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        this.size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {

        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (isEmpty()) {
            return false;
        }
        LinkedList<Node<K, V>> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valEquals(value, node.value)) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null)
            return;

        traversal(this.root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null) {
            return;
        }
        if (visitor.stop)
            return;
        traversal(node.left, visitor);
        if (visitor.stop)
            return;
        visitor.stop = visitor.visit(node.key, node.value);
        if (visitor.stop)
            return;
        traversal(node.right, visitor);
    }

    private boolean valEquals(V v1, V v2) {
        if (v1 == null) {
            return v2 == null;
        } else {
            return v1.equals(v2);
        }
    }

    private V remove(Node<K, V> node) {

        if (node == null) {
            return null;
        }
        V oldValue = node.value;
        size--;
        if (node.hasTwoChildren()) {

            Node<K, V> s = this.successor2(node);
            node.key = s.key;
            node.value = s.value;
            node = s;

        }

        // 删除node节点（node的度必为1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
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
        return oldValue;
    }

    private void afterRemove(Node<K, V> node, Node<K, V> replacement) {
        // 如果删除的节点是红色
        if (isRed(node)) {
            return;
        }

        // 用以取代node的节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点
        // 判断被删除的node
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
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
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有一个是红色节点，父节点要向下根兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent, null);
                }

            } else {
                // 兄弟节点至少有一个红色节点，向兄弟节点借元素

                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateRight(parent);
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
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有一个是红色节点，父节点要向下根兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent, null);
                }

            } else {
                // 兄弟节点至少有一个红色节点，向兄弟节点借元素

                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

    }

    private Node<K, V> predesessor2(Node<K, V> node) {
        // return predesessor(node, 0);
        if (node == null) {
            return node;
        }

        if (node.left != null) {
            Node<K, V> current = node.left;
            // 左子树存在，向下检索
            while (current.right != null) {
                current = current.right;
            }
            return current;
        } else {
            // 左子树不存在，父节点存在，向上检索

            // 向上检索
            // Node<K, V> current = node;
            // while (current.parent != null) {
            // if (current.parent.right == current) {
            // return current.parent;
            // } else {
            // current = current.parent;
            // }
            // }

            Node<K, V> current = node;
            while (current.parent != null && current.parent.left == current) {
                current = current.parent;
            }

            return current.parent;

        }
        // return null;
    }

    // 后置节点
    private Node<K, V> successor2(Node<K, V> node) {
        // return predesessor(node, 0);
        if (node == null) {
            return node;
        }

        if (node.right != null) {
            Node<K, V> current = node.right;
            // 左子树存在，向下检索
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            // 左子树不存在，父节点存在，向上检索
            // if (node.parent != null) {
            // 向上检索
            Node<K, V> current = node;
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

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
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

        Node<K, V> grand = parent.parent;
        Node<K, V> uncle = parent.sibling();
        if (isRed(uncle)) {
            // uncel是红色,上溢
            black(parent);
            black(uncle);

            red(grand);
            afterPut(grand);
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

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> grand = node;
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> grand = node;
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);

    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {

        Node<K, V> gg = grand.parent;
        parent.parent = gg;
        if (grand.isLeftChild()) {
            gg.left = parent;
        } else if (grand.isRightChild()) {
            gg.right = parent;
        } else {
            // 没有父节点
            root = parent;
        }
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;

    }

    private void checkKeyNotNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null.");
        }
    }

    private int compare(K k1, K k2) {
        if (this.comparator != null) {
            return this.comparator.compare(k1, k2);
        }
        return ((Comparable<K>) k1).compareTo(k2);
    }

    private Node<K, V> color(Node<K, V> node, Boolean color) {
        if (node == null) {
            return node;
        }

        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private Boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private Boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private Node<K, V> node(K key) {
        Node<K, V> node = this.root;
        while (node != null) {
            int cmp = compare(key, node.key);
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

    /**
     * Node<K,V>
     */
    public static class Node<K, V> {
        K key;
        V value;

        boolean color = RED;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
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

        public Node<K, V> sibling() {
            if (this.isLeftChild()) {
                return this.parent.right;
            } else if (this.isRightChild()) {
                return this.parent.left;
            } else {
                return null;
            }
        }

    }
}