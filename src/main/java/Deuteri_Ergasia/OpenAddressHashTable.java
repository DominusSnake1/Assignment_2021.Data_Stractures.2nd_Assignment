package Deuteri_Ergasia;

//region Imports
import java.util.*;

import static java.lang.String.valueOf;
//endregion

public class OpenAddressHashTable<K, V> implements Dictionary<K, V> {

      private static final int INITIAL_SIZE = 4;

      private int current_size;
      private HashNode<K, V>[] HashTable;
      int[][] BxUMatrix;

      @SuppressWarnings("unchecked")
      public OpenAddressHashTable() {
            this.current_size = 0;
            this.HashTable = new HashNode[INITIAL_SIZE];
            this.BxUMatrix  = generateBxUMatrix();
      }

      //private int hash(K key) {return key.hashCode() % HashTable.length;}
      
      private int newHashFunction(K key)
      {
            String keyInBinaryFormat = keyToBinaryString(key);
            int[][] CurrentBinaryMMatrix = BxUMatrix;
            String Index = "";


            Integer [] CurrentKeyMatrixInBinaryFormat = new Integer[32];
            System.out.println(keyInBinaryFormat);
            for(int j=0 ; j<keyInBinaryFormat.length() ; j++)
            {
                  CurrentKeyMatrixInBinaryFormat[j] = keyInBinaryFormat.charAt(j)-48;
            }
            Collections.reverse(Arrays.asList(CurrentKeyMatrixInBinaryFormat));
            for(int l = 0; l < 32 ; l++)
            {
                  if(CurrentKeyMatrixInBinaryFormat[l]==null)
                  {
                        CurrentKeyMatrixInBinaryFormat[l]=0;
                  }
            }
            //System.out.println("Array after is : " + Arrays.toString(CurrentKeyMatrixInBinaryFormat));
            //System.out.println("Our M matrix is: " + Arrays.deepToString(CurrentBinaryMMatrix));
            for (int i = 0; i < CurrentBinaryMMatrix.length; i++)
            {
                  Index = Index + multiplyMatrixCells(CurrentBinaryMMatrix, CurrentKeyMatrixInBinaryFormat, i);
            }

            //System.out.println("Index is: " + binaryStringToInteger(Index) + " And key you are inserting is: " + key.hashCode());

            return (binaryStringToInteger(Index) % HashTable.length);
      }

      @Override
      public void put(K key, V value) {
            if (isFull()) {doubleCapacity();}

            int i = newHashFunction(key);
            int hash = i;

            HashNode<K, V> temp;

            while ((temp = HashTable[i]) != null) {

                  if (i == temp.hash && temp.key.equals(key)) {
                        HashTable[i].value = value;
                        return;
                  }
                  i = (i + 1) % HashTable.length;
            }

            HashTable[i] = new HashNode<>(hash, key, value);
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

      @Override
      public V get(K key) {
            int i = newHashFunction(key);

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

      public String keyToBinaryString(K key) {
            return (Integer.toBinaryString((key.hashCode())));
      }

      public int binaryStringToInteger(String binaryString) {
            return (Integer.parseInt(binaryString));
      }

      public int random0or1() {
            Random random = new Random();

            return random.nextInt(2);
      }

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

      public String multiplyMatrixCells(int[][] BxUMatrix, Integer[] X, int row)
      {
            int result = 0;

            for (int k = 0; k < X.length; k++)
            {
                  result = result + (BxUMatrix[row][k] * X[k]);
            }
            result %= 2;

            //System.out.println("The result is: " + result);

            return valueOf(result);
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
      public Iterator<Deuteri_Ergasia.Dictionary.Entry<K, V>> iterator() {
            return null;
      }

      @SuppressWarnings("unchecked")
      private void doubleCapacity() {
            HashNode<K, V>[] oldHashTable = HashTable;
            BxUMatrix = generateBxUMatrix();

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
            BxUMatrix = generateBxUMatrix();

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
            for (HashNode<K, V> Node : this.HashTable) {
                  if (Node == null) {
                        System.out.print("null ");
                  } else {
                        System.out.print(Node.key.toString() + ":" + Node.value.toString() + " ");
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
