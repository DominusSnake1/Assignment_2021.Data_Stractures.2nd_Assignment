package Deuteri_Ergasia;

import java.util.Iterator;

public interface Dictionary<K, V> extends Iterable<Dictionary.Entry<K, V>> {
      
      void put(K key, V value);

      V remove(K key);

      V get(K key);

      boolean contains(K key);

      boolean isEmpty();

      int size();

      void clear();

      Iterator<Entry<K, V>> iterator();

      interface Entry<K, V> {
            K getKey();

            V getValue();
      }
}

