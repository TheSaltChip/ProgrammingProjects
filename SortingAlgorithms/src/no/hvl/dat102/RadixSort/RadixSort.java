package no.hvl.dat102.RadixSort;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.SortingAlgorithmADT;
import no.hvl.dat102.sirkulaer.SirkulaerKoe;

public class RadixSort implements SortingAlgorithmADT {
	private Integer[] arr;
	private Integer[] testArr;
	private Integer max;
	
	public RadixSort(Integer[] arr, int highestDigit) {
		this.arr = arr;
		testArr = new Integer[arr.length];
		findMax();
	}

	public void makeTestArray() {
		for (int i = 0; i < arr.length; i++) {
			testArr[i] = arr[i];
		}
	}
	
	public Integer findMax() {
		max = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		
		return max;
	}

	public Integer[] sort() {
		@SuppressWarnings("unchecked")
		KoeADT<Integer>[] digitArray = new SirkulaerKoe[10];

		for (int i = 0; i < digitArray.length; i++) {
			digitArray[i] = new SirkulaerKoe<Integer>();
		}

		max = (int) Math.pow(10, max.toString().length());

		for (int exp = 1; max / exp > 0; exp *= 10) {
			for (int i = 0; i < testArr.length; i++) {
				digitArray[(testArr[i] / exp) % 10].innKoe(testArr[i]);
			}

			int index = 0;

			for (int digit = 0; digit < digitArray.length; digit++) {
				while (!digitArray[digit].erTom()) {
					testArr[index] = digitArray[digit].utKoe();
					index++;
				}
			}
		}

		return testArr;
	}
}
