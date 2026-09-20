import java.util.Random;

public class InputGenerator {

    public static int[] randomArray(int n) {
        Random random = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100000);
        }

        return array;
    }

    public static int[] sortedArray(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        return array;
    }

    public static int[] reverseSortedArray(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = n - i;
        }

        return array;
    }

    public static int[] duplicateHeavyArray(int n) {
        Random random = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(10);
        }

        return array;
    }
}