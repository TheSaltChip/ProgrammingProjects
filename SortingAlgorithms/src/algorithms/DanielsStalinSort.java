package algorithms;

import adt.SortingAlgorithmADT;

public class DanielsStalinSort implements SortingAlgorithmADT {
    private Integer[] arr;
    private Integer[] testArr;

    public DanielsStalinSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    @Override
    public void makeTestArray() {
        System.arraycopy(arr, 0, testArr, 0, arr.length);
    }

    @Override
    public Integer[] sort() {
        int sortVal = 0;
        for (int i = 0; i < testArr.length; i++) {
            if (testArr[i] >= sortVal)
                sortVal = testArr[i];
             else
                testArr[i] = 0;
        }

        return testArr;
    }
}
