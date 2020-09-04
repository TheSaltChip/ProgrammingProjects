package comparison;

import adt.SortingAlgorithmADT;

public class AlgorithmThread implements Runnable {
    private SortingAlgorithmADT sAlg;
    private Integer[] arr;
    private double timeStart, time;
    private Algorithm alg;
    private Comparison c;


    public AlgorithmThread(SortingAlgorithmADT sAlg, Algorithm alg, Comparison c){
        this.sAlg = sAlg;
        this.alg = alg;
        this.c = c;
    }

    @Override
    public void run() {
        sAlg.makeTestArray();

        timeStart = System.nanoTime();
        arr = sAlg.sort();
        time = (System.nanoTime() - timeStart) / 1000000.0;

        alg.addTime(time);
        alg.addSortedStatus(c.isSorted(arr));
    }
}
