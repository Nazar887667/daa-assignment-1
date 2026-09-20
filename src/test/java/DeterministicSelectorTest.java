import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeterministicSelectorTest {

    @Test
    void testSimpleArray() {
        int[] array = {7, 2, 9, 1, 5};

        DeterministicSelector selector = new DeterministicSelector();

        assertEquals(1, selector.select(array.clone(), 0));
        assertEquals(2, selector.select(array.clone(), 1));
        assertEquals(5, selector.select(array.clone(), 2));
        assertEquals(7, selector.select(array.clone(), 3));
        assertEquals(9, selector.select(array.clone(), 4));
    }

    @Test
    void testDuplicates() {
        int[] array = {5, 2, 5, 3, 2, 5, 1};

        int[] expected = array.clone();
        Arrays.sort(expected);

        DeterministicSelector selector = new DeterministicSelector();

        for (int k = 0; k < array.length; k++) {
            assertEquals(
                    expected[k],
                    selector.select(array.clone(), k)
            );
        }
    }

    @Test
    void testSingleElement() {
        int[] array = {42};

        DeterministicSelector selector = new DeterministicSelector();

        assertEquals(42, selector.select(array, 0));
    }

    @Test
    void testAlreadySorted() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        DeterministicSelector selector = new DeterministicSelector();

        for (int k = 0; k < array.length; k++) {
            assertEquals(
                    array[k],
                    selector.select(array.clone(), k)
            );
        }
    }

    @Test
    void testReverseSorted() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        int[] expected = array.clone();
        Arrays.sort(expected);

        DeterministicSelector selector = new DeterministicSelector();

        for (int k = 0; k < array.length; k++) {
            assertEquals(
                    expected[k],
                    selector.select(array.clone(), k)
            );
        }
    }

    @Test
    void test100RandomArrays() {
        Random random = new Random(42);

        DeterministicSelector selector =
                new DeterministicSelector();

        for (int test = 0; test < 100; test++) {

            int size = random.nextInt(200) + 1;

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int[] expected = array.clone();
            Arrays.sort(expected);

            int k = random.nextInt(size);

            int actual =
                    selector.select(array.clone(), k);

            assertEquals(expected[k], actual);
        }
    }

    @Test
    void testInvalidK() {
        int[] array = {1, 2, 3};

        DeterministicSelector selector =
                new DeterministicSelector();

        assertThrows(
                IllegalArgumentException.class,
                () -> selector.select(array, -1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> selector.select(array, 3)
        );
    }

    @Test
    void testMetrics() {
        int[] array = {
                10, 4, 7, 2, 9,
                1, 6, 3, 8, 5
        };

        DeterministicSelector selector =
                new DeterministicSelector();

        selector.select(array, 5);

        assertEquals(true,
                selector.getComparisons() > 0);

        assertEquals(true,
                selector.getSwaps() > 0);

        assertEquals(true,
                selector.getMaxRecursionDepth() > 0);
    }
}