package no.hvl.dat102.InsertionQuickSort;

import no.hvl.dat102.adt.SortingAlgorithmADT;

public class InsertionQuickSort implements SortingAlgorithmADT{
	private Integer[] array;
	private Integer[] testArr;
	private final int MIN;

	public InsertionQuickSort(Integer[] array, int min) {
		this.array = array;
		testArr = new Integer[array.length];
		MIN = min;
	}

	public void makeTestArray() {
		for (int i = 0; i < array.length; i++) {
			testArr[i] = array[i];
		}
	}

	public Integer[] sort() {
		quickSort(testArr, 0, testArr.length - 1);
		insertionSort(testArr, 0, testArr.length);

		return testArr;
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
		for (int i = min+1; i < max; i++) {
			Integer nextValue = array[i];
			int pos = i;

			while (pos > 0 && nextValue.compareTo(testArr[pos - 1]) < 0) {
				array[pos] = array[pos - 1];
				pos--;
			}

			array[pos] = nextValue;
		}
	}
}
