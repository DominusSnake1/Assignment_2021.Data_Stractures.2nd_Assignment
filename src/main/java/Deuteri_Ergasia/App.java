package Deuteri_Ergasia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class App {
      static Scanner Input = new Scanner(System.in);

      public static void main(String[] args) throws Exception {
            OpenAddressHashTable<String,Integer> Hash = new OpenAddressHashTable<>();
            File file = new File("src/main/java/Deuteri_Ergasia/Test.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String protasi;

            while ((protasi = reader.readLine()) != null)
            {
                  protasi = protasi.toLowerCase();
                  String[] words = protasi.split("\\W");
                  for(String word : words)
                  {
                        if(Hash.get(word) != null)
                        {
                              Hash.put(word, (Hash.get(word) + 1) );
                        }
                        else
                        {
                              Hash.put(word, 1);
                        }
                  }
            }
            Hash.printHashTable();
            reader.close();


      }
}
