package client;

import comparison.Display;

public class ClientThreadTest extends Thread {
    private String algName= "";

    public ClientThreadTest(String algName) {
        this.algName = algName;
    }

    @Override
    public void run() {
        Display display = new Display(2, 1337);

        int[] sizes = {500, 200, 100};
        int testSize = 200;

        display.run(algName, sizes, testSize);
    }
}
