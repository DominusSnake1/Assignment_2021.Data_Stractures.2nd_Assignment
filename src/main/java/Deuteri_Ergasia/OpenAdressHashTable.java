package Deuteri_Ergasia;

import java.util.Iterator;

public class OpenAdressHashTable<K, V> implements Dictionary<K, V> {

      private static final int INITIAL_SIZE = 16;

      private int current_size;
      private K[] keys;
      private V[] values;

      public OpenAdressHashTable() {
            current_size = 0;
            keys = (K[]) new Object[INITIAL_SIZE];
            values = (V[]) new Object[INITIAL_SIZE];
      }

      private int hash(K key) {return key.hashCode() % INITIAL_SIZE;}

      @Override
      public void put(K key, V value) {
            int temp = hash(key);
            int i = temp;

            do {
                  if (keys[i] == null) {
                        keys[i] = key;
                        values[i] = value;
                        current_size++;
                        continue;
                  }

                  if (keys[i].equals(key)) {
                        values[i] = value;
                        continue;
                  }

                  i = (i + 1) % INITIAL_SIZE;
            } while (i != temp);
      }

      @Override
      public V remove(K key) {
            if (!contains(key)) {
            }
            int i = hash(key);
            while (!key.equals(keys[i]))
            i = (i + 1) % INITIAL_SIZE;
            keys[i] = null;
            values[i] = null;

            for (i = (i + 1) % INITIAL_SIZE; keys[i] != null; i = (i + 1) % INITIAL_SIZE)
            {
                K temp1 = keys[i];
                V temp2 = values[i];
                keys[i] = null;
                values[i] = null;
                current_size--;
                put(temp1, temp2);
            }
            current_size--;
      }

      @Override
      public V get(K key) {
            int i = hash(key);

            while (keys[i] != null) {
                  if (keys[i].equals(key)) {
                        return values[i];
                  }
                  i = (i + 1) % INITIAL_SIZE;
            }
            return null;
      }

      @Override
      public boolean contains(K key) {return (get(key) != null);}

      @Override
      public boolean isEmpty() {return (current_size == 0);}

      @Override
      public int size() {return current_size;}

      @Override
      public void clear() {current_size = 0;}

      @Override
      public Iterator<Entry<K, V>> iterator() {
            return null;
      }
}
