public class Experiment {

    public static void main(String[] args) {

        System.out.println("DAA Execution Time Experiment");
        System.out.println("=============================");

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {

            int[] array = InputGenerator.randomArray(n);

            MergeSorter sorter = new MergeSorter();

            long startTime = System.nanoTime();

            sorter.sort(array);

            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;

            int recursionDepth = sorter.getMaxRecursionDepth();
            long comparisons = sorter.getComparisons();

            System.out.println(
                    "n = " + n +
                            ", time = " + executionTime + " ns" +
                            ", recursion depth = " + recursionDepth +
                            ", comparisons = " + comparisons
            );
        }
    }
}