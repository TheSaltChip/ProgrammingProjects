package algorithms;

import adt.SortingAlgorithmADT;

public class BubbleSort implements SortingAlgorithmADT {
    private final Integer[] arr;
    private final Integer[] testArr;

    public BubbleSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
    }

    public void makeTestArray() {
        for (int i = 0; i < arr.length; i++) {
            testArr[i] = arr[i];
        }
    }

    public Integer[] sort() {
        boolean swapped;

        for (int i = 0; i < testArr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < testArr.length - i - 1; j++) {
                if (testArr[j].compareTo(testArr[j + 1]) > 0) {
                    Integer temp = testArr[j];
                    testArr[j] = testArr[j + 1];
                    testArr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (swapped == false) {
                break;
            }
        }

        return testArr;
    }
}
