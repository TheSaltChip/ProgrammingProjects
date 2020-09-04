package client;

import comparison.Display;

import java.time.LocalTime;

public class ClientThread {

    public static void main(String[] args) {

        LocalTime start = LocalTime.now();

        Thread q = new ClientThreadTest("quicksort");
        q.start();

//		run("insertionquicksort");
//
//		run("insertionsort");
//
//        Thread m = new ClientThreadTest("mergesort");
//        m.start();
//
//		display.run("radixsort");
//
//		display.run("bubblesort");
//
//		display.run("selectionsort");

        try {
            q.join();
            //m.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime end = LocalTime.now();

        System.out.println("Start: " + start
                + "\nEnd: " + end);
    }
}
