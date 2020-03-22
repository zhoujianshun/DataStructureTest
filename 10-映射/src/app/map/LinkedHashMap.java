package app.map;

/**
 * LinkedHashMap
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {

    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        this.last = null;
        this.first = null;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }

        LinkedNode<K, V> node = this.first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) {
                break;
            }
            node = node.next;
        }
    }

    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {

        LinkedNode<K, V> node = new LinkedNode(key, value, parent);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    @Override
    protected void afterRemove(Node<K, V> willN, Node<K, V> removeN) {
        LinkedNode<K, V> node1 = ( LinkedNode<K, V>)willN;
        LinkedNode<K, V> node2 = ( LinkedNode<K, V>)removeN;
        if(node1 != node2){
            // 交换位置
            LinkedNode<K, V>temp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = temp;

            if(node1.prev == null){
                this.first = node1;
            }else{
                node1.prev.next = node1;
            }
            
            if(node2.prev == null){
                this.first = node2;
            }else{
                node2.prev.next = node2;
            }

            temp = node1.next;
            node1.next = node2.next;
            node2.next = temp;

            if(node1.next == null){
                this.last = node1;
            }else{
                node1.next.prev = node1;
            }
            
            if(node2.next == null){
                this.last = node2;
            }else{
                node2.next.prev = node2;
            }

        }
        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;

        if(prev == null){
            // 删除根节点
            this.first = next;
            if(next == null){
                this.last = prev;
            }else{
                next.prev = prev;
            }
           
        }else{
            prev.next = next;
            if(next == null){
                this.last = prev;
            }else{
                next.prev = prev;
            }
        }

      

    }

    /**
     * InnerLinkedHashMap
     */
    private static class LinkedNode<K, V> extends Node<K, V> {
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);

        }

    }
}