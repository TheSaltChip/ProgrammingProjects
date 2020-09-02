package algorithms;

import adt.SortingAlgorithmADT;

public class QuickSort implements SortingAlgorithmADT {
    private Integer[] array;
    private Integer[] testArr;

    public QuickSort(Integer[] array) {
        this.array = array;
        testArr = new Integer[array.length];
    }

    public void makeTestArray() {
        for (int i = 0; i < array.length; i++) {
            testArr[i] = array[i];
        }
    }

    public Integer[] sort() {
        sort(testArr, 0, testArr.length - 1);

        return testArr;
    }

    private void sort(Integer[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);

            sort(arr, left, pivot - 1);
            sort(arr, pivot + 1, right);
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
}
