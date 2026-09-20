public class DeterministicSelector {

    private int maxRecursionDepth;
    private long comparisons;
    private long swaps;

    public int select(int[] array, int k) {
        maxRecursionDepth = 0;
        comparisons = 0;
        swaps = 0;

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("k is outside the array range");
        }

        return select(array, 0, array.length - 1, k, 1);
    }

    private int select(
            int[] array,
            int left,
            int right,
            int k,
            int depth) {

        maxRecursionDepth = Math.max(maxRecursionDepth, depth);

        while (true) {
            if (left == right) {
                return array[left];
            }

            int pivotValue = medianOfMedians(array, left, right);

            int[] equalRange = partition(
                    array,
                    left,
                    right,
                    pivotValue
            );

            int equalStart = equalRange[0];
            int equalEnd = equalRange[1];

            if (k < equalStart) {
                right = equalStart - 1;
                depth++;
                maxRecursionDepth = Math.max(maxRecursionDepth, depth);
            } else if (k > equalEnd) {
                left = equalEnd + 1;
                depth++;
                maxRecursionDepth = Math.max(maxRecursionDepth, depth);
            } else {
                return array[k];
            }
        }
    }

    private int medianOfMedians(int[] array, int left, int right) {
        int size = right - left + 1;

        if (size <= 5) {
            insertionSort(array, left, right);

            return array[left + size / 2];
        }

        int medianCount = 0;

        for (int groupStart = left; groupStart <= right; groupStart += 5) {
            int groupEnd = Math.min(groupStart + 4, right);

            insertionSort(array, groupStart, groupEnd);

            int medianIndex = groupStart + (groupEnd - groupStart) / 2;

            swap(array, left + medianCount, medianIndex);

            medianCount++;
        }

        int medianLeft = left;
        int medianRight = left + medianCount - 1;
        int medianK = medianLeft + medianCount / 2;

        return select(
                array,
                medianLeft,
                medianRight,
                medianK,
                1
        );
    }

    private int[] partition(
            int[] array,
            int left,
            int right,
            int pivotValue) {

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {
            comparisons++;

            if (array[current] < pivotValue) {
                swap(array, less, current);
                less++;
                current++;
            } else if (array[current] > pivotValue) {
                swap(array, current, greater);
                greater--;
            } else {
                current++;
            }
        }

        return new int[]{less, greater};
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