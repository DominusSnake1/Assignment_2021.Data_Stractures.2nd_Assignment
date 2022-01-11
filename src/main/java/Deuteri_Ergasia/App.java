package Deuteri_Ergasia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class App {
      static Scanner Input = new Scanner(System.in);

      public static void main(String[] args) throws Exception {
            OpenAddressHashTable<String,Integer> Hash = new OpenAddressHashTable<>();

            FileReader file = new FileReader("src/main/java/Deuteri_Ergasia/Test.txt");
            BufferedReader reader = new BufferedReader(file);

            String protasi = reader.readLine();

            String[] words = protasi.split("\\W");

            protasi = protasi.toLowerCase();

            while (protasi != null) {
                  for (String word : words) {
                        if (Hash.get(word) != null) {
                              Hash.put(word, (Hash.get(word) + 1));
                        } else {
                              Hash.put(word, 1);
                        }
                  }
                  protasi = reader.readLine();
            }

            reader.close();

            Hash.printHashTable();
      }
}
