package comparison;

import adt.MengdeADT;
import adt.SortingAlgorithmADT;
import algorithms.*;
import arraylist.TabellMengde;

import java.util.Iterator;
import java.util.Random;

/**
 * Comparison class that is used to sort different sizes of arrays with an sorting algorithm
 *
 * @author Sebastian
 */
public class Comparison {
    private MengdeADT<Algorithm> algorithmResult;
    private int amount;
    private static final int STANDARD_SIZE = 20;
    private static final int STANDARD_SEED = 1337;
    private int amountOfRuns;
    private long seed;

    public Comparison(int amountOfRuns, long seed) {
        this.amountOfRuns = amountOfRuns;
        this.seed = seed;
    }

    public Comparison() {
        this(STANDARD_SIZE, STANDARD_SEED);
    }

    /**
     * Decides which algorithm
     *
     * @param algorihtmName The name of the algorithm that is to be used
     * @param sizes         Array of the sizes that is being used to make the arrays which is being sorted
     */
    public void decideWhichAlgorithm(String algorihtmName, int[] sizes) {
        Integer[][] allArrays = makeArrays(sizes);
        algorithmResult = new TabellMengde<>();
        amount = 0;

        if (algorihtmName.equals("bubblesort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult
                        .leggTil(new Algorithm("BubbleSort", "n^2", new BubbleSort(allArrays[i]), allArrays[i].length));
                amount++;
            }
        } else if (algorihtmName.equals("insertionsort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(
                        new Algorithm("InsertionSort", "n^2", new InsertionSort(allArrays[i]), allArrays[i].length));
                amount++;
            }

        } else if (algorihtmName.equals("selectionsort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(
                        new Algorithm("SelectionSort", "n^2", new SelectionSort(allArrays[i]), allArrays[i].length));
                amount++;
            }

        } else if (algorihtmName.equals("quicksort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(
                        new Algorithm("QuickSort", "nlog2n", new QuickSort(allArrays[i]), allArrays[i].length));
                amount++;
            }

        } else if (algorihtmName.equals("mergesort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(
                        new Algorithm("MergeSort", "nlog2n", new MergeSort(allArrays[i]), allArrays[i].length));
                amount++;
            }

        } else if (algorihtmName.equals("insertionquicksort")) {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(new Algorithm("InsertionQuickSort", "nlog2n",
                        new InsertionQuickSort(allArrays[i], 30), allArrays[i].length));
                amount++;
            }

        } else {

            for (int i = 0; i < sizes.length; i++) {
                algorithmResult.leggTil(
                        new Algorithm("RadixSort", "kn", new RadixSort(allArrays[i], 10), allArrays[i].length));
                amount++;
            }

        }
    }

    /**
     * Goes through the list of algorithms and sorts the arrays the given amount of time
     *
     * @return A list of Algorithms where all the arrays are sorted
     */
    public MengdeADT<Algorithm> executeSorting() {
        Iterator<Algorithm> it = algorithmResult.oppramser();

        Integer[] arr;
        double timeStart, time;

        while (it.hasNext()) {
            Algorithm algorithm = it.next();
            SortingAlgorithmADT sortingAlg = algorithm.getAlgorithm();

            for (int i = 0; i < amountOfRuns; i++) {
                sortingAlg.makeTestArray();

                timeStart = System.nanoTime();
                arr = sortingAlg.sort();
                time = (System.nanoTime() - timeStart) / 1000000.0;

                algorithm.addTime(time);
                algorithm.addSortedStatus(isSorted(arr));
            }
        }

        return algorithmResult;
    }

    /**
     * Computes the theoretical time given by the c-value and the given Algorithm
     *
     * @param c         C-value in the formula T(n) = c * f(n)
     * @param algorithm The algorithm that you want to find the c-value for
     * @return Returns the theoretical time for the time it would sort the array associated with the algorithm
     */
    public double computeTheoreticalTime(double c, Algorithm algorithm) {
        String fn = algorithm.getBigO();
        double tTime;

        if (fn.equals("n^2")) {
            tTime = c * Math.pow(algorithm.getLengthOfArr(), 2);
        } else if (fn.equals("nlog2n")) {
            tTime = c * (algorithm.getLengthOfArr() * (Math.log(algorithm.getLengthOfArr()) / Math.log(2)));
        } else {
            tTime = c * algorithm.getLengthOfArr();
        }

        return tTime;
    }

    /**
     * Method that makes all the arrays that are to be used in the program
     *
     * @param sizes Array of sizes of the arrays that are going to be solved
     * @return Returns a two dimensional array, where the arrays are filled with random numbers
     */
    private Integer[][] makeArrays(int[] sizes) {
        Integer[][] allArray = new Integer[sizes.length][];

        for (int i = 0; i < sizes.length; i++) {
            Random rand = new Random(seed);
            allArray[i] = new Integer[sizes[i]];

            for (int j = 0; j < sizes[i]; j++) {
                allArray[i][j] = rand.nextInt(100000);
            }
        }

        return allArray;
    }

    /**
     * Checks if the array is sorted
     *
     * @param arr The array that is being checked
     * @return Returns true if the array is sorted in ascending order
     */
    private boolean isSorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the amount of algorithms in the list
     *
     * @return The amount of algorithms
     */
    public int getAmount() {
        return amount;
    }
}
