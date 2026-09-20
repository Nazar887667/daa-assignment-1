public class Experiment {

    public static void main(String[] args) {

        System.out.println("DAA Experimental Results");
        System.out.println("========================");

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {

            System.out.println();
            System.out.println("Input size: " + n);

            runMergeSortExperiment("Random", InputGenerator.randomArray(n));
            runMergeSortExperiment("Sorted", InputGenerator.sortedArray(n));
            runMergeSortExperiment("Reverse-sorted", InputGenerator.reverseSortedArray(n));
            runMergeSortExperiment("Duplicate-heavy", InputGenerator.duplicateHeavyArray(n));

            runQuickSortExperiment("Random", InputGenerator.randomArray(n));
            runQuickSortExperiment("Sorted", InputGenerator.sortedArray(n));
            runQuickSortExperiment("Reverse-sorted", InputGenerator.reverseSortedArray(n));
            runQuickSortExperiment("Duplicate-heavy", InputGenerator.duplicateHeavyArray(n));
        }
    }

    private static void runMergeSortExperiment(String type, int[] array) {

        MergeSorter sorter = new MergeSorter();

        long startTime = System.nanoTime();

        sorter.sort(array);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        int recursionDepth = sorter.getMaxRecursionDepth();
        long comparisons = sorter.getComparisons();

        System.out.println(
                "Merge Sort - " +
                        type +
                        ": time = " + executionTime + " ns" +
                        ", recursion depth = " + recursionDepth +
                        ", comparisons = " + comparisons
        );
    }

    private static void runQuickSortExperiment(String type, int[] array) {

        QuickSorter sorter = new QuickSorter();

        long startTime = System.nanoTime();

        sorter.sort(array);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        int recursionDepth = sorter.getMaxRecursionDepth();
        long comparisons = sorter.getComparisons();
        long swaps = sorter.getSwaps();

        System.out.println(
                "Quick Sort - " +
                        type +
                        ": time = " + executionTime + " ns" +
                        ", recursion depth = " + recursionDepth +
                        ", comparisons = " + comparisons +
                        ", swaps = " + swaps
        );
    }
}