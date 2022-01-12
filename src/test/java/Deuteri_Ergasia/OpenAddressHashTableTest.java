package Deuteri_Ergasia;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;
import static org.testng.AssertJUnit.assertTrue;

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
                  assertTrue(HashMap.get(v) == (v + 1));
            }
      }

      @Test
      public void Test2() {

      }

      @Test
      public void Test3() {

      }
}