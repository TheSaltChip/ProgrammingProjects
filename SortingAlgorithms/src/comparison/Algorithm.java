package comparison;

import adt.SortingAlgorithmADT;
import arraylist.ArrayList;

import java.util.Iterator;

/**
 * This class is used to store all the data of a single sorting algorithm
 *
 */
public class Algorithm {
    private final String NAME;
    private int numOfReadings;
    private double avgTime;
    private final SortingAlgorithmADT ALGORITHM;
    private final ArrayList<Double> TIMES;
    private boolean sortedStatus;
    private final String BIG_O;
    private final int LENGTH_OFF_ARRAY;

    /**
     * Create an algorithm object
     *
     * @param name The name of the sorting algorithm
     * @param bigO The big O notation of the sorting algorithm
     * @param algorithm The sorting algorithm object
     * @param lengthOfArr The length of the array of which the sorting is to be done
     */
    public Algorithm(String name, String bigO, SortingAlgorithmADT algorithm, int lengthOfArr) {
        NAME = name;
        numOfReadings = 0;
        avgTime = 0;
        TIMES = new ArrayList<>();
        sortedStatus = true;
        BIG_O = bigO;
        ALGORITHM = algorithm;
        LENGTH_OFF_ARRAY = lengthOfArr;
    }

    /**
     * Increments the number of readings the algorithm has read
     *
     */
    private void incrementReadings() {
        numOfReadings++;
    }

    /**
     * Adds the time taken to sort the array
     *
     * @param time The time taken to sort
     */
    public void addTime(double time) {
        TIMES.add(time);
        incrementReadings();
    }

    /**
     * Adds the boolean value for if the array is sorted
     *
     * @param isSorted Boolean value that describes if the array is sorted
     */
    public void addSortedStatus(boolean isSorted) {
        if (sortedStatus && !isSorted) {
            sortedStatus = false;
        }
    }

    /**
     * Returns the number of readings
     *
     * @return The number of readings
     */
    public int getNumOfReadings() {
        return numOfReadings;
    }

    /**
     * Returns the average time
     *
     * @return The average time
     */
    public double getAvgTime() {
        double sumTime = 0;

        Iterator<Double> it = TIMES.iterator();

        while (it.hasNext()) {
            sumTime += it.next();
        }

        avgTime = sumTime / numOfReadings;

        return avgTime;
    }

    /**
     * Return the name of the sorting algorithm
     *
     * @return The name of the sorting algorithm
     */
    public String getName() {
        return NAME;
    }

    /**
     * Returns the big O notation of the sorting algorithm
     *
     * @return The big O notation
     */
    public String getBigO() {
        return BIG_O;
    }

    /**
     * Returns the sorting algorithm
     *
     * @return The sorting algorithm
     */
    public SortingAlgorithmADT getAlgorithm() {
        return ALGORITHM;
    }

    /**
     * Returns the length of the sorting arrays
     *
     * @return The length of the sorting arrays
     */
    public int getLengthOfArr() {
        return LENGTH_OFF_ARRAY;
    }

    /**
     * Returns the boolean value for if all the arrays are sorted
     *
     * @return True if all arrays are sorted, false if not
     */
    public boolean isAllSorted() {
        return sortedStatus;
    }
}
