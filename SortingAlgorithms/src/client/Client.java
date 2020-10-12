package client;

import comparison.Display;

import java.time.LocalTime;
import java.util.Random;

/**
 * The client for the program, which controls what sorting algorithms to use, the size of the arrays
 * and the size of the test array
 */
public class Client {

    private static final Display DISPLAY = new Display(100, new Random().nextInt(10000000));

    private static void run(String s) {

        int[] sizes = {500000, 200000, 100000};
        int testSize = 200000;
        DISPLAY.run(s, sizes, testSize);
    }

    public static void main(String[] args) {

        LocalTime start = LocalTime.now();

        run("stalinsort");

        run("danielsstalinsort");

        //run("quicksort");
//	
//		run("insertionquicksort");
//		
//		run("insertionsort");
//		
//    	run("mergesort");
//		
//		run("radixsort");
//		
//		run("bubblesort");
//		
//		run("selectionsort");

        LocalTime end = LocalTime.now();

        System.out.println("Start: " + start
                + "\nEnd: " + end);
    }
}
