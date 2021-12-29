package Deuteri_Ergasia;

import java.util.Iterator;

public class OpenAddressHashTable<K, V> implements Dictionary<K, V> {

      private static final int INITIAL_SIZE = 4;

      private int current_size;
      private HashNode<K, V>[] HashTable;

      @SuppressWarnings("unchecked")
      public OpenAddressHashTable() {
            this.current_size = 0;
            this.HashTable = new HashNode[INITIAL_SIZE];
      }

      private int hash(K key) {return key.hashCode() % HashTable.length;}

      @Override
      public void put(K key, V value) {
            if (isFull()) {doubleCapacity();}

            int i = hash(key);

            HashNode<K, V> temp;

            while ((temp = HashTable[i]) != null) {

                  if (i == temp.hash && temp.key.equals(key)) {
                        HashTable[i].value = value;
                        return;
                  }
                  i = (i + 1) % HashTable.length;
            }

            HashTable[i] = new HashNode<>(hash(key), key, value);
            current_size++;


      }

      @Override
      public V remove(K key) {
            if (isEmpty()) {
                  System.out.println("Nothing to remove");
            }

            if (!contains(key)) {
                  System.out.println("No value for this key!");
                  System.exit(2);
            }

            int i = hash(key);
            while (HashTable[i] == null) {
                  i = (i + 1) % HashTable.length;
            }

            while (!key.equals(HashTable[i].key)) {
                  i = (i + 1) % HashTable.length;
            }

            V temp = get(key);

            HashTable[i] = null;
            current_size--;

            if (isQuarterFilled()) {halfCapacity();}

            return temp;
      }

      @Override
      public V get(K key) {
            int i = hash(key);

            while (HashTable[i] == null) {
                  i = (i + 1) % HashTable.length;
            }

            while (HashTable[i] != null) {
                  if (HashTable[i].key.equals(key)) {
                        return HashTable[i].value;
                  }
                  i = (i + 1) % HashTable.length;
            }
            return null;
      }

      @Override
      public boolean contains(K key) {return (get(key) != null);}

      @Override
      public boolean isEmpty() {return (current_size == 0);}

      public boolean isQuarterFilled() {return (current_size <= (HashTable.length / 4));}

      public boolean isFull() {return (current_size == HashTable.length);}

      @Override
      public int size() {return current_size;}

      @Override
      public void clear() {current_size = 0;}

      @Override
      public Iterator<Entry<K, V>> iterator() {return null;}

       @SuppressWarnings("unchecked")
      private void doubleCapacity() {
            HashNode<K, V>[] oldHashTable = HashTable;

            int newLength = (HashTable.length * 2);
            HashTable = new HashNode[newLength];

            current_size = 0;
            for (HashNode<K, V> HashNode : oldHashTable) {
                  if (HashNode != null) {
                        put(HashNode.key, HashNode.value);
                  }
            }
      }

      @SuppressWarnings("unchecked")
      private void halfCapacity() {
            HashNode<K, V>[] oldHashTable = HashTable;
            int newLength = (HashTable.length / 2);
            HashTable = new HashNode[newLength];
            current_size = 0;
            for (HashNode<K, V> hashNode : oldHashTable) {
                  if (hashNode != null) {
                        put(hashNode.key, hashNode.value);
                  }
            }
      }

      public void printHashTable() {
            for (int i = 0; i < this.HashTable.length; i++) {
                  if (this.HashTable[i] == null) {
                        System.out.print("null ");
                  } else {
                        System.out.print(this.HashTable[i].key.toString() + ":" + this.HashTable[i].value.toString() + " ");
                  }
            }
            System.out.println();
      }

      static class HashNode<K, V> {
            final int hash;
            K key;
            V value;

            HashNode(int hash, K key, V value) {
                  this.hash = hash;
                  this.key = key;
                  this.value = value;
            }
      }
}
