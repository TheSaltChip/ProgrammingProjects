package algorithms;

import adt.SortingAlgorithmADT;

public class InsertionSort implements SortingAlgorithmADT {
    private Integer[] arr;
    private Integer[] testArr;

    public InsertionSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    public void makeTestArray() {
        for (int i = 0; i < arr.length; i++) {
            testArr[i] = arr[i];
        }
    }

    public Integer[] sort() {
        for (int i = 1; i < testArr.length; i++) {
            Integer nextValue = testArr[i];
            int pos = i;

            while (pos > 0 && nextValue.compareTo(testArr[pos - 1]) < 0) {
                testArr[pos] = testArr[pos - 1];
                pos--;
            }

            testArr[pos] = nextValue;
        }

        return testArr;
    }
}
