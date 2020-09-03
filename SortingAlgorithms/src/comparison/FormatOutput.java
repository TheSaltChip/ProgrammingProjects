package comparison;

import arraylist.ArrayList;
import java.util.Iterator;

/**
 * Class used to format the results of the sorting algorithms
 */
public class FormatOutput {
    private final Comparison COMPARISON; // The object used to compare the sorting of the different size of arrays
    private final ArrayList<Algorithm> ALGORITHMS; // List of algorithms

    private Algorithm findingCAlgorithm; // The algorithm that was used to make the c-value

    private String horizontalLine = ""; // The horizontal line in the output
    private String fancyLine = ""; // The fancy horizontal line in the output
    private final String[] cString; // A string array of all the info about the c-value

    /**
     * Creates an FormatOutput object that creates a beautiful text output of the algorithms in the collection
     *
     * @param algorithms Collection of algorithms that are finished and ready to be used
     * @param comparison A comparison object used to compute the c-values of the algorithms
     */
    public FormatOutput(ArrayList<Algorithm> algorithms, Comparison comparison) {
        this.COMPARISON = comparison;
        this.ALGORITHMS = algorithms;
        getCAlgorithm();

        makeLines();

        cString = buildCString(findingCAlgorithm.getLengthOfArr(), findingCAlgorithm);
    }

    /**
     * Makes the horizontal lines in the output
     */
    private void makeLines() {
        StringBuilder line = new StringBuilder();
        StringBuilder fLine = new StringBuilder();

        for (int i = 0; i < 20; i++) {
             line.append("-");

            if (i % 2 == 0) {
                fLine.append("/\\");
            }
        }

        fancyLine = fLine.toString();
        horizontalLine = line.toString();
    }

    /**
     * Prints the complete line for the sorting algorithms
     *
     * @param string A complete line ready to be printed
     */
    private void completeLineForSort(String string) {
        for (int i = 0; i < 6; i++)
            System.out.format("+%s", string);

        System.out.format("+%n");
    }

    /**
     * Prints the complete line for the c-value box
     *
     * @param string A complete line ready to be printed
     */
    private void completeLineForCBox(String string) {
        for (int i = 0; i < 4; i++)
            System.out.format("+%s", string);

        System.out.format("+%n");
    }

    /**
     * Creates a fully formatted string from a single algorithm
     *
     * @param algorithm A complete algorithm ready to be formatted
     * @param cValue    The c-value for the given algorithm
     * @return A fully formatted string which has the details of the algorithm
     */
    private String formatSingleEntryToString(Algorithm algorithm, double cValue) {
        return String.format("| %-18d | %-18d | %-18f | %-18f | %-18g | %-18b |", algorithm.getLengthOfArr(), algorithm.getNumOfReadings(),
                (algorithm.getAvgTime()), COMPARISON.computeTheoreticalTime(cValue, algorithm), cValue,
                algorithm.isAllSorted());
    }

    /**
     * Assigns the algorithm, the last one list of algorithms,
     * used to find the c-value to the variable findingCAlgorithm
     */
    private void getCAlgorithm() {
        Iterator<Algorithm> it = ALGORITHMS.iterator();

        Algorithm cAlgorithm = null;

        while(it.hasNext())
            cAlgorithm = it.next();

        findingCAlgorithm = ALGORITHMS.remove(cAlgorithm);
    }

    /**
     * Builds a string array with the given c-value algorithm
     *
     * @param size      The size of the test array
     * @param algorithm The algorithm that was used to compute the c-value
     * @return A string array with all the details ready
     */
    private String[] buildCString(int size, Algorithm algorithm) {

        String avgTime = String.format("%-18g", algorithm.getAvgTime());
        String cValue = String.format("%-18g", findC());

        return new String[]{size + "", algorithm.getNumOfReadings() + "", avgTime, cValue, algorithm.getBigO()};

    }

    /**
     * Finds the c-value from the findingCAlgorithm
     *
     * @return The c-value from the given Algorithm
     */
    private double findC() {
        double c, n = findingCAlgorithm.getLengthOfArr();

        String fn = findingCAlgorithm.getBigO();

        if (fn.equals("n^2")) {
            c = findingCAlgorithm.getAvgTime() / Math.pow(n, 2);

        } else if (fn.equals("nlog2n")) {
            double nlog2n = n * (Math.log(n) / Math.log(2));
            c = findingCAlgorithm.getAvgTime() / nlog2n;

        } else {
            c = findingCAlgorithm.getAvgTime() / n;
        }

        return c;
    }

    /**
     * Makes the header for the sorting algorithm box
     */
    private void makeHeader() {
        Algorithm titleAlgorithm = ALGORITHMS.iterator().next();

        System.out.format("+%-18s+%n", horizontalLine);
        System.out.format("| %-18s |%n", titleAlgorithm.getName());

        completeLineForSort(horizontalLine);

        String MAIN_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |";

        System.out.format(MAIN_STRING_NAMES + "%n", "n", "Readings", "Average Time", "Theoretical Time", "c",
                "All arrays sorted");

        System.out.format(MAIN_STRING_NAMES + "%n", "", "", "in ms", "in ms (c*" + titleAlgorithm.getBigO() + ")", "", "");

        completeLineForSort(horizontalLine);

    }

    /**
     * Makes the body for the sorting algorithm box
     *
     * @param cValue The c-value of the algorithm
     */
    private void makeBody(double cValue) {
        Iterator<Algorithm> it = ALGORITHMS.iterator();

        while (it.hasNext()) {
            Algorithm algorithm = it.next();
            System.out.format(formatSingleEntryToString(algorithm, cValue) + "%n");
        }

        completeLineForSort(horizontalLine);

    }

    /**
     * Makes the entire box with all the info about the c-value
     */
    private void makeCBox() {
        for (int i = 0; i < 4; i++)
            System.out.format("|%s", fancyLine);

        System.out.format("|%n");

        completeLineForCBox(horizontalLine);

        String c_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s |";

        System.out.format(c_STRING_NAMES + "%n", "n", "Readings", "Average Time", "c");
        System.out.format(c_STRING_NAMES + "%n", "", "", "in ms", "AvgTime/" + cString[4]);

        completeLineForCBox(horizontalLine);

        System.out.format("| %-18s | %-18s | %s | %s |" + "%n", cString[0], cString[1], cString[2], cString[3]);

        completeLineForCBox(horizontalLine);

        System.out.println();
    }

    /**
     * Runs every method, and creates the output
     */
    public void run() {
        makeHeader();
        makeBody(findC());
        makeCBox();
    }
}
