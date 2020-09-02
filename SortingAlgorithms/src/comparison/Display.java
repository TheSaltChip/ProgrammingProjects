package comparison;

public class Display {
    private Comparison comparison;
    private String whichAlgorithm;
    private int[] sizes;

    public Display(int amountOfRuns, long seed) {
        comparison = new Comparison(amountOfRuns, seed);
    }

    public Display() {
        comparison = new Comparison();
    }

    private void displayAll() {
        comparison.decideWhichAlgorithm(whichAlgorithm, sizes);
        FormatOutput fo = new FormatOutput(comparison.executeSorting(), comparison);
        fo.run();

    }

    public void run(String whichAlgorithm, int[] sizes, int testSize) {
        this.whichAlgorithm = whichAlgorithm;

        int[] arr = new int[sizes.length + 1];

        arr[arr.length - 1] = testSize;

        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = sizes[i];
        }

        this.sizes = arr;

        displayAll();
    }
}
