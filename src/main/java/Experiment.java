import java.io.FileWriter;
import java.io.IOException;

public class Experiment {
    private static final int RUNS = 5;

    public static void main(String[] args) {

        System.out.println("DAA Experimental Results");
        System.out.println("========================");

        int[] sizes = {100, 1000, 5000};

        try (FileWriter writer = new FileWriter("results.csv")) {

            writer.write(
                    "algorithm,input_type,input_size,average_time_ns," +
                            "recursion_depth,comparisons,swaps\n"
            );

            for (int n : sizes) {

                System.out.println();
                System.out.println("Input size: " + n);

                runMergeSortExperiment(
                        "Random",
                        n,
                        InputGenerator.randomArray(n),
                        writer
                );

                runMergeSortExperiment(
                        "Sorted",
                        n,
                        InputGenerator.sortedArray(n),
                        writer
                );

                runMergeSortExperiment(
                        "Reverse-sorted",
                        n,
                        InputGenerator.reverseSortedArray(n),
                        writer
                );

                runMergeSortExperiment(
                        "Duplicate-heavy",
                        n,
                        InputGenerator.duplicateHeavyArray(n),
                        writer
                );

                runQuickSortExperiment(
                        "Random",
                        n,
                        InputGenerator.randomArray(n),
                        writer
                );

                runQuickSortExperiment(
                        "Sorted",
                        n,
                        InputGenerator.sortedArray(n),
                        writer
                );

                runQuickSortExperiment(
                        "Reverse-sorted",
                        n,
                        InputGenerator.reverseSortedArray(n),
                        writer
                );

                runQuickSortExperiment(
                        "Duplicate-heavy",
                        n,
                        InputGenerator.duplicateHeavyArray(n),
                        writer
                );

                runSelectExperiment(
                        "Random",
                        n,
                        InputGenerator.randomArray(n),
                        writer
                );

                runSelectExperiment(
                        "Sorted",
                        n,
                        InputGenerator.sortedArray(n),
                        writer
                );

                runSelectExperiment(
                        "Reverse-sorted",
                        n,
                        InputGenerator.reverseSortedArray(n),
                        writer
                );

                runSelectExperiment(
                        "Duplicate-heavy",
                        n,
                        InputGenerator.duplicateHeavyArray(n),
                        writer
                );

                runClosestPairExperiment(
                        "Random points",
                        n,
                        InputGenerator.randomPoints(n),
                        writer
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runMergeSortExperiment(
            String type,
            int n,
            int[] array,
            FileWriter writer) throws IOException {

        long totalTime = 0;
        int recursionDepth = 0;
        long comparisons = 0;

        for (int i = 0; i < RUNS; i++) {

            int[] testArray = array.clone();
            MergeSorter sorter = new MergeSorter();

            long startTime = System.nanoTime();

            sorter.sort(testArray);

            long endTime = System.nanoTime();

            totalTime += endTime - startTime;

            recursionDepth = Math.max(
                    recursionDepth,
                    sorter.getMaxRecursionDepth()
            );

            comparisons = Math.max(
                    comparisons,
                    sorter.getComparisons()
            );
        }

        long averageTime = totalTime / RUNS;

        writer.write(
                "Merge Sort," +
                        type + "," +
                        n + "," +
                        averageTime + "," +
                        recursionDepth + "," +
                        comparisons + "," +
                        0 + "\n"
        );

        System.out.println(
                "Merge Sort - " +
                        type +
                        ": average time = " +
                        averageTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons
        );
    }

    private static void runQuickSortExperiment(
            String type,
            int n,
            int[] array,
            FileWriter writer) throws IOException {

        long totalTime = 0;
        int recursionDepth = 0;
        long comparisons = 0;
        long swaps = 0;

        for (int i = 0; i < RUNS; i++) {

            int[] testArray = array.clone();
            QuickSorter sorter = new QuickSorter();

            long startTime = System.nanoTime();

            sorter.sort(testArray);

            long endTime = System.nanoTime();

            totalTime += endTime - startTime;

            recursionDepth = Math.max(
                    recursionDepth,
                    sorter.getMaxRecursionDepth()
            );

            comparisons = Math.max(
                    comparisons,
                    sorter.getComparisons()
            );

            swaps = Math.max(
                    swaps,
                    sorter.getSwaps()
            );
        }

        long averageTime = totalTime / RUNS;

        writer.write(
                "Quick Sort," +
                        type + "," +
                        n + "," +
                        averageTime + "," +
                        recursionDepth + "," +
                        comparisons + "," +
                        swaps + "\n"
        );

        System.out.println(
                "Quick Sort - " +
                        type +
                        ": average time = " +
                        averageTime +
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
            int n,
            int[] array,
            FileWriter writer) throws IOException {

        long totalTime = 0;
        int recursionDepth = 0;
        long comparisons = 0;
        long swaps = 0;
        int selectedValue = 0;

        for (int i = 0; i < RUNS; i++) {

            int[] testArray = array.clone();

            DeterministicSelector selector =
                    new DeterministicSelector();

            int k = testArray.length / 2;

            long startTime = System.nanoTime();

            selectedValue = selector.select(testArray, k);

            long endTime = System.nanoTime();

            totalTime += endTime - startTime;

            recursionDepth = Math.max(
                    recursionDepth,
                    selector.getMaxRecursionDepth()
            );

            comparisons = Math.max(
                    comparisons,
                    selector.getComparisons()
            );

            swaps = Math.max(
                    swaps,
                    selector.getSwaps()
            );
        }

        long averageTime = totalTime / RUNS;

        writer.write(
                "Deterministic Select," +
                        type + "," +
                        n + "," +
                        averageTime + "," +
                        recursionDepth + "," +
                        comparisons + "," +
                        swaps + "\n"
        );

        System.out.println(
                "Deterministic Select - " +
                        type +
                        ": average time = " +
                        averageTime +
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
            int n,
            Point[] points,
            FileWriter writer) throws IOException {

        long totalTime = 0;
        int recursionDepth = 0;
        long comparisons = 0;
        double distance = 0;

        for (int i = 0; i < RUNS; i++) {

            Point[] testPoints = points.clone();
            ClosestPairSolver solver =
                    new ClosestPairSolver();

            long startTime = System.nanoTime();

            distance = solver.findClosestDistance(testPoints);

            long endTime = System.nanoTime();

            totalTime += endTime - startTime;

            recursionDepth = Math.max(
                    recursionDepth,
                    solver.getMaxRecursionDepth()
            );

            comparisons = Math.max(
                    comparisons,
                    solver.getComparisons()
            );
        }

        long averageTime = totalTime / RUNS;

        writer.write(
                "Closest Pair," +
                        type + "," +
                        n + "," +
                        averageTime + "," +
                        recursionDepth + "," +
                        comparisons + "," +
                        0 + "\n"
        );

        System.out.println(
                "Closest Pair - " +
                        type +
                        ": average time = " +
                        averageTime +
                        " ns, recursion depth = " +
                        recursionDepth +
                        ", comparisons = " +
                        comparisons +
                        ", distance = " +
                        distance
        );
    }
}