package Questions.LRUCache;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

class Node<K,V> {
    K key;
    V value;

    Node<K,V> prev, next;

    Node(K key, V value){
        this.key  = key;
        this.value = value;
    }

}

public class LRUCache<K, V> {

    private final int capacity;
    private final HashMap<K, Node<K, V>> cache;

    private  Node<K,V> head;
    private  Node<K,V> tail;

    private final ReentrantLock lock = new ReentrantLock();


    LRUCache(int capacity){

        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        this.head = new Node(null, null);
        this.tail = new Node(null, null);

        head.next = tail;
        tail.prev = head;
    }


    public V getItem(K key){

        lock.lock();

        try {
            Node<K, V> node = null;

            if(cache.containsKey(key)){
                node = cache.get(key);
            }

            if(node == null){
                return null;
            }

            moveToFront(node);
            return node.value;
        }
        finally{
            lock.unlock();
        }

    }

    public void moveToFront(Node<K,V> node){

       removeNode(node);
       addNode(node);

    }

    public void addNode(Node<K,V> node){

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node<K,V> node){
        node.prev.next = node.next;
        node.next.prev =  node.prev;
    }


    public void put(K key, V value){

        lock.lock();

      try{
        Node<K, V> node = null;

        if(cache.containsKey(key)){
            node = cache.get(key);
            moveToFront(node);
            return;
        }

        Node<K, V> newNode = new Node<>(key, value);

        cache.put(key, newNode);
        addNode(newNode);

        if(capacity == cache.size()){
            evictLRU();
        }   
      }

      finally{
        lock.unlock();
      }

    }

    private void evictLRU() {

        Node<K, V> lru = tail.prev;

        removeNode(lru);
        cache.remove(lru.key);
    }

    


    
}
