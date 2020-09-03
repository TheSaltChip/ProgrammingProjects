package client;

import comparison.Display;

import java.time.LocalTime;

/**
 * The client for the program, which controls what sorting algorithms to use, the size of the arrays
 * and the size of the test array
 */
public class Client {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Display display = new Display(5, 1337);

        int[] sizes = {5_000_00, 2_500_00, 1_250_00};
        int testSize = 2_000_00;

        LocalTime start = LocalTime.now();

        display.run("quicksort", sizes, testSize);
//	
//		display.run("insertionquicksort", sizes, testSize);
//		
//		display.run("insertionsort", sizes, testSize);
//		
   	    display.run("mergesort", sizes, testSize);
//		
//		display.run("radixsort", sizes, testSize);
//		
//		display.run("bubblesort", sizes, testSize);
//		
//		display.run("selectionsort", sizes, testSize);

        LocalTime end = LocalTime.now();

        System.out.println("Start: " + start
                + "\nEnd: " + end);
    }
}
