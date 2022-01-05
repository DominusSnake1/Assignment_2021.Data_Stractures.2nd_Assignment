package Deuteri_Ergasia;

public class App {

      public static void main(String[] args) {
            OpenAddressHashTable<Integer,Integer> Hash = new OpenAddressHashTable<>();

            Hash.printHashTable();

            Hash.put(0,0);
            Hash.printHashTable();

            Hash.put(0,1);
            Hash.printHashTable();

            Hash.put(1,2);
            Hash.printHashTable();

            Hash.put(4,5);
            Hash.printHashTable();

            Hash.put(10,2);
            Hash.printHashTable();

            Hash.put(26,5);
            Hash.printHashTable();

            Hash.put(1532,5);
            Hash.printHashTable();

            Hash.put(23,2);
            Hash.printHashTable();

            Hash.put(32,2);
            Hash.printHashTable();

            Hash.put(49,5);
            Hash.printHashTable();

      }
}
