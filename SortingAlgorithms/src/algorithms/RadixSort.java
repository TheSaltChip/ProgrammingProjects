package algorithms;

import adt.SortingAlgorithmADT;
import queue.CircularQueue;

public class RadixSort implements SortingAlgorithmADT {
    private final Integer[] arr;
    private final Integer[] testArr;
    private Integer max;

    public RadixSort(Integer[] arr) {
        this.arr = arr;
        testArr = new Integer[arr.length];
        findMax();
    }

    public void makeTestArray() {
        System.arraycopy(arr, 0, testArr, 0, arr.length);
    }

    public void findMax() {
        max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

    }

    public Integer[] sort() {
        @SuppressWarnings("unchecked")
        CircularQueue<Integer>[] digitArray = new CircularQueue[10];

        for (int i = 0; i < digitArray.length; i++) {
            digitArray[i] = new CircularQueue<>();
        }

        max = (int) Math.pow(10, max.toString().length());

        for (int exp = 1; max / exp > 0; exp *= 10) {
            for (Integer integer : testArr) {
                digitArray[(integer / exp) % 10].enqueue(integer);
            }

            int index = 0;

            for (CircularQueue<Integer> integerCircularQueue : digitArray) {
                while (!integerCircularQueue.isEmpty()) {
                    testArr[index] = integerCircularQueue.dequeue();
                    index++;
                }
            }
        }

        return testArr;
    }
}
