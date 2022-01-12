package Deuteri_Ergasia;

//region Imports & Utils
import java.util.*;
import static java.lang.String.valueOf;
//endregion

public class OpenAddressHashTable<K, V> implements Dictionary<K, V> {

      //region Fields
      private static final int INITIAL_SIZE = 4;
      private int current_size;
      private Entry<K, V>[] HashTable;
      int[][] BxUMatrix;
      //endregion

      //region Constructor
      @SuppressWarnings("unchecked")
      public OpenAddressHashTable() {
            this.current_size = 0;
            this.HashTable = new Entry[INITIAL_SIZE];
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
                  HashTable[i] = new Entry<>(i, key, value);
                  current_size++;
            }

            if (current_size == HashTable.length) {changeCapacity("Double");}
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

            if (current_size <= (HashTable.length / 4)) {changeCapacity("Half");}

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

      //region size
      @Override
      public int size() {return current_size;}
      //endregion

      //region clear
      @Override
      @SuppressWarnings("unchecked")
      public void clear() {
            current_size = 0;
            HashTable = new Entry[INITIAL_SIZE];
            BxUMatrix  = generateBxUMatrix();
      }
      //endregion

      //region changeCapacity
      @SuppressWarnings("unchecked")
      private void changeCapacity(String action) {
            Entry<K, V>[] oldHashTable = HashTable;

            int newLength = 0;
            if (action.equals("Double")) {
                  newLength = (HashTable.length * 2);
            } else if (action.equals("Half")) {
                  newLength = (HashTable.length / 2);
            }

            HashTable = new Entry[newLength];
            current_size = 0;

            BxUMatrix = generateBxUMatrix();

            for (Entry<K, V> Entry : oldHashTable) {
                  if (Entry != null) {
                        put(Entry.key, Entry.value);
                  }
            }
      }
      //endregion

      //region printHashTable
      public void printHashTable() {
            for (Entry<K, V> Entry : this.HashTable) {
                  if (Entry == null) {
                        System.out.print("null ");
                  } else {
                        System.out.print(Entry.getKey().toString() + ":" + Entry.getValue().toString() + " ");
                  }
            }
            System.out.println();
      }

      //endregion
      //endregion

      //region "Entry" Class
      static class Entry<K, V> implements Dictionary.Entry<K, V> {

            final int hash;
            K key;
            V value;

            public Entry(int hash, K key, V value) {
                  this.hash = hash;
                  this.key = key;
                  this.value = value;
            }

            @Override
            public K getKey() {return key;}

            @Override
            public V getValue() {return value;}
      }
      //endregion
}