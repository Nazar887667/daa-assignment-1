public class Experiment {

    public static void main(String[] args) {

        System.out.println("DAA Experimental Results");
        System.out.println("========================");

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {

            System.out.println();
            System.out.println("Input size: " + n);

            runExperiment("Random", InputGenerator.randomArray(n));
            runExperiment("Sorted", InputGenerator.sortedArray(n));
            runExperiment("Reverse-sorted", InputGenerator.reverseSortedArray(n));
            runExperiment("Duplicate-heavy", InputGenerator.duplicateHeavyArray(n));
        }
    }

    private static void runExperiment(String type, int[] array) {

        MergeSorter sorter = new MergeSorter();

        long startTime = System.nanoTime();

        sorter.sort(array);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        int recursionDepth = sorter.getMaxRecursionDepth();
        long comparisons = sorter.getComparisons();

        System.out.println(
                type +
                        ": time = " + executionTime + " ns" +
                        ", recursion depth = " + recursionDepth +
                        ", comparisons = " + comparisons
        );
    }
}