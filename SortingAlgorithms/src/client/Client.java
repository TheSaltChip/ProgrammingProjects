package client;

import comparison.Display;

import java.time.LocalTime;

public class Client {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        //50 er hvor mange ganger hver algoritme skal sortere den samme listen, de blir tilfedige hver gang
        //1337 er seed til random-generatoren
        Display display = new Display(50, 1337);

        int[] sizes = {16000, 32000, 20000, 500000};
        int testSize = 16000;

        LocalTime start = LocalTime.now();

        display.run("quicksort", sizes, testSize);
//	
//		display.run("insertionquicksort", sizes, testSize);
//		
//		display.run("insertionsort", sizes, testSize);
//		
//   	display.run("mergesort", sizes, testSize);
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
