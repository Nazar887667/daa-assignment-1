import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClosestPairTest {

    private static final double EPSILON = 1e-9;

    @Test
    void testSimplePoints() {

        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(10, 10)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.findClosestDistance(points);

        assertEquals(
                Math.sqrt(2),
                result,
                EPSILON
        );
    }

    @Test
    void testHorizontalPoints() {

        Point[] points = {
                new Point(0, 0),
                new Point(5, 0),
                new Point(2, 0),
                new Point(10, 0)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.findClosestDistance(points);

        assertEquals(
                2.0,
                result,
                EPSILON
        );
    }

    @Test
    void testDuplicatePoints() {

        Point[] points = {
                new Point(0, 0),
                new Point(5, 5),
                new Point(2, 2),
                new Point(2, 2),
                new Point(10, 10)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.findClosestDistance(points);

        assertEquals(
                0.0,
                result,
                EPSILON
        );
    }

    @Test
    void testTwoPoints() {

        Point[] points = {
                new Point(0, 0),
                new Point(3, 4)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.findClosestDistance(points);

        assertEquals(
                5.0,
                result,
                EPSILON
        );
    }

    @Test
    void testInvalidInput() {

        ClosestPairSolver solver =
                new ClosestPairSolver();

        assertThrows(
                IllegalArgumentException.class,
                () -> solver.findClosestDistance(
                        new Point[]{new Point(0, 0)}
                )
        );
    }

    @Test
    void testRandomSmallDatasets() {

        Random random = new Random(42);

        ClosestPairSolver solver =
                new ClosestPairSolver();

        for (int test = 0; test < 100; test++) {

            int size =
                    random.nextInt(50) + 2;

            Point[] points =
                    new Point[size];

            for (int i = 0; i < size; i++) {

                double x =
                        random.nextInt(1000);

                double y =
                        random.nextInt(1000);

                points[i] =
                        new Point(x, y);
            }

            double expected =
                    bruteForce(points);

            double actual =
                    solver.findClosestDistance(points);

            assertEquals(
                    expected,
                    actual,
                    EPSILON
            );
        }
    }

    @Test
    void testRecursionAndComparisons() {

        Point[] points = {
                new Point(0, 0),
                new Point(10, 10),
                new Point(3, 4),
                new Point(7, 2),
                new Point(20, 20),
                new Point(5, 5)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        solver.findClosestDistance(points);

        assertEquals(
                true,
                solver.getMaxRecursionDepth() > 0
        );

        assertEquals(
                true,
                solver.getComparisons() > 0
        );
    }

    private double bruteForce(Point[] points) {

        double minimum =
                Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {

            for (
                    int j = i + 1;
                    j < points.length;
                    j++
            ) {

                double distance =
                        points[i]
                                .distanceSquared(points[j]);

                minimum =
                        Math.min(
                                minimum,
                                distance
                        );
            }
        }

        return Math.sqrt(minimum);
    }
}