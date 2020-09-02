package no.hvl.dat102.Client;

import java.util.Random;

import no.hvl.dat102.BubbleSort.BubbleSort;
import no.hvl.dat102.InsertionSort.InsertionSort;
import no.hvl.dat102.MergeSort.MergeSort;
import no.hvl.dat102.QuickSort.QuickSort;
import no.hvl.dat102.RadixSort.RadixSort;
import no.hvl.dat102.SelectionSort.SelectionSort;

public class SmallClient {
	private static Integer[] arr;

	private static void displayArray(Integer[] arr) {
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private static Integer[] getNewArray() {

		Integer[] arr = new Integer[10];

		Random randomNumber = new Random(1337);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = randomNumber.nextInt(100);
		}

		return arr;
	}

	public static void main(String[] args) {

		// BubbleSort
		System.out.println("BubbleSort");
		arr = getNewArray();
		displayArray(arr);

		BubbleSort bs = new BubbleSort(arr);
		bs.makeTestArray();
		displayArray(bs.sort());

		System.out.println();

		// InsertionSort
		System.out.println("InsertionSort");
		arr = getNewArray();
		displayArray(arr);

		InsertionSort is = new InsertionSort(arr);
		is.makeTestArray();
		displayArray(is.sort());

		System.out.println();

		// MergeSort
		System.out.println("MergeSort");
		arr = getNewArray();
		displayArray(arr);

		MergeSort ms = new MergeSort(arr);
		ms.makeTestArray();
		displayArray(ms.sort());

		System.out.println();

		// SelectionSort
		System.out.println("SelectionSort");
		arr = getNewArray();
		displayArray(arr);

		SelectionSort ss = new SelectionSort(arr);
		ss.makeTestArray();
		displayArray(ss.sort());

		System.out.println();

		// QuickSort
		System.out.println("QuickSort");
		arr = getNewArray();
		displayArray(arr);

		QuickSort qs = new QuickSort(arr);
		qs.makeTestArray();
		displayArray(qs.sort());

		System.out.println();
		
		// RadixSort
		System.out.println("RadixSort");
		arr = getNewArray();
		displayArray(arr);

		RadixSort rs = new RadixSort(arr, 10);
		rs.makeTestArray();
		displayArray(rs.sort());

		System.out.println();

	}
}
