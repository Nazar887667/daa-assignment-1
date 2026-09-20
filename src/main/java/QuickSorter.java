import java.util.Random;

public class QuickSorter {

    private final Random random;

    private int maxRecursionDepth;
    private long comparisons;
    private long swaps;

    public QuickSorter() {
        random = new Random();
    }

    public void sort(int[] array) {
        maxRecursionDepth = 0;
        comparisons = 0;
        swaps = 0;

        if (array == null || array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1, 1);
    }

    private void quickSort(int[] array, int left, int right, int depth) {
        while (left < right) {
            maxRecursionDepth = Math.max(maxRecursionDepth, depth);

            int pivotIndex = chooseRandomPivot(left, right);
            int pivotPosition = partition(array, left, right, pivotIndex);

            int leftSize = pivotPosition - left;
            int rightSize = right - pivotPosition;

            if (leftSize < rightSize) {
                if (left < pivotPosition - 1) {
                    quickSort(array, left, pivotPosition - 1, depth + 1);
                }

                left = pivotPosition + 1;
            } else {
                if (pivotPosition + 1 < right) {
                    quickSort(array, pivotPosition + 1, right, depth + 1);
                }

                right = pivotPosition - 1;
            }
        }

        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
    }

    private int chooseRandomPivot(int left, int right) {
        return left + random.nextInt(right - left + 1);
    }

    private int partition(int[] array, int left, int right, int pivotIndex) {
        swap(array, pivotIndex, right);

        int pivot = array[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            comparisons++;

            if (array[i] <= pivot) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right);

        return storeIndex;
    }

    private void swap(int[] array, int first, int second) {
        if (first == second) {
            return;
        }

        int temporary = array[first];
        array[first] = array[second];
        array[second] = temporary;

        swaps++;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }
}