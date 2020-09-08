package comparison;

import adt.SortingAlgorithmADT;

public class AlgorithmThread implements Runnable {
    private final Comparison C;
    private final int AMOUNT_OF_RUNS;
    private final Algorithm ALG;


    public AlgorithmThread(Algorithm alg, Comparison c, int aor) {
        this.C = c;
        this.AMOUNT_OF_RUNS = aor;
        this.ALG = alg;
    }

    @Override
    public void run() {
        SortingAlgorithmADT sortingAlg = ALG.getAlgorithm();

        for (int i = 0; i < AMOUNT_OF_RUNS; i++) {
            sortingAlg.makeTestArray();

            double timeStart = System.nanoTime();
            Integer[] arr = sortingAlg.sort();
            double time = (System.nanoTime() - timeStart) / 1000000.0;

            ALG.addTime(time);
            ALG.addSortedStatus(C.isSorted(arr));
        }
    }
}
