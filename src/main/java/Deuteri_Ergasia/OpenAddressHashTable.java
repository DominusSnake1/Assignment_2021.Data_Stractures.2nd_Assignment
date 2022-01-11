package Deuteri_Ergasia;

//region Imports & Utils
import com.sun.jdi.Value;

import java.util.*;
import static java.lang.String.valueOf;
//endregion

public class OpenAddressHashTable<K, V> implements Dictionary<K, V>, Dictionary.Entry<K, V> {

      //region Fields
      private static final int INITIAL_SIZE = 4;
      private int current_size;
      private HashNode<K, V>[] HashTable;
      int[][] BxUMatrix;
      //endregion

      //region Constructor
      @SuppressWarnings("unchecked")
      public OpenAddressHashTable() {
            this.current_size = 0;
            this.HashTable = new HashNode[INITIAL_SIZE];
            this.BxUMatrix  = generateBxUMatrix();
      }
      //endregion

      //region Methods
      //region newHashFunction
      private int newHashFunction(K key) {
            String keyInBinaryFormat = keyToBinaryString(key);
            int[][] CurrentBxUMatrix = BxUMatrix;
            StringBuilder temp = new StringBuilder();

            Integer [] CurrentKeyMatrixInBinaryFormat = new Integer[32];

            for(int j=0 ; j<keyInBinaryFormat.length() ; j++) {
                  CurrentKeyMatrixInBinaryFormat[j] = keyInBinaryFormat.charAt(j)-48;
            }

            Collections.reverse(Arrays.asList(CurrentKeyMatrixInBinaryFormat));

            for(int l = 0; l < 32 ; l++) {
                  if(CurrentKeyMatrixInBinaryFormat[l]==null) {
                        CurrentKeyMatrixInBinaryFormat[l]=0;
                  }
            }

            for (int i = 0; i < CurrentBxUMatrix.length; i++) {
                  temp.append(multiplyMatrixCells(CurrentBxUMatrix, CurrentKeyMatrixInBinaryFormat, i));
            }

            String Index = temp.toString();

            return (binaryStringToInteger(Index) % HashTable.length);
      }
      //endregion

      //region put
      @Override
      public void put(K key, V value) {
            int i = newHashFunction(key);

            if (contains(key)) {
                  while (!(HashTable[i].key.equals(key))) {
                        i = (i + 1) % HashTable.length;
                  }
                  HashTable[i].value = value;
            } else {
                  while (HashTable[i] != null) {
                        i = (i + 1) % HashTable.length;
                  }
                  HashTable[i] = new HashNode<>(i, key, value);
                  current_size++;
            }

            if (isFull()) {doubleCapacity();}
      }
      //endregion

      //region remove
      @Override
      public V remove(K key) throws Exception {
            if (isEmpty()) {
                  throw new Exception("Can't remove; Table is empty!");
            }

            if (!contains(key)) {
                  throw new Exception("No value for this key!");
            }

            int i = newHashFunction(key);
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
      //endregion

      //region get
      @Override
      public V get(K key) {
            int i = newHashFunction(key);

            if (!isEmpty()){
                  while (HashTable[i] == null) {
                        i = (i + 1) % HashTable.length;
                  }
            }

            while (HashTable[i] != null) {
                  if (HashTable[i].key.equals(key)) {
                        return HashTable[i].value;
                  }
                  i = (i + 1) % HashTable.length;
            }
            return null;
      }
      //endregion

      //region keyToBinaryString
      public String keyToBinaryString(K key) {return (Integer.toBinaryString((key.hashCode())));}
      //endregion

      //region binaryStringToInteger
      public int binaryStringToInteger(String binaryString) {return (Integer.parseInt(binaryString, 2));}
      //endregion

      //region random0or1
      public int random0or1() {
            Random random = new Random();

            return random.nextInt(2);
      }
      //endregion

      //region generateBxUMatrix
      public int[][] generateBxUMatrix() {
            int u = 32;
            assert HashTable != null;
            int b = (int) Math.sqrt(HashTable.length);
            int[][] BxUMatrix = new int[b][u];

            for (int i = 0; i < b; i++) {
                  for (int j = 0; j < u; j++) {
                        BxUMatrix[i][j] = random0or1();
                  }
            }

            return BxUMatrix;
      }
      //endregion

      //region multiplyMatrixCells
      public String multiplyMatrixCells(int[][] BxUMatrix, Integer[] X, int row) {
            int result = 0;

            for (int k = 0; k < X.length; k++) {
                  result = result + (BxUMatrix[row][k] * X[k]);
            }
            result %= 2;

            return valueOf(result);
      }
      //endregion

      //region contains
      @Override
      public boolean contains(K key) {return (get(key) != null);}
      //endregion

      //region isEmpty
      @Override
      public boolean isEmpty() {return (current_size == 0);}
      //endregion

      //region isQuarterFilled
      public boolean isQuarterFilled() {return (current_size <= (HashTable.length / 4));}
      //endregion

      //region isFull
      public boolean isFull() {return (current_size == HashTable.length);}
      //endregion

      //region size
      @Override
      public int size() {return current_size;}
      //endregion

      //region clear
      @Override
      public void clear() {current_size = 0;}
      //endregion

      //region Iterator
      @Override
      public Iterator<Dictionary.Entry<K, V>> iterator() {
            return null;
      }
      //endregion

      //region doubleCapacity
      @SuppressWarnings("unchecked")
      private void doubleCapacity() {
            HashNode<K, V>[] oldHashTable = HashTable;
            int newLength = (HashTable.length * 2);
            HashTable = new HashNode[newLength];
            current_size = 0;

            BxUMatrix = generateBxUMatrix();

            for (HashNode<K, V> HashNode : oldHashTable) {
                  if (HashNode != null) {
                        put(HashNode.key, HashNode.value);
                  }
            }
      }
      //endregion

      //region halfCapacity
      @SuppressWarnings("unchecked")
      private void halfCapacity() {
            HashNode<K, V>[] oldHashTable = HashTable;
            int newLength = (HashTable.length / 2);
            HashTable = new HashNode[newLength];
            current_size = 0;

            BxUMatrix = generateBxUMatrix();

            for (HashNode<K, V> hashNode : oldHashTable) {
                  if (hashNode != null) {
                        put(hashNode.key, hashNode.value);
                  }
            }
      }
      //endregion

      //region printHashTable
      public void printHashTable() {
            for (HashNode<K, V> Node : this.HashTable) {
                  if (Node == null) {
                        System.out.print("null ");
                  } else {
                        System.out.print(Node.key.toString() + ":" + Node.value.toString() + " ");
                  }
            }
            System.out.println();
      }

      @Override
      public K getKey() {
            return null;
      }

      @Override
      public V getValue() {
            return null;
      }
      //endregion

      //region HashNode
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
      //endregion
      //endregion

      //region "Entry" Class
      private static class Entry<K, V> implements Dictionary.Entry<K, V> {

            private K key;
            private V value;

            public Entry(K key, V value) {
                  this.key = key;
                  this.value = value;
            }

            @Override
            public K getKey() {
                  return key;
            }

            @Override
            public V getValue() {
                  return value;
            }
      }
      //endregion

      //region "HashIterator" Class
      private class HashIterator implements Iterator<Entry<K, V>> {

            private int i;
            private Iterator<Entry<K, V>> it;

            public HashIterator() {
                  i = 0;
                  //it = HashTable[0].iterator();
            }

            @Override
            public boolean hasNext() {
                  return false;
            }

            @Override
            public Entry<K, V> next() {
                  return null;
            }
      }
      //endregion
}
