package app;

import java.util.HashMap;

/**
 * Trie
 */
public class Trie<V> {
    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value) {

        if (root == null) {
            root = new Node<>(null, null);
            root.children = new HashMap<>();
        }

        Node<V> node = root;
        for (int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            Node<V> child = node.children.get(ch);
            if (child == null) {
                Node<V> newNode = new Node<>(ch, node);
                newNode.children = new HashMap<>();
                node.children.put(ch, newNode);
                node = newNode;
            } else {
                node = child;
            }
        }
        V old = node.value;
        node.word = true;
        node.value = value;
        size++;
        return old;
    }

    public V remove(String key) {
        if (key == null) {
            return null;
        }

        if (isEmpty()) {
            return null;
        }

        Node<V> node = node(key);
        if (node == null || !node.word) {
            return null;
        }

        V value = node.value;
        if (node.children != null && node.children.size() > 0) {
            node.word = false;
            node.value = null;
        } else {
             node = node.parent;
            while (node != null) {
                node.parent.children.remove(node.ch);
                if (!node.children.isEmpty()){
                    break;
                }
                
                node = node.parent;
            }
        }

        return value;
    }

    public boolean startWith(String key) {
        return node(key) != null;
    }

    private Node<V> node(String key) {

        if (root == null) {
            root = new Node<>(null, null);
            root.children = new HashMap<>();
        }

        Node<V> node = root;
        for (int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            node = node.children.get(ch);
            if (node == null) {
                return null;
            }

        }
        return node;
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        Boolean word;
        Node<V> parent;
        Character ch;

        public Node(Character ch, Node<V> parent) {
            this.ch = ch;
            this.parent = parent;
        }

    }

}