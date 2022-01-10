package Deuteri_Ergasia;

import java.util.Scanner;

public class App {
      static Scanner Input = new Scanner(System.in);

      public static void main(String[] args) {
            OpenAddressHashTable<String,Integer> Hash = new OpenAddressHashTable<>();

            Hash.printHashTable();

            String TextToCheck = Input.nextLine();
            int counter = 0;

            for (int i = 0; i < TextToCheck.length(); i++) {
                  char temp = TextToCheck.charAt(i);
                  for (int j = 0; j < TextToCheck.length(); j++) {
                        if (temp == TextToCheck.charAt(j)) {
                              counter++;
                        }
                  }

                  if (!(Hash.contains(Character.toString(TextToCheck.charAt(i))))) {
                        Hash.put(Character.toString(TextToCheck.charAt(i)), counter);
                  }

                  counter = 0;
            }

            Hash.printHashTable();
      }
}
