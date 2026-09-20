public class Experiment {

    public static void main(String[] args) {

        System.out.println("DAA Experimental Framework");
        System.out.println("==========================");

        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {
            System.out.println("Testing input size: " + n);
        }
    }
}