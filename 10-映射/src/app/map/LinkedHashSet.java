package app.map;

/**
 * LinkedHashSet
 */
public class LinkedHashSet<E> implements Set<E>{
    private LinkedHashMap<E, Object> hashMap = new LinkedHashMap<>();
    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void clear() {
        hashMap.clear();

    }

    @Override
    public boolean contains(E element) {
        return hashMap.containsKey(element);
    }

    @Override
    public void add(E element) {
        hashMap.put(element, null);

    }

    @Override
    public void remove(E element) {
        hashMap.remove(element);

    }

    @Override
    public void traversal(Visitor<E> visitor) {
        hashMap.traversal(new Map.Visitor<E,Object>() {

            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });

    }

    
    
}