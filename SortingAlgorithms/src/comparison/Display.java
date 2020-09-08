package comparison;

/**
 * Class used to display the results of the comparison
 */
public class Display {
    private final Comparison COMPARISON;
    private String whichAlgorithm;
    private int[] sizes;

    /**
     * Constructs a Display object with the amount of runs wanted and with what seed is wanted for the random seed
     *
     * @param amountOfRuns The amount of sorting runs wanted for each algorithm
     * @param seed         The seed used for the random generator
     */
    public Display(int amountOfRuns, long seed) {
        COMPARISON = new Comparison(amountOfRuns, seed);
    }

    /**
     * Displays all the algorithm results
     */
    private void displayAll() {
        COMPARISON.decideWhichAlgorithm(whichAlgorithm, sizes);
        FormatOutput fo = new FormatOutput(COMPARISON.executeSorting(), COMPARISON);
        fo.run();
    }

    /**
     * This method starts everything in the program
     *
     * @param whichAlgorithm Which algorithm to be used
     * @param sizes          The sizes of the arrays that are to be sorted
     * @param testSize       The size of the test array, which is used to compute the c-values
     */
    public void run(String whichAlgorithm, int[] sizes, int testSize) {
        this.whichAlgorithm = whichAlgorithm;

        int[] arr = new int[sizes.length + 1];

        arr[arr.length - 1] = testSize;

        System.arraycopy(sizes, 0, arr, 0, arr.length - 1);

        this.sizes = arr;

        displayAll();
    }
}
