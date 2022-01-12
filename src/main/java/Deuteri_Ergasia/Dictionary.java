package Deuteri_Ergasia;

public interface Dictionary<K, V> {
      
      void put(K key, V value);

      V remove(K key) throws Exception;

      V get(K key);

      boolean contains(K key);

      boolean isEmpty();

      int size();

      void clear();

      interface Entry<K, V> {
            K getKey();

            V getValue();
      }
}

