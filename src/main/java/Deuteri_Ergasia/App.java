package Deuteri_Ergasia;
import java.util.Scanner;
public class App {
      static Scanner Input = new Scanner(System.in);
      public static void main(String[] args) {
            OpenAddressHashTable<String,Integer> Hash = new OpenAddressHashTable<>();

            Hash.printHashTable();

            String TextToCheck = Input.nextLine();
            int counter = 0;

            for(int i = 0 ; i < TextToCheck.length() ; i++)
            {
                  char temp = TextToCheck.charAt(i);
                  //System.out.println(temp);
                  for (int j = 0 ; j < TextToCheck.length() ; j++)
                  {
                        if(temp == TextToCheck.charAt(j))
                        {
                              counter++;
                        }
                  }
                  //System.out.println(counter);
                  Hash.put(String.valueOf(TextToCheck.charAt(i)), counter);
                  counter = 0;
            }
            Hash.printHashTable();
            /*Hash.put(Input.nextLine(),0);
            Hash.printHashTable();

            Hash.put("BB",1);
            Hash.printHashTable();

            Hash.put("CB",2);
            Hash.printHashTable();

            Hash.put("DB",5);
            Hash.printHashTable();

            Hash.put("E",2);
            Hash.printHashTable();

            Hash.put("F",5);
            Hash.printHashTable();

            Hash.put("G",5);
            Hash.printHashTable();

            Hash.put("H",2);
            Hash.printHashTable();

            Hash.put("I",2);
            Hash.printHashTable();

            Hash.put("J",5);
            Hash.printHashTable();*/

      }
}
