package Deuteri_Ergasia;

import org.testng.annotations.Test;

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

            for (int i = 0; i < 100; i++) {
                  HashMap.put(i, (i+1));
            }

            int PreviousLength = HashMap.getLength();

            for (int i = 100; i < 200; i++) {
                  HashMap.put(i, (i+1));
            }

            int PreviousSize = HashMap.size();
            assertEquals(HashMap.getLength(), (PreviousLength * 2));
            assertFalse(HashMap.isEmpty());
            assertNotSame(HashMap.size(), HashMap.getLength());

            PreviousLength = HashMap.getLength();

            for (int i = 200; i < 400; i++) {
                  HashMap.put(i, (i+1));
            }

            assertNotSame(HashMap.size(), 0);

            HashMap.printHashTable();

            for (int i = 0; i < 400; i++) {
                  HashMap.remove(i);
            }
      }

      @Test
      public void Test3() {
            OpenAddressHashTable<Integer, Integer> HashMap = new OpenAddressHashTable<>();
      }
}