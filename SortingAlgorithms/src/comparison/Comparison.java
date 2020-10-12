package comparison;

import adt.SortingAlgorithmADT;
import algorithms.*;
import arraylist.ArrayIterator;
import arraylist.ArrayList;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Comparison class that is used to sort different sizes of arrays with a sorting algorithm
 *
 * @author Sebastian
 */
public class Comparison {
    private ArrayList<Algorithm> algorithmResult;
    private final int AMOUNT_OF_RUNS;
    private final long SEED;

    /**
     * Creates a comparison object with the amount of runs the algorithms are to be run
     * and the seed to be used in the random generator
     *
     * @param amountOfRuns The amount of sorting runs for each algorithm
     * @param seed         The seed which is to be used in the random generator
     */
    public Comparison(int amountOfRuns, long seed) {
        AMOUNT_OF_RUNS = amountOfRuns;
        SEED = seed;
    }

    /**
     * Decides which algorithm
     *
     * @param algorithmName The name of the algorithm that is to be used
     * @param sizes         Array of the sizes that is being used to make the arrays which is being sorted
     */
    public void decideWhichAlgorithm(String algorithmName, int[] sizes) {
        Integer[][] allArrays = makeArrays(sizes);
        algorithmResult = new ArrayList<>();

        switch (algorithmName) {
            case "bubblesort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult
                            .add(new Algorithm("BubbleSort", "n^2", new BubbleSort(allArrays[i]), allArrays[i].length));
                }
                break;
            case "insertionsort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(
                            new Algorithm("InsertionSort", "n^2", new InsertionSort(allArrays[i]), allArrays[i].length));
                }

                break;
            case "selectionsort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(
                            new Algorithm("SelectionSort", "n^2", new SelectionSort(allArrays[i]), allArrays[i].length));
                }

                break;
            case "quicksort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(
                            new Algorithm("QuickSort", "nlog2n", new QuickSort(allArrays[i]), allArrays[i].length));
                }

                break;
            case "mergesort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(
                            new Algorithm("MergeSort", "nlog2n", new MergeSort(allArrays[i]), allArrays[i].length));
                }

                break;
            case "insertionquicksort":

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(new Algorithm("InsertionQuickSort", "nlog2n",
                            new InsertionQuickSort(allArrays[i], 30), allArrays[i].length));
                }

                break;
            case "stalinsort":
                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(new Algorithm("StalinSort", "n",
                            new StalinSort(allArrays[i]), allArrays[i].length));
                }

                break;
            case "danielsstalinsort":
                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(new Algorithm("DanielsStalinSort", "n",
                            new DanielsStalinSort(allArrays[i]), allArrays[i].length));
                }

                break;
            default:

                for (int i = 0; i < sizes.length; i++) {
                    algorithmResult.add(
                            new Algorithm("RadixSort", "kn", new RadixSort(allArrays[i]), allArrays[i].length));
                }

                break;
        }
    }

    /**
     * Goes through the list of algorithms and sorts the arrays the given amount of time
     *
     * @return A list of Algorithms where all the arrays are sorted
     */
    public ArrayList<Algorithm> executeSorting() {
        ArrayIterator<Algorithm> it = algorithmResult.iterator();

        //Integer[] arr;
        //double timeStart, time;

        ExecutorService es = Executors.newCachedThreadPool();

        while (it.hasNext()) {
            es.execute(new AlgorithmThread(it.next(), this, AMOUNT_OF_RUNS));
        }

        es.shutdown();

        while(true){
            try {
                if (es.awaitTermination(10, TimeUnit.HOURS)) break;
            } catch (InterruptedException ignored) {
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
            Random rand = new Random(SEED);
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
    public synchronized boolean isSorted(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
