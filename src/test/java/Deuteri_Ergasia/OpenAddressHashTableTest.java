package Deuteri_Ergasia;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static org.testng.AssertJUnit.*;

public class OpenAddressHashTableTest {

      private static final int SIZE = 10000;

      @Test
      public void Test1() {
            OpenAddressHashTable<Integer, Integer> HashMap = new OpenAddressHashTable<>();
            ArrayList<Integer> values = new ArrayList<>();

            Random rng = new Random(2022);

            for (int i = 0; i < SIZE; i++) {
                  int n = rng.nextInt(100);
                  values.add(n);
                  HashMap.put(n, (n+1));
            }

            for (Integer v : values) {
                  assertEquals((int) HashMap.get(v), (v + 1));
            }
      }

      @Test
      public void Test2() throws Exception {
            OpenAddressHashTable<Integer, Integer> HashMap = new OpenAddressHashTable<>();

            assertTrue(HashMap.isEmpty());

            for (int i = 0; i < 200; i++) {
                  HashMap.put(i, (i+1));
            }

            int PreviousLength = HashMap.getLength();

            for (int i = 100; i < 400; i++) {
                  HashMap.put(i, (i+1));
            }

            assertEquals(HashMap.getLength(), (PreviousLength * 2));
            PreviousLength = HashMap.getLength();
            assertEquals(HashMap.size(),400);
            for (int i = 0; i < 300; i++) {
                  HashMap.remove(i);
            }
            assertEquals(HashMap.size(),100);
            assertEquals(HashMap.getLength(),(PreviousLength/2));
            for(int i = 300; i<400 ; i++)
            {
                  HashMap.remove(i);
            }
            assertTrue(HashMap.isEmpty());
      }

      @Test
      public void Test3() throws IOException {
            OpenAddressHashTable<String, Integer> HashMap = new OpenAddressHashTable<>();
            File file = new File("src/main/java/Deuteri_Ergasia/TextDocument.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String sentence;

            while ((sentence = reader.readLine()) != null) {
                  sentence = sentence.toLowerCase();
                  String[] words = sentence.split("\\W");
                  for (String word : words) {
                        if (HashMap.get(word) != null) {
                              HashMap.put(word, (HashMap.get(word) + 1) );
                        } else {
                              HashMap.put(word, 1);
                        }
                  }
            }
            reader.close();
            assertEquals(37, (int) HashMap.get("never"));
            assertEquals(39, (int) HashMap.get("gonna"));
            assertEquals(6, (int) HashMap.get("cry"));

      }
}