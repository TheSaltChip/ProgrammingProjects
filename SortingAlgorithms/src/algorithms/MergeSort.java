package algorithms;

import adt.SortingAlgorithmADT;

public class MergeSort implements SortingAlgorithmADT {
    private final Integer[] array;
    private final Integer[] testArr;

    public MergeSort(Integer[] array) {
        this.array = array;
        testArr = new Integer[array.length];
    }

    public void makeTestArray() {
        System.arraycopy(array, 0, testArr, 0, array.length);
    }

    private void merge(Integer[] arr, int left, int mid, int right) {
        int size = right - left + 1;

        Integer[] helpArr = new Integer[size];

        int leftArrFirst = left, rightArrFirst = mid + 1, index = 0;

        while ((leftArrFirst <= mid) && (rightArrFirst <= right)) {

            if (arr[leftArrFirst].compareTo(arr[rightArrFirst]) <= 0) {
                helpArr[index] = arr[leftArrFirst];
                leftArrFirst++;

            } else {
                helpArr[index] = arr[rightArrFirst];
                rightArrFirst++;
            }

            index++;
        }

        while (leftArrFirst <= mid) {
            helpArr[index] = arr[leftArrFirst];
            leftArrFirst++;
            index++;
        }

        while (rightArrFirst <= right) {
            helpArr[index] = arr[rightArrFirst];
            rightArrFirst++;
            index++;
        }

        for (int i = left, h = 0; i <= right; i++, h++) {
            arr[i] = helpArr[h];
        }
    }

    private void sort(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(arr, left, mid);

            sort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public Integer[] sort() {
        int left = 0, right = testArr.length - 1;

        sort(testArr, left, right);

        return testArr;
    }
}
