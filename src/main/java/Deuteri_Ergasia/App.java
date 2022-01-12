package Deuteri_Ergasia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class App {
      public static void main(String[] args) throws Exception {
            OpenAddressHashTable<String,Integer> Hash = new OpenAddressHashTable<>();
            File file = new File("src/main/java/Deuteri_Ergasia/TextDocument.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String sentence;

            Hash.printHashTable();

            System.out.println("Current size: " + Hash.size());

            while ((sentence = reader.readLine()) != null) {
                  sentence = sentence.toLowerCase();
                  String[] words = sentence.split("\\W");
                  for (String word : words) {
                        if (Hash.get(word) != null) {
                              Hash.put(word, (Hash.get(word) + 1) );
                        } else {
                              Hash.put(word, 1);
                        }
                  }
            }
            reader.close();

            Hash.printHashTable();

            System.out.println("Current size is: " + Hash.size());
            Hash.put("TEST", 0);
            System.out.println("Current size is: " + Hash.size());
            System.out.println("Contains key <<TEST>>: " + Hash.contains("TEST"));
            System.out.println("Get value of Entry with key <<TEST>>: " + Hash.get("TEST"));

            Hash.printHashTable();

            System.out.println("Current size is: " + Hash.size());
            System.out.println("Removed Entry with key<<TEST>> and value: " + Hash.remove("TEST"));
            System.out.println("Current size is: " + Hash.size());

            Hash.printHashTable();

            System.out.println("Clearing HashTable..."); Hash.clear();

            Hash.printHashTable();
      }
}
