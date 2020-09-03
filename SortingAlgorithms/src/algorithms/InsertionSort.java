package algorithms;

import adt.SortingAlgorithmADT;

public class InsertionSort implements SortingAlgorithmADT {
    private final Integer[] arr;
    private final Integer[] testArr;

    public InsertionSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    public void makeTestArray() {
        System.arraycopy(arr, 0, testArr, 0, arr.length);
    }

    public Integer[] sort() {
        for (int i = 1; i < testArr.length; i++) {
            sortLoop(i, testArr, testArr);
        }

        return testArr;
    }

    static void sortLoop(int i, Integer[] testArr, Integer[] testArr2) {
        Integer nextValue = testArr[i];
        int pos = i;

        while (pos > 0 && nextValue.compareTo(testArr2[pos - 1]) < 0) {
            testArr[pos] = testArr[pos - 1];
            pos--;
        }

        testArr[pos] = nextValue;
    }
}
