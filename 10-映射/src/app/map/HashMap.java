package app.map;

import java.util.LinkedList;
import java.util.Objects;

/**
 * HashMap
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size = 0;
    private Node<K, V>[] table;

    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
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

        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            // 表示没有根节点
            Node<K, V> newNode = new Node<K, V>(key, value, null);
            root = newNode;
            table[index] = root;
            this.size++;
            afterPut(newNode);
            return null;
        }

        // 哈希冲突
        // 添加到红黑树的节点上
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int compareResult = 0;
        K k1 = key;
        Node<K, V> result = null;
        int h1 = hash(k1);
        boolean searched = false;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compareResult = 1;
            } else if (h1 < h2) {
                compareResult = -1;
            } else if (Objects.equals(k1, k2)) {
                compareResult = 0;
            } else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
                    && (compareResult = ((Comparable) k1).compareTo(k2)) != 0) {
                // 同一种类具有可比较性,并且比较出了结果

            } else if (searched) {
                // 已经扫描过了
                compareResult = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                // 还没扫描过
                if ((node.right != null && (result = node(node.right, k1)) != null)
                        || (node.left != null && (result = node(node.left, k1)) != null)) {
                    searched = true;
                    node = result;
                    compareResult = 0;
                } else {
                    searched = true;
                    compareResult = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (compareResult > 0) {
                node = node.left;
            } else if (compareResult < 0) {
                node = node.right;
            } else {
                V old = node.value;
                node.key = key;
                node.value = value;
                return old;
            }

        } while (node != null);
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
        return node == null ? null : node.value;
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
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root != null) {
                queue.offer(root);
                while (!queue.isEmpty()) {
                    Node<K, V> node = queue.poll();
                    if (Objects.equals(value, node.value)) {
                        return true;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (isEmpty()) {
            return;
        }
        LinkedList<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root != null) {
                queue.offer(root);
                while (!queue.isEmpty()) {
                    Node<K, V> node = queue.poll();
                    if (visitor.visit(node.key, node.value)) {
                        return;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }

    }

    private void resize() {
        if (size / this.table.length < DEFAULT_LOAD_FACTOR)
            return;

        Node<K, V>[] old = table;
        table = new Node[this.table.length << 1];
        LinkedList<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            if (root != null) {
                queue.offer(root);
                while (!queue.isEmpty()) {
                    Node<K, V> node = queue.poll();

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    moveNode(node);
                }
            }
        }

    }

    private void moveNode(Node<K, V> newNode) {
        newNode.left = null;
        newNode.right = null;
        newNode.parent = null;
        red(newNode);

        int index = index(newNode.key);
        Node<K, V> root = table[index];
        if (root == null) {
            // 表示没有根节点
            table[index] = newNode;
            this.size++;
            afterPut(newNode);
            return;
        }

        // 哈希冲突
        // 添加到红黑树的节点上
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int compareResult = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        Node<K, V> result = null;
        boolean searched = false;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compareResult = 1;
            } else if (h1 < h2) {
                compareResult = -1;
            } else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
                    && (compareResult = ((Comparable) k1).compareTo(k2)) != 0) {
                // 同一种类具有可比较性,并且比较出了结果

            } else {
                // 已经扫描过了
                compareResult = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (compareResult > 0) {
                node = node.left;
            } else if (compareResult < 0) {
                node = node.right;
            }

        } while (node != null);

        if (compareResult > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        newNode.parent = parent;
        this.size++;
        afterPut(newNode);

    }

    private int index(K key) {

        int hashCode = hash(key);
        return hashCode & (table.length - 1); // 在 table.length 等于2的次幂的情况下等价于 hashCode%table.length
    }

    private int index(Node<K, V> node) {
        int hashCode = node.hash;
        return hashCode & (table.length - 1);
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
            table[index(grand)] = parent;
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

    private int compare(K k1, K k2, int h1, int h2) {

        // 比较哈希值
        int result = h1 - h2;
        if (result != 0) {
            return result;
        }

        // 哈希值相等
        // 比较equals
        if (Objects.equals(k1, k2)) {
            return 0;
        }

        // 哈希值相等，但不equals
        // 比较类名
        if (k1 != null && k2 != null) {
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) {
                return result;
            }
            // 同一种类型，并且具有比较性
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // 同一种类型，但不具备比较性
        // k1为空，k2不为空
        // k2为空，k1不为空

        return System.identityHashCode(k1) - System.identityHashCode(k2);
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
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;

            Node<K, V> result = null;
            // 先比较哈希值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // 同一种类具有可比较性
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                return result;
            } else {
                // 右测扫描过了找不到，去左边找。
                // 这里不同递归，减少递归调用
                node = node.left;
            }
        }

        return null;
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
            node.hash = s.hash;
            node = s;

        }

        // 删除node节点（node的度必为1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            // node的度为1
            replacement.parent = node.parent;
            if (node.parent == null) {
                // node的度为1，且是根节点
                table[index(node)] = replacement;
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
                table[index(node)] = null;
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

    private int hash(K k1) {
        int hash = k1 == null ? 0 : k1.hashCode();
        return hash ^ (hash >>> 16);
    }

    /**
     * Node<K,V>
     */
    public static class Node<K, V> {
        K key;
        V value;
        int hash;

        boolean color = RED;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            int hashCode = key == null ? 0 : key.hashCode();
            this.hash = hashCode ^ (hashCode >>> 16); // >>>无符号右移
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