package algorithms;

import adt.SortingAlgorithmADT;

public class StalinSort implements SortingAlgorithmADT {
    private Integer[] arr;
    private Integer[] testArr;

    public StalinSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    @Override
    public void makeTestArray() {
        System.arraycopy(arr, 0, testArr, 0, arr.length);
    }

    @Override
    public Integer[] sort() {
        for (int i = 1, j = 0; i < testArr.length; i++) {
            if (testArr[j] > testArr[i])
                testArr[i] = 0;
            else
                j = i;
        }

        return testArr;
    }
}
