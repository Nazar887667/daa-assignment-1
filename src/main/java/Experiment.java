public class Experiment {

    public static void main(String[] args) {

        System.out.println("DAA Experimental Results");
        System.out.println("========================");

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {

            System.out.println();
            System.out.println("Input size: " + n);

            // Merge Sort
            runMergeSortExperiment(
                    "Random",
                    InputGenerator.randomArray(n)
            );

            runMergeSortExperiment(
                    "Sorted",
                    InputGenerator.sortedArray(n)
            );

            runMergeSortExperiment(
                    "Reverse-sorted",
                    InputGenerator.reverseSortedArray(n)
            );

            runMergeSortExperiment(
                    "Duplicate-heavy",
                    InputGenerator.duplicateHeavyArray(n)
            );

            // Quick Sort
            runQuickSortExperiment(
                    "Random",
                    InputGenerator.randomArray(n)
            );

            runQuickSortExperiment(
                    "Sorted",
                    InputGenerator.sortedArray(n)
            );

            runQuickSortExperiment(
                    "Reverse-sorted",
                    InputGenerator.reverseSortedArray(n)
            );

            runQuickSortExperiment(
                    "Duplicate-heavy",
                    InputGenerator.duplicateHeavyArray(n)
            );

            // Deterministic Select
            runSelectExperiment(
                    "Random",
                    InputGenerator.randomArray(n)
            );

            runSelectExperiment(
                    "Sorted",
                    InputGenerator.sortedArray(n)
            );

            runSelectExperiment(
                    "Reverse-sorted",
                    InputGenerator.reverseSortedArray(n)
            );

            runSelectExperiment(
                    "Duplicate-heavy",
                    InputGenerator.duplicateHeavyArray(n)
            );

            // Closest Pair
            runClosestPairExperiment(
                    "Random points",
                    InputGenerator.randomPoints(n)
            );
        }
    }

    private static void runMergeSortExperiment(
            String type,
            int[] array) {

        MergeSorter sorter = new MergeSorter();

        long startTime = System.nanoTime();

        sorter.sort(array);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        int recursionDepth =
                sorter.getMaxRecursionDepth();

        long comparisons =
                sorter.getComparisons();

        System.out.println(
                "Merge Sort - " +
                        type +
                        ": time = " +
                        executionTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons
        );
    }

    private static void runQuickSortExperiment(
            String type,
            int[] array) {

        QuickSorter sorter = new QuickSorter();

        long startTime = System.nanoTime();

        sorter.sort(array);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        int recursionDepth =
                sorter.getMaxRecursionDepth();

        long comparisons =
                sorter.getComparisons();

        long swaps =
                sorter.getSwaps();

        System.out.println(
                "Quick Sort - " +
                        type +
                        ": time = " +
                        executionTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons +
                        ", swaps = " +
                        swaps
        );
    }

    private static void runSelectExperiment(
            String type,
            int[] array) {

        DeterministicSelector selector =
                new DeterministicSelector();

        int k = array.length / 2;

        long startTime = System.nanoTime();

        int selectedValue =
                selector.select(array, k);

        long endTime = System.nanoTime();

        long executionTime =
                endTime - startTime;

        int recursionDepth =
                selector.getMaxRecursionDepth();

        long comparisons =
                selector.getComparisons();

        long swaps =
                selector.getSwaps();

        System.out.println(
                "Deterministic Select - " +
                        type +
                        ": time = " +
                        executionTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons +
                        ", swaps = " +
                        swaps +
                        ", selected value = " +
                        selectedValue
        );
    }

    private static void runClosestPairExperiment(
            String type,
            Point[] points) {

        ClosestPairSolver solver =
                new ClosestPairSolver();

        long startTime = System.nanoTime();

        double distance =
                solver.findClosestDistance(points);

        long endTime = System.nanoTime();

        long executionTime =
                endTime - startTime;

        int recursionDepth =
                solver.getMaxRecursionDepth();

        long comparisons =
                solver.getComparisons();

        System.out.println(
                "Closest Pair - " +
                        type +
                        ": time = " +
                        executionTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons +
                        ", distance = " +
                        distance
        );
    }
}