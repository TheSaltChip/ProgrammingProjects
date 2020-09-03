package algorithms;

import adt.SortingAlgorithmADT;

public class SelectionSort implements SortingAlgorithmADT {
    private final Integer[] arr;
    private final Integer[] testArr;

    public SelectionSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    public void makeTestArray() {
        System.arraycopy(arr, 0, testArr, 0, arr.length);
    }

    public Integer[] sort() {

        for (int i = 0; i < testArr.length; i++) {
            Integer min = Integer.MAX_VALUE;
            int index = i;
            for (int j = i; j < testArr.length; j++) {
                if (testArr[j].compareTo(min) <= 0) {
                    min = testArr[j];
                    index = j;
                }
            }

            Integer temp = testArr[i];
            testArr[i] = min;
            testArr[index] = temp;
        }

        return testArr;
    }
}
