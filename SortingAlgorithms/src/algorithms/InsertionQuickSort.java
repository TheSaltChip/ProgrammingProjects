package algorithms;

import adt.SortingAlgorithmADT;

public class InsertionQuickSort implements SortingAlgorithmADT {
    private final Integer[] ARRAY;
    private final Integer[] TEST_ARR;
    private final int MIN;

    public InsertionQuickSort(Integer[] array, int min) {
        this.ARRAY = array;
        TEST_ARR = new Integer[array.length];
        MIN = min;
    }

    public void makeTestArray() {
        System.arraycopy(ARRAY, 0, TEST_ARR, 0, ARRAY.length);
    }

    public Integer[] sort() {
        quickSort(TEST_ARR, 0, TEST_ARR.length - 1);
        insertionSort(TEST_ARR, 0, TEST_ARR.length);

        return TEST_ARR;
    }

    private void quickSort(Integer[] arr, int left, int right) {
        if (right - left + 1 > MIN) {
            int pivot = partition(arr, left, right);

            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private int partition(Integer[] arr, int left, int right) {
        Integer pivot = arr[left];
        Integer temp;
        int min = left, max = right;

        while (min < max) {
            while (min < max && arr[min].compareTo(pivot) <= 0) {
                min++;
            }

            while (arr[max].compareTo(pivot) > 0)
                max--;

            if (min < max) {
                temp = arr[min];
                arr[min] = arr[max];
                arr[max] = temp;
            }
        }

        temp = arr[left];
        arr[left] = arr[max];
        arr[max] = temp;

        return max;
    }

    public void insertionSort(Integer[] array, int min, int max) {
        for (int i = min + 1; i < max; i++) {
            InsertionSort.sortLoop(i, array, TEST_ARR);
        }
    }
}
