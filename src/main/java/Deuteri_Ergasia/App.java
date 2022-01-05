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

            Hash.put(15,5);
            Hash.printHashTable();

            Hash.put(20,2);
            Hash.printHashTable();

            Hash.put(25,5);
            Hash.printHashTable();

            Hash.put(30,2);
            Hash.printHashTable();

            Hash.put(35,5);
            Hash.printHashTable();

            Hash.remove(25);
            Hash.printHashTable();

            Hash.remove(1);
            Hash.printHashTable();

            Hash.remove(30);
            Hash.printHashTable();

            Hash.remove(10);
            Hash.printHashTable();
      }
}
