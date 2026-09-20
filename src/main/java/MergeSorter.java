public class MergeSorter {
    private static final int INSERTION_SORT_CUTOFF = 16;
    private int[] auxiliary;
    private int maxRecursionDepth;
    private long comparisons;

    public void sort(int[] array) {
        if (array == null || array.length <= 1) {
            maxRecursionDepth = 0;
            comparisons = 0;
            return;
        }
        auxiliary = new int[array.length];
        maxRecursionDepth = 0;
        comparisons = 0;

        mergeSort(array, 0, array.length - 1, 1);
    }
    private void mergeSort(int[] array, int left, int right, int depth) {
        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
        if (right - left + 1 <= INSERTION_SORT_CUTOFF) {
            insertionSort(array, left, right);
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(array, left, middle, depth + 1);
        mergeSort(array, middle + 1, right, depth + 1);
        if (array[middle] <= array[middle + 1]) {
            return;
        }
        merge(array, left, middle, right);
    }
    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left) {
                comparisons++;

                if (array[j] <= key) {
                    break;
                }

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
    private void merge(int[] array, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            auxiliary[i] = array[i];
        }
        int i = left;
        int j = middle + 1;
        int k = left;
        while (i <= middle && j <= right) {
            comparisons++;

            if (auxiliary[i] <= auxiliary[j]) {
                array[k] = auxiliary[i];
                i++;
            } else {
                array[k] = auxiliary[j];
                j++;
            }

            k++;
        }
        while (i <= middle) {
            array[k] = auxiliary[i];
            i++;
            k++;
        }
        while (j <= right) {
            array[k] = auxiliary[j];
            j++;
            k++;
        }
    }
    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
    public long getComparisons() {
        return comparisons;
    }
}