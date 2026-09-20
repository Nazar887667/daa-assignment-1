import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ClosestPairSolver {

    private int maxRecursionDepth;
    private long comparisons;

    public double findClosestDistance(Point[] points) {
        maxRecursionDepth = 0;
        comparisons = 0;

        if (points == null || points.length < 2) {
            throw new IllegalArgumentException(
                    "At least two points are required"
            );
        }

        Point[] pointsByX = points.clone();
        Point[] pointsByY = points.clone();

        Arrays.sort(
                pointsByX,
                Comparator.comparingDouble(Point::getX)
        );

        Arrays.sort(
                pointsByY,
                Comparator.comparingDouble(Point::getY)
        );

        double distanceSquared =
                closestPair(
                        pointsByX,
                        pointsByY,
                        1
                );

        return Math.sqrt(distanceSquared);
    }

    private double closestPair(
            Point[] pointsByX,
            Point[] pointsByY,
            int depth) {

        maxRecursionDepth =
                Math.max(maxRecursionDepth, depth);

        int n = pointsByX.length;

        if (n <= 3) {
            return bruteForce(pointsByX);
        }

        int middle = n / 2;

        Point middlePoint = pointsByX[middle];

        Point[] leftX =
                Arrays.copyOfRange(
                        pointsByX,
                        0,
                        middle
                );

        Point[] rightX =
                Arrays.copyOfRange(
                        pointsByX,
                        middle,
                        n
                );

        Set<Point> leftPoints = new HashSet<>();

        for (Point point : leftX) {
            leftPoints.add(point);
        }

        Point[] leftY =
                new Point[leftX.length];

        Point[] rightY =
                new Point[rightX.length];

        int leftIndex = 0;
        int rightIndex = 0;

        for (Point point : pointsByY) {
            if (leftPoints.contains(point)) {
                leftY[leftIndex] = point;
                leftIndex++;
            } else {
                rightY[rightIndex] = point;
                rightIndex++;
            }
        }

        double leftDistance =
                closestPair(
                        leftX,
                        leftY,
                        depth + 1
                );

        double rightDistance =
                closestPair(
                        rightX,
                        rightY,
                        depth + 1
                );

        double delta =
                Math.min(
                        leftDistance,
                        rightDistance
                );

        Point[] strip =
                new Point[n];

        int stripSize = 0;

        double deltaSquared = delta;

        for (Point point : pointsByY) {

            double dx =
                    point.getX()
                            - middlePoint.getX();

            comparisons++;

            if (dx * dx < deltaSquared) {
                strip[stripSize] = point;
                stripSize++;
            }
        }

        for (int i = 0; i < stripSize; i++) {

            for (
                    int j = i + 1;
                    j < stripSize;
                    j++
            ) {

                double dy =
                        strip[j].getY()
                                - strip[i].getY();

                comparisons++;

                if (dy * dy >= deltaSquared) {
                    break;
                }

                double distance =
                        strip[i]
                                .distanceSquared(strip[j]);

                comparisons++;

                if (distance < deltaSquared) {
                    deltaSquared = distance;
                }
            }
        }

        return deltaSquared;
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

                comparisons++;

                double distance =
                        points[i]
                                .distanceSquared(points[j]);

                if (distance < minimum) {
                    minimum = distance;
                }
            }
        }

        return minimum;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}