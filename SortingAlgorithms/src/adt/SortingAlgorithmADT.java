package adt;

public interface SortingAlgorithmADT {

    /**
     * Make a duplicate array of the array that is goind to be sorted.
     * This makes it possible to run the sorting algorithm multiple times on the same array of number
     */
    void makeTestArray();

    /**
     * Function that sorts the array in the object and returns it
     *
     * @return the sorted array
     */
    Integer[] sort();
}
