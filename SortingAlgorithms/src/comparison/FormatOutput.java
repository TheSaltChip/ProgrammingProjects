package comparison;

import adt.MengdeADT;

import java.util.Iterator;

/**
 * Class used to format the results of the sorting algorithms
 */
public class FormatOutput {
    private final Comparison COMPARISON;
    private final MengdeADT<Algorithm> ALGORITHMS;

    private Algorithm titleAlgorithm;
    private Algorithm algorithm;
    private Algorithm findingCAlgorithm;

    private String horizontalLine = "";
    private String fancyLine = "";
    private final String[] cString;

    private final String MAIN_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |";
    private final String C_STRING_NAMES = "| %-18s | %-18s | %-18s | %-18s |";
    private final String C_STRING_VALUES = "| %-18s | %-18s | %s | %s |";

    /**
     * Creates an FormatOutput object that creates a beautiful text output of the algorithms in the collection
     *
     * @param algorithms Collection of algorithms that are finished and ready to be used
     * @param comparison A comparison object used to compute the c-values of the algorithms
     */
    public FormatOutput(MengdeADT<Algorithm> algorithms, Comparison comparison) {
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
        for (int i = 0; i < 20; i++) {
            horizontalLine += "-";

            if (i % 2 == 0) {
                fancyLine += "/\\";
            }
        }
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
        String leftAlign = "| %-18d | %-18d | %-18f | %-18f | %-18g | %-18b |";

        String out = String.format(leftAlign, algorithm.getLengthOfArr(), algorithm.getNumOfReadings(),
                (algorithm.getAvgTime()), COMPARISON.computeTheoreticalTime(cValue, algorithm), cValue,
                algorithm.isAllSorted());

        return out;
    }

    /**
     * Assigns the algorithm used to find the c-value to the variable findingCAlgorithm
     */
    private void getCAlgorithm() {
        Iterator<Algorithm> it = ALGORITHMS.oppramser();
        findingCAlgorithm = ALGORITHMS.fjern(it.next());
    }

    /**
     * Builds a string array with the given c-value algorithm
     *
     * @param size      The size of the test array
     * @param algorithm The algorithm that was used to compute the c-value
     * @return A string array with all the details ready
     */
    private String[] buildCString(int size, Algorithm algorithm) {

        double c = findC();

        String avgTime = String.format("%-18g", algorithm.getAvgTime());
        String cValue = String.format("%-18g", c);

        String[] findCString = {size + "", algorithm.getNumOfReadings() + "", avgTime, cValue, algorithm.getBigO()};

        return findCString;

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
        titleAlgorithm = ALGORITHMS.oppramser().next();

        System.out.format("+%-18s+%n", horizontalLine);
        System.out.format("| %-18s |%n", titleAlgorithm.getName());

        completeLineForSort(horizontalLine);

        System.out.format(MAIN_STRING_NAMES + "%n", "n", "Readings", "Average Time", "Theoretical Time", "c",
                "All arryas sorted");

        System.out.format(MAIN_STRING_NAMES + "%n", "", "", "in ms", "in ms (c*" + titleAlgorithm.getBigO() + ")", "", "");

        completeLineForSort(horizontalLine);

    }

    /**
     * Makes the body for the sorting algorithm box
     *
     * @param cValue The c-value of the algorithm
     */
    private void makeBody(double cValue) {
        Iterator<Algorithm> it = ALGORITHMS.oppramser();

        while (it.hasNext()) {
            algorithm = it.next();
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

        System.out.format(C_STRING_NAMES + "%n", "n", "Readings", "Average Time", "c");
        System.out.format(C_STRING_NAMES + "%n", "", "", "in ms", "AvgTime/" + cString[4]);


        completeLineForCBox(horizontalLine);

        System.out.format(C_STRING_VALUES + "%n", cString[0], cString[1], cString[2], cString[3]);

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
